package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.CrewPriceHistory;
import com.alexanthony.dreambumps.service.CrewPositionHistoryService;
import com.alexanthony.dreambumps.service.dto.CrewPositionHistoryDTO;
import com.alexanthony.dreambumps.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CrewPositionHistory.
 */
@RestController
@RequestMapping("/api")
public class CrewPositionHistoryResource {

  private final Logger log = LoggerFactory.getLogger(CrewPositionHistoryResource.class);

  private static final String ENTITY_NAME = "crewPositionHistory";

  private final CrewPositionHistoryService crewPositionHistoryService;

  public CrewPositionHistoryResource(CrewPositionHistoryService crewPositionHistoryService) {
    this.crewPositionHistoryService = crewPositionHistoryService;
  }

  @GetMapping("/crews/{crewId}/position-history")
  @Timed
  public List<CrewPositionHistoryDTO> getPositionHistoryForCrew(@PathVariable Long crewId) {
    log.debug("REST request to get Position History for Crew : {}", crewId);
    return crewPositionHistoryService.findAllByCrew(crewId);
  }

  /**
   * POST /crew-position-histories : Create a new crewPositionHistory.
   *
   * @param crewPositionHistoryDTO
   *          the crewPositionHistory to create
   * @return the ResponseEntity with status 201 (Created) and with body the new
   *         crewPositionHistory, or with status 400 (Bad Request) if the
   *         crewPositionHistory has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
//  @PostMapping("/crew-position-histories")
//  @Timed
//  public ResponseEntity<CrewPositionHistoryDTO> createCrewPositionHistory(
//      @Valid @RequestBody CrewPositionHistoryDTO crewPositionHistoryDTO) throws URISyntaxException {
//    log.debug("REST request to save CrewPositionHistory : {}", crewPositionHistoryDTO);
//    if (crewPositionHistoryDTO.getId() != null) {
//      return ResponseEntity.badRequest().headers(
//          HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new crewPositionHistory cannot already have an ID"))
//          .body(null);
//    }
//    CrewPositionHistoryDTO result = crewPositionHistoryService.save(crewPositionHistoryDTO);
//    return ResponseEntity.created(new URI("/api/crew-position-histories/" + result.getId()))
//        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
//  }

  @PostMapping("/bumps")
  @Timed
  public List<CrewPositionHistoryDTO> uploadBumps(@RequestBody List<CrewPositionHistoryDTO> crewPositionHistoryDTOs) {
    log.debug("REST request to upload CrewPositionHistories");
    return crewPositionHistoryService.saveFullBumpSet(crewPositionHistoryDTOs);
  }

  @PutMapping("/bumps")
  @Timed
  public List<CrewPositionHistoryDTO> changeBumps(@RequestBody List<CrewPositionHistoryDTO> crewPositionHistoryDTOs) {
    log.debug("REST request to upload CrewPositionHistories");
    return crewPositionHistoryService.updateFullBumps(crewPositionHistoryDTOs);
  }

  /**
   * PUT /crew-position-histories : Updates an existing crewPositionHistory.
   *
   * @param crewPositionHistoryDTO
   *          the crewPositionHistory to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated
   *         crewPositionHistory, or with status 400 (Bad Request) if the
   *         crewPositionHistory is not valid, or with status 500 (Internal
   *         Server Error) if the crewPositionHistory couldnt be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
//  @PutMapping("/crew-position-histories")
//  @Timed
//  public ResponseEntity<CrewPositionHistoryDTO> updateCrewPositionHistory(
//      @Valid @RequestBody CrewPositionHistoryDTO crewPositionHistoryDTO) throws URISyntaxException {
//    log.debug("REST request to update CrewPositionHistory : {}", crewPositionHistoryDTO);
//    if (crewPositionHistoryDTO.getId() == null) {
//      return createCrewPositionHistory(crewPositionHistoryDTO);
//    }
//    CrewPositionHistoryDTO result = crewPositionHistoryService.save(crewPositionHistoryDTO);
//    return ResponseEntity.ok()
//        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, crewPositionHistoryDTO.getId().toString()))
//        .body(result);
//  }

  /**
   * GET /crew-position-histories : get all the crewPositionHistories.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of
   *         crewPositionHistories in body
   */
//  @GetMapping("/crew-position-histories")
//  @Timed
//  public List<CrewPositionHistoryDTO> getAllCrewPositionHistories() {
//    log.debug("REST request to get all CrewPositionHistories");
//    List<CrewPositionHistoryDTO> crewPositionHistories = crewPositionHistoryService.findAll();
//    return crewPositionHistories;
//  }

  /**
   * GET /crew-position-histories/:id : get the "id" crewPositionHistory.
   *
   * @param id
   *          the id of the crewPositionHistory to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the
   *         crewPositionHistory, or with status 404 (Not Found)
   */
//  @GetMapping("/crew-position-histories/{id}")
//  @Timed
//  public ResponseEntity<CrewPositionHistoryDTO> getCrewPositionHistory(@PathVariable Long id) {
//    log.debug("REST request to get CrewPositionHistory : {}", id);
//    CrewPositionHistoryDTO crewPositionHistoryDTO = crewPositionHistoryService.findOne(id);
//    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(crewPositionHistoryDTO));
//  }

  /**
   * DELETE /crew-position-histories/:id : delete the "id" crewPositionHistory.
   *
   * @param id
   *          the id of the crewPositionHistory to delete
   * @return the ResponseEntity with status 200 (OK)
   */
//  @DeleteMapping("/crew-position-histories/{id}")
//  @Timed
//  public ResponseEntity<Void> deleteCrewPositionHistory(@PathVariable Long id) {
//    log.debug("REST request to delete CrewPositionHistory : {}", id);
//    crewPositionHistoryService.delete(id);
//    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//  }

}
