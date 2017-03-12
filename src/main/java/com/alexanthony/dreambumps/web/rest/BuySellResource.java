package com.alexanthony.dreambumps.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexanthony.dreambumps.service.BuySellService;
import com.alexanthony.dreambumps.service.MarketStatusHistoryService;
import com.alexanthony.dreambumps.service.dto.TransactionDTO;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;

@RestController
@RequestMapping("/api")
public class BuySellResource {
  private final Logger log = LoggerFactory.getLogger(BuySellResource.class);
  
  private final BuySellService buySellService;

  public BuySellResource(BuySellService buySellService) {
    this.buySellService = buySellService;
  }
  
  @PostMapping("/buy")
  public UserCrewMemberDTO purchaseRower(@RequestBody TransactionDTO transaction) {
    log.debug("REST request to purchase rower : {}", transaction);
    return buySellService.purchaseRower(transaction);
  }
  
  @PostMapping("/sell")
  public UserCrewMemberDTO sellRower(@RequestBody TransactionDTO transaction) {
    log.debug("REST request to sell rower : {}", transaction);
    return buySellService.sellRower(transaction);
  }
}
