package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SinhVien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SinhVien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {}
