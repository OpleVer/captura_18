package com.dayao.prep18.web.rest;

import com.dayao.prep18.Captura18App;

import com.dayao.prep18.domain.Distritos;
import com.dayao.prep18.repository.DistritosRepository;
import com.dayao.prep18.web.rest.errors.ExceptionTranslator;

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
 * Test class for the DistritosResource REST controller.
 *
 * @see DistritosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Captura18App.class)
public class DistritosResourceIntTest {

    private static final Integer DEFAULT_NUM_DISTRITO = 1;
    private static final Integer UPDATED_NUM_DISTRITO = 2;

    private static final String DEFAULT_NOM_DISTRITO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_DISTRITO = "BBBBBBBBBB";

    @Autowired
    private DistritosRepository distritosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDistritosMockMvc;

    private Distritos distritos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DistritosResource distritosResource = new DistritosResource(distritosRepository);
        this.restDistritosMockMvc = MockMvcBuilders.standaloneSetup(distritosResource)
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
    public static Distritos createEntity(EntityManager em) {
        Distritos distritos = new Distritos()
            .num_distrito(DEFAULT_NUM_DISTRITO)
            .nom_distrito(DEFAULT_NOM_DISTRITO);
        return distritos;
    }

    @Before
    public void initTest() {
        distritos = createEntity(em);
    }

    @Test
    @Transactional
    public void createDistritos() throws Exception {
        int databaseSizeBeforeCreate = distritosRepository.findAll().size();

        // Create the Distritos
        restDistritosMockMvc.perform(post("/api/distritos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distritos)))
            .andExpect(status().isCreated());

        // Validate the Distritos in the database
        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeCreate + 1);
        Distritos testDistritos = distritosList.get(distritosList.size() - 1);
        assertThat(testDistritos.getNum_distrito()).isEqualTo(DEFAULT_NUM_DISTRITO);
        assertThat(testDistritos.getNom_distrito()).isEqualTo(DEFAULT_NOM_DISTRITO);
    }

    @Test
    @Transactional
    public void createDistritosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = distritosRepository.findAll().size();

        // Create the Distritos with an existing ID
        distritos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistritosMockMvc.perform(post("/api/distritos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distritos)))
            .andExpect(status().isBadRequest());

        // Validate the Distritos in the database
        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNum_distritoIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setNum_distrito(null);

        // Create the Distritos, which fails.

        restDistritosMockMvc.perform(post("/api/distritos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distritos)))
            .andExpect(status().isBadRequest());

        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNom_distritoIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setNom_distrito(null);

        // Create the Distritos, which fails.

        restDistritosMockMvc.perform(post("/api/distritos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distritos)))
            .andExpect(status().isBadRequest());

        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDistritos() throws Exception {
        // Initialize the database
        distritosRepository.saveAndFlush(distritos);

        // Get all the distritosList
        restDistritosMockMvc.perform(get("/api/distritos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distritos.getId().intValue())))
            .andExpect(jsonPath("$.[*].num_distrito").value(hasItem(DEFAULT_NUM_DISTRITO)))
            .andExpect(jsonPath("$.[*].nom_distrito").value(hasItem(DEFAULT_NOM_DISTRITO.toString())));
    }

    @Test
    @Transactional
    public void getDistritos() throws Exception {
        // Initialize the database
        distritosRepository.saveAndFlush(distritos);

        // Get the distritos
        restDistritosMockMvc.perform(get("/api/distritos/{id}", distritos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(distritos.getId().intValue()))
            .andExpect(jsonPath("$.num_distrito").value(DEFAULT_NUM_DISTRITO))
            .andExpect(jsonPath("$.nom_distrito").value(DEFAULT_NOM_DISTRITO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDistritos() throws Exception {
        // Get the distritos
        restDistritosMockMvc.perform(get("/api/distritos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDistritos() throws Exception {
        // Initialize the database
        distritosRepository.saveAndFlush(distritos);
        int databaseSizeBeforeUpdate = distritosRepository.findAll().size();

        // Update the distritos
        Distritos updatedDistritos = distritosRepository.findOne(distritos.getId());
        updatedDistritos
            .num_distrito(UPDATED_NUM_DISTRITO)
            .nom_distrito(UPDATED_NOM_DISTRITO);

        restDistritosMockMvc.perform(put("/api/distritos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDistritos)))
            .andExpect(status().isOk());

        // Validate the Distritos in the database
        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeUpdate);
        Distritos testDistritos = distritosList.get(distritosList.size() - 1);
        assertThat(testDistritos.getNum_distrito()).isEqualTo(UPDATED_NUM_DISTRITO);
        assertThat(testDistritos.getNom_distrito()).isEqualTo(UPDATED_NOM_DISTRITO);
    }

    @Test
    @Transactional
    public void updateNonExistingDistritos() throws Exception {
        int databaseSizeBeforeUpdate = distritosRepository.findAll().size();

        // Create the Distritos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDistritosMockMvc.perform(put("/api/distritos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distritos)))
            .andExpect(status().isCreated());

        // Validate the Distritos in the database
        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDistritos() throws Exception {
        // Initialize the database
        distritosRepository.saveAndFlush(distritos);
        int databaseSizeBeforeDelete = distritosRepository.findAll().size();

        // Get the distritos
        restDistritosMockMvc.perform(delete("/api/distritos/{id}", distritos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Distritos> distritosList = distritosRepository.findAll();
        assertThat(distritosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Distritos.class);
        Distritos distritos1 = new Distritos();
        distritos1.setId(1L);
        Distritos distritos2 = new Distritos();
        distritos2.setId(distritos1.getId());
        assertThat(distritos1).isEqualTo(distritos2);
        distritos2.setId(2L);
        assertThat(distritos1).isNotEqualTo(distritos2);
        distritos1.setId(null);
        assertThat(distritos1).isNotEqualTo(distritos2);
    }
}
