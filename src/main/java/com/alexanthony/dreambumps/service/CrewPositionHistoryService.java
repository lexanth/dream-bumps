package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.repository.CrewPositionHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing CrewPositionHistory.
 */
@Service
@Transactional
public class CrewPositionHistoryService {

  private final Logger log = LoggerFactory.getLogger(CrewPositionHistoryService.class);

  private final CrewPositionHistoryRepository crewPositionHistoryRepository;

  public CrewPositionHistoryService(CrewPositionHistoryRepository crewPositionHistoryRepository) {
    this.crewPositionHistoryRepository = crewPositionHistoryRepository;
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

  /**
   * Get all the crewPositionHistories.
   * 
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<CrewPositionHistory> findAll() {
    log.debug("Request to get all CrewPositionHistories");
    List<CrewPositionHistory> result = crewPositionHistoryRepository.findAll();

    return result;
  }

  /**
   * Get one crewPositionHistory by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public CrewPositionHistory findOne(Long id) {
    log.debug("Request to get CrewPositionHistory : {}", id);
    CrewPositionHistory crewPositionHistory = crewPositionHistoryRepository.findOne(id);
    return crewPositionHistory;
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

  public List<CrewPositionHistory> findAllByCrew(Long crewId) {
    log.debug("Request to get CrewPositionHistory for : {}", crewId);
    return crewPositionHistoryRepository.findByCrewId(crewId);
  }
}
