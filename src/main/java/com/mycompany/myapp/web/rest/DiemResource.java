package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.repository.DiemRepository;
import com.mycompany.myapp.service.DiemService;
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

@RestController
@RequestMapping("/api")
public class DiemResource {

    private final Logger log = LoggerFactory.getLogger(DiemResource.class);

    private static final String ENTITY_NAME = "diem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiemService diemService;

    private final DiemRepository diemRepository;

    public DiemResource(DiemService diemService, DiemRepository diemRepository) {
        this.diemService = diemService;
        this.diemRepository = diemRepository;
    }

    @PostMapping("/diems")
    public ResponseEntity<Diem> createDiem(@Valid @RequestBody Diem diem) throws URISyntaxException {
        log.debug("REST request to save Diem : {}", diem);
        if (diem.getId() != null) {
            throw new BadRequestAlertException("A new diem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Diem result = diemService.save(diem);
        return ResponseEntity
            .created(new URI("/api/diems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/diems/{id}")
    public ResponseEntity<Diem> updateDiem(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Diem diem)
        throws URISyntaxException {
        log.debug("REST request to update Diem : {}, {}", id, diem);
        if (diem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, diem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!diemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Diem result = diemService.save(diem);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, diem.getId().toString()))
            .body(result);
    }

    @PatchMapping(value = "/diems/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Diem> partialUpdateDiem(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Diem diem
    ) throws URISyntaxException {
        log.debug("REST request to partial update Diem partially : {}, {}", id, diem);
        if (diem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, diem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!diemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Diem> result = diemService.partialUpdate(diem);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, diem.getId().toString())
        );
    }

    @GetMapping("/diems")
    public ResponseEntity<List<Diem>> getAllDiems(Pageable pageable) {
        log.debug("REST request to get a page of Diems");
        Page<Diem> page = diemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/diems/{id}")
    public ResponseEntity<Diem> getDiem(@PathVariable Long id) {
        log.debug("REST request to get Diem : {}", id);
        Optional<Diem> diem = diemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diem);
    }

    @DeleteMapping("/diems/{id}")
    public ResponseEntity<Void> deleteDiem(@PathVariable Long id) {
        log.debug("REST request to delete Diem : {}", id);
        diemService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
