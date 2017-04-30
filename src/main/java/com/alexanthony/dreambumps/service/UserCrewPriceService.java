package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.domain.UserCrewPositionHistory;
import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.UserCrewPositionHistoryRepository;
import com.alexanthony.dreambumps.repository.UserCrewPriceRepository;
import com.alexanthony.dreambumps.service.dto.UserCrewPriceDTO;
import com.alexanthony.dreambumps.service.mapper.UserCrewPriceMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing UserCrewPrice.
 */
@Service
@Transactional
public class UserCrewPriceService {

  private final Logger log = LoggerFactory.getLogger(UserCrewPriceService.class);

  private final UserCrewPriceRepository userCrewPriceRepository;
  private final UserCrewPriceHistoryService userCrewPriceHistoryService;
  private final UserCrewPositionHistoryRepository userCrewPositionHistoryRepository;

  private final UserCrewPriceMapper userCrewPriceMapper;

  public UserCrewPriceService(UserCrewPriceRepository userCrewPriceRepository,
      UserCrewPriceHistoryService userCrewPriceHistoryService, UserCrewPriceMapper userCrewPriceMapper, UserCrewPositionHistoryRepository userCrewPositionHistoryRepository) {
    this.userCrewPriceRepository = userCrewPriceRepository;
    this.userCrewPriceHistoryService = userCrewPriceHistoryService;
    this.userCrewPriceMapper = userCrewPriceMapper;
    this.userCrewPositionHistoryRepository = userCrewPositionHistoryRepository;
  }

  /**
   * Save a userCrewPrice.
   *
   * @param userCrewPrice
   *          the entity to save
   * @return the persisted entity
   */
  public UserCrewPrice save(UserCrewPrice userCrewPrice) {
    log.debug("Request to save UserCrewPrice : {}", userCrewPrice);
    UserCrewPrice result = userCrewPriceRepository.save(userCrewPrice);
    userCrewPriceHistoryService.createHistoryRecord(result);
    return result;
  }

  /**
   * Get all the userCrewPrices.
   *
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<UserCrewPriceDTO> findAll() {
    log.debug("Request to get all UserCrewPrices");
    return userCrewPriceRepository.findAll().stream().map(userCrewPriceMapper::userCrewPriceToUserCrewPriceDTO)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one userCrewPrice by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public UserCrewPrice findOne(Long id) {
    log.debug("Request to get UserCrewPrice : {}", id);
    UserCrewPrice userCrewPrice = userCrewPriceRepository.findOne(id);
    return userCrewPrice;
  }

  /**
   * Delete the userCrewPrice by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete UserCrewPrice : {}", id);
    userCrewPriceRepository.delete(id);
  }

  public List<UserCrewPriceDTO> findAllForSex(Sex sex) {
    // TODO Auto-generated method stub
    return userCrewPriceRepository.findBySex(sex).stream()
      .map(userCrewPriceMapper::userCrewPriceToUserCrewPriceDTO)
      .map(this::populateBumpsAndDividends)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  private UserCrewPriceDTO populateBumpsAndDividends(UserCrewPriceDTO userCrewPriceDTO) {
    List<UserCrewPositionHistory> racingHistories = userCrewPositionHistoryRepository.findByUserIdAndSex(userCrewPriceDTO.getUserId(), userCrewPriceDTO.getSex());
    Integer bumps = racingHistories.stream().reduce(0, (sum, racingHistory) -> (sum += racingHistory.getBumps()), (sum1, sum2) -> (sum1 + sum2));
    BigDecimal dividends = racingHistories.stream().reduce(BigDecimal.ZERO, (sum, racingHistory) -> (sum.add(racingHistory.getDividend())), (sum1, sum2) -> (sum1.add(sum2)));
    userCrewPriceDTO.setBumps(bumps);
    userCrewPriceDTO.setDividends(dividends);
    return userCrewPriceDTO;
  }

  public void createDetailsForUser(User user) {
    UserCrewPrice maleCrewPrice = getEmptyUserCrewPrice(user).sex(Sex.male);
    save(maleCrewPrice);

    UserCrewPrice femaleCrewPrice = getEmptyUserCrewPrice(user).sex(Sex.female);
    save(femaleCrewPrice);
  }

  private UserCrewPrice getEmptyUserCrewPrice(User user) {
    UserCrewPrice userCrewPrice = new UserCrewPrice();
    userCrewPrice.setCash(new BigDecimal("1000.00"));
    userCrewPrice.setValue(BigDecimal.ZERO);
    userCrewPrice.setUser(user);
    return userCrewPrice;
  }

  @Transactional(readOnly=true)
  public UserCrewPriceDTO findForUserAndSexDTO(Long userId, Sex sex) {
    return userCrewPriceMapper.userCrewPriceToUserCrewPriceDTO(findForUserAndSex(userId, sex));
  }

  @Transactional(readOnly=true)
  public UserCrewPrice findForUserAndSex(Long userId, Sex sex) {
    UserCrewPrice userCrewPrice = userCrewPriceRepository.findFirstByUserIdAndSex(userId, sex);
    return userCrewPrice;
  }
}
