package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.repository.CrewPositionHistoryRepository;
import com.alexanthony.dreambumps.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CrewPositionHistoryResource REST controller.
 *
 * @see CrewPositionHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class CrewPositionHistoryResourceIntTest {

    private static final Integer DEFAULT_DAY = 0;
    private static final Integer UPDATED_DAY = 1;

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final Integer DEFAULT_BUMPS = 1;
    private static final Integer UPDATED_BUMPS = 2;

    private static final BigDecimal DEFAULT_DIVIDEND = new BigDecimal(0);
    private static final BigDecimal UPDATED_DIVIDEND = new BigDecimal(1);

    @Autowired
    private CrewPositionHistoryRepository crewPositionHistoryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCrewPositionHistoryMockMvc;

    private CrewPositionHistory crewPositionHistory;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
//            CrewPositionHistoryResource crewPositionHistoryResource = new CrewPositionHistoryResource(crewPositionHistoryService);
//        this.restCrewPositionHistoryMockMvc = MockMvcBuilders.standaloneSetup(crewPositionHistoryResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CrewPositionHistory createEntity(EntityManager em) {
        CrewPositionHistory crewPositionHistory = new CrewPositionHistory()
                .day(DEFAULT_DAY)
                .position(DEFAULT_POSITION)
                .bumps(DEFAULT_BUMPS)
                .dividend(DEFAULT_DIVIDEND);
        // Add required entity
        Crew crew = CrewResourceIntTest.createEntity(em);
        em.persist(crew);
        em.flush();
        crewPositionHistory.setCrew(crew);
        return crewPositionHistory;
    }

    @Before
    public void initTest() {
        crewPositionHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCrewPositionHistory() throws Exception {
        int databaseSizeBeforeCreate = crewPositionHistoryRepository.findAll().size();

        // Create the CrewPositionHistory

        restCrewPositionHistoryMockMvc.perform(post("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPositionHistory)))
            .andExpect(status().isCreated());

        // Validate the CrewPositionHistory in the database
        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        CrewPositionHistory testCrewPositionHistory = crewPositionHistoryList.get(crewPositionHistoryList.size() - 1);
        assertThat(testCrewPositionHistory.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testCrewPositionHistory.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testCrewPositionHistory.getBumps()).isEqualTo(DEFAULT_BUMPS);
        assertThat(testCrewPositionHistory.getDividend()).isEqualTo(DEFAULT_DIVIDEND);
    }

    @Test
    @Transactional
    public void createCrewPositionHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = crewPositionHistoryRepository.findAll().size();

        // Create the CrewPositionHistory with an existing ID
        CrewPositionHistory existingCrewPositionHistory = new CrewPositionHistory();
        existingCrewPositionHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrewPositionHistoryMockMvc.perform(post("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewPositionHistoryRepository.findAll().size();
        // set the field null
        crewPositionHistory.setDay(null);

        // Create the CrewPositionHistory, which fails.

        restCrewPositionHistoryMockMvc.perform(post("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewPositionHistoryRepository.findAll().size();
        // set the field null
        crewPositionHistory.setPosition(null);

        // Create the CrewPositionHistory, which fails.

        restCrewPositionHistoryMockMvc.perform(post("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBumpsIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewPositionHistoryRepository.findAll().size();
        // set the field null
        crewPositionHistory.setBumps(null);

        // Create the CrewPositionHistory, which fails.

        restCrewPositionHistoryMockMvc.perform(post("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDividendIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewPositionHistoryRepository.findAll().size();
        // set the field null
        crewPositionHistory.setDividend(null);

        // Create the CrewPositionHistory, which fails.

        restCrewPositionHistoryMockMvc.perform(post("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCrewPositionHistories() throws Exception {
        // Initialize the database
        crewPositionHistoryRepository.saveAndFlush(crewPositionHistory);

        // Get all the crewPositionHistoryList
        restCrewPositionHistoryMockMvc.perform(get("/api/crew-position-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crewPositionHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].bumps").value(hasItem(DEFAULT_BUMPS)))
            .andExpect(jsonPath("$.[*].dividend").value(hasItem(DEFAULT_DIVIDEND.intValue())));
    }

    @Test
    @Transactional
    public void getCrewPositionHistory() throws Exception {
        // Initialize the database
        crewPositionHistoryRepository.saveAndFlush(crewPositionHistory);

        // Get the crewPositionHistory
        restCrewPositionHistoryMockMvc.perform(get("/api/crew-position-histories/{id}", crewPositionHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(crewPositionHistory.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.bumps").value(DEFAULT_BUMPS))
            .andExpect(jsonPath("$.dividend").value(DEFAULT_DIVIDEND.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCrewPositionHistory() throws Exception {
        // Get the crewPositionHistory
        restCrewPositionHistoryMockMvc.perform(get("/api/crew-position-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCrewPositionHistory() throws Exception {
        // Initialize the database
        crewPositionHistoryRepository.saveAndFlush(crewPositionHistory);
        int databaseSizeBeforeUpdate = crewPositionHistoryRepository.findAll().size();

        // Update the crewPositionHistory
        CrewPositionHistory updatedCrewPositionHistory = crewPositionHistoryRepository.findOne(crewPositionHistory.getId());
        updatedCrewPositionHistory
                .day(UPDATED_DAY)
                .position(UPDATED_POSITION)
                .bumps(UPDATED_BUMPS)
                .dividend(UPDATED_DIVIDEND);

        restCrewPositionHistoryMockMvc.perform(put("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCrewPositionHistory)))
            .andExpect(status().isOk());

        // Validate the CrewPositionHistory in the database
        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeUpdate);
        CrewPositionHistory testCrewPositionHistory = crewPositionHistoryList.get(crewPositionHistoryList.size() - 1);
        assertThat(testCrewPositionHistory.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testCrewPositionHistory.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testCrewPositionHistory.getBumps()).isEqualTo(UPDATED_BUMPS);
        assertThat(testCrewPositionHistory.getDividend()).isEqualTo(UPDATED_DIVIDEND);
    }

    @Test
    @Transactional
    public void updateNonExistingCrewPositionHistory() throws Exception {
        int databaseSizeBeforeUpdate = crewPositionHistoryRepository.findAll().size();

        // Create the CrewPositionHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCrewPositionHistoryMockMvc.perform(put("/api/crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPositionHistory)))
            .andExpect(status().isCreated());

        // Validate the CrewPositionHistory in the database
        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCrewPositionHistory() throws Exception {
        // Initialize the database
        crewPositionHistoryRepository.saveAndFlush(crewPositionHistory);
        int databaseSizeBeforeDelete = crewPositionHistoryRepository.findAll().size();

        // Get the crewPositionHistory
        restCrewPositionHistoryMockMvc.perform(delete("/api/crew-position-histories/{id}", crewPositionHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CrewPositionHistory> crewPositionHistoryList = crewPositionHistoryRepository.findAll();
        assertThat(crewPositionHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrewPositionHistory.class);
    }
}
