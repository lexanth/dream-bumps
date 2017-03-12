package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.UserCrewMember;
import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.repository.UserCrewMemberRepository;
import com.alexanthony.dreambumps.service.UserCrewMemberService;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;
import com.alexanthony.dreambumps.service.mapper.UserCrewMemberMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexanthony.dreambumps.domain.enumeration.Sex;
/**
 * Test class for the UserCrewMemberResource REST controller.
 *
 * @see UserCrewMemberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class UserCrewMemberResourceIntTest {

    private static final Sex DEFAULT_SEX = Sex.male;
    private static final Sex UPDATED_SEX = Sex.female;

    private static final Integer DEFAULT_SEAT = 1;
    private static final Integer UPDATED_SEAT = 2;

    @Autowired
    private UserCrewMemberRepository userCrewMemberRepository;

    @Autowired
    private UserCrewMemberMapper userCrewMemberMapper;

    @Autowired
    private UserCrewMemberService userCrewMemberService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserCrewMemberMockMvc;

    private UserCrewMember userCrewMember;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserCrewMemberResource userCrewMemberResource = new UserCrewMemberResource(userCrewMemberService);
        this.restUserCrewMemberMockMvc = MockMvcBuilders.standaloneSetup(userCrewMemberResource)
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
    public static UserCrewMember createEntity(EntityManager em) {
        UserCrewMember userCrewMember = new UserCrewMember()
                .sex(DEFAULT_SEX)
                .seat(DEFAULT_SEAT);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        userCrewMember.setUser(user);
        return userCrewMember;
    }

    @Before
    public void initTest() {
        userCrewMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCrewMember() throws Exception {
        int databaseSizeBeforeCreate = userCrewMemberRepository.findAll().size();

        // Create the UserCrewMember
        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);

        restUserCrewMemberMockMvc.perform(post("/api/user-crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the UserCrewMember in the database
        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeCreate + 1);
        UserCrewMember testUserCrewMember = userCrewMemberList.get(userCrewMemberList.size() - 1);
        assertThat(testUserCrewMember.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testUserCrewMember.getSeat()).isEqualTo(DEFAULT_SEAT);
    }

    @Test
    @Transactional
    public void createUserCrewMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCrewMemberRepository.findAll().size();

        // Create the UserCrewMember with an existing ID
        UserCrewMember existingUserCrewMember = new UserCrewMember();
        existingUserCrewMember.setId(1L);
        UserCrewMemberDTO existingUserCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(existingUserCrewMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCrewMemberMockMvc.perform(post("/api/user-crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserCrewMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewMemberRepository.findAll().size();
        // set the field null
        userCrewMember.setSex(null);

        // Create the UserCrewMember, which fails.
        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);

        restUserCrewMemberMockMvc.perform(post("/api/user-crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewMemberDTO)))
            .andExpect(status().isBadRequest());

        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeatIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCrewMemberRepository.findAll().size();
        // set the field null
        userCrewMember.setSeat(null);

        // Create the UserCrewMember, which fails.
        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);

        restUserCrewMemberMockMvc.perform(post("/api/user-crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewMemberDTO)))
            .andExpect(status().isBadRequest());

        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserCrewMembers() throws Exception {
        // Initialize the database
        userCrewMemberRepository.saveAndFlush(userCrewMember);

        // Get all the userCrewMemberList
        restUserCrewMemberMockMvc.perform(get("/api/user-crew-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCrewMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].seat").value(hasItem(DEFAULT_SEAT)));
    }

    @Test
    @Transactional
    public void getUserCrewMember() throws Exception {
        // Initialize the database
        userCrewMemberRepository.saveAndFlush(userCrewMember);

        // Get the userCrewMember
        restUserCrewMemberMockMvc.perform(get("/api/user-crew-members/{id}", userCrewMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userCrewMember.getId().intValue()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.seat").value(DEFAULT_SEAT));
    }

    @Test
    @Transactional
    public void getNonExistingUserCrewMember() throws Exception {
        // Get the userCrewMember
        restUserCrewMemberMockMvc.perform(get("/api/user-crew-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCrewMember() throws Exception {
        // Initialize the database
        userCrewMemberRepository.saveAndFlush(userCrewMember);
        int databaseSizeBeforeUpdate = userCrewMemberRepository.findAll().size();

        // Update the userCrewMember
        UserCrewMember updatedUserCrewMember = userCrewMemberRepository.findOne(userCrewMember.getId());
        updatedUserCrewMember
                .sex(UPDATED_SEX)
                .seat(UPDATED_SEAT);
        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(updatedUserCrewMember);

        restUserCrewMemberMockMvc.perform(put("/api/user-crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewMemberDTO)))
            .andExpect(status().isOk());

        // Validate the UserCrewMember in the database
        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeUpdate);
        UserCrewMember testUserCrewMember = userCrewMemberList.get(userCrewMemberList.size() - 1);
        assertThat(testUserCrewMember.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testUserCrewMember.getSeat()).isEqualTo(UPDATED_SEAT);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCrewMember() throws Exception {
        int databaseSizeBeforeUpdate = userCrewMemberRepository.findAll().size();

        // Create the UserCrewMember
        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserCrewMemberMockMvc.perform(put("/api/user-crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCrewMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the UserCrewMember in the database
        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserCrewMember() throws Exception {
        // Initialize the database
        userCrewMemberRepository.saveAndFlush(userCrewMember);
        int databaseSizeBeforeDelete = userCrewMemberRepository.findAll().size();

        // Get the userCrewMember
        restUserCrewMemberMockMvc.perform(delete("/api/user-crew-members/{id}", userCrewMember.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserCrewMember> userCrewMemberList = userCrewMemberRepository.findAll();
        assertThat(userCrewMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCrewMember.class);
    }
}
