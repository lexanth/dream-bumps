package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.UserCrewPriceHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.service.UserCrewPriceHistoryService;
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
 * REST controller for managing UserCrewPriceHistory.
 */
@RestController
@RequestMapping("/api")
public class UserCrewPriceHistoryResource {

    private final Logger log = LoggerFactory.getLogger(UserCrewPriceHistoryResource.class);

    private static final String ENTITY_NAME = "userCrewPriceHistory";
        
    private final UserCrewPriceHistoryService userCrewPriceHistoryService;

    public UserCrewPriceHistoryResource(UserCrewPriceHistoryService userCrewPriceHistoryService) {
        this.userCrewPriceHistoryService = userCrewPriceHistoryService;
    }

    /**
     * POST  /user-crew-price-histories : Create a new userCrewPriceHistory.
     *
     * @param userCrewPriceHistory the userCrewPriceHistory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCrewPriceHistory, or with status 400 (Bad Request) if the userCrewPriceHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/user-crew-price-histories")
//    @Timed
//    public ResponseEntity<UserCrewPriceHistory> createUserCrewPriceHistory(@Valid @RequestBody UserCrewPriceHistory userCrewPriceHistory) throws URISyntaxException {
//        log.debug("REST request to save UserCrewPriceHistory : {}", userCrewPriceHistory);
//        if (userCrewPriceHistory.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userCrewPriceHistory cannot already have an ID")).body(null);
//        }
//        UserCrewPriceHistory result = userCrewPriceHistoryService.save(userCrewPriceHistory);
//        return ResponseEntity.created(new URI("/api/user-crew-price-histories/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /user-crew-price-histories : Updates an existing userCrewPriceHistory.
     *
     * @param userCrewPriceHistory the userCrewPriceHistory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userCrewPriceHistory,
     * or with status 400 (Bad Request) if the userCrewPriceHistory is not valid,
     * or with status 500 (Internal Server Error) if the userCrewPriceHistory couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/user-crew-price-histories")
//    @Timed
//    public ResponseEntity<UserCrewPriceHistory> updateUserCrewPriceHistory(@Valid @RequestBody UserCrewPriceHistory userCrewPriceHistory) throws URISyntaxException {
//        log.debug("REST request to update UserCrewPriceHistory : {}", userCrewPriceHistory);
//        if (userCrewPriceHistory.getId() == null) {
//            return createUserCrewPriceHistory(userCrewPriceHistory);
//        }
//        UserCrewPriceHistory result = userCrewPriceHistoryService.save(userCrewPriceHistory);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCrewPriceHistory.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /user-crew-price-histories : get all the userCrewPriceHistories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userCrewPriceHistories in body
     */
    @GetMapping("/user-crew-price-histories")
    @Timed
    public List<UserCrewPriceHistory> getAllUserCrewPriceHistories() {
        log.debug("REST request to get all UserCrewPriceHistories");
        return userCrewPriceHistoryService.findAll();
    }

    /**
     * GET  /user-crew-price-histories/:id : get the "id" userCrewPriceHistory.
     *
     * @param id the id of the userCrewPriceHistory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userCrewPriceHistory, or with status 404 (Not Found)
     */
//    @GetMapping("/user-crew-price-histories/{id}")
//    @Timed
//    public ResponseEntity<UserCrewPriceHistory> getUserCrewPriceHistory(@PathVariable Long id) {
//        log.debug("REST request to get UserCrewPriceHistory : {}", id);
//        UserCrewPriceHistory userCrewPriceHistory = userCrewPriceHistoryService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userCrewPriceHistory));
//    }

    /**
     * DELETE  /user-crew-price-histories/:id : delete the "id" userCrewPriceHistory.
     *
     * @param id the id of the userCrewPriceHistory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/user-crew-price-histories/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteUserCrewPriceHistory(@PathVariable Long id) {
//        log.debug("REST request to delete UserCrewPriceHistory : {}", id);
//        userCrewPriceHistoryService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
    
//    @GetMapping("/rankings")
//    @Timed
//    public List<UserCrewPriceHistory> getMostRecentRankingPerUser(@RequestParam Sex sex) {
//    	return userCrewPriceHistoryService.findMostRecentPricePerUserForSex(sex);
//    }
    
    @GetMapping("/users/{userId}/price-history")
    @Timed
    public List<UserCrewPriceHistory> getAllUserCrewPriceHistories(@PathVariable Long userId, @RequestParam Sex sex) {
        log.debug("REST request to get all UserCrewPriceHistories for user: {}", userId);
        return userCrewPriceHistoryService.findAllForUserForSex(userId, sex);
    }
    

}
