package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.MonHoc;
import com.mycompany.myapp.repository.MonHocRepository;
import com.mycompany.myapp.service.criteria.MonHocCriteria;
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
 * Service for executing complex queries for {@link MonHoc} entities in the database.
 * The main input is a {@link MonHocCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonHoc} or a {@link Page} of {@link MonHoc} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonHocQueryService extends QueryService<MonHoc> {

    private final Logger log = LoggerFactory.getLogger(MonHocQueryService.class);

    private final MonHocRepository monHocRepository;

    public MonHocQueryService(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
    }

    /**
     * Return a {@link List} of {@link MonHoc} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonHoc> findByCriteria(MonHocCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonHoc> specification = createSpecification(criteria);
        return monHocRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link MonHoc} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonHoc> findByCriteria(MonHocCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonHoc> specification = createSpecification(criteria);
        return monHocRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonHocCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonHoc> specification = createSpecification(criteria);
        return monHocRepository.count(specification);
    }

    /**
     * Function to convert {@link MonHocCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MonHoc> createSpecification(MonHocCriteria criteria) {
        Specification<MonHoc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MonHoc_.id));
            }
            if (criteria.getMaMonHoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaMonHoc(), MonHoc_.maMonHoc));
            }
            if (criteria.getTenMonHoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenMonHoc(), MonHoc_.tenMonHoc));
            }
            if (criteria.getSoTinChi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoTinChi(), MonHoc_.soTinChi));
            }
            if (criteria.getChuyenCan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChuyenCan(), MonHoc_.chuyenCan));
            }
            if (criteria.getKiemTra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKiemTra(), MonHoc_.kiemTra));
            }
            if (criteria.getThucHanh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThucHanh(), MonHoc_.thucHanh));
            }
            if (criteria.getBaiTap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaiTap(), MonHoc_.baiTap));
            }
            if (criteria.getThi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThi(), MonHoc_.thi));
            }
            if (criteria.getDiemId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDiemId(), root -> root.join(MonHoc_.diems, JoinType.LEFT).get(Diem_.id))
                    );
            }
        }
        return specification;
    }
}
