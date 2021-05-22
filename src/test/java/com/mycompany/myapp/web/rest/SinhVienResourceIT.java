package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.SinhVien;
import com.mycompany.myapp.domain.enumeration.Lop;
import com.mycompany.myapp.domain.enumeration.Nganh;
import com.mycompany.myapp.domain.enumeration.Sex;
import com.mycompany.myapp.repository.SinhVienRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SinhVienResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SinhVienResourceIT {

    private static final String DEFAULT_MA_SINH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_SINH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_SINH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_SINH = LocalDate.now(ZoneId.systemDefault());

    private static final Sex DEFAULT_GIOI_TINH = Sex.Male;
    private static final Sex UPDATED_GIOI_TINH = Sex.Female;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final Nganh DEFAULT_NGANH = Nganh.InformationTechnology;
    private static final Nganh UPDATED_NGANH = Nganh.Marketing;

    private static final String DEFAULT_KHOA_HOC = "AAAAAAAAAA";
    private static final String UPDATED_KHOA_HOC = "BBBBBBBBBB";

    private static final Lop DEFAULT_LOP = Lop.D17CQCN03;
    private static final Lop UPDATED_LOP = Lop.D17CQCN04;

    private static final String ENTITY_API_URL = "/api/sinh-viens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSinhVienMockMvc;

    private SinhVien sinhVien;

    public static SinhVien createEntity(EntityManager em) {
        SinhVien sinhVien = new SinhVien()
            .maSinhVien(DEFAULT_MA_SINH_VIEN)
            .password(DEFAULT_PASSWORD)
            .hoTen(DEFAULT_HO_TEN)
            .ngaySinh(DEFAULT_NGAY_SINH)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .email(DEFAULT_EMAIL)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .nganh(DEFAULT_NGANH)
            .khoaHoc(DEFAULT_KHOA_HOC)
            .lop(DEFAULT_LOP);
        return sinhVien;
    }

    public static SinhVien createUpdatedEntity(EntityManager em) {
        SinhVien sinhVien = new SinhVien()
            .maSinhVien(UPDATED_MA_SINH_VIEN)
            .password(UPDATED_PASSWORD)
            .hoTen(UPDATED_HO_TEN)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .nganh(UPDATED_NGANH)
            .khoaHoc(UPDATED_KHOA_HOC)
            .lop(UPDATED_LOP);
        return sinhVien;
    }

    @BeforeEach
    public void initTest() {
        sinhVien = createEntity(em);
    }

    @Test
    @Transactional
    void createSinhVien() throws Exception {
        int databaseSizeBeforeCreate = sinhVienRepository.findAll().size();
        // Create the SinhVien
        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isCreated());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeCreate + 1);
        SinhVien testSinhVien = sinhVienList.get(sinhVienList.size() - 1);
        assertThat(testSinhVien.getMaSinhVien()).isEqualTo(DEFAULT_MA_SINH_VIEN);
        assertThat(testSinhVien.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testSinhVien.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testSinhVien.getNgaySinh()).isEqualTo(DEFAULT_NGAY_SINH);
        assertThat(testSinhVien.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testSinhVien.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSinhVien.getSoDienThoai()).isEqualTo(DEFAULT_SO_DIEN_THOAI);
        assertThat(testSinhVien.getNganh()).isEqualTo(DEFAULT_NGANH);
        assertThat(testSinhVien.getKhoaHoc()).isEqualTo(DEFAULT_KHOA_HOC);
        assertThat(testSinhVien.getLop()).isEqualTo(DEFAULT_LOP);
    }

    @Test
    @Transactional
    void createSinhVienWithExistingId() throws Exception {
        // Create the SinhVien with an existing ID
        sinhVien.setId(1L);

        int databaseSizeBeforeCreate = sinhVienRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMaSinhVienIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setMaSinhVien(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setPassword(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHoTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setHoTen(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNgaySinhIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setNgaySinh(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNganhIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setNganh(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkKhoaHocIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setKhoaHoc(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSinhViens() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList
        restSinhVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sinhVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maSinhVien").value(hasItem(DEFAULT_MA_SINH_VIEN)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(DEFAULT_NGAY_SINH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].nganh").value(hasItem(DEFAULT_NGANH.toString())))
            .andExpect(jsonPath("$.[*].khoaHoc").value(hasItem(DEFAULT_KHOA_HOC)))
            .andExpect(jsonPath("$.[*].lop").value(hasItem(DEFAULT_LOP.toString())));
    }

    @Test
    @Transactional
    void getSinhVien() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get the sinhVien
        restSinhVienMockMvc
            .perform(get(ENTITY_API_URL_ID, sinhVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sinhVien.getId().intValue()))
            .andExpect(jsonPath("$.maSinhVien").value(DEFAULT_MA_SINH_VIEN))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN))
            .andExpect(jsonPath("$.ngaySinh").value(DEFAULT_NGAY_SINH.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.nganh").value(DEFAULT_NGANH.toString()))
            .andExpect(jsonPath("$.khoaHoc").value(DEFAULT_KHOA_HOC))
            .andExpect(jsonPath("$.lop").value(DEFAULT_LOP.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSinhVien() throws Exception {
        // Get the sinhVien
        restSinhVienMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSinhVien() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();

        // Update the sinhVien
        SinhVien updatedSinhVien = sinhVienRepository.findById(sinhVien.getId()).get();
        // Disconnect from session so that the updates on updatedSinhVien are not directly saved in db
        em.detach(updatedSinhVien);
        updatedSinhVien
            .maSinhVien(UPDATED_MA_SINH_VIEN)
            .password(UPDATED_PASSWORD)
            .hoTen(UPDATED_HO_TEN)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .nganh(UPDATED_NGANH)
            .khoaHoc(UPDATED_KHOA_HOC)
            .lop(UPDATED_LOP);

        restSinhVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSinhVien.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSinhVien))
            )
            .andExpect(status().isOk());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
        SinhVien testSinhVien = sinhVienList.get(sinhVienList.size() - 1);
        assertThat(testSinhVien.getMaSinhVien()).isEqualTo(UPDATED_MA_SINH_VIEN);
        assertThat(testSinhVien.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testSinhVien.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testSinhVien.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testSinhVien.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testSinhVien.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSinhVien.getSoDienThoai()).isEqualTo(UPDATED_SO_DIEN_THOAI);
        assertThat(testSinhVien.getNganh()).isEqualTo(UPDATED_NGANH);
        assertThat(testSinhVien.getKhoaHoc()).isEqualTo(UPDATED_KHOA_HOC);
        assertThat(testSinhVien.getLop()).isEqualTo(UPDATED_LOP);
    }

    @Test
    @Transactional
    void putNonExistingSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();
        sinhVien.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSinhVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sinhVien.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sinhVien))
            )
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();
        sinhVien.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSinhVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sinhVien))
            )
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();
        sinhVien.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSinhVienMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSinhVienWithPatch() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();

        // Update the sinhVien using partial update
        SinhVien partialUpdatedSinhVien = new SinhVien();
        partialUpdatedSinhVien.setId(sinhVien.getId());

        partialUpdatedSinhVien
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .khoaHoc(UPDATED_KHOA_HOC)
            .lop(UPDATED_LOP);

        restSinhVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSinhVien.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSinhVien))
            )
            .andExpect(status().isOk());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
        SinhVien testSinhVien = sinhVienList.get(sinhVienList.size() - 1);
        assertThat(testSinhVien.getMaSinhVien()).isEqualTo(DEFAULT_MA_SINH_VIEN);
        assertThat(testSinhVien.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testSinhVien.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testSinhVien.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testSinhVien.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testSinhVien.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSinhVien.getSoDienThoai()).isEqualTo(UPDATED_SO_DIEN_THOAI);
        assertThat(testSinhVien.getNganh()).isEqualTo(DEFAULT_NGANH);
        assertThat(testSinhVien.getKhoaHoc()).isEqualTo(UPDATED_KHOA_HOC);
        assertThat(testSinhVien.getLop()).isEqualTo(UPDATED_LOP);
    }

    @Test
    @Transactional
    void fullUpdateSinhVienWithPatch() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();

        // Update the sinhVien using partial update
        SinhVien partialUpdatedSinhVien = new SinhVien();
        partialUpdatedSinhVien.setId(sinhVien.getId());

        partialUpdatedSinhVien
            .maSinhVien(UPDATED_MA_SINH_VIEN)
            .password(UPDATED_PASSWORD)
            .hoTen(UPDATED_HO_TEN)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .nganh(UPDATED_NGANH)
            .khoaHoc(UPDATED_KHOA_HOC)
            .lop(UPDATED_LOP);

        restSinhVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSinhVien.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSinhVien))
            )
            .andExpect(status().isOk());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
        SinhVien testSinhVien = sinhVienList.get(sinhVienList.size() - 1);
        assertThat(testSinhVien.getMaSinhVien()).isEqualTo(UPDATED_MA_SINH_VIEN);
        assertThat(testSinhVien.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testSinhVien.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testSinhVien.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testSinhVien.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testSinhVien.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSinhVien.getSoDienThoai()).isEqualTo(UPDATED_SO_DIEN_THOAI);
        assertThat(testSinhVien.getNganh()).isEqualTo(UPDATED_NGANH);
        assertThat(testSinhVien.getKhoaHoc()).isEqualTo(UPDATED_KHOA_HOC);
        assertThat(testSinhVien.getLop()).isEqualTo(UPDATED_LOP);
    }

    @Test
    @Transactional
    void patchNonExistingSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();
        sinhVien.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSinhVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sinhVien.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sinhVien))
            )
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();
        sinhVien.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSinhVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sinhVien))
            )
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();
        sinhVien.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSinhVienMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSinhVien() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        int databaseSizeBeforeDelete = sinhVienRepository.findAll().size();

        // Delete the sinhVien
        restSinhVienMockMvc
            .perform(delete(ENTITY_API_URL_ID, sinhVien.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
