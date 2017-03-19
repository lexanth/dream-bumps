package com.alexanthony.dreambumps.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.domain.UserCrewMember;
import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.repository.UserCrewPriceHistoryRepository;
import com.alexanthony.dreambumps.repository.UserRepository;
import com.alexanthony.dreambumps.security.SecurityUtils;
import com.alexanthony.dreambumps.service.dto.TransactionDTO;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;
import com.alexanthony.dreambumps.service.mapper.UserCrewMemberMapper;

@Service
@Transactional
public class BuySellService {

  private final Logger log = LoggerFactory.getLogger(BuySellService.class);

  // private static final BigDecimal MINUS_ONE = new BigDecimal("-1");
  private static final String transactionCostMultiplier = "0.00";
  private static final BigDecimal transactionCostMultiplierBuy = (new BigDecimal(transactionCostMultiplier))
      .add(BigDecimal.ONE);
  private static final BigDecimal transactionCostMultiplierSell = BigDecimal.ONE
      .subtract((new BigDecimal(transactionCostMultiplier)));
  private static final String priceChangeMultiplier = "0.005";
  private static final BigDecimal priceChangeMultiplierBuy = (new BigDecimal(priceChangeMultiplier))
      .add(BigDecimal.ONE);
  private static final BigDecimal priceChangeMultiplierSell = BigDecimal.ONE
      .subtract(new BigDecimal(priceChangeMultiplier));
  private static final BigDecimal priceChangeAbsolute = new BigDecimal("0.1");

  private final MarketStatusHistoryService marketStatusService;
  private final UserCrewMemberService userCrewMemberService;
  private final UserRepository userRepository;
  private final CrewService crewService;
  private final UserCrewPriceService userCrewPriceService;

  private final UserCrewMemberMapper userCrewMemberMapper;

  public BuySellService(MarketStatusHistoryService marketStatusHistoryService,
      UserCrewMemberService userCrewMemberService, UserRepository userRepository, CrewService crewService,
      UserCrewPriceService userCrewPriceService, UserCrewMemberMapper userCrewMemberMapper) {
    this.marketStatusService = marketStatusHistoryService;
    this.userCrewMemberService = userCrewMemberService;
    this.userRepository = userRepository;
    this.crewService = crewService;
    this.userCrewPriceService = userCrewPriceService;
    this.userCrewMemberMapper = userCrewMemberMapper;
  }

  public UserCrewMemberDTO purchaseRower(TransactionDTO transaction) {
    // Load the current user
    if (!marketStatusService.isMarketOpen()) {
      // Hmm handle better
      return null;
    }

    UserCrewMember userCrewMember = userCrewMemberService.findOne(transaction.getUserCrewMemberId());
    if (userCrewMember.getCrew() != null) {
      // throw an error
      return null;
    }

    // Get Current User's Crew
    Optional<User> currentUserOptional = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
    User currentUser;
    if (currentUserOptional.isPresent()) {
      currentUser = currentUserOptional.get();
      if (!currentUser.getId().equals(userCrewMember.getUser().getId())) {
        // Hmm error?
        return null;
      }

    } else {
      // not authenticated?
      return null;
    }

    Crew crewToBuy = crewService.findOne(transaction.getCrewId());
    if (crewToBuy == null) {
      // Error
      return null;
    }
    if (crewToBuy.getSex() != userCrewMember.getSex()) {
      // Error
      return null;
    }

    UserCrewPrice userCrewPrice = userCrewPriceService.findForUserAndSex(currentUser.getId(),
        userCrewMember.getSex());
    
    if (userCrewPrice.getCash().compareTo(crewToBuy.getPrice()) == -1) {
      // not enough money, error
      return null;
    }
    
    userCrewPrice.setCash(userCrewPrice.getCash().subtract(crewToBuy.getPrice()));
    
    BigDecimal newPrice = crewToBuy.getPrice().multiply(priceChangeMultiplierBuy).add(priceChangeAbsolute).setScale(2, BigDecimal.ROUND_HALF_UP);
    
    userCrewPrice.setValue(userCrewPrice.getValue().add(newPrice));
    
    userCrewPriceService.save(userCrewPrice);
    
    userCrewMember.setCrew(crewToBuy);
    userCrewMemberService.save(userCrewMember);
    
    crewToBuy.setPrice(newPrice);
    crewService.save(crewToBuy);
    

    UserCrewMemberDTO result = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);
    
    // TODO change everybody else slightly
    
    return result;
  }

  public UserCrewMemberDTO sellRower(TransactionDTO transaction) {
    // Load the current user
    if (!marketStatusService.isMarketOpen()) {
      // Hmm handle better
      return null;
    }

    UserCrewMember userCrewMember = userCrewMemberService.findOne(transaction.getUserCrewMemberId());
    if (userCrewMember.getCrew() == null) {
      // throw an error
      return null;
    }

    // Get Current User's Crew
    Optional<User> currentUserOptional = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
    User currentUser;
    if (currentUserOptional.isPresent()) {
      currentUser = currentUserOptional.get();
      if (!currentUser.getId().equals(userCrewMember.getUser().getId())) {
        // Hmm error?
        return null;
      }

    } else {
      // not authenticated?
      return null;
    }

    Crew crewToSell = userCrewMember.getCrew();
    if (crewToSell == null) {
      // Error
      return null;
    }

    UserCrewPrice userCrewPrice = userCrewPriceService.findForUserAndSex(currentUser.getId(),
        userCrewMember.getSex());
    userCrewPrice.setValue(userCrewPrice.getValue().subtract(crewToSell.getPrice()));
    
    BigDecimal newPrice = crewToSell.getPrice().multiply(priceChangeMultiplierSell).subtract(priceChangeAbsolute).setScale(2, BigDecimal.ROUND_HALF_UP);

    userCrewPrice.setCash(userCrewPrice.getCash().add(newPrice));
    
    userCrewPriceService.save(userCrewPrice);
    
    userCrewMember.setCrew(null);
    userCrewMemberService.save(userCrewMember);
    
    crewToSell.setPrice(newPrice);
    crewService.save(crewToSell);
    
 // TODO change everybody else slightly

    UserCrewMemberDTO result = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);
    return result;
  }

}
