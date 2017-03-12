package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.UserCrewPositionHistory;
import com.alexanthony.dreambumps.service.UserCrewRacingHistoryService;
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
 * REST controller for managing UserCrewPositionHistory.
 */
@RestController
@RequestMapping("/api")
public class UserCrewPositionHistoryResource {

    private final Logger log = LoggerFactory.getLogger(UserCrewPositionHistoryResource.class);

    private static final String ENTITY_NAME = "userCrewPositionHistory";
        
    private final UserCrewRacingHistoryService userCrewPositionHistoryService;

    public UserCrewPositionHistoryResource(UserCrewRacingHistoryService userCrewPositionHistoryService) {
        this.userCrewPositionHistoryService = userCrewPositionHistoryService;
    }

    /**
     * POST  /user-crew-position-histories : Create a new userCrewPositionHistory.
     *
     * @param userCrewPositionHistory the userCrewPositionHistory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCrewPositionHistory, or with status 400 (Bad Request) if the userCrewPositionHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-crew-position-histories")
    @Timed
    public ResponseEntity<UserCrewPositionHistory> createUserCrewPositionHistory(@Valid @RequestBody UserCrewPositionHistory userCrewPositionHistory) throws URISyntaxException {
        log.debug("REST request to save UserCrewPositionHistory : {}", userCrewPositionHistory);
        if (userCrewPositionHistory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userCrewPositionHistory cannot already have an ID")).body(null);
        }
        UserCrewPositionHistory result = userCrewPositionHistoryService.save(userCrewPositionHistory);
        return ResponseEntity.created(new URI("/api/user-crew-position-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-crew-position-histories : Updates an existing userCrewPositionHistory.
     *
     * @param userCrewPositionHistory the userCrewPositionHistory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userCrewPositionHistory,
     * or with status 400 (Bad Request) if the userCrewPositionHistory is not valid,
     * or with status 500 (Internal Server Error) if the userCrewPositionHistory couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-crew-position-histories")
    @Timed
    public ResponseEntity<UserCrewPositionHistory> updateUserCrewPositionHistory(@Valid @RequestBody UserCrewPositionHistory userCrewPositionHistory) throws URISyntaxException {
        log.debug("REST request to update UserCrewPositionHistory : {}", userCrewPositionHistory);
        if (userCrewPositionHistory.getId() == null) {
            return createUserCrewPositionHistory(userCrewPositionHistory);
        }
        UserCrewPositionHistory result = userCrewPositionHistoryService.save(userCrewPositionHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCrewPositionHistory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-crew-position-histories : get all the userCrewPositionHistories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userCrewPositionHistories in body
     */
    @GetMapping("/user-crew-position-histories")
    @Timed
    public List<UserCrewPositionHistory> getAllUserCrewPositionHistories() {
        log.debug("REST request to get all UserCrewPositionHistories");
        return userCrewPositionHistoryService.findAll();
    }

    /**
     * GET  /user-crew-position-histories/:id : get the "id" userCrewPositionHistory.
     *
     * @param id the id of the userCrewPositionHistory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userCrewPositionHistory, or with status 404 (Not Found)
     */
    @GetMapping("/user-crew-position-histories/{id}")
    @Timed
    public ResponseEntity<UserCrewPositionHistory> getUserCrewPositionHistory(@PathVariable Long id) {
        log.debug("REST request to get UserCrewPositionHistory : {}", id);
        UserCrewPositionHistory userCrewPositionHistory = userCrewPositionHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userCrewPositionHistory));
    }

    /**
     * DELETE  /user-crew-position-histories/:id : delete the "id" userCrewPositionHistory.
     *
     * @param id the id of the userCrewPositionHistory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-crew-position-histories/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserCrewPositionHistory(@PathVariable Long id) {
        log.debug("REST request to delete UserCrewPositionHistory : {}", id);
        userCrewPositionHistoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
