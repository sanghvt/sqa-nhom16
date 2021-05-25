package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SinhVien;
import com.mycompany.myapp.repository.SinhVienRepository;
import com.mycompany.myapp.service.SinhVienQueryService;
import com.mycompany.myapp.service.SinhVienService;
import com.mycompany.myapp.service.criteria.SinhVienCriteria;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.SinhVien}.
 */
@RestController
@RequestMapping("/api")
public class SinhVienResource {

    private final Logger log = LoggerFactory.getLogger(SinhVienResource.class);

    private static final String ENTITY_NAME = "sinhVien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SinhVienService sinhVienService;

    private final SinhVienRepository sinhVienRepository;

    private final SinhVienQueryService sinhVienQueryService;

    public SinhVienResource(
        SinhVienService sinhVienService,
        SinhVienRepository sinhVienRepository,
        SinhVienQueryService sinhVienQueryService
    ) {
        this.sinhVienService = sinhVienService;
        this.sinhVienRepository = sinhVienRepository;
        this.sinhVienQueryService = sinhVienQueryService;
    }

    /**
     * {@code POST  /sinh-viens} : Create a new sinhVien.
     *
     * @param sinhVien the sinhVien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sinhVien, or with status {@code 400 (Bad Request)} if the sinhVien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sinh-viens")
    public ResponseEntity<SinhVien> createSinhVien(@Valid @RequestBody SinhVien sinhVien) throws URISyntaxException {
        log.debug("REST request to save SinhVien : {}", sinhVien);
        if (sinhVien.getId() != null) {
            throw new BadRequestAlertException("A new sinhVien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SinhVien result = sinhVienService.save(sinhVien);
        return ResponseEntity
            .created(new URI("/api/sinh-viens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sinh-viens/:id} : Updates an existing sinhVien.
     *
     * @param id the id of the sinhVien to save.
     * @param sinhVien the sinhVien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sinhVien,
     * or with status {@code 400 (Bad Request)} if the sinhVien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sinhVien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sinh-viens/{id}")
    public ResponseEntity<SinhVien> updateSinhVien(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SinhVien sinhVien
    ) throws URISyntaxException {
        log.debug("REST request to update SinhVien : {}, {}", id, sinhVien);
        if (sinhVien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sinhVien.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sinhVienRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SinhVien result = sinhVienService.save(sinhVien);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sinhVien.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sinh-viens/:id} : Partial updates given fields of an existing sinhVien, field will ignore if it is null
     *
     * @param id the id of the sinhVien to save.
     * @param sinhVien the sinhVien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sinhVien,
     * or with status {@code 400 (Bad Request)} if the sinhVien is not valid,
     * or with status {@code 404 (Not Found)} if the sinhVien is not found,
     * or with status {@code 500 (Internal Server Error)} if the sinhVien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sinh-viens/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SinhVien> partialUpdateSinhVien(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SinhVien sinhVien
    ) throws URISyntaxException {
        log.debug("REST request to partial update SinhVien partially : {}, {}", id, sinhVien);
        if (sinhVien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sinhVien.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sinhVienRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SinhVien> result = sinhVienService.partialUpdate(sinhVien);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sinhVien.getId().toString())
        );
    }

    /**
     * {@code GET  /sinh-viens} : get all the sinhViens.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sinhViens in body.
     */
    @GetMapping("/sinh-viens")
    public ResponseEntity<List<SinhVien>> getAllSinhViens(SinhVienCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SinhViens by criteria: {}", criteria);
        Page<SinhVien> page = sinhVienQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sinh-viens/count} : count all the sinhViens.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/sinh-viens/count")
    public ResponseEntity<Long> countSinhViens(SinhVienCriteria criteria) {
        log.debug("REST request to count SinhViens by criteria: {}", criteria);
        return ResponseEntity.ok().body(sinhVienQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sinh-viens/:id} : get the "id" sinhVien.
     *
     * @param id the id of the sinhVien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sinhVien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sinh-viens/{id}")
    public ResponseEntity<SinhVien> getSinhVien(@PathVariable Long id) {
        log.debug("REST request to get SinhVien : {}", id);
        Optional<SinhVien> sinhVien = sinhVienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sinhVien);
    }

    /**
     * {@code DELETE  /sinh-viens/:id} : delete the "id" sinhVien.
     *
     * @param id the id of the sinhVien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sinh-viens/{id}")
    public ResponseEntity<Void> deleteSinhVien(@PathVariable Long id) {
        log.debug("REST request to delete SinhVien : {}", id);
        sinhVienService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
