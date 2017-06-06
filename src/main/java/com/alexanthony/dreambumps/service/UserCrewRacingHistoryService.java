package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.UserCrewMember;
import com.alexanthony.dreambumps.domain.UserCrewPositionHistory;
import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.repository.UserCrewMemberRepository;
import com.alexanthony.dreambumps.repository.UserCrewPositionHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing UserCrewPositionHistory.
 */
@Service
@Transactional
public class UserCrewRacingHistoryService {

    private final Logger log = LoggerFactory.getLogger(UserCrewRacingHistoryService.class);

    private final UserCrewPositionHistoryRepository userCrewPositionHistoryRepository;
    private final UserCrewMemberRepository userCrewMemberRepository;
  private final UserCrewPriceService userCrewPriceService;

    public UserCrewRacingHistoryService(UserCrewPositionHistoryRepository userCrewPositionHistoryRepository, UserCrewMemberRepository userCrewMemberRepository, UserCrewPriceService userCrewPriceService) {
        this.userCrewPositionHistoryRepository = userCrewPositionHistoryRepository;
        this.userCrewMemberRepository = userCrewMemberRepository;
      this.userCrewPriceService = userCrewPriceService;
    }

    /**
     * Save a userCrewPositionHistory.
     *
     * @param userCrewPositionHistory the entity to save
     * @return the persisted entity
     */
    public UserCrewPositionHistory save(UserCrewPositionHistory userCrewPositionHistory) {
        log.debug("Request to save UserCrewPositionHistory : {}", userCrewPositionHistory);
        UserCrewPositionHistory result = userCrewPositionHistoryRepository.save(userCrewPositionHistory);
        return result;
    }

    /**
     *  Get all the userCrewPositionHistories.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserCrewPositionHistory> findAll() {
        log.debug("Request to get all UserCrewPositionHistories");
        List<UserCrewPositionHistory> result = userCrewPositionHistoryRepository.findAll();

        return result;
    }

    /**
     *  Get one userCrewPositionHistory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public UserCrewPositionHistory findOne(Long id) {
        log.debug("Request to get UserCrewPositionHistory : {}", id);
        UserCrewPositionHistory userCrewPositionHistory = userCrewPositionHistoryRepository.findOne(id);
        return userCrewPositionHistory;
    }

    /**
     *  Delete the  userCrewPositionHistory by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserCrewPositionHistory : {}", id);
        userCrewPositionHistoryRepository.delete(id);
    }

    public void updateCrewsForNewBumps(List<CrewPositionHistory> crewPositionHistories) {
      List<UserCrewPositionHistory> userBumps = new ArrayList<UserCrewPositionHistory>();
      for (CrewPositionHistory crewPositionHistory : crewPositionHistories) {
        List<UserCrewMember> holdings = userCrewMemberRepository.findByCrew(crewPositionHistory.getCrew());
        for (UserCrewMember holding: holdings) {
          UserCrewPositionHistory userBump = new UserCrewPositionHistory(crewPositionHistory, holding);
          userBumps.add(userBump);
        }
      }
      userCrewPositionHistoryRepository.save(userBumps);
    }

    public void payDividends(Integer day) {
      List<UserCrewPositionHistory> userBumps = userCrewPositionHistoryRepository.findByDay(day);

      // TODO - do this better so we only save each UserCrewPrice once.
      for (UserCrewPositionHistory bump : userBumps) {
        UserCrewPrice userCrewPrice = userCrewPriceService.findForUserAndSex(bump.getUser().getId(), bump.getSex());
        userCrewPrice.setCash(userCrewPrice.getCash().add(bump.getDividend()));
        userCrewPriceService.save(userCrewPrice);
      }
    }

  public void updateCrewsForUpdatedBumps(List<CrewPositionHistory> bumps) {
    List<UserCrewPositionHistory> userBumpsToSave = new ArrayList<>();
    for (CrewPositionHistory bump: bumps) {
      List<UserCrewPositionHistory> userBumps = userCrewPositionHistoryRepository.findByCrewAndDay(bump.getCrew(), bump.getDay());
      for (UserCrewPositionHistory userBump : userBumps) {
        userBump.setBumps(bump.getBumps());
        userBump.setDividend(bump.getDividend());
        userBumpsToSave.add(userBump);
      }
    }
    userCrewPositionHistoryRepository.save(userBumpsToSave);
  }
}
