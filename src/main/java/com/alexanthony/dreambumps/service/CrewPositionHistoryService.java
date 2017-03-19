package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.config.Constants;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.CrewPositionHistoryRepository;
import com.alexanthony.dreambumps.repository.CrewRepository;
import com.alexanthony.dreambumps.service.dto.CrewPositionHistoryDTO;
import com.alexanthony.dreambumps.service.mapper.CrewPositionHistoryMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CrewPositionHistory.
 */
@Service
@Transactional
public class CrewPositionHistoryService {

  private final Logger log = LoggerFactory.getLogger(CrewPositionHistoryService.class);

  private final CrewPositionHistoryRepository crewPositionHistoryRepository;
  
  private final CrewPositionHistoryMapper crewPositionHistoryMapper;
  private final CrewRepository crewRepository;
  private final UserCrewRacingHistoryService userCrewRacingHistoryService;

  public CrewPositionHistoryService(CrewPositionHistoryRepository crewPositionHistoryRepository, CrewPositionHistoryMapper crewPositionHistoryMapper, CrewRepository crewRepository, UserCrewRacingHistoryService userCrewRacingHistoryService) {
    this.crewPositionHistoryRepository = crewPositionHistoryRepository;
    this.crewPositionHistoryMapper = crewPositionHistoryMapper;
    this.crewRepository = crewRepository;
    this.userCrewRacingHistoryService = userCrewRacingHistoryService;
  }

  /**
   * Save a crewPositionHistory.
   *
   * @param crewPositionHistory
   *          the entity to save
   * @return the persisted entity
   */
  public CrewPositionHistory save(CrewPositionHistory crewPositionHistory) {
    log.debug("Request to save CrewPositionHistory : {}", crewPositionHistory);
    CrewPositionHistory result = crewPositionHistoryRepository.save(crewPositionHistory);
    return result;
  }
  
  public CrewPositionHistoryDTO save(CrewPositionHistoryDTO crewPositionHistoryDTO) {
    CrewPositionHistory crewPositionHistory = save(crewPositionHistoryMapper.crewPositionHistoryDTOToCrewPositionHistory(crewPositionHistoryDTO));
    return crewPositionHistoryMapper.crewPositionHistoryToCrewPositionHistoryDTO(crewPositionHistory);
  }

  /**
   * Get all the crewPositionHistories.
   * 
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<CrewPositionHistoryDTO> findAll() {
    log.debug("Request to get all CrewPositionHistories");
    List<CrewPositionHistory> result = crewPositionHistoryRepository.findAll();

    return result.stream().map(crewPositionHistoryMapper::crewPositionHistoryToCrewPositionHistoryDTO).collect(Collectors.toList());
  }

  /**
   * Get one crewPositionHistory by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public CrewPositionHistoryDTO findOne(Long id) {
    log.debug("Request to get CrewPositionHistory : {}", id);
    CrewPositionHistory crewPositionHistory = crewPositionHistoryRepository.findOne(id);
    return crewPositionHistoryMapper.crewPositionHistoryToCrewPositionHistoryDTO(crewPositionHistory);
  }

  /**
   * Delete the crewPositionHistory by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete CrewPositionHistory : {}", id);
    crewPositionHistoryRepository.delete(id);
  }

  public List<CrewPositionHistoryDTO> findAllByCrew(Long crewId) {
    log.debug("Request to get CrewPositionHistory for : {}", crewId);
    return crewPositionHistoryRepository.findByCrewId(crewId).stream().map(crewPositionHistoryMapper::crewPositionHistoryToCrewPositionHistoryDTO).collect(Collectors.toList());
  }

  public List<CrewPositionHistoryDTO> saveFullBumpSet(List<CrewPositionHistoryDTO> crewPositionHistoryDTOs) {
    if (crewPositionHistoryDTOs.isEmpty()) {
      return crewPositionHistoryDTOs;
    }
    List<CrewPositionHistory> crewPositionHistories = crewPositionHistoryMapper.crewPositionHistoryDTOsToCrewPositionHistorys(crewPositionHistoryDTOs);
    crewPositionHistories.forEach(crewPositionHistory -> crewPositionHistory.setCrew(crewRepository.findOne(crewPositionHistory.getCrew().getId())));
    Sex firstSex = crewPositionHistories.get(0).getCrew().getSex();
    Integer firstDay = crewPositionHistories.get(0).getDay();
    
    if (crewPositionHistories.stream().anyMatch(a -> a.getCrew().getSex() != firstSex)) {
      // Error handling
      return null;
    }
    if (crewPositionHistories.stream().anyMatch(a -> a.getDay() != firstDay)) {
      // Error handling
      return null;
    }
    
    Integer numberOfCrews;
    if (firstSex == Sex.male) {
      numberOfCrews = Constants.MENS_CREWS;
    } else {
      numberOfCrews = Constants.WOMENS_CREWS;
    }
    
    if (false && crewPositionHistories.size() != numberOfCrews) {
      return null;
    }
    
    crewPositionHistories = crewPositionHistories.stream().map(this::setBumpsAndDividend).collect(Collectors.toList());
    
    crewPositionHistories = crewPositionHistoryRepository.save(crewPositionHistories);
    
    userCrewRacingHistoryService.updateCrewsForNewBumps(crewPositionHistories);
    
    return crewPositionHistoryMapper.crewPositionHistorysToCrewPositionHistoryDTOs(crewPositionHistories);
  }
  
  private CrewPositionHistory setBumpsAndDividend(CrewPositionHistory crewPositionHistory) {
    CrewPositionHistory lastPosition = crewPositionHistoryRepository.findFirstByCrewAndDay(crewPositionHistory.getCrew(), crewPositionHistory.getDay() - 1);
    Integer startPosition = lastPosition.getPosition();
    Integer endPosition = crewPositionHistory.getPosition();
    Integer bumps = endPosition - startPosition;
    crewPositionHistory.setBumps(bumps);
    
    if (startPosition == 1 && endPosition == 1) {
      // headship rowover
      crewPositionHistory.setDividend(Constants.HEADSHIP_MULTIPLIER.multiply(calculateRowOverDividendForPosition(startPosition)));
    } else if (bumps == 0) {
      // rowover
      crewPositionHistory.setDividend(calculateRowOverDividendForPosition(startPosition));
    } else if (bumps < 0) {
      //bumped
      crewPositionHistory.setDividend(BigDecimal.ZERO);
    } else {
      // bump
      if (isPositionSandwichBoat(startPosition, crewPositionHistory.getCrew().getSex())) {
        crewPositionHistory.setDividend(calculateBumpDividend(startPosition, bumps).add(calculateRowOverDividendForPosition(startPosition)));
      } else {
        crewPositionHistory.setDividend(calculateBumpDividend(startPosition, bumps));
      }
    }
    return crewPositionHistory;
  }
  
  private boolean isPositionSandwichBoat(Integer startPosition, Sex sex) {
    if (startPosition.equals(Constants.getNumberOfCrewsForSex(sex))) {
      return false;
    }
    return startPosition % Constants.CREWS_PER_DIVISION == 1;
  }

  private BigDecimal calculateStartPriceForPosition(Integer position) {
    return Constants.THREE_HUNDRED.subtract(Constants.MULTIPLIER.multiply(new BigDecimal(Math.log(position))));
  }
  
  private BigDecimal calculateRowOverDividendForPosition(Integer position) {
    return Constants.ROWOVER_DIVIDEND.multiply(calculateStartPriceForPosition(position));
  }
  
  private BigDecimal calculateBumpDividend(Integer startPosition, Integer bumps) {
    return calculateRowOverDividendForPosition(startPosition).multiply(Constants.BUMP_MULTIPLIER).multiply(new BigDecimal(bumps));
  }

  public List<CrewPositionHistoryDTO> updateFullBumps(List<CrewPositionHistoryDTO> crewPositionHistoryDTOs) {
    // TODO Auto-generated method stub
    return null;
  }
}
