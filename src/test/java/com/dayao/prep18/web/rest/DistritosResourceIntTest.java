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

    private static final Integer DEFAULT_TOTAL_ACTAS_DISTRITO = 1;
    private static final Integer UPDATED_TOTAL_ACTAS_DISTRITO = 2;

    private static final Integer DEFAULT_LISTA_NOMINAL_DISTRITO = 1;
    private static final Integer UPDATED_LISTA_NOMINAL_DISTRITO = 2;

    private static final Integer DEFAULT_ACTAS_PROCESADAS = 1;
    private static final Integer UPDATED_ACTAS_PROCESADAS = 2;

    private static final Float DEFAULT_PORC_ACTAS_PROCESADAS = 1F;
    private static final Float UPDATED_PORC_ACTAS_PROCESADAS = 2F;

    private static final Integer DEFAULT_TOTAL_VOTOS = 1;
    private static final Integer UPDATED_TOTAL_VOTOS = 2;

    private static final Float DEFAULT_PORC_PARTICIPACION = 1F;
    private static final Float UPDATED_PORC_PARTICIPACION = 2F;

    private static final Integer DEFAULT_ACTAS_CORRECTAS = 1;
    private static final Integer UPDATED_ACTAS_CORRECTAS = 2;

    private static final Integer DEFAULT_ACTAS_DIGITALIZADAS = 1;
    private static final Integer UPDATED_ACTAS_DIGITALIZADAS = 2;

    private static final Integer DEFAULT_ACTAS_ACOPIADAS = 1;
    private static final Integer UPDATED_ACTAS_ACOPIADAS = 2;

    private static final Integer DEFAULT_TOTAL_VOTANTES = 1;
    private static final Integer UPDATED_TOTAL_VOTANTES = 2;

    private static final Integer DEFAULT_TOTAL_SOBRANTES = 1;
    private static final Integer UPDATED_TOTAL_SOBRANTES = 2;

    private static final Integer DEFAULT_VOTOS_SACADOS_URNA = 1;
    private static final Integer UPDATED_VOTOS_SACADOS_URNA = 2;

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
            .nom_distrito(DEFAULT_NOM_DISTRITO)
            .total_actas_distrito(DEFAULT_TOTAL_ACTAS_DISTRITO)
            .lista_nominal_distrito(DEFAULT_LISTA_NOMINAL_DISTRITO)
            .actas_procesadas(DEFAULT_ACTAS_PROCESADAS)
            .porc_actas_procesadas(DEFAULT_PORC_ACTAS_PROCESADAS)
            .total_votos(DEFAULT_TOTAL_VOTOS)
            .porc_participacion(DEFAULT_PORC_PARTICIPACION)
            .actas_correctas(DEFAULT_ACTAS_CORRECTAS)
            .actas_digitalizadas(DEFAULT_ACTAS_DIGITALIZADAS)
            .actas_acopiadas(DEFAULT_ACTAS_ACOPIADAS)
            .total_votantes(DEFAULT_TOTAL_VOTANTES)
            .total_sobrantes(DEFAULT_TOTAL_SOBRANTES)
            .votos_sacados_urna(DEFAULT_VOTOS_SACADOS_URNA);
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
        assertThat(testDistritos.getTotal_actas_distrito()).isEqualTo(DEFAULT_TOTAL_ACTAS_DISTRITO);
        assertThat(testDistritos.getLista_nominal_distrito()).isEqualTo(DEFAULT_LISTA_NOMINAL_DISTRITO);
        assertThat(testDistritos.getActas_procesadas()).isEqualTo(DEFAULT_ACTAS_PROCESADAS);
        assertThat(testDistritos.getPorc_actas_procesadas()).isEqualTo(DEFAULT_PORC_ACTAS_PROCESADAS);
        assertThat(testDistritos.getTotal_votos()).isEqualTo(DEFAULT_TOTAL_VOTOS);
        assertThat(testDistritos.getPorc_participacion()).isEqualTo(DEFAULT_PORC_PARTICIPACION);
        assertThat(testDistritos.getActas_correctas()).isEqualTo(DEFAULT_ACTAS_CORRECTAS);
        assertThat(testDistritos.getActas_digitalizadas()).isEqualTo(DEFAULT_ACTAS_DIGITALIZADAS);
        assertThat(testDistritos.getActas_acopiadas()).isEqualTo(DEFAULT_ACTAS_ACOPIADAS);
        assertThat(testDistritos.getTotal_votantes()).isEqualTo(DEFAULT_TOTAL_VOTANTES);
        assertThat(testDistritos.getTotal_sobrantes()).isEqualTo(DEFAULT_TOTAL_SOBRANTES);
        assertThat(testDistritos.getVotos_sacados_urna()).isEqualTo(DEFAULT_VOTOS_SACADOS_URNA);
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
    public void checkTotal_actas_distritoIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setTotal_actas_distrito(null);

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
    public void checkLista_nominal_distritoIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setLista_nominal_distrito(null);

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
    public void checkActas_procesadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setActas_procesadas(null);

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
    public void checkPorc_actas_procesadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setPorc_actas_procesadas(null);

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
    public void checkTotal_votosIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setTotal_votos(null);

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
    public void checkPorc_participacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setPorc_participacion(null);

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
    public void checkActas_correctasIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setActas_correctas(null);

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
    public void checkActas_digitalizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setActas_digitalizadas(null);

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
    public void checkActas_acopiadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setActas_acopiadas(null);

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
    public void checkTotal_votantesIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setTotal_votantes(null);

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
    public void checkTotal_sobrantesIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setTotal_sobrantes(null);

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
    public void checkVotos_sacados_urnaIsRequired() throws Exception {
        int databaseSizeBeforeTest = distritosRepository.findAll().size();
        // set the field null
        distritos.setVotos_sacados_urna(null);

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
            .andExpect(jsonPath("$.[*].nom_distrito").value(hasItem(DEFAULT_NOM_DISTRITO.toString())))
            .andExpect(jsonPath("$.[*].total_actas_distrito").value(hasItem(DEFAULT_TOTAL_ACTAS_DISTRITO)))
            .andExpect(jsonPath("$.[*].lista_nominal_distrito").value(hasItem(DEFAULT_LISTA_NOMINAL_DISTRITO)))
            .andExpect(jsonPath("$.[*].actas_procesadas").value(hasItem(DEFAULT_ACTAS_PROCESADAS)))
            .andExpect(jsonPath("$.[*].porc_actas_procesadas").value(hasItem(DEFAULT_PORC_ACTAS_PROCESADAS.doubleValue())))
            .andExpect(jsonPath("$.[*].total_votos").value(hasItem(DEFAULT_TOTAL_VOTOS)))
            .andExpect(jsonPath("$.[*].porc_participacion").value(hasItem(DEFAULT_PORC_PARTICIPACION.doubleValue())))
            .andExpect(jsonPath("$.[*].actas_correctas").value(hasItem(DEFAULT_ACTAS_CORRECTAS)))
            .andExpect(jsonPath("$.[*].actas_digitalizadas").value(hasItem(DEFAULT_ACTAS_DIGITALIZADAS)))
            .andExpect(jsonPath("$.[*].actas_acopiadas").value(hasItem(DEFAULT_ACTAS_ACOPIADAS)))
            .andExpect(jsonPath("$.[*].total_votantes").value(hasItem(DEFAULT_TOTAL_VOTANTES)))
            .andExpect(jsonPath("$.[*].total_sobrantes").value(hasItem(DEFAULT_TOTAL_SOBRANTES)))
            .andExpect(jsonPath("$.[*].votos_sacados_urna").value(hasItem(DEFAULT_VOTOS_SACADOS_URNA)));
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
            .andExpect(jsonPath("$.nom_distrito").value(DEFAULT_NOM_DISTRITO.toString()))
            .andExpect(jsonPath("$.total_actas_distrito").value(DEFAULT_TOTAL_ACTAS_DISTRITO))
            .andExpect(jsonPath("$.lista_nominal_distrito").value(DEFAULT_LISTA_NOMINAL_DISTRITO))
            .andExpect(jsonPath("$.actas_procesadas").value(DEFAULT_ACTAS_PROCESADAS))
            .andExpect(jsonPath("$.porc_actas_procesadas").value(DEFAULT_PORC_ACTAS_PROCESADAS.doubleValue()))
            .andExpect(jsonPath("$.total_votos").value(DEFAULT_TOTAL_VOTOS))
            .andExpect(jsonPath("$.porc_participacion").value(DEFAULT_PORC_PARTICIPACION.doubleValue()))
            .andExpect(jsonPath("$.actas_correctas").value(DEFAULT_ACTAS_CORRECTAS))
            .andExpect(jsonPath("$.actas_digitalizadas").value(DEFAULT_ACTAS_DIGITALIZADAS))
            .andExpect(jsonPath("$.actas_acopiadas").value(DEFAULT_ACTAS_ACOPIADAS))
            .andExpect(jsonPath("$.total_votantes").value(DEFAULT_TOTAL_VOTANTES))
            .andExpect(jsonPath("$.total_sobrantes").value(DEFAULT_TOTAL_SOBRANTES))
            .andExpect(jsonPath("$.votos_sacados_urna").value(DEFAULT_VOTOS_SACADOS_URNA));
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
            .nom_distrito(UPDATED_NOM_DISTRITO)
            .total_actas_distrito(UPDATED_TOTAL_ACTAS_DISTRITO)
            .lista_nominal_distrito(UPDATED_LISTA_NOMINAL_DISTRITO)
            .actas_procesadas(UPDATED_ACTAS_PROCESADAS)
            .porc_actas_procesadas(UPDATED_PORC_ACTAS_PROCESADAS)
            .total_votos(UPDATED_TOTAL_VOTOS)
            .porc_participacion(UPDATED_PORC_PARTICIPACION)
            .actas_correctas(UPDATED_ACTAS_CORRECTAS)
            .actas_digitalizadas(UPDATED_ACTAS_DIGITALIZADAS)
            .actas_acopiadas(UPDATED_ACTAS_ACOPIADAS)
            .total_votantes(UPDATED_TOTAL_VOTANTES)
            .total_sobrantes(UPDATED_TOTAL_SOBRANTES)
            .votos_sacados_urna(UPDATED_VOTOS_SACADOS_URNA);

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
        assertThat(testDistritos.getTotal_actas_distrito()).isEqualTo(UPDATED_TOTAL_ACTAS_DISTRITO);
        assertThat(testDistritos.getLista_nominal_distrito()).isEqualTo(UPDATED_LISTA_NOMINAL_DISTRITO);
        assertThat(testDistritos.getActas_procesadas()).isEqualTo(UPDATED_ACTAS_PROCESADAS);
        assertThat(testDistritos.getPorc_actas_procesadas()).isEqualTo(UPDATED_PORC_ACTAS_PROCESADAS);
        assertThat(testDistritos.getTotal_votos()).isEqualTo(UPDATED_TOTAL_VOTOS);
        assertThat(testDistritos.getPorc_participacion()).isEqualTo(UPDATED_PORC_PARTICIPACION);
        assertThat(testDistritos.getActas_correctas()).isEqualTo(UPDATED_ACTAS_CORRECTAS);
        assertThat(testDistritos.getActas_digitalizadas()).isEqualTo(UPDATED_ACTAS_DIGITALIZADAS);
        assertThat(testDistritos.getActas_acopiadas()).isEqualTo(UPDATED_ACTAS_ACOPIADAS);
        assertThat(testDistritos.getTotal_votantes()).isEqualTo(UPDATED_TOTAL_VOTANTES);
        assertThat(testDistritos.getTotal_sobrantes()).isEqualTo(UPDATED_TOTAL_SOBRANTES);
        assertThat(testDistritos.getVotos_sacados_urna()).isEqualTo(UPDATED_VOTOS_SACADOS_URNA);
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
