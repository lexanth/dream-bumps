package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.MarketStatusHistory;
import com.alexanthony.dreambumps.repository.MarketStatusHistoryRepository;
import com.alexanthony.dreambumps.service.MarketStatusHistoryService;
import com.alexanthony.dreambumps.service.dto.MarketStatusHistoryDTO;
import com.alexanthony.dreambumps.service.mapper.MarketStatusHistoryMapper;
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
import java.util.List;

import static com.alexanthony.dreambumps.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MarketStatusHistoryResource REST controller.
 *
 * @see MarketStatusHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class MarketStatusHistoryResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_DAY = 0;
    private static final Integer UPDATED_DAY = 1;

    private static final Boolean DEFAULT_OPEN = false;
    private static final Boolean UPDATED_OPEN = true;

    @Autowired
    private MarketStatusHistoryRepository marketStatusHistoryRepository;

    @Autowired
    private MarketStatusHistoryMapper marketStatusHistoryMapper;

    @Autowired
    private MarketStatusHistoryService marketStatusHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMarketStatusHistoryMockMvc;

    private MarketStatusHistory marketStatusHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MarketStatusHistoryResource marketStatusHistoryResource = new MarketStatusHistoryResource(marketStatusHistoryService);
        this.restMarketStatusHistoryMockMvc = MockMvcBuilders.standaloneSetup(marketStatusHistoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MarketStatusHistory createEntity(EntityManager em) {
        MarketStatusHistory marketStatusHistory = new MarketStatusHistory()
                .dateTime(DEFAULT_DATE_TIME)
                .day(DEFAULT_DAY)
                .open(DEFAULT_OPEN);
        return marketStatusHistory;
    }

    @Before
    public void initTest() {
        marketStatusHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createMarketStatusHistory() throws Exception {
        int databaseSizeBeforeCreate = marketStatusHistoryRepository.findAll().size();

        // Create the MarketStatusHistory
        MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);

        restMarketStatusHistoryMockMvc.perform(post("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketStatusHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the MarketStatusHistory in the database
        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        MarketStatusHistory testMarketStatusHistory = marketStatusHistoryList.get(marketStatusHistoryList.size() - 1);
        assertThat(testMarketStatusHistory.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testMarketStatusHistory.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testMarketStatusHistory.isOpen()).isEqualTo(DEFAULT_OPEN);
    }

    @Test
    @Transactional
    public void createMarketStatusHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marketStatusHistoryRepository.findAll().size();

        // Create the MarketStatusHistory with an existing ID
        MarketStatusHistory existingMarketStatusHistory = new MarketStatusHistory();
        existingMarketStatusHistory.setId(1L);
        MarketStatusHistoryDTO existingMarketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(existingMarketStatusHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarketStatusHistoryMockMvc.perform(post("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMarketStatusHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = marketStatusHistoryRepository.findAll().size();
        // set the field null
        marketStatusHistory.setDateTime(null);

        // Create the MarketStatusHistory, which fails.
        MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);

        restMarketStatusHistoryMockMvc.perform(post("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketStatusHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = marketStatusHistoryRepository.findAll().size();
        // set the field null
        marketStatusHistory.setDay(null);

        // Create the MarketStatusHistory, which fails.
        MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);

        restMarketStatusHistoryMockMvc.perform(post("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketStatusHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = marketStatusHistoryRepository.findAll().size();
        // set the field null
        marketStatusHistory.setOpen(null);

        // Create the MarketStatusHistory, which fails.
        MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);

        restMarketStatusHistoryMockMvc.perform(post("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketStatusHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMarketStatusHistories() throws Exception {
        // Initialize the database
        marketStatusHistoryRepository.saveAndFlush(marketStatusHistory);

        // Get all the marketStatusHistoryList
        restMarketStatusHistoryMockMvc.perform(get("/api/market-status-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(marketStatusHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].open").value(hasItem(DEFAULT_OPEN.booleanValue())));
    }

    @Test
    @Transactional
    public void getMarketStatusHistory() throws Exception {
        // Initialize the database
        marketStatusHistoryRepository.saveAndFlush(marketStatusHistory);

        // Get the marketStatusHistory
        restMarketStatusHistoryMockMvc.perform(get("/api/market-status-histories/{id}", marketStatusHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(marketStatusHistory.getId().intValue()))
            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY))
            .andExpect(jsonPath("$.open").value(DEFAULT_OPEN.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMarketStatusHistory() throws Exception {
        // Get the marketStatusHistory
        restMarketStatusHistoryMockMvc.perform(get("/api/market-status-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMarketStatusHistory() throws Exception {
        // Initialize the database
        marketStatusHistoryRepository.saveAndFlush(marketStatusHistory);
        int databaseSizeBeforeUpdate = marketStatusHistoryRepository.findAll().size();

        // Update the marketStatusHistory
        MarketStatusHistory updatedMarketStatusHistory = marketStatusHistoryRepository.findOne(marketStatusHistory.getId());
        updatedMarketStatusHistory
                .dateTime(UPDATED_DATE_TIME)
                .day(UPDATED_DAY)
                .open(UPDATED_OPEN);
        MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(updatedMarketStatusHistory);

        restMarketStatusHistoryMockMvc.perform(put("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketStatusHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the MarketStatusHistory in the database
        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeUpdate);
        MarketStatusHistory testMarketStatusHistory = marketStatusHistoryList.get(marketStatusHistoryList.size() - 1);
        assertThat(testMarketStatusHistory.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testMarketStatusHistory.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testMarketStatusHistory.isOpen()).isEqualTo(UPDATED_OPEN);
    }

    @Test
    @Transactional
    public void updateNonExistingMarketStatusHistory() throws Exception {
        int databaseSizeBeforeUpdate = marketStatusHistoryRepository.findAll().size();

        // Create the MarketStatusHistory
        MarketStatusHistoryDTO marketStatusHistoryDTO = marketStatusHistoryMapper.marketStatusHistoryToMarketStatusHistoryDTO(marketStatusHistory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMarketStatusHistoryMockMvc.perform(put("/api/market-status-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketStatusHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the MarketStatusHistory in the database
        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMarketStatusHistory() throws Exception {
        // Initialize the database
        marketStatusHistoryRepository.saveAndFlush(marketStatusHistory);
        int databaseSizeBeforeDelete = marketStatusHistoryRepository.findAll().size();

        // Get the marketStatusHistory
        restMarketStatusHistoryMockMvc.perform(delete("/api/market-status-histories/{id}", marketStatusHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MarketStatusHistory> marketStatusHistoryList = marketStatusHistoryRepository.findAll();
        assertThat(marketStatusHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketStatusHistory.class);
    }
}
