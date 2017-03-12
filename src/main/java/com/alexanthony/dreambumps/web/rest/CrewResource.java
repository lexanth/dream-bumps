package com.alexanthony.dreambumps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.service.CrewService;
import com.alexanthony.dreambumps.web.rest.util.HeaderUtil;
import com.alexanthony.dreambumps.service.dto.CrewDTO;
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
 * REST controller for managing Crew.
 */
@RestController
@RequestMapping("/api")
public class CrewResource {

    private final Logger log = LoggerFactory.getLogger(CrewResource.class);

    private static final String ENTITY_NAME = "crew";
        
    private final CrewService crewService;

    public CrewResource(CrewService crewService) {
        this.crewService = crewService;
    }

    /**
     * POST  /crews : Create a new crew.
     *
     * @param crewDTO the crewDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new crewDTO, or with status 400 (Bad Request) if the crew has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/crews")
    @Timed
    public ResponseEntity<CrewDTO> createCrew(@Valid @RequestBody CrewDTO crewDTO) throws URISyntaxException {
        log.debug("REST request to save Crew : {}", crewDTO);
        if (crewDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new crew cannot already have an ID")).body(null);
        }
        CrewDTO result = crewService.save(crewDTO);
        return ResponseEntity.created(new URI("/api/crews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /crews : Updates an existing crew.
     *
     * @param crewDTO the crewDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated crewDTO,
     * or with status 400 (Bad Request) if the crewDTO is not valid,
     * or with status 500 (Internal Server Error) if the crewDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/crews")
    @Timed
    public ResponseEntity<CrewDTO> updateCrew(@Valid @RequestBody CrewDTO crewDTO) throws URISyntaxException {
        log.debug("REST request to update Crew : {}", crewDTO);
        if (crewDTO.getId() == null) {
            return createCrew(crewDTO);
        }
        CrewDTO result = crewService.save(crewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, crewDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /crews : get all the crews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of crews in body
     */
    @GetMapping("/crews")
    @Timed
    public List<CrewDTO> getAllCrews(@RequestParam Sex sex) {
        log.debug("REST request to get all Crews for sex: {}", sex);
        return crewService.findAllForSex(sex);
    }

    /**
     * GET  /crews/:id : get the "id" crew.
     *
     * @param id the id of the crewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the crewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/crews/{id}")
    @Timed
    public ResponseEntity<CrewDTO> getCrew(@PathVariable Long id) {
        log.debug("REST request to get Crew : {}", id);
        CrewDTO crewDTO = crewService.findOneDTO(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(crewDTO));
    }

    /**
     * DELETE  /crews/:id : delete the "id" crew.
     *
     * @param id the id of the crewDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/crews/{id}")
    @Timed
    public ResponseEntity<Void> deleteCrew(@PathVariable Long id) {
        log.debug("REST request to delete Crew : {}", id);
        crewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
