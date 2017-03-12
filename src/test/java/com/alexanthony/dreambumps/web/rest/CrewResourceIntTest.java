package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.DreamBumpsApp;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.repository.CrewRepository;
import com.alexanthony.dreambumps.service.CrewService;
import com.alexanthony.dreambumps.service.dto.CrewDTO;
import com.alexanthony.dreambumps.service.mapper.CrewMapper;
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
 * Test class for the CrewResource REST controller.
 *
 * @see CrewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreamBumpsApp.class)
public class CrewResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Sex DEFAULT_SEX = Sex.male;
    private static final Sex UPDATED_SEX = Sex.female;

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private CrewMapper crewMapper;

    @Autowired
    private CrewService crewService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCrewMockMvc;

    private Crew crew;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CrewResource crewResource = new CrewResource(crewService);
        this.restCrewMockMvc = MockMvcBuilders.standaloneSetup(crewResource)
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
    public static Crew createEntity(EntityManager em) {
        Crew crew = new Crew()
                .name(DEFAULT_NAME)
                .sex(DEFAULT_SEX)
                .image(DEFAULT_IMAGE);
        return crew;
    }

    @Before
    public void initTest() {
        crew = createEntity(em);
    }

    @Test
    @Transactional
    public void createCrew() throws Exception {
        int databaseSizeBeforeCreate = crewRepository.findAll().size();

        // Create the Crew
        CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);

        restCrewMockMvc.perform(post("/api/crews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewDTO)))
            .andExpect(status().isCreated());

        // Validate the Crew in the database
        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeCreate + 1);
        Crew testCrew = crewList.get(crewList.size() - 1);
        assertThat(testCrew.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCrew.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testCrew.getImage()).isEqualTo(DEFAULT_IMAGE);
    }

    @Test
    @Transactional
    public void createCrewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = crewRepository.findAll().size();

        // Create the Crew with an existing ID
        Crew existingCrew = new Crew();
        existingCrew.setId(1L);
        CrewDTO existingCrewDTO = crewMapper.crewToCrewDTO(existingCrew);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrewMockMvc.perform(post("/api/crews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCrewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewRepository.findAll().size();
        // set the field null
        crew.setName(null);

        // Create the Crew, which fails.
        CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);

        restCrewMockMvc.perform(post("/api/crews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewDTO)))
            .andExpect(status().isBadRequest());

        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = crewRepository.findAll().size();
        // set the field null
        crew.setSex(null);

        // Create the Crew, which fails.
        CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);

        restCrewMockMvc.perform(post("/api/crews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewDTO)))
            .andExpect(status().isBadRequest());

        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCrews() throws Exception {
        // Initialize the database
        crewRepository.saveAndFlush(crew);

        // Get all the crewList
        restCrewMockMvc.perform(get("/api/crews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crew.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())));
    }

    @Test
    @Transactional
    public void getCrew() throws Exception {
        // Initialize the database
        crewRepository.saveAndFlush(crew);

        // Get the crew
        restCrewMockMvc.perform(get("/api/crews/{id}", crew.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(crew.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCrew() throws Exception {
        // Get the crew
        restCrewMockMvc.perform(get("/api/crews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCrew() throws Exception {
        // Initialize the database
        crewRepository.saveAndFlush(crew);
        int databaseSizeBeforeUpdate = crewRepository.findAll().size();

        // Update the crew
        Crew updatedCrew = crewRepository.findOne(crew.getId());
        updatedCrew
                .name(UPDATED_NAME)
                .sex(UPDATED_SEX)
                .image(UPDATED_IMAGE);
        CrewDTO crewDTO = crewMapper.crewToCrewDTO(updatedCrew);

        restCrewMockMvc.perform(put("/api/crews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewDTO)))
            .andExpect(status().isOk());

        // Validate the Crew in the database
        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeUpdate);
        Crew testCrew = crewList.get(crewList.size() - 1);
        assertThat(testCrew.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCrew.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testCrew.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingCrew() throws Exception {
        int databaseSizeBeforeUpdate = crewRepository.findAll().size();

        // Create the Crew
        CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCrewMockMvc.perform(put("/api/crews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crewDTO)))
            .andExpect(status().isCreated());

        // Validate the Crew in the database
        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCrew() throws Exception {
        // Initialize the database
        crewRepository.saveAndFlush(crew);
        int databaseSizeBeforeDelete = crewRepository.findAll().size();

        // Get the crew
        restCrewMockMvc.perform(delete("/api/crews/{id}", crew.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Crew> crewList = crewRepository.findAll();
        assertThat(crewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Crew.class);
    }
}
