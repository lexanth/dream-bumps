package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.service.CrewMemberService;
import com.alexanthony.dreambumps.web.rest.util.HeaderUtil;
import com.alexanthony.dreambumps.service.dto.CrewMemberDTO;
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
 * REST controller for managing CrewMember.
 */
@RestController
@RequestMapping("/api")
public class CrewMemberResource {

    private final Logger log = LoggerFactory.getLogger(CrewMemberResource.class);

    private static final String ENTITY_NAME = "crewMember";

    private final CrewMemberService crewMemberService;

    public CrewMemberResource(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @GetMapping("/crews/{crewId}/members")
    @Timed
    public List<CrewMemberDTO> getCrewMembersForCrew(@PathVariable Long crewId) {
    	log.debug("REST request to get CrewMembers for Crew : {}", crewId);
    	return crewMemberService.getCrewMembersForCrew(crewId);
    }

    /**
     * POST  /crew-members : Create a new crewMember.
     *
     * @param crewMemberDTO the crewMemberDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new crewMemberDTO, or with status 400 (Bad Request) if the crewMember has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/crew-members")
//    @Timed
//    public ResponseEntity<CrewMemberDTO> createCrewMember(@Valid @RequestBody CrewMemberDTO crewMemberDTO) throws URISyntaxException {
//        log.debug("REST request to save CrewMember : {}", crewMemberDTO);
//        if (crewMemberDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new crewMember cannot already have an ID")).body(null);
//        }
//        CrewMemberDTO result = crewMemberService.save(crewMemberDTO);
//        return ResponseEntity.created(new URI("/api/crew-members/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /crew-members : Updates an existing crewMember.
     *
     * @param crewMemberDTOs the crewMemberDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated crewMemberDTO,
     * or with status 400 (Bad Request) if the crewMemberDTO is not valid,
     * or with status 500 (Internal Server Error) if the crewMemberDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/crews/{crewId}/members")
    @Timed
    public ResponseEntity<List<CrewMemberDTO>> updateCrewMember(@Valid @RequestBody List<CrewMemberDTO> crewMemberDTOs) throws URISyntaxException {
        log.debug("REST request to update CrewMembers : {}", crewMemberDTOs);
        if (crewMemberDTOs.stream().anyMatch(a -> a.getId() == null)) {
          return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "iddoesnotexist", "An updated crewMember must already have an ID")).body(null);
        }
        List<CrewMemberDTO> results = crewMemberService.save(crewMemberDTOs);
        return ResponseEntity.ok(results);
    }

    /**
     * GET  /crew-members : get all the crewMembers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of crewMembers in body
     */
    @GetMapping("/crews/members")
    @Timed
    public List<CrewMemberDTO> getAllCrewMembers() {
        log.debug("REST request to get all CrewMembers");
        return crewMemberService.findAll();
    }

    /**
     * GET  /crew-members/:id : get the "id" crewMember.
     *
     * @param id the id of the crewMemberDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the crewMemberDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/crew-members/{id}")
//    @Timed
//    public ResponseEntity<CrewMemberDTO> getCrewMember(@PathVariable Long id) {
//        log.debug("REST request to get CrewMember : {}", id);
//        CrewMemberDTO crewMemberDTO = crewMemberService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(crewMemberDTO));
//    }

    /**
     * DELETE  /crew-members/:id : delete the "id" crewMember.
     *
     * @param id the id of the crewMemberDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/crew-members/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteCrewMember(@PathVariable Long id) {
//        log.debug("REST request to delete CrewMember : {}", id);
//        crewMemberService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

}
