package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.repository.DiemRepository;
import com.mycompany.myapp.service.criteria.DiemCriteria;
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
 * Service for executing complex queries for {@link Diem} entities in the database.
 * The main input is a {@link DiemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Diem} or a {@link Page} of {@link Diem} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DiemQueryService extends QueryService<Diem> {

    private final Logger log = LoggerFactory.getLogger(DiemQueryService.class);

    private final DiemRepository diemRepository;

    public DiemQueryService(DiemRepository diemRepository) {
        this.diemRepository = diemRepository;
    }

    /**
     * Return a {@link List} of {@link Diem} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Diem> findByCriteria(DiemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Diem> specification = createSpecification(criteria);
        return diemRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Diem} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Diem> findByCriteria(DiemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Diem> specification = createSpecification(criteria);
        return diemRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DiemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Diem> specification = createSpecification(criteria);
        return diemRepository.count(specification);
    }

    /**
     * Function to convert {@link DiemCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Diem> createSpecification(DiemCriteria criteria) {
        Specification<Diem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Diem_.id));
            }
            if (criteria.getChuyenCan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChuyenCan(), Diem_.chuyenCan));
            }
            if (criteria.getKiemTra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKiemTra(), Diem_.kiemTra));
            }
            if (criteria.getThucHanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThucHanh(), Diem_.thucHanh));
            }
            if (criteria.getBaiTap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaiTap(), Diem_.baiTap));
            }
            if (criteria.getThi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThi(), Diem_.thi));
            }
            if (criteria.getSoLanHoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoLanHoc(), Diem_.soLanHoc));
            }
            if (criteria.getTongKet() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTongKet(), Diem_.tongKet));
            }
            if (criteria.getTongKetThangChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTongKetThangChu(), Diem_.tongKetThangChu));
            }
            if (criteria.getKetQua() != null) {
                specification = specification.and(buildSpecification(criteria.getKetQua(), Diem_.ketQua));
            }
            if (criteria.getMaSinhVienId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getMaSinhVienId(), root -> root.join(Diem_.maSinhVien, JoinType.LEFT).get(SinhVien_.id))
                    );
            }
            if (criteria.getMaMonHocId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getMaMonHocId(), root -> root.join(Diem_.maMonHoc, JoinType.LEFT).get(MonHoc_.id))
                    );
            }
        }
        return specification;
    }
}
