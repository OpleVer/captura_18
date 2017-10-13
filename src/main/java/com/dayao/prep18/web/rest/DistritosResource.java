package com.dayao.prep18.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dayao.prep18.domain.Distritos;

import com.dayao.prep18.repository.DistritosRepository;
import com.dayao.prep18.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Distritos.
 */
@RestController
@RequestMapping("/api")
public class DistritosResource {

    private final Logger log = LoggerFactory.getLogger(DistritosResource.class);

    private static final String ENTITY_NAME = "distritos";

    private final DistritosRepository distritosRepository;

    public DistritosResource(DistritosRepository distritosRepository) {
        this.distritosRepository = distritosRepository;
    }

    /**
     * POST  /distritos : Create a new distritos.
     *
     * @param distritos the distritos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new distritos, or with status 400 (Bad Request) if the distritos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/distritos")
    @Timed
    public ResponseEntity<Distritos> createDistritos(@Valid @RequestBody Distritos distritos) throws URISyntaxException {
        log.debug("REST request to save Distritos : {}", distritos);
        if (distritos.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new distritos cannot already have an ID")).body(null);
        }
        Distritos result = distritosRepository.save(distritos);
        return ResponseEntity.created(new URI("/api/distritos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /distritos : Updates an existing distritos.
     *
     * @param distritos the distritos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated distritos,
     * or with status 400 (Bad Request) if the distritos is not valid,
     * or with status 500 (Internal Server Error) if the distritos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/distritos")
    @Timed
    public ResponseEntity<Distritos> updateDistritos(@Valid @RequestBody Distritos distritos) throws URISyntaxException {
        log.debug("REST request to update Distritos : {}", distritos);
        if (distritos.getId() == null) {
            return createDistritos(distritos);
        }
        Distritos result = distritosRepository.save(distritos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, distritos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /distritos : get all the distritos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of distritos in body
     */
    @GetMapping("/distritos")
    @Timed
    public List<Distritos> getAllDistritos() {
        log.debug("REST request to get all Distritos");
        return distritosRepository.findAll();
        }

    /**
     * GET  /distritos/:id : get the "id" distritos.
     *
     * @param id the id of the distritos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the distritos, or with status 404 (Not Found)
     */
    @GetMapping("/distritos/{id}")
    @Timed
    public ResponseEntity<Distritos> getDistritos(@PathVariable Long id) {
        log.debug("REST request to get Distritos : {}", id);
        Distritos distritos = distritosRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(distritos));
    }

    /**
     * DELETE  /distritos/:id : delete the "id" distritos.
     *
     * @param id the id of the distritos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/distritos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDistritos(@PathVariable Long id) {
        log.debug("REST request to delete Distritos : {}", id);
        distritosRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
