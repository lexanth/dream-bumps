package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.UserCrewPositionHistory;
import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.repository.UserCrewPositionHistoryRepository;
import com.alexanthony.dreambumps.service.UserCrewRacingHistoryService;
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

import com.alexanthony.dreambumps.domain.enumeration.Sex;
/**
 * Test class for the UserCrewPositionHistoryResource REST controller.
 *
 * @see UserCrewPositionHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class UserCrewPositionHistoryResourceIntTest {

    private static final Sex DEFAULT_SEX = Sex.male;
    private static final Sex UPDATED_SEX = Sex.female;

    private static final Integer DEFAULT_DAY = 1;
    private static final Integer UPDATED_DAY = 2;

    private static final Integer DEFAULT_SEAT = 1;
    private static final Integer UPDATED_SEAT = 2;

    private static final Integer DEFAULT_BUMPS = 1;
    private static final Integer UPDATED_BUMPS = 2;

    private static final BigDecimal DEFAULT_DIVIDEND = new BigDecimal(0);
    private static final BigDecimal UPDATED_DIVIDEND = new BigDecimal(1);

    @Autowired
    private UserCrewPositionHistoryRepository userCrewPositionHistoryRepository;

    @Autowired
    private UserCrewRacingHistoryService userCrewPositionHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserCrewPositionHistoryMockMvc;

    private UserCrewPositionHistory userCrewPositionHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserCrewPositionHistoryResource userCrewPositionHistoryResource = new UserCrewPositionHistoryResource(userCrewPositionHistoryService);
        this.restUserCrewPositionHistoryMockMvc = MockMvcBuilders.standaloneSetup(userCrewPositionHistoryResource)
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
    public static UserCrewPositionHistory createEntity(EntityManager em) {
        UserCrewPositionHistory userCrewPositionHistory = new UserCrewPositionHistory()
                .sex(DEFAULT_SEX)
                .day(DEFAULT_DAY)
                .seat(DEFAULT_SEAT)
                .bumps(DEFAULT_BUMPS)
                .dividend(DEFAULT_DIVIDEND);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        userCrewPositionHistory.setUser(user);
        // Add required entity
        Crew crew = CrewResourceIntTest.createEntity(em);
        em.persist(crew);
        em.flush();
        userCrewPositionHistory.setCrew(crew);
        return userCrewPositionHistory;
    }

    @Before
    public void initTest() {
        userCrewPositionHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCrewPositionHistory() throws Exception {
        int databaseSizeBeforeCreate = userCrewPositionHistoryRepository.findAll().size();

        // Create the UserCrewPositionHistory

        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isCreated());

        // Validate the UserCrewPositionHistory in the database
        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        UserCrewPositionHistory testUserCrewPositionHistory = userCrewPositionHistoryList.get(userCrewPositionHistoryList.size() - 1);
        assertThat(testUserCrewPositionHistory.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testUserCrewPositionHistory.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testUserCrewPositionHistory.getSeat()).isEqualTo(DEFAULT_SEAT);
        assertThat(testUserCrewPositionHistory.getBumps()).isEqualTo(DEFAULT_BUMPS);
        assertThat(testUserCrewPositionHistory.getDividend()).isEqualTo(DEFAULT_DIVIDEND);
    }

    @Test
    @Transactional
    public void createUserCrewPositionHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCrewPositionHistoryRepository.findAll().size();

        // Create the UserCrewPositionHistory with an existing ID
        UserCrewPositionHistory existingUserCrewPositionHistory = new UserCrewPositionHistory();
        existingUserCrewPositionHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPositionHistoryRepository.findAll().size();
        // set the field null
        userCrewPositionHistory.setSex(null);

        // Create the UserCrewPositionHistory, which fails.

        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPositionHistoryRepository.findAll().size();
        // set the field null
        userCrewPositionHistory.setDay(null);

        // Create the UserCrewPositionHistory, which fails.

        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeatIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPositionHistoryRepository.findAll().size();
        // set the field null
        userCrewPositionHistory.setSeat(null);

        // Create the UserCrewPositionHistory, which fails.

        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBumpsIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPositionHistoryRepository.findAll().size();
        // set the field null
        userCrewPositionHistory.setBumps(null);

        // Create the UserCrewPositionHistory, which fails.

        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDividendIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPositionHistoryRepository.findAll().size();
        // set the field null
        userCrewPositionHistory.setDividend(null);

        // Create the UserCrewPositionHistory, which fails.

        restUserCrewPositionHistoryMockMvc.perform(post("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserCrewPositionHistories() throws Exception {
        // Initialize the database
        userCrewPositionHistoryRepository.saveAndFlush(userCrewPositionHistory);

        // Get all the userCrewPositionHistoryList
        restUserCrewPositionHistoryMockMvc.perform(get("/api/user-crew-position-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCrewPositionHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].seat").value(hasItem(DEFAULT_SEAT)))
            .andExpect(jsonPath("$.[*].bumps").value(hasItem(DEFAULT_BUMPS)))
            .andExpect(jsonPath("$.[*].dividend").value(hasItem(DEFAULT_DIVIDEND.intValue())));
    }

    @Test
    @Transactional
    public void getUserCrewPositionHistory() throws Exception {
        // Initialize the database
        userCrewPositionHistoryRepository.saveAndFlush(userCrewPositionHistory);

        // Get the userCrewPositionHistory
        restUserCrewPositionHistoryMockMvc.perform(get("/api/user-crew-position-histories/{id}", userCrewPositionHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userCrewPositionHistory.getId().intValue()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY))
            .andExpect(jsonPath("$.seat").value(DEFAULT_SEAT))
            .andExpect(jsonPath("$.bumps").value(DEFAULT_BUMPS))
            .andExpect(jsonPath("$.dividend").value(DEFAULT_DIVIDEND.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserCrewPositionHistory() throws Exception {
        // Get the userCrewPositionHistory
        restUserCrewPositionHistoryMockMvc.perform(get("/api/user-crew-position-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCrewPositionHistory() throws Exception {
        // Initialize the database
        userCrewPositionHistoryService.save(userCrewPositionHistory);

        int databaseSizeBeforeUpdate = userCrewPositionHistoryRepository.findAll().size();

        // Update the userCrewPositionHistory
        UserCrewPositionHistory updatedUserCrewPositionHistory = userCrewPositionHistoryRepository.findOne(userCrewPositionHistory.getId());
        updatedUserCrewPositionHistory
                .sex(UPDATED_SEX)
                .day(UPDATED_DAY)
                .seat(UPDATED_SEAT)
                .bumps(UPDATED_BUMPS)
                .dividend(UPDATED_DIVIDEND);

        restUserCrewPositionHistoryMockMvc.perform(put("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserCrewPositionHistory)))
            .andExpect(status().isOk());

        // Validate the UserCrewPositionHistory in the database
        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeUpdate);
        UserCrewPositionHistory testUserCrewPositionHistory = userCrewPositionHistoryList.get(userCrewPositionHistoryList.size() - 1);
        assertThat(testUserCrewPositionHistory.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testUserCrewPositionHistory.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testUserCrewPositionHistory.getSeat()).isEqualTo(UPDATED_SEAT);
        assertThat(testUserCrewPositionHistory.getBumps()).isEqualTo(UPDATED_BUMPS);
        assertThat(testUserCrewPositionHistory.getDividend()).isEqualTo(UPDATED_DIVIDEND);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCrewPositionHistory() throws Exception {
        int databaseSizeBeforeUpdate = userCrewPositionHistoryRepository.findAll().size();

        // Create the UserCrewPositionHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserCrewPositionHistoryMockMvc.perform(put("/api/user-crew-position-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPositionHistory)))
            .andExpect(status().isCreated());

        // Validate the UserCrewPositionHistory in the database
        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserCrewPositionHistory() throws Exception {
        // Initialize the database
        userCrewPositionHistoryService.save(userCrewPositionHistory);

        int databaseSizeBeforeDelete = userCrewPositionHistoryRepository.findAll().size();

        // Get the userCrewPositionHistory
        restUserCrewPositionHistoryMockMvc.perform(delete("/api/user-crew-position-histories/{id}", userCrewPositionHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserCrewPositionHistory> userCrewPositionHistoryList = userCrewPositionHistoryRepository.findAll();
        assertThat(userCrewPositionHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCrewPositionHistory.class);
    }
}
