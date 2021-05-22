package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.repository.DiemRepository;
import com.mycompany.myapp.service.DiemService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Diem}.
 */
@Service
@Transactional
public class DiemServiceImpl implements DiemService {

    private final Logger log = LoggerFactory.getLogger(DiemServiceImpl.class);

    private final DiemRepository diemRepository;

    public DiemServiceImpl(DiemRepository diemRepository) {
        this.diemRepository = diemRepository;
    }

    @Override
    public Diem save(Diem diem) {
        log.debug("Request to save Diem : {}", diem);
        return diemRepository.save(diem);
    }

    @Override
    public Optional<Diem> partialUpdate(Diem diem) {
        log.debug("Request to partially update Diem : {}", diem);

        return diemRepository
            .findById(diem.getId())
            .map(
                existingDiem -> {
                    if (diem.getChuyenCan() != null) {
                        existingDiem.setChuyenCan(diem.getChuyenCan());
                    }
                    if (diem.getKiemTra() != null) {
                        existingDiem.setKiemTra(diem.getKiemTra());
                    }
                    if (diem.getThucHanh() != null) {
                        existingDiem.setThucHanh(diem.getThucHanh());
                    }
                    if (diem.getBaiTap() != null) {
                        existingDiem.setBaiTap(diem.getBaiTap());
                    }
                    if (diem.getThi() != null) {
                        existingDiem.setThi(diem.getThi());
                    }
                    if (diem.getSoLanHoc() != null) {
                        existingDiem.setSoLanHoc(diem.getSoLanHoc());
                    }
                    if (diem.getTongKet() != null) {
                        existingDiem.setTongKet(diem.getTongKet());
                    }
                    if (diem.getTongKetThangChu() != null) {
                        existingDiem.setTongKetThangChu(diem.getTongKetThangChu());
                    }
                    if (diem.getKetQua() != null) {
                        existingDiem.setKetQua(diem.getKetQua());
                    }

                    return existingDiem;
                }
            )
            .map(diemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Diem> findAll(Pageable pageable) {
        log.debug("Request to get all Diems");
        return diemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Diem> findOne(Long id) {
        log.debug("Request to get Diem : {}", id);
        return diemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Diem : {}", id);
        diemRepository.deleteById(id);
    }
}
