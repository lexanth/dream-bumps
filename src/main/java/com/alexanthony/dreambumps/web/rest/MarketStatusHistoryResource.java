package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.service.MarketStatusHistoryService;
import com.alexanthony.dreambumps.web.rest.util.HeaderUtil;
import com.alexanthony.dreambumps.service.dto.MarketStatusHistoryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing MarketStatusHistory.
 */
@RestController
@RequestMapping("/api")
public class MarketStatusHistoryResource {

  private final Logger log = LoggerFactory.getLogger(MarketStatusHistoryResource.class);

  private static final String ENTITY_NAME = "marketStatusHistory";

  private final MarketStatusHistoryService marketStatusHistoryService;

  public MarketStatusHistoryResource(MarketStatusHistoryService marketStatusHistoryService) {
    this.marketStatusHistoryService = marketStatusHistoryService;
  }

  /**
   * POST /market-status-histories : Create a new marketStatusHistory.
   *
   * @param marketStatusHistoryDTO
   *          the marketStatusHistoryDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new
   *         marketStatusHistoryDTO, or with status 400 (Bad Request) if the
   *         marketStatusHistory has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
  @PostMapping("/market-status")
  @Timed
  public ResponseEntity<MarketStatusHistoryDTO> createMarketStatusHistory(
      @Valid @RequestBody MarketStatusHistoryDTO marketStatusHistoryDTO) throws URISyntaxException {
    log.debug("REST request to save MarketStatusHistory : {}", marketStatusHistoryDTO);
    if (marketStatusHistoryDTO.getId() != null) {
      return ResponseEntity.badRequest().headers(
          HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new marketStatusHistory cannot already have an ID"))
          .body(null);
    }
    MarketStatusHistoryDTO result = marketStatusHistoryService.save(marketStatusHistoryDTO);
    return ResponseEntity.created(new URI("/api/market-status-histories/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
  }

  /**
   * PUT /market-status-histories : Updates an existing marketStatusHistory.
   *
   * @param marketStatusHistoryDTO
   *          the marketStatusHistoryDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated
   *         marketStatusHistoryDTO, or with status 400 (Bad Request) if the
   *         marketStatusHistoryDTO is not valid, or with status 500 (Internal
   *         Server Error) if the marketStatusHistoryDTO couldnt be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
  // @PutMapping("/market-status-histories")
  // @Timed
  // public ResponseEntity<MarketStatusHistoryDTO>
  // updateMarketStatusHistory(@Valid @RequestBody MarketStatusHistoryDTO
  // marketStatusHistoryDTO) throws URISyntaxException {
  // log.debug("REST request to update MarketStatusHistory : {}",
  // marketStatusHistoryDTO);
  // if (marketStatusHistoryDTO.getId() == null) {
  // return createMarketStatusHistory(marketStatusHistoryDTO);
  // }
  // MarketStatusHistoryDTO result =
  // marketStatusHistoryService.save(marketStatusHistoryDTO);
  // return ResponseEntity.ok()
  // .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
  // marketStatusHistoryDTO.getId().toString()))
  // .body(result);
  // }

  /**
   * GET /market-status-histories : get all the marketStatusHistories.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of
   *         marketStatusHistories in body
   */
  @GetMapping("/market-status/all")
  @Timed
  public List<MarketStatusHistoryDTO> getAllMarketStatusHistories() {
    log.debug("REST request to get all MarketStatusHistories");
    return marketStatusHistoryService.findAll();
  }

  /**
   * GET /market-status-histories/:id : get the "id" marketStatusHistory.
   *
   * @param id
   *          the id of the marketStatusHistoryDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the
   *         marketStatusHistoryDTO, or with status 404 (Not Found)
   */
  @GetMapping("/market-status")
  @Timed
  public ResponseEntity<MarketStatusHistoryDTO> getMarketStatusHistory() {
    log.debug("REST request to get latest MarketStatusHistory");
    MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryService.findLatest();
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(marketStatusHistoryDTO));
  }

  /**
   * DELETE /market-status-histories/:id : delete the "id" marketStatusHistory.
   *
   * @param id
   *          the id of the marketStatusHistoryDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  // @DeleteMapping("/market-status-histories/{id}")
  // @Timed
  // public ResponseEntity<Void> deleteMarketStatusHistory(@PathVariable Long
  // id) {
  // log.debug("REST request to delete MarketStatusHistory : {}", id);
  // marketStatusHistoryService.delete(id);
  // return
  // ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
  // id.toString())).build();
  // }

}
