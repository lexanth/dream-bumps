package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.UserCrewPriceHistory;
import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.repository.UserCrewPriceHistoryRepository;
import com.alexanthony.dreambumps.service.UserCrewPriceHistoryService;
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

import com.alexanthony.dreambumps.domain.enumeration.Sex;
/**
 * Test class for the UserCrewPriceHistoryResource REST controller.
 *
 * @see UserCrewPriceHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class UserCrewPriceHistoryResourceIntTest {

    private static final Sex DEFAULT_SEX = Sex.male;
    private static final Sex UPDATED_SEX = Sex.female;

    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALUE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASH = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASH = new BigDecimal(2);

    @Autowired
    private UserCrewPriceHistoryRepository userCrewPriceHistoryRepository;

    @Autowired
    private UserCrewPriceHistoryService userCrewPriceHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserCrewPriceHistoryMockMvc;

    private UserCrewPriceHistory userCrewPriceHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserCrewPriceHistoryResource userCrewPriceHistoryResource = new UserCrewPriceHistoryResource(userCrewPriceHistoryService);
        this.restUserCrewPriceHistoryMockMvc = MockMvcBuilders.standaloneSetup(userCrewPriceHistoryResource)
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
    public static UserCrewPriceHistory createEntity(EntityManager em) {
        UserCrewPriceHistory userCrewPriceHistory = new UserCrewPriceHistory()
                .sex(DEFAULT_SEX)
                .dateTime(DEFAULT_DATE_TIME)
                .value(DEFAULT_VALUE)
                .cash(DEFAULT_CASH);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        userCrewPriceHistory.setUser(user);
        return userCrewPriceHistory;
    }

    @Before
    public void initTest() {
        userCrewPriceHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCrewPriceHistory() throws Exception {
        int databaseSizeBeforeCreate = userCrewPriceHistoryRepository.findAll().size();

        // Create the UserCrewPriceHistory

        restUserCrewPriceHistoryMockMvc.perform(post("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPriceHistory)))
            .andExpect(status().isCreated());

        // Validate the UserCrewPriceHistory in the database
        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        UserCrewPriceHistory testUserCrewPriceHistory = userCrewPriceHistoryList.get(userCrewPriceHistoryList.size() - 1);
        assertThat(testUserCrewPriceHistory.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testUserCrewPriceHistory.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testUserCrewPriceHistory.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testUserCrewPriceHistory.getCash()).isEqualTo(DEFAULT_CASH);
    }

    @Test
    @Transactional
    public void createUserCrewPriceHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCrewPriceHistoryRepository.findAll().size();

        // Create the UserCrewPriceHistory with an existing ID
        UserCrewPriceHistory existingUserCrewPriceHistory = new UserCrewPriceHistory();
        existingUserCrewPriceHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCrewPriceHistoryMockMvc.perform(post("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserCrewPriceHistory)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceHistoryRepository.findAll().size();
        // set the field null
        userCrewPriceHistory.setSex(null);

        // Create the UserCrewPriceHistory, which fails.

        restUserCrewPriceHistoryMockMvc.perform(post("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPriceHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceHistoryRepository.findAll().size();
        // set the field null
        userCrewPriceHistory.setDateTime(null);

        // Create the UserCrewPriceHistory, which fails.

        restUserCrewPriceHistoryMockMvc.perform(post("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPriceHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceHistoryRepository.findAll().size();
        // set the field null
        userCrewPriceHistory.setValue(null);

        // Create the UserCrewPriceHistory, which fails.

        restUserCrewPriceHistoryMockMvc.perform(post("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPriceHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCashIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceHistoryRepository.findAll().size();
        // set the field null
        userCrewPriceHistory.setCash(null);

        // Create the UserCrewPriceHistory, which fails.

        restUserCrewPriceHistoryMockMvc.perform(post("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPriceHistory)))
            .andExpect(status().isBadRequest());

        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserCrewPriceHistories() throws Exception {
        // Initialize the database
        userCrewPriceHistoryRepository.saveAndFlush(userCrewPriceHistory);

        // Get all the userCrewPriceHistoryList
        restUserCrewPriceHistoryMockMvc.perform(get("/api/user-crew-price-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCrewPriceHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].cash").value(hasItem(DEFAULT_CASH.intValue())));
    }

    @Test
    @Transactional
    public void getUserCrewPriceHistory() throws Exception {
        // Initialize the database
        userCrewPriceHistoryRepository.saveAndFlush(userCrewPriceHistory);

        // Get the userCrewPriceHistory
        restUserCrewPriceHistoryMockMvc.perform(get("/api/user-crew-price-histories/{id}", userCrewPriceHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userCrewPriceHistory.getId().intValue()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.intValue()))
            .andExpect(jsonPath("$.cash").value(DEFAULT_CASH.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserCrewPriceHistory() throws Exception {
        // Get the userCrewPriceHistory
        restUserCrewPriceHistoryMockMvc.perform(get("/api/user-crew-price-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCrewPriceHistory() throws Exception {
        // Initialize the database
        userCrewPriceHistoryService.save(userCrewPriceHistory);

        int databaseSizeBeforeUpdate = userCrewPriceHistoryRepository.findAll().size();

        // Update the userCrewPriceHistory
        UserCrewPriceHistory updatedUserCrewPriceHistory = userCrewPriceHistoryRepository.findOne(userCrewPriceHistory.getId());
        updatedUserCrewPriceHistory
                .sex(UPDATED_SEX)
                .dateTime(UPDATED_DATE_TIME)
                .value(UPDATED_VALUE)
                .cash(UPDATED_CASH);

        restUserCrewPriceHistoryMockMvc.perform(put("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserCrewPriceHistory)))
            .andExpect(status().isOk());

        // Validate the UserCrewPriceHistory in the database
        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeUpdate);
        UserCrewPriceHistory testUserCrewPriceHistory = userCrewPriceHistoryList.get(userCrewPriceHistoryList.size() - 1);
        assertThat(testUserCrewPriceHistory.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testUserCrewPriceHistory.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testUserCrewPriceHistory.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testUserCrewPriceHistory.getCash()).isEqualTo(UPDATED_CASH);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCrewPriceHistory() throws Exception {
        int databaseSizeBeforeUpdate = userCrewPriceHistoryRepository.findAll().size();

        // Create the UserCrewPriceHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserCrewPriceHistoryMockMvc.perform(put("/api/user-crew-price-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPriceHistory)))
            .andExpect(status().isCreated());

        // Validate the UserCrewPriceHistory in the database
        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserCrewPriceHistory() throws Exception {
        // Initialize the database
        userCrewPriceHistoryService.save(userCrewPriceHistory);

        int databaseSizeBeforeDelete = userCrewPriceHistoryRepository.findAll().size();

        // Get the userCrewPriceHistory
        restUserCrewPriceHistoryMockMvc.perform(delete("/api/user-crew-price-histories/{id}", userCrewPriceHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserCrewPriceHistory> userCrewPriceHistoryList = userCrewPriceHistoryRepository.findAll();
        assertThat(userCrewPriceHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCrewPriceHistory.class);
    }
}
