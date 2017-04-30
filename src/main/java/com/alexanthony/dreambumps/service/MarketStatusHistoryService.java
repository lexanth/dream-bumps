package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.MarketStatusHistory;
import com.alexanthony.dreambumps.repository.MarketStatusHistoryRepository;
import com.alexanthony.dreambumps.service.dto.MarketStatusHistoryDTO;
import com.alexanthony.dreambumps.service.mapper.MarketStatusHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MarketStatusHistory.
 */
@Service
@Transactional
public class MarketStatusHistoryService {

  private final Logger log = LoggerFactory.getLogger(MarketStatusHistoryService.class);

  private final MarketStatusHistoryRepository marketStatusHistoryRepository;

  private final MarketStatusHistoryMapper marketStatusHistoryMapper;

  private final CrewPositionHistoryService crewPositionHistoryService;

  private final UserCrewRacingHistoryService userCrewRacingHistoryService;

  public MarketStatusHistoryService(MarketStatusHistoryRepository marketStatusHistoryRepository,
      MarketStatusHistoryMapper marketStatusHistoryMapper, CrewPositionHistoryService crewPositionHistoryService, UserCrewRacingHistoryService userCrewRacingHistoryService) {
    this.marketStatusHistoryRepository = marketStatusHistoryRepository;
    this.marketStatusHistoryMapper = marketStatusHistoryMapper;
    this.crewPositionHistoryService = crewPositionHistoryService;
    this.userCrewRacingHistoryService = userCrewRacingHistoryService;
  }

  /**
   * Save a marketStatusHistory.
   *
   * @param marketStatusHistoryDTO
   *          the entity to save
   * @return the persisted entity
   */
  public MarketStatusHistoryDTO save(MarketStatusHistoryDTO marketStatusHistoryDTO) {
    log.debug("Request to save MarketStatusHistory : {}", marketStatusHistoryDTO);
    MarketStatusHistory marketStatusHistory = marketStatusHistoryMapper
        .marketStatusHistoryDTOToMarketStatusHistory(marketStatusHistoryDTO);
    marketStatusHistory.setDateTime(ZonedDateTime.now());
    Integer currentDay = findLatest().getDay();
    if (marketStatusHistory.getDay() < currentDay || marketStatusHistory.getDay() > currentDay + 1) {
      // throw an error
    } else if (marketStatusHistory.getDay() == currentDay + 1) {
      advanceDay(marketStatusHistory.getDay());
    }


    marketStatusHistory = marketStatusHistoryRepository.save(marketStatusHistory);
    MarketStatusHistoryDTO result = marketStatusHistoryMapper
        .marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);
    return result;
  }

  private void advanceDay(Integer day) {
    if (!crewPositionHistoryService.getAllCrewsHaveBumps(day)) {
      // throw an error
    }
    userCrewRacingHistoryService.payDividends(day);

  }

  /**
   * Get all the marketStatusHistories.
   *
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<MarketStatusHistoryDTO> findAll() {
    log.debug("Request to get all MarketStatusHistories");
    List<MarketStatusHistoryDTO> result = marketStatusHistoryRepository.findAll().stream()
        .map(marketStatusHistoryMapper::marketStatusHistoryToMarketStatusHistoryDTO)
        .collect(Collectors.toCollection(LinkedList::new));

    return result;
  }

  /**
   * Get one marketStatusHistory by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public MarketStatusHistoryDTO findOne(Long id) {
    log.debug("Request to get MarketStatusHistory : {}", id);
    MarketStatusHistory marketStatusHistory = marketStatusHistoryRepository.findOne(id);
    MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper
        .marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);
    return marketStatusHistoryDTO;
  }

  /**
   * Delete the marketStatusHistory by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete MarketStatusHistory : {}", id);
    marketStatusHistoryRepository.delete(id);
  }

  public MarketStatusHistoryDTO findLatest() {
    log.debug("Request to get most recent MarketStatusHistories");
    MarketStatusHistory marketStatusHistory = marketStatusHistoryRepository.findFirstByOrderByDateTimeDesc();
    return marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);
  }

  public boolean isMarketOpen() {
    return findLatest().getOpen();
  }
}
