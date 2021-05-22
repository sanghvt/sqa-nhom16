package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SinhVien;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link SinhVien}.
 */
public interface SinhVienService {
    /**
     * Save a sinhVien.
     *
     * @param sinhVien the entity to save.
     * @return the persisted entity.
     */
    SinhVien save(SinhVien sinhVien);

    /**
     * Partially updates a sinhVien.
     *
     * @param sinhVien the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SinhVien> partialUpdate(SinhVien sinhVien);

    /**
     * Get all the sinhViens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SinhVien> findAll(Pageable pageable);

    /**
     * Get the "id" sinhVien.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SinhVien> findOne(Long id);

    /**
     * Delete the "id" sinhVien.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
