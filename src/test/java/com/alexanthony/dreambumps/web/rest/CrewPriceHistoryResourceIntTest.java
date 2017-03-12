package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.CrewPriceHistory;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.repository.CrewPriceHistoryRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static com.alexanthony.dreambumps.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CrewPriceHistoryResource REST controller.
 *
 * @see CrewPriceHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class CrewPriceHistoryResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(1);

    @Autowired
    private CrewPriceHistoryRepository crewPriceHistoryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCrewPriceHistoryMockMvc;

    private CrewPriceHistory crewPriceHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//            CrewPriceHistoryResource crewPriceHistoryResource = new CrewPriceHistoryResource(crewPriceHistoryRepository);
//        this.restCrewPriceHistoryMockMvc = MockMvcBuilders.standaloneSetup(crewPriceHistoryResource)
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
    public static CrewPriceHistory createEntity(EntityManager em) {
        CrewPriceHistory crewPriceHistory = new CrewPriceHistory()
                .dateTime(DEFAULT_DATE_TIME)
                .price(DEFAULT_PRICE);
        // Add required entity
        Crew crew = CrewResourceIntTest.createEntity(em);
        em.persist(crew);
        em.flush();
        crewPriceHistory.setCrew(crew);
        return crewPriceHistory;
    }

    @Before
    public void initTest() {
        crewPriceHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCrewPriceHistory() throws Exception {
        int databaseSizeBeforeCreate = crewPriceHistoryRepository.findAll().size();

        // Create the CrewPriceHistory

        restCrewPriceHistoryMockMvc.perform(post("/api/crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPriceHistory)))
            .andExpect(status().isCreated());

        // Validate the CrewPriceHistory in the database
        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        CrewPriceHistory testCrewPriceHistory = crewPriceHistoryList.get(crewPriceHistoryList.size() - 1);
        assertThat(testCrewPriceHistory.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testCrewPriceHistory.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createCrewPriceHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = crewPriceHistoryRepository.findAll().size();

        // Create the CrewPriceHistory with an existing ID
        CrewPriceHistory existingCrewPriceHistory = new CrewPriceHistory();
        existingCrewPriceHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrewPriceHistoryMockMvc.perform(post("/api/crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCrewPriceHistory)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewPriceHistoryRepository.findAll().size();
        // set the field null
        crewPriceHistory.setDateTime(null);

        // Create the CrewPriceHistory, which fails.

        restCrewPriceHistoryMockMvc.perform(post("/api/crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPriceHistory)))
            .andExpect(status().isBadRequest());

        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewPriceHistoryRepository.findAll().size();
        // set the field null
        crewPriceHistory.setPrice(null);

        // Create the CrewPriceHistory, which fails.

        restCrewPriceHistoryMockMvc.perform(post("/api/crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPriceHistory)))
            .andExpect(status().isBadRequest());

        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCrewPriceHistories() throws Exception {
        // Initialize the database
        crewPriceHistoryRepository.saveAndFlush(crewPriceHistory);

        // Get all the crewPriceHistoryList
        restCrewPriceHistoryMockMvc.perform(get("/api/crew-price-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crewPriceHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getCrewPriceHistory() throws Exception {
        // Initialize the database
        crewPriceHistoryRepository.saveAndFlush(crewPriceHistory);

        // Get the crewPriceHistory
        restCrewPriceHistoryMockMvc.perform(get("/api/crew-price-histories/{id}", crewPriceHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(crewPriceHistory.getId().intValue()))
            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCrewPriceHistory() throws Exception {
        // Get the crewPriceHistory
        restCrewPriceHistoryMockMvc.perform(get("/api/crew-price-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCrewPriceHistory() throws Exception {
        // Initialize the database
        crewPriceHistoryRepository.saveAndFlush(crewPriceHistory);
        int databaseSizeBeforeUpdate = crewPriceHistoryRepository.findAll().size();

        // Update the crewPriceHistory
        CrewPriceHistory updatedCrewPriceHistory = crewPriceHistoryRepository.findOne(crewPriceHistory.getId());
        updatedCrewPriceHistory
                .dateTime(UPDATED_DATE_TIME)
                .price(UPDATED_PRICE);

        restCrewPriceHistoryMockMvc.perform(put("/api/crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCrewPriceHistory)))
            .andExpect(status().isOk());

        // Validate the CrewPriceHistory in the database
        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeUpdate);
        CrewPriceHistory testCrewPriceHistory = crewPriceHistoryList.get(crewPriceHistoryList.size() - 1);
        assertThat(testCrewPriceHistory.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testCrewPriceHistory.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingCrewPriceHistory() throws Exception {
        int databaseSizeBeforeUpdate = crewPriceHistoryRepository.findAll().size();

        // Create the CrewPriceHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCrewPriceHistoryMockMvc.perform(put("/api/crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewPriceHistory)))
            .andExpect(status().isCreated());

        // Validate the CrewPriceHistory in the database
        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCrewPriceHistory() throws Exception {
        // Initialize the database
        crewPriceHistoryRepository.saveAndFlush(crewPriceHistory);
        int databaseSizeBeforeDelete = crewPriceHistoryRepository.findAll().size();

        // Get the crewPriceHistory
        restCrewPriceHistoryMockMvc.perform(delete("/api/crew-price-histories/{id}", crewPriceHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CrewPriceHistory> crewPriceHistoryList = crewPriceHistoryRepository.findAll();
        assertThat(crewPriceHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrewPriceHistory.class);
    }
}
