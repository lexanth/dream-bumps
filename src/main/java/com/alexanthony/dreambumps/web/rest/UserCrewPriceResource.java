package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.service.UserCrewPriceService;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;
import com.alexanthony.dreambumps.service.dto.UserCrewPriceDTO;
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
 * REST controller for managing UserCrewPrice.
 */
@RestController
@RequestMapping("/api")
public class UserCrewPriceResource {

  private final Logger log = LoggerFactory.getLogger(UserCrewPriceResource.class);

  private static final String ENTITY_NAME = "userCrewPrice";

  private final UserCrewPriceService userCrewPriceService;

  public UserCrewPriceResource(UserCrewPriceService userCrewPriceService) {
    this.userCrewPriceService = userCrewPriceService;
  }

//  /**
//   * POST /user-crew-prices : Create a new userCrewPrice.
//   *
//   * @param userCrewPrice
//   *          the userCrewPrice to create
//   * @return the ResponseEntity with status 201 (Created) and with body the new
//   *         userCrewPrice, or with status 400 (Bad Request) if the
//   *         userCrewPrice has already an ID
//   * @throws URISyntaxException
//   *           if the Location URI syntax is incorrect
//   */
//  @PostMapping("/user-crew-prices")
//  @Timed
//  public ResponseEntity<UserCrewPrice> createUserCrewPrice(@Valid @RequestBody UserCrewPrice userCrewPrice)
//      throws URISyntaxException {
//    log.debug("REST request to save UserCrewPrice : {}", userCrewPrice);
//    if (userCrewPrice.getId() != null) {
//      return ResponseEntity.badRequest()
//          .headers(
//              HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userCrewPrice cannot already have an ID"))
//          .body(null);
//    }
//    UserCrewPrice result = userCrewPriceService.save(userCrewPrice);
//    return ResponseEntity.created(new URI("/api/user-crew-prices/" + result.getId()))
//        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
//  }
//
//  /**
//   * PUT /user-crew-prices : Updates an existing userCrewPrice.
//   *
//   * @param userCrewPrice
//   *          the userCrewPrice to update
//   * @return the ResponseEntity with status 200 (OK) and with body the updated
//   *         userCrewPrice, or with status 400 (Bad Request) if the
//   *         userCrewPrice is not valid, or with status 500 (Internal Server
//   *         Error) if the userCrewPrice couldnt be updated
//   * @throws URISyntaxException
//   *           if the Location URI syntax is incorrect
//   */
//  @PutMapping("/user-crew-prices")
//  @Timed
//  public ResponseEntity<UserCrewPrice> updateUserCrewPrice(@Valid @RequestBody UserCrewPrice userCrewPrice)
//      throws URISyntaxException {
//    log.debug("REST request to update UserCrewPrice : {}", userCrewPrice);
//    if (userCrewPrice.getId() == null) {
//      return createUserCrewPrice(userCrewPrice);
//    }
//    UserCrewPrice result = userCrewPriceService.save(userCrewPrice);
//    return ResponseEntity.ok()
//        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCrewPrice.getId().toString())).body(result);
//  }

  /**
   * GET /user-crew-prices : get all the userCrewPrices.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of
   *         userCrewPrices in body
   */
//  @GetMapping("/user-crew-prices")
//  @Timed
//  public List<UserCrewPriceDTO> getAllUserCrewPrices() {
//    log.debug("REST request to get all UserCrewPrices");
//    return userCrewPriceService.findAll();
//  }
//
//  /**
//   * GET /user-crew-prices/:id : get the "id" userCrewPrice.
//   *
//   * @param id
//   *          the id of the userCrewPrice to retrieve
//   * @return the ResponseEntity with status 200 (OK) and with body the
//   *         userCrewPrice, or with status 404 (Not Found)
//   */
//  @GetMapping("/user-crew-prices/{id}")
//  @Timed
//  public ResponseEntity<UserCrewPrice> getUserCrewPrice(@PathVariable Long id) {
//    log.debug("REST request to get UserCrewPrice : {}", id);
//    UserCrewPrice userCrewPrice = userCrewPriceService.findOne(id);
//    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userCrewPrice));
//  }
//
//  /**
//   * DELETE /user-crew-prices/:id : delete the "id" userCrewPrice.
//   *
//   * @param id
//   *          the id of the userCrewPrice to delete
//   * @return the ResponseEntity with status 200 (OK)
//   */
//  @DeleteMapping("/user-crew-prices/{id}")
//  @Timed
//  public ResponseEntity<Void> deleteUserCrewPrice(@PathVariable Long id) {
//    log.debug("REST request to delete UserCrewPrice : {}", id);
//    userCrewPriceService.delete(id);
//    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//  }

  @GetMapping("/rankings")
  @Timed
  public List<UserCrewPriceDTO> getMostRecentRankingPerUser(@RequestParam Sex sex) {
    return userCrewPriceService.findAllForSex(sex);
  }

  @GetMapping("/users/{userId}/ranking")
  @Timed
  public UserCrewPriceDTO getMostRecentForUserForSex(@PathVariable Long userId, @RequestParam Sex sex) {
    log.debug("REST request to get UserCrewPrice for  : {} {}", userId, sex);
    return userCrewPriceService.findForUserAndSexDTO(userId, sex);
  }
}
