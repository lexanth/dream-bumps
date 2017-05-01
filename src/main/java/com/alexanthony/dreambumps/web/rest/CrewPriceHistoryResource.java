package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.CrewPriceHistory;

import com.alexanthony.dreambumps.service.CrewPriceHistoryService;
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
 * REST controller for managing CrewPriceHistory.
 */
@RestController
@RequestMapping("/api")
public class CrewPriceHistoryResource {

    private final Logger log = LoggerFactory.getLogger(CrewPriceHistoryResource.class);

    private static final String ENTITY_NAME = "crewPriceHistory";

    private final CrewPriceHistoryService crewPriceHistoryService;

    public CrewPriceHistoryResource(CrewPriceHistoryService crewPriceHistoryService) {
        this.crewPriceHistoryService = crewPriceHistoryService;
    }

    @GetMapping("/crews/{crewId}/price-history")
    @Timed
    public List<CrewPriceHistory> getPriceHistoryForCrew(@PathVariable Long crewId) {
    	log.debug("REST request to get Price History for Crew : {}", crewId);
    	return crewPriceHistoryService.findAllByCrew(crewId);
    }

    /**
     * POST  /crew-price-histories : Create a new crewPriceHistory.
     *
     * @param crewPriceHistory the crewPriceHistory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new crewPriceHistory, or with status 400 (Bad Request) if the crewPriceHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/crew-price-histories")
//    @Timed
//    public ResponseEntity<CrewPriceHistory> createCrewPriceHistory(@Valid @RequestBody CrewPriceHistory crewPriceHistory) throws URISyntaxException {
//        log.debug("REST request to save CrewPriceHistory : {}", crewPriceHistory);
//        if (crewPriceHistory.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new crewPriceHistory cannot already have an ID")).body(null);
//        }
//        CrewPriceHistory result = crewPriceHistoryService.save(crewPriceHistory);
//        return ResponseEntity.created(new URI("/api/crew-price-histories/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /crew-price-histories : Updates an existing crewPriceHistory.
     *
     * @param crewPriceHistory the crewPriceHistory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated crewPriceHistory,
     * or with status 400 (Bad Request) if the crewPriceHistory is not valid,
     * or with status 500 (Internal Server Error) if the crewPriceHistory couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/crew-price-histories")
//    @Timed
//    public ResponseEntity<CrewPriceHistory> updateCrewPriceHistory(@Valid @RequestBody CrewPriceHistory crewPriceHistory) throws URISyntaxException {
//        log.debug("REST request to update CrewPriceHistory : {}", crewPriceHistory);
//        if (crewPriceHistory.getId() == null) {
//            return createCrewPriceHistory(crewPriceHistory);
//        }
//        CrewPriceHistory result = crewPriceHistoryService.save(crewPriceHistory);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, crewPriceHistory.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /crew-price-histories : get all the crewPriceHistories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of crewPriceHistories in body
     */
//    @GetMapping("/crew-price-histories")
//    @Timed
//    public List<CrewPriceHistory> getAllCrewPriceHistories() {
//        log.debug("REST request to get all CrewPriceHistories");
//        List<CrewPriceHistory> crewPriceHistories = crewPriceHistoryService.findAll();
//        return crewPriceHistories;
//    }

    /**
     * GET  /crew-price-histories/:id : get the "id" crewPriceHistory.
     *
     * @param id the id of the crewPriceHistory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the crewPriceHistory, or with status 404 (Not Found)
     */
//    @GetMapping("/crew-price-histories/{id}")
//    @Timed
//    public ResponseEntity<CrewPriceHistory> getCrewPriceHistory(@PathVariable Long id) {
//        log.debug("REST request to get CrewPriceHistory : {}", id);
//        CrewPriceHistory crewPriceHistory = crewPriceHistoryService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(crewPriceHistory));
//    }

    /**
     * DELETE  /crew-price-histories/:id : delete the "id" crewPriceHistory.
     *
     * @param id the id of the crewPriceHistory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/crew-price-histories/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteCrewPriceHistory(@PathVariable Long id) {
//        log.debug("REST request to delete CrewPriceHistory : {}", id);
//        crewPriceHistoryService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

}
