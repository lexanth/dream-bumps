package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.domain.UserCrewPriceHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.UserCrewPriceHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing UserCrewPriceHistory.
 */
@Service
@Transactional
public class UserCrewPriceHistoryService {

  private final Logger log = LoggerFactory.getLogger(UserCrewPriceHistoryService.class);

  private final UserCrewPriceHistoryRepository userCrewPriceHistoryRepository;

  public UserCrewPriceHistoryService(UserCrewPriceHistoryRepository userCrewPriceHistoryRepository) {
    this.userCrewPriceHistoryRepository = userCrewPriceHistoryRepository;
  }

  /**
   * Save a userCrewPriceHistory.
   *
   * @param userCrewPriceHistory
   *          the entity to save
   * @return the persisted entity
   */
  public UserCrewPriceHistory save(UserCrewPriceHistory userCrewPriceHistory) {
    log.debug("Request to save UserCrewPriceHistory : {}", userCrewPriceHistory);
    UserCrewPriceHistory result = userCrewPriceHistoryRepository.save(userCrewPriceHistory);
    return result;
  }

  /**
   * Get all the userCrewPriceHistories.
   * 
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<UserCrewPriceHistory> findAll() {
    log.debug("Request to get all UserCrewPriceHistories");
    List<UserCrewPriceHistory> result = userCrewPriceHistoryRepository.findAll();

    return result;
  }

  /**
   * Get one userCrewPriceHistory by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public UserCrewPriceHistory findOne(Long id) {
    log.debug("Request to get UserCrewPriceHistory : {}", id);
    UserCrewPriceHistory userCrewPriceHistory = userCrewPriceHistoryRepository.findOne(id);
    return userCrewPriceHistory;
  }

  /**
   * Delete the userCrewPriceHistory by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete UserCrewPriceHistory : {}", id);
    userCrewPriceHistoryRepository.delete(id);
  }


  public void createHistoryRecord(UserCrewPrice userCrewPrice) {
    save(new UserCrewPriceHistory(userCrewPrice));
  }

  public List<UserCrewPriceHistory> findAllForUserForSex(Long userId, Sex sex) {
    return userCrewPriceHistoryRepository.findByUserIdAndSex(userId, sex);
  }
}
