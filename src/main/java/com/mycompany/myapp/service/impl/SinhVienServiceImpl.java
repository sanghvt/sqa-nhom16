package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.SinhVien;
import com.mycompany.myapp.repository.SinhVienRepository;
import com.mycompany.myapp.service.SinhVienService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SinhVien}.
 */
@Service
@Transactional
public class SinhVienServiceImpl implements SinhVienService {

    private final Logger log = LoggerFactory.getLogger(SinhVienServiceImpl.class);

    private final SinhVienRepository sinhVienRepository;

    public SinhVienServiceImpl(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    @Override
    public SinhVien save(SinhVien sinhVien) {
        log.debug("Request to save SinhVien : {}", sinhVien);
        return sinhVienRepository.save(sinhVien);
    }

    @Override
    public Optional<SinhVien> partialUpdate(SinhVien sinhVien) {
        log.debug("Request to partially update SinhVien : {}", sinhVien);

        return sinhVienRepository
            .findById(sinhVien.getId())
            .map(
                existingSinhVien -> {
                    if (sinhVien.getMaSinhVien() != null) {
                        existingSinhVien.setMaSinhVien(sinhVien.getMaSinhVien());
                    }
                    if (sinhVien.getPassword() != null) {
                        existingSinhVien.setPassword(sinhVien.getPassword());
                    }
                    if (sinhVien.getHoTen() != null) {
                        existingSinhVien.setHoTen(sinhVien.getHoTen());
                    }
                    if (sinhVien.getNgaySinh() != null) {
                        existingSinhVien.setNgaySinh(sinhVien.getNgaySinh());
                    }
                    if (sinhVien.getGioiTinh() != null) {
                        existingSinhVien.setGioiTinh(sinhVien.getGioiTinh());
                    }
                    if (sinhVien.getEmail() != null) {
                        existingSinhVien.setEmail(sinhVien.getEmail());
                    }
                    if (sinhVien.getSoDienThoai() != null) {
                        existingSinhVien.setSoDienThoai(sinhVien.getSoDienThoai());
                    }
                    if (sinhVien.getNganh() != null) {
                        existingSinhVien.setNganh(sinhVien.getNganh());
                    }
                    if (sinhVien.getKhoaHoc() != null) {
                        existingSinhVien.setKhoaHoc(sinhVien.getKhoaHoc());
                    }
                    if (sinhVien.getLop() != null) {
                        existingSinhVien.setLop(sinhVien.getLop());
                    }

                    return existingSinhVien;
                }
            )
            .map(sinhVienRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SinhVien> findAll(Pageable pageable) {
        log.debug("Request to get all SinhViens");
        return sinhVienRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SinhVien> findOne(Long id) {
        log.debug("Request to get SinhVien : {}", id);
        return sinhVienRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SinhVien : {}", id);
        sinhVienRepository.deleteById(id);
    }
}
