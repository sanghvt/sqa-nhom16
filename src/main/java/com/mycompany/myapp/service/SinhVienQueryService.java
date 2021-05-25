package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.SinhVien;
import com.mycompany.myapp.repository.SinhVienRepository;
import com.mycompany.myapp.service.criteria.SinhVienCriteria;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link SinhVien} entities in the database.
 * The main input is a {@link SinhVienCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SinhVien} or a {@link Page} of {@link SinhVien} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SinhVienQueryService extends QueryService<SinhVien> {

    private final Logger log = LoggerFactory.getLogger(SinhVienQueryService.class);

    private final SinhVienRepository sinhVienRepository;

    public SinhVienQueryService(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    /**
     * Return a {@link List} of {@link SinhVien} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SinhVien> findByCriteria(SinhVienCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SinhVien> specification = createSpecification(criteria);
        return sinhVienRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link SinhVien} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SinhVien> findByCriteria(SinhVienCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SinhVien> specification = createSpecification(criteria);
        return sinhVienRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SinhVienCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SinhVien> specification = createSpecification(criteria);
        return sinhVienRepository.count(specification);
    }

    /**
     * Function to convert {@link SinhVienCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SinhVien> createSpecification(SinhVienCriteria criteria) {
        Specification<SinhVien> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SinhVien_.id));
            }
            if (criteria.getMaSinhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaSinhVien(), SinhVien_.maSinhVien));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), SinhVien_.password));
            }
            if (criteria.getHoTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHoTen(), SinhVien_.hoTen));
            }
            if (criteria.getNgaySinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaySinh(), SinhVien_.ngaySinh));
            }
            if (criteria.getGioiTinh() != null) {
                specification = specification.and(buildSpecification(criteria.getGioiTinh(), SinhVien_.gioiTinh));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SinhVien_.email));
            }
            if (criteria.getSoDienThoai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoDienThoai(), SinhVien_.soDienThoai));
            }
            if (criteria.getNganh() != null) {
                specification = specification.and(buildSpecification(criteria.getNganh(), SinhVien_.nganh));
            }
            if (criteria.getKhoaHoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKhoaHoc(), SinhVien_.khoaHoc));
            }
            if (criteria.getLop() != null) {
                specification = specification.and(buildSpecification(criteria.getLop(), SinhVien_.lop));
            }
            if (criteria.getDiemId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDiemId(), root -> root.join(SinhVien_.diems, JoinType.LEFT).get(Diem_.id))
                    );
            }
        }
        return specification;
    }
}
