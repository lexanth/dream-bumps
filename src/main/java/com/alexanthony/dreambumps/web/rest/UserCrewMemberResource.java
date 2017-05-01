package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.service.UserCrewMemberService;
import com.alexanthony.dreambumps.web.rest.util.HeaderUtil;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
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
 * REST controller for managing UserCrewMember.
 */
@RestController
@RequestMapping("/api")
public class UserCrewMemberResource {

    private final Logger log = LoggerFactory.getLogger(UserCrewMemberResource.class);

    private static final String ENTITY_NAME = "userCrewMember";

    private final UserCrewMemberService userCrewMemberService;

    public UserCrewMemberResource(UserCrewMemberService userCrewMemberService) {
        this.userCrewMemberService = userCrewMemberService;
    }

    @GetMapping("/users/{userId}/members")
    @Timed
    public List<UserCrewMemberDTO> getUserCrewMembersForUserForSex(@PathVariable Long userId, @RequestParam Sex sex) {
    	log.debug("REST request to get UserCrewMembers for  : {} {}", userId, sex);
    	return userCrewMemberService.getMembersForUserAndSex(userId, sex);
    }

    /**
     * POST  /user-crew-members : Create a new userCrewMember.
     *
     * @param userCrewMemberDTO the userCrewMemberDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCrewMemberDTO, or with status 400 (Bad Request) if the userCrewMember has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/user-crew-members")
//    @Timed
//    public ResponseEntity<UserCrewMemberDTO> createUserCrewMember(@Valid @RequestBody UserCrewMemberDTO userCrewMemberDTO) throws URISyntaxException {
//        log.debug("REST request to save UserCrewMember : {}", userCrewMemberDTO);
//        if (userCrewMemberDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userCrewMember cannot already have an ID")).body(null);
//        }
//        UserCrewMemberDTO result = userCrewMemberService.save(userCrewMemberDTO);
//        return ResponseEntity.created(new URI("/api/user-crew-members/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /user-crew-members : Updates an existing userCrewMember.
     *
     * @param userCrewMemberDTO the userCrewMemberDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userCrewMemberDTO,
     * or with status 400 (Bad Request) if the userCrewMemberDTO is not valid,
     * or with status 500 (Internal Server Error) if the userCrewMemberDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/user-crew-members")
//    @Timed
//    public ResponseEntity<UserCrewMemberDTO> updateUserCrewMember(@Valid @RequestBody UserCrewMemberDTO userCrewMemberDTO) throws URISyntaxException {
//        log.debug("REST request to update UserCrewMember : {}", userCrewMemberDTO);
//        if (userCrewMemberDTO.getId() == null) {
//            return createUserCrewMember(userCrewMemberDTO);
//        }
//        UserCrewMemberDTO result = userCrewMemberService.save(userCrewMemberDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCrewMemberDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /user-crew-members : get all the userCrewMembers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userCrewMembers in body
     */
//    @GetMapping("/user-crew-members")
//    @Timed
//    public List<UserCrewMemberDTO> getAllUserCrewMembers() {
//        log.debug("REST request to get all UserCrewMembers");
//        return userCrewMemberService.findAll();
//    }

    /**
     * GET  /user-crew-members/:id : get the "id" userCrewMember.
     *
     * @param id the id of the userCrewMemberDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userCrewMemberDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/user-crew-members/{id}")
//    @Timed
//    public ResponseEntity<UserCrewMemberDTO> getUserCrewMember(@PathVariable Long id) {
//        log.debug("REST request to get UserCrewMember : {}", id);
//        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberService.findOneDTO(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userCrewMemberDTO));
//    }

    /**
     * DELETE  /user-crew-members/:id : delete the "id" userCrewMember.
     *
     * @param id the id of the userCrewMemberDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/user-crew-members/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteUserCrewMember(@PathVariable Long id) {
//        log.debug("REST request to delete UserCrewMember : {}", id);
//        userCrewMemberService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

}
