package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MonHoc;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link MonHoc}.
 */
public interface MonHocService {
    /**
     * Save a monHoc.
     *
     * @param monHoc the entity to save.
     * @return the persisted entity.
     */
    MonHoc save(MonHoc monHoc);

    /**
     * Partially updates a monHoc.
     *
     * @param monHoc the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MonHoc> partialUpdate(MonHoc monHoc);

    /**
     * Get all the monHocs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MonHoc> findAll(Pageable pageable);

    /**
     * Get the "id" monHoc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MonHoc> findOne(Long id);

    /**
     * Delete the "id" monHoc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
