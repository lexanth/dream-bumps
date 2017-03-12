package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.repository.UserCrewPriceRepository;
import com.alexanthony.dreambumps.service.UserCrewPriceService;
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
 * Test class for the UserCrewPriceResource REST controller.
 *
 * @see UserCrewPriceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class UserCrewPriceResourceIntTest {

    private static final Sex DEFAULT_SEX = Sex.male;
    private static final Sex UPDATED_SEX = Sex.female;

    private static final BigDecimal DEFAULT_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALUE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASH = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASH = new BigDecimal(2);

    @Autowired
    private UserCrewPriceRepository userCrewPriceRepository;

    @Autowired
    private UserCrewPriceService userCrewPriceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserCrewPriceMockMvc;

    private UserCrewPrice userCrewPrice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserCrewPriceResource userCrewPriceResource = new UserCrewPriceResource(userCrewPriceService);
        this.restUserCrewPriceMockMvc = MockMvcBuilders.standaloneSetup(userCrewPriceResource)
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
    public static UserCrewPrice createEntity(EntityManager em) {
        UserCrewPrice userCrewPrice = new UserCrewPrice()
                .sex(DEFAULT_SEX)
                .value(DEFAULT_VALUE)
                .cash(DEFAULT_CASH);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        userCrewPrice.setUser(user);
        return userCrewPrice;
    }

    @Before
    public void initTest() {
        userCrewPrice = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCrewPrice() throws Exception {
        int databaseSizeBeforeCreate = userCrewPriceRepository.findAll().size();

        // Create the UserCrewPrice

        restUserCrewPriceMockMvc.perform(post("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPrice)))
            .andExpect(status().isCreated());

        // Validate the UserCrewPrice in the database
        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeCreate + 1);
        UserCrewPrice testUserCrewPrice = userCrewPriceList.get(userCrewPriceList.size() - 1);
        assertThat(testUserCrewPrice.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testUserCrewPrice.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testUserCrewPrice.getCash()).isEqualTo(DEFAULT_CASH);
    }

    @Test
    @Transactional
    public void createUserCrewPriceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCrewPriceRepository.findAll().size();

        // Create the UserCrewPrice with an existing ID
        UserCrewPrice existingUserCrewPrice = new UserCrewPrice();
        existingUserCrewPrice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCrewPriceMockMvc.perform(post("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserCrewPrice)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceRepository.findAll().size();
        // set the field null
        userCrewPrice.setSex(null);

        // Create the UserCrewPrice, which fails.

        restUserCrewPriceMockMvc.perform(post("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPrice)))
            .andExpect(status().isBadRequest());

        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceRepository.findAll().size();
        // set the field null
        userCrewPrice.setValue(null);

        // Create the UserCrewPrice, which fails.

        restUserCrewPriceMockMvc.perform(post("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPrice)))
            .andExpect(status().isBadRequest());

        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCashIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewPriceRepository.findAll().size();
        // set the field null
        userCrewPrice.setCash(null);

        // Create the UserCrewPrice, which fails.

        restUserCrewPriceMockMvc.perform(post("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPrice)))
            .andExpect(status().isBadRequest());

        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserCrewPrices() throws Exception {
        // Initialize the database
        userCrewPriceRepository.saveAndFlush(userCrewPrice);

        // Get all the userCrewPriceList
        restUserCrewPriceMockMvc.perform(get("/api/user-crew-prices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCrewPrice.getId().intValue())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].cash").value(hasItem(DEFAULT_CASH.intValue())));
    }

    @Test
    @Transactional
    public void getUserCrewPrice() throws Exception {
        // Initialize the database
        userCrewPriceRepository.saveAndFlush(userCrewPrice);

        // Get the userCrewPrice
        restUserCrewPriceMockMvc.perform(get("/api/user-crew-prices/{id}", userCrewPrice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userCrewPrice.getId().intValue()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.intValue()))
            .andExpect(jsonPath("$.cash").value(DEFAULT_CASH.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserCrewPrice() throws Exception {
        // Get the userCrewPrice
        restUserCrewPriceMockMvc.perform(get("/api/user-crew-prices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCrewPrice() throws Exception {
        // Initialize the database
        userCrewPriceService.save(userCrewPrice);

        int databaseSizeBeforeUpdate = userCrewPriceRepository.findAll().size();

        // Update the userCrewPrice
        UserCrewPrice updatedUserCrewPrice = userCrewPriceRepository.findOne(userCrewPrice.getId());
        updatedUserCrewPrice
                .sex(UPDATED_SEX)
                .value(UPDATED_VALUE)
                .cash(UPDATED_CASH);

        restUserCrewPriceMockMvc.perform(put("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserCrewPrice)))
            .andExpect(status().isOk());

        // Validate the UserCrewPrice in the database
        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeUpdate);
        UserCrewPrice testUserCrewPrice = userCrewPriceList.get(userCrewPriceList.size() - 1);
        assertThat(testUserCrewPrice.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testUserCrewPrice.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testUserCrewPrice.getCash()).isEqualTo(UPDATED_CASH);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCrewPrice() throws Exception {
        int databaseSizeBeforeUpdate = userCrewPriceRepository.findAll().size();

        // Create the UserCrewPrice

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserCrewPriceMockMvc.perform(put("/api/user-crew-prices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewPrice)))
            .andExpect(status().isCreated());

        // Validate the UserCrewPrice in the database
        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserCrewPrice() throws Exception {
        // Initialize the database
        userCrewPriceService.save(userCrewPrice);

        int databaseSizeBeforeDelete = userCrewPriceRepository.findAll().size();

        // Get the userCrewPrice
        restUserCrewPriceMockMvc.perform(delete("/api/user-crew-prices/{id}", userCrewPrice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserCrewPrice> userCrewPriceList = userCrewPriceRepository.findAll();
        assertThat(userCrewPriceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCrewPrice.class);
    }
}
