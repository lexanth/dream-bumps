package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.CrewMember;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.repository.CrewMemberRepository;
import com.alexanthony.dreambumps.service.CrewMemberService;
import com.alexanthony.dreambumps.service.dto.CrewMemberDTO;
import com.alexanthony.dreambumps.service.mapper.CrewMemberMapper;
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

/**
 * Test class for the CrewMemberResource REST controller.
 *
 * @see CrewMemberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class CrewMemberResourceIntTest {

    private static final Integer DEFAULT_SEAT = 1;
    private static final Integer UPDATED_SEAT = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private CrewMemberRepository crewMemberRepository;

    @Autowired
    private CrewMemberMapper crewMemberMapper;

    @Autowired
    private CrewMemberService crewMemberService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCrewMemberMockMvc;

    private CrewMember crewMember;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CrewMemberResource crewMemberResource = new CrewMemberResource(crewMemberService);
        this.restCrewMemberMockMvc = MockMvcBuilders.standaloneSetup(crewMemberResource)
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
    public static CrewMember createEntity(EntityManager em) {
        CrewMember crewMember = new CrewMember()
                .seat(DEFAULT_SEAT)
                .name(DEFAULT_NAME);
        // Add required entity
        Crew crew = CrewResourceIntTest.createEntity(em);
        em.persist(crew);
        em.flush();
        crewMember.setCrew(crew);
        return crewMember;
    }

    @Before
    public void initTest() {
        crewMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createCrewMember() throws Exception {
        int databaseSizeBeforeCreate = crewMemberRepository.findAll().size();

        // Create the CrewMember
        CrewMemberDTO crewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(crewMember);

        restCrewMemberMockMvc.perform(post("/api/crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the CrewMember in the database
        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeCreate + 1);
        CrewMember testCrewMember = crewMemberList.get(crewMemberList.size() - 1);
        assertThat(testCrewMember.getSeat()).isEqualTo(DEFAULT_SEAT);
        assertThat(testCrewMember.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createCrewMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = crewMemberRepository.findAll().size();

        // Create the CrewMember with an existing ID
        CrewMember existingCrewMember = new CrewMember();
        existingCrewMember.setId(1L);
        CrewMemberDTO existingCrewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(existingCrewMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrewMemberMockMvc.perform(post("/api/crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCrewMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSeatIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewMemberRepository.findAll().size();
        // set the field null
        crewMember.setSeat(null);

        // Create the CrewMember, which fails.
        CrewMemberDTO crewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(crewMember);

        restCrewMemberMockMvc.perform(post("/api/crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewMemberDTO)))
            .andExpect(status().isBadRequest());

        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewMemberRepository.findAll().size();
        // set the field null
        crewMember.setName(null);

        // Create the CrewMember, which fails.
        CrewMemberDTO crewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(crewMember);

        restCrewMemberMockMvc.perform(post("/api/crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewMemberDTO)))
            .andExpect(status().isBadRequest());

        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCrewMembers() throws Exception {
        // Initialize the database
        crewMemberRepository.saveAndFlush(crewMember);

        // Get all the crewMemberList
        restCrewMemberMockMvc.perform(get("/api/crew-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crewMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].seat").value(hasItem(DEFAULT_SEAT)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCrewMember() throws Exception {
        // Initialize the database
        crewMemberRepository.saveAndFlush(crewMember);

        // Get the crewMember
        restCrewMemberMockMvc.perform(get("/api/crew-members/{id}", crewMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(crewMember.getId().intValue()))
            .andExpect(jsonPath("$.seat").value(DEFAULT_SEAT))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCrewMember() throws Exception {
        // Get the crewMember
        restCrewMemberMockMvc.perform(get("/api/crew-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCrewMember() throws Exception {
        // Initialize the database
        crewMemberRepository.saveAndFlush(crewMember);
        int databaseSizeBeforeUpdate = crewMemberRepository.findAll().size();

        // Update the crewMember
        CrewMember updatedCrewMember = crewMemberRepository.findOne(crewMember.getId());
        updatedCrewMember
                .seat(UPDATED_SEAT)
                .name(UPDATED_NAME);
        CrewMemberDTO crewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(updatedCrewMember);

        restCrewMemberMockMvc.perform(put("/api/crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewMemberDTO)))
            .andExpect(status().isOk());

        // Validate the CrewMember in the database
        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeUpdate);
        CrewMember testCrewMember = crewMemberList.get(crewMemberList.size() - 1);
        assertThat(testCrewMember.getSeat()).isEqualTo(UPDATED_SEAT);
        assertThat(testCrewMember.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCrewMember() throws Exception {
        int databaseSizeBeforeUpdate = crewMemberRepository.findAll().size();

        // Create the CrewMember
        CrewMemberDTO crewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(crewMember);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCrewMemberMockMvc.perform(put("/api/crew-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the CrewMember in the database
        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCrewMember() throws Exception {
        // Initialize the database
        crewMemberRepository.saveAndFlush(crewMember);
        int databaseSizeBeforeDelete = crewMemberRepository.findAll().size();

        // Get the crewMember
        restCrewMemberMockMvc.perform(delete("/api/crew-members/{id}", crewMember.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        assertThat(crewMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrewMember.class);
    }
}
