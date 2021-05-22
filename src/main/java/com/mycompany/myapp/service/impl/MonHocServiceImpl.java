package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.MonHoc;
import com.mycompany.myapp.repository.MonHocRepository;
import com.mycompany.myapp.service.MonHocService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MonHoc}.
 */
@Service
@Transactional
public class MonHocServiceImpl implements MonHocService {

    private final Logger log = LoggerFactory.getLogger(MonHocServiceImpl.class);

    private final MonHocRepository monHocRepository;

    public MonHocServiceImpl(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
    }

    @Override
    public MonHoc save(MonHoc monHoc) {
        log.debug("Request to save MonHoc : {}", monHoc);
        return monHocRepository.save(monHoc);
    }

    @Override
    public Optional<MonHoc> partialUpdate(MonHoc monHoc) {
        log.debug("Request to partially update MonHoc : {}", monHoc);

        return monHocRepository
            .findById(monHoc.getId())
            .map(
                existingMonHoc -> {
                    if (monHoc.getMaMonHoc() != null) {
                        existingMonHoc.setMaMonHoc(monHoc.getMaMonHoc());
                    }
                    if (monHoc.getTenMonHoc() != null) {
                        existingMonHoc.setTenMonHoc(monHoc.getTenMonHoc());
                    }
                    if (monHoc.getSoTinChi() != null) {
                        existingMonHoc.setSoTinChi(monHoc.getSoTinChi());
                    }
                    if (monHoc.getChuyenCan() != null) {
                        existingMonHoc.setChuyenCan(monHoc.getChuyenCan());
                    }
                    if (monHoc.getKiemTra() != null) {
                        existingMonHoc.setKiemTra(monHoc.getKiemTra());
                    }
                    if (monHoc.getThucHanh() != null) {
                        existingMonHoc.setThucHanh(monHoc.getThucHanh());
                    }
                    if (monHoc.getBaiTap() != null) {
                        existingMonHoc.setBaiTap(monHoc.getBaiTap());
                    }
                    if (monHoc.getThi() != null) {
                        existingMonHoc.setThi(monHoc.getThi());
                    }

                    return existingMonHoc;
                }
            )
            .map(monHocRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MonHoc> findAll(Pageable pageable) {
        log.debug("Request to get all MonHocs");
        return monHocRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MonHoc> findOne(Long id) {
        log.debug("Request to get MonHoc : {}", id);
        return monHocRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonHoc : {}", id);
        monHocRepository.deleteById(id);
    }
}
