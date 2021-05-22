package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Diem;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Diem}.
 */
public interface DiemService {
    /**
     * Save a diem.
     *
     * @param diem the entity to save.
     * @return the persisted entity.
     */
    Diem save(Diem diem);

    /**
     * Partially updates a diem.
     *
     * @param diem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Diem> partialUpdate(Diem diem);

    /**
     * Get all the diems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Diem> findAll(Pageable pageable);

    /**
     * Get the "id" diem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Diem> findOne(Long id);

    /**
     * Delete the "id" diem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
