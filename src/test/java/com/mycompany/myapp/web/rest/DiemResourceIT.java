package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.repository.DiemRepository;
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
 * Integration tests for the {@link DiemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DiemResourceIT {

    private static final Double DEFAULT_CHUYEN_CAN = 0D;
    private static final Double UPDATED_CHUYEN_CAN = 1D;

    private static final Double DEFAULT_KIEM_TRA = 0D;
    private static final Double UPDATED_KIEM_TRA = 1D;

    private static final Double DEFAULT_THUC_HANH = 0D;
    private static final Double UPDATED_THUC_HANH = 1D;

    private static final Double DEFAULT_BAI_TAP = 0D;
    private static final Double UPDATED_BAI_TAP = 1D;

    private static final Double DEFAULT_THI = 0D;
    private static final Double UPDATED_THI = 1D;

    private static final Integer DEFAULT_SO_LAN_HOC = 1;
    private static final Integer UPDATED_SO_LAN_HOC = 2;

    private static final Double DEFAULT_TONG_KET = 0D;
    private static final Double UPDATED_TONG_KET = 1D;

    private static final String DEFAULT_TONG_KET_THANG_CHU = "AAAAAAAAAA";
    private static final String UPDATED_TONG_KET_THANG_CHU = "BBBBBBBBBB";

    private static final Status DEFAULT_KET_QUA = Status.PASS;
    private static final Status UPDATED_KET_QUA = Status.NOTPASS;

    private static final String ENTITY_API_URL = "/api/diems";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DiemRepository diemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDiemMockMvc;

    private Diem diem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Diem createEntity(EntityManager em) {
        Diem diem = new Diem()
            .chuyenCan(DEFAULT_CHUYEN_CAN)
            .kiemTra(DEFAULT_KIEM_TRA)
            .thucHanh(DEFAULT_THUC_HANH)
            .baiTap(DEFAULT_BAI_TAP)
            .thi(DEFAULT_THI)
            .soLanHoc(DEFAULT_SO_LAN_HOC)
            .tongKet(DEFAULT_TONG_KET)
            .tongKetThangChu(DEFAULT_TONG_KET_THANG_CHU)
            .ketQua(DEFAULT_KET_QUA);
        return diem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Diem createUpdatedEntity(EntityManager em) {
        Diem diem = new Diem()
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP)
            .thi(UPDATED_THI)
            .soLanHoc(UPDATED_SO_LAN_HOC)
            .tongKet(UPDATED_TONG_KET)
            .tongKetThangChu(UPDATED_TONG_KET_THANG_CHU)
            .ketQua(UPDATED_KET_QUA);
        return diem;
    }

    @BeforeEach
    public void initTest() {
        diem = createEntity(em);
    }

    @Test
    @Transactional
    void createDiem() throws Exception {
        int databaseSizeBeforeCreate = diemRepository.findAll().size();
        // Create the Diem
        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isCreated());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeCreate + 1);
        Diem testDiem = diemList.get(diemList.size() - 1);
        assertThat(testDiem.getChuyenCan()).isEqualTo(DEFAULT_CHUYEN_CAN);
        assertThat(testDiem.getKiemTra()).isEqualTo(DEFAULT_KIEM_TRA);
        assertThat(testDiem.getThucHanh()).isEqualTo(DEFAULT_THUC_HANH);
        assertThat(testDiem.getBaiTap()).isEqualTo(DEFAULT_BAI_TAP);
        assertThat(testDiem.getThi()).isEqualTo(DEFAULT_THI);
        assertThat(testDiem.getSoLanHoc()).isEqualTo(DEFAULT_SO_LAN_HOC);
        assertThat(testDiem.getTongKet()).isEqualTo(DEFAULT_TONG_KET);
        assertThat(testDiem.getTongKetThangChu()).isEqualTo(DEFAULT_TONG_KET_THANG_CHU);
        assertThat(testDiem.getKetQua()).isEqualTo(DEFAULT_KET_QUA);
    }

    @Test
    @Transactional
    void createDiemWithExistingId() throws Exception {
        // Create the Diem with an existing ID
        diem.setId(1L);

        int databaseSizeBeforeCreate = diemRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkChuyenCanIsRequired() throws Exception {
        int databaseSizeBeforeTest = diemRepository.findAll().size();
        // set the field null
        diem.setChuyenCan(null);

        // Create the Diem, which fails.

        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkKiemTraIsRequired() throws Exception {
        int databaseSizeBeforeTest = diemRepository.findAll().size();
        // set the field null
        diem.setKiemTra(null);

        // Create the Diem, which fails.

        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkThiIsRequired() throws Exception {
        int databaseSizeBeforeTest = diemRepository.findAll().size();
        // set the field null
        diem.setThi(null);

        // Create the Diem, which fails.

        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSoLanHocIsRequired() throws Exception {
        int databaseSizeBeforeTest = diemRepository.findAll().size();
        // set the field null
        diem.setSoLanHoc(null);

        // Create the Diem, which fails.

        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTongKetIsRequired() throws Exception {
        int databaseSizeBeforeTest = diemRepository.findAll().size();
        // set the field null
        diem.setTongKet(null);

        // Create the Diem, which fails.

        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTongKetThangChuIsRequired() throws Exception {
        int databaseSizeBeforeTest = diemRepository.findAll().size();
        // set the field null
        diem.setTongKetThangChu(null);

        // Create the Diem, which fails.

        restDiemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isBadRequest());

        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDiems() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList
        restDiemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diem.getId().intValue())))
            .andExpect(jsonPath("$.[*].chuyenCan").value(hasItem(DEFAULT_CHUYEN_CAN.doubleValue())))
            .andExpect(jsonPath("$.[*].kiemTra").value(hasItem(DEFAULT_KIEM_TRA.doubleValue())))
            .andExpect(jsonPath("$.[*].thucHanh").value(hasItem(DEFAULT_THUC_HANH.doubleValue())))
            .andExpect(jsonPath("$.[*].baiTap").value(hasItem(DEFAULT_BAI_TAP.doubleValue())))
            .andExpect(jsonPath("$.[*].thi").value(hasItem(DEFAULT_THI.doubleValue())))
            .andExpect(jsonPath("$.[*].soLanHoc").value(hasItem(DEFAULT_SO_LAN_HOC)))
            .andExpect(jsonPath("$.[*].tongKet").value(hasItem(DEFAULT_TONG_KET.doubleValue())))
            .andExpect(jsonPath("$.[*].tongKetThangChu").value(hasItem(DEFAULT_TONG_KET_THANG_CHU)))
            .andExpect(jsonPath("$.[*].ketQua").value(hasItem(DEFAULT_KET_QUA.toString())));
    }

    @Test
    @Transactional
    void getDiem() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get the diem
        restDiemMockMvc
            .perform(get(ENTITY_API_URL_ID, diem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(diem.getId().intValue()))
            .andExpect(jsonPath("$.chuyenCan").value(DEFAULT_CHUYEN_CAN.doubleValue()))
            .andExpect(jsonPath("$.kiemTra").value(DEFAULT_KIEM_TRA.doubleValue()))
            .andExpect(jsonPath("$.thucHanh").value(DEFAULT_THUC_HANH.doubleValue()))
            .andExpect(jsonPath("$.baiTap").value(DEFAULT_BAI_TAP.doubleValue()))
            .andExpect(jsonPath("$.thi").value(DEFAULT_THI.doubleValue()))
            .andExpect(jsonPath("$.soLanHoc").value(DEFAULT_SO_LAN_HOC))
            .andExpect(jsonPath("$.tongKet").value(DEFAULT_TONG_KET.doubleValue()))
            .andExpect(jsonPath("$.tongKetThangChu").value(DEFAULT_TONG_KET_THANG_CHU))
            .andExpect(jsonPath("$.ketQua").value(DEFAULT_KET_QUA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDiem() throws Exception {
        // Get the diem
        restDiemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDiem() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        int databaseSizeBeforeUpdate = diemRepository.findAll().size();

        // Update the diem
        Diem updatedDiem = diemRepository.findById(diem.getId()).get();
        // Disconnect from session so that the updates on updatedDiem are not directly saved in db
        em.detach(updatedDiem);
        updatedDiem
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP)
            .thi(UPDATED_THI)
            .soLanHoc(UPDATED_SO_LAN_HOC)
            .tongKet(UPDATED_TONG_KET)
            .tongKetThangChu(UPDATED_TONG_KET_THANG_CHU)
            .ketQua(UPDATED_KET_QUA);

        restDiemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDiem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDiem))
            )
            .andExpect(status().isOk());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
        Diem testDiem = diemList.get(diemList.size() - 1);
        assertThat(testDiem.getChuyenCan()).isEqualTo(UPDATED_CHUYEN_CAN);
        assertThat(testDiem.getKiemTra()).isEqualTo(UPDATED_KIEM_TRA);
        assertThat(testDiem.getThucHanh()).isEqualTo(UPDATED_THUC_HANH);
        assertThat(testDiem.getBaiTap()).isEqualTo(UPDATED_BAI_TAP);
        assertThat(testDiem.getThi()).isEqualTo(UPDATED_THI);
        assertThat(testDiem.getSoLanHoc()).isEqualTo(UPDATED_SO_LAN_HOC);
        assertThat(testDiem.getTongKet()).isEqualTo(UPDATED_TONG_KET);
        assertThat(testDiem.getTongKetThangChu()).isEqualTo(UPDATED_TONG_KET_THANG_CHU);
        assertThat(testDiem.getKetQua()).isEqualTo(UPDATED_KET_QUA);
    }

    @Test
    @Transactional
    void putNonExistingDiem() throws Exception {
        int databaseSizeBeforeUpdate = diemRepository.findAll().size();
        diem.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, diem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(diem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDiem() throws Exception {
        int databaseSizeBeforeUpdate = diemRepository.findAll().size();
        diem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(diem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDiem() throws Exception {
        int databaseSizeBeforeUpdate = diemRepository.findAll().size();
        diem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiemMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDiemWithPatch() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        int databaseSizeBeforeUpdate = diemRepository.findAll().size();

        // Update the diem using partial update
        Diem partialUpdatedDiem = new Diem();
        partialUpdatedDiem.setId(diem.getId());

        partialUpdatedDiem
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .soLanHoc(UPDATED_SO_LAN_HOC)
            .tongKet(UPDATED_TONG_KET)
            .tongKetThangChu(UPDATED_TONG_KET_THANG_CHU);

        restDiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDiem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDiem))
            )
            .andExpect(status().isOk());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
        Diem testDiem = diemList.get(diemList.size() - 1);
        assertThat(testDiem.getChuyenCan()).isEqualTo(UPDATED_CHUYEN_CAN);
        assertThat(testDiem.getKiemTra()).isEqualTo(UPDATED_KIEM_TRA);
        assertThat(testDiem.getThucHanh()).isEqualTo(DEFAULT_THUC_HANH);
        assertThat(testDiem.getBaiTap()).isEqualTo(DEFAULT_BAI_TAP);
        assertThat(testDiem.getThi()).isEqualTo(DEFAULT_THI);
        assertThat(testDiem.getSoLanHoc()).isEqualTo(UPDATED_SO_LAN_HOC);
        assertThat(testDiem.getTongKet()).isEqualTo(UPDATED_TONG_KET);
        assertThat(testDiem.getTongKetThangChu()).isEqualTo(UPDATED_TONG_KET_THANG_CHU);
        assertThat(testDiem.getKetQua()).isEqualTo(DEFAULT_KET_QUA);
    }

    @Test
    @Transactional
    void fullUpdateDiemWithPatch() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        int databaseSizeBeforeUpdate = diemRepository.findAll().size();

        // Update the diem using partial update
        Diem partialUpdatedDiem = new Diem();
        partialUpdatedDiem.setId(diem.getId());

        partialUpdatedDiem
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP)
            .thi(UPDATED_THI)
            .soLanHoc(UPDATED_SO_LAN_HOC)
            .tongKet(UPDATED_TONG_KET)
            .tongKetThangChu(UPDATED_TONG_KET_THANG_CHU)
            .ketQua(UPDATED_KET_QUA);

        restDiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDiem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDiem))
            )
            .andExpect(status().isOk());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
        Diem testDiem = diemList.get(diemList.size() - 1);
        assertThat(testDiem.getChuyenCan()).isEqualTo(UPDATED_CHUYEN_CAN);
        assertThat(testDiem.getKiemTra()).isEqualTo(UPDATED_KIEM_TRA);
        assertThat(testDiem.getThucHanh()).isEqualTo(UPDATED_THUC_HANH);
        assertThat(testDiem.getBaiTap()).isEqualTo(UPDATED_BAI_TAP);
        assertThat(testDiem.getThi()).isEqualTo(UPDATED_THI);
        assertThat(testDiem.getSoLanHoc()).isEqualTo(UPDATED_SO_LAN_HOC);
        assertThat(testDiem.getTongKet()).isEqualTo(UPDATED_TONG_KET);
        assertThat(testDiem.getTongKetThangChu()).isEqualTo(UPDATED_TONG_KET_THANG_CHU);
        assertThat(testDiem.getKetQua()).isEqualTo(UPDATED_KET_QUA);
    }

    @Test
    @Transactional
    void patchNonExistingDiem() throws Exception {
        int databaseSizeBeforeUpdate = diemRepository.findAll().size();
        diem.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, diem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(diem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDiem() throws Exception {
        int databaseSizeBeforeUpdate = diemRepository.findAll().size();
        diem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(diem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDiem() throws Exception {
        int databaseSizeBeforeUpdate = diemRepository.findAll().size();
        diem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiemMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(diem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Diem in the database
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDiem() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        int databaseSizeBeforeDelete = diemRepository.findAll().size();

        // Delete the diem
        restDiemMockMvc
            .perform(delete(ENTITY_API_URL_ID, diem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Diem> diemList = diemRepository.findAll();
        assertThat(diemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
