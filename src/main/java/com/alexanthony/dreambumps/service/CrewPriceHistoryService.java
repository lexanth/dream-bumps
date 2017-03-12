package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewPriceHistory;
import com.alexanthony.dreambumps.repository.CrewPriceHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing CrewPriceHistory.
 */
@Service
@Transactional
public class CrewPriceHistoryService {

    private final Logger log = LoggerFactory.getLogger(CrewPriceHistoryService.class);
    
    private final CrewPriceHistoryRepository crewPriceHistoryRepository;

    public CrewPriceHistoryService(CrewPriceHistoryRepository crewPriceHistoryRepository) {
        this.crewPriceHistoryRepository = crewPriceHistoryRepository;
    }

    /**
     * Save a crewPriceHistory.
     *
     * @param crewPriceHistory the entity to save
     * @return the persisted entity
     */
    public CrewPriceHistory save(CrewPriceHistory crewPriceHistory) {
        log.debug("Request to save CrewPriceHistory : {}", crewPriceHistory);
        CrewPriceHistory result = crewPriceHistoryRepository.save(crewPriceHistory);
        return result;
    }

    /**
     *  Get all the crewPriceHistories.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CrewPriceHistory> findAll() {
        log.debug("Request to get all CrewPriceHistories");
        List<CrewPriceHistory> result = crewPriceHistoryRepository.findAll();

        return result;
    }

    /**
     *  Get one crewPriceHistory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CrewPriceHistory findOne(Long id) {
        log.debug("Request to get CrewPriceHistory : {}", id);
        CrewPriceHistory crewPriceHistory = crewPriceHistoryRepository.findOne(id);
        return crewPriceHistory;
    }

    /**
     *  Delete the  crewPriceHistory by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CrewPriceHistory : {}", id);
        crewPriceHistoryRepository.delete(id);
    }

    @Transactional(readOnly = true)
	public List<CrewPriceHistory> findAllByCrew(Long crewId) {
      log.debug("Request to get CrewPriceHistory for : {}", crewId);
      return crewPriceHistoryRepository.findByCrewId(crewId);
	}

    public CrewPriceHistory findOldestForCrew(Long crewId) {
      // TODO Do this better
      return crewPriceHistoryRepository.findTop1ByCrewIdOrderByDateTimeAsc(crewId);
    }

    public void createForCrew(Crew crew) {
      CrewPriceHistory crewPriceHistory = new CrewPriceHistory();
      crewPriceHistory.setCrew(crew);
      crewPriceHistory.setPrice(crew.getPrice());
      crewPriceHistory.setDateTime(ZonedDateTime.now());
      save(crewPriceHistory);
    }
}
