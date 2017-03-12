package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.CrewPriceHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.CrewRepository;
import com.alexanthony.dreambumps.service.dto.CrewDTO;
import com.alexanthony.dreambumps.service.mapper.CrewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Crew.
 */
@Service
@Transactional
public class CrewService {

  private final Logger log = LoggerFactory.getLogger(CrewService.class);

  private final CrewRepository crewRepository;

  private final CrewMapper crewMapper;
  
  private final CrewMemberService crewMemberService;
  
  private final CrewPositionHistoryService crewPositionHistoryService;
  
  private final CrewPriceHistoryService crewPriceHistoryService;

  public CrewService(CrewRepository crewRepository, CrewMapper crewMapper, CrewMemberService crewMemberService, CrewPositionHistoryService crewPositionHistoryService, CrewPriceHistoryService crewPriceHistoryService) {
    this.crewRepository = crewRepository;
    this.crewMapper = crewMapper;
    this.crewMemberService = crewMemberService;
    this.crewPositionHistoryService = crewPositionHistoryService;
    this.crewPriceHistoryService = crewPriceHistoryService;
  }

  /**
   * Save a crew.
   *
   * @param crewDTO
   *          the entity to save
   * @return the persisted entity
   */
  public CrewDTO save(CrewDTO crewDTO) {
    log.debug("Request to save Crew : {}", crewDTO);
    Crew crew = crewMapper.crewDTOToCrew(crewDTO);
    crew = save(crew);
    CrewDTO result = crewMapper.crewToCrewDTO(crew);
    return result;
  }

  public Crew save(Crew crew) {
    boolean isNew = crew.getId() == null;
    crew = crewRepository.save(crew);
    crewPriceHistoryService.createForCrew(crew);
    if (isNew) {
      crewMemberService.createMembersForCrew(crew);
    }
    return crew;
  }

  /**
   * Get all the crews.
   * 
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<CrewDTO> findAll() {
    log.debug("Request to get all Crews");
    List<CrewDTO> result = crewRepository.findAll().stream().map(crewMapper::crewToCrewDTO).map(this::populatePositionsAndPrices)
        .collect(Collectors.toCollection(LinkedList::new));

    return result;
  }

  /**
   * Get one crew by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public CrewDTO findOneDTO(Long id) {
    log.debug("Request to get CrewDTO : {}", id);
    Crew crew = crewRepository.findOne(id);
    CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);
    
    return populatePositionsAndPrices(crewDTO);
  }
  
  @Transactional(readOnly = true)
  public Crew findOne(Long id) {
    log.debug("Request to get Crew : {}", id);
    return crewRepository.findOne(id);
    
  }
  
  private CrewDTO populatePositionsAndPrices(CrewDTO crewDTO) {
    List<CrewPositionHistory> positionHistories = crewPositionHistoryService.findAllByCrew(crewDTO.getId());
    Optional<CrewPositionHistory> zeroDay = positionHistories.stream().filter(a -> a.getDay().equals(0)).findFirst();
    if (zeroDay.isPresent()) {
      crewDTO.setStartPosition(zeroDay.get().getPosition());
    }
    // Might want to reverse the sorting here
    Optional<CrewPositionHistory> mostRecentDay = positionHistories.stream().sorted((d1,d2) -> Integer.compare(d2.getDay(), d1.getDay())).findFirst();
    if (mostRecentDay.isPresent()) {
      crewDTO.setPosition(mostRecentDay.get().getPosition());
    }

    CrewPriceHistory oldestPriceHistory = crewPriceHistoryService.findOldestForCrew(crewDTO.getId());
    if (oldestPriceHistory != null) {
      crewDTO.setStartPrice(oldestPriceHistory.getPrice());
    }
    
    return crewDTO;
  }

  /**
   * Delete the crew by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete Crew : {}", id);
    crewRepository.delete(id);
  }

  public List<CrewDTO> findAllForSex(Sex sex) {
    log.debug("Request to find all Crews for : {}", sex);
    return crewRepository.findBySex(sex).stream().map(crewMapper::crewToCrewDTO).map(this::populatePositionsAndPrices)
        .collect(Collectors.toCollection(LinkedList::new));
  }
}
