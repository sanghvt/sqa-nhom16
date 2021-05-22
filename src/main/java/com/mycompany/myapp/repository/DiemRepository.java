package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Diem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Diem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiemRepository extends JpaRepository<Diem, Long> {}
