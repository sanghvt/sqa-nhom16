package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MonHoc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MonHoc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Long>, JpaSpecificationExecutor<MonHoc> {}
