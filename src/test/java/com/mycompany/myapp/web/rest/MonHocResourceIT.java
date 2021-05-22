package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MonHoc;
import com.mycompany.myapp.repository.MonHocRepository;
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
 * Integration tests for the {@link MonHocResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MonHocResourceIT {

    private static final String DEFAULT_MA_MON_HOC = "AAAAAAAAAA";
    private static final String UPDATED_MA_MON_HOC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_MON_HOC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_MON_HOC = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_TIN_CHI = 1;
    private static final Integer UPDATED_SO_TIN_CHI = 2;

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

    private static final String ENTITY_API_URL = "/api/mon-hocs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMonHocMockMvc;

    private MonHoc monHoc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MonHoc createEntity(EntityManager em) {
        MonHoc monHoc = new MonHoc()
            .maMonHoc(DEFAULT_MA_MON_HOC)
            .tenMonHoc(DEFAULT_TEN_MON_HOC)
            .soTinChi(DEFAULT_SO_TIN_CHI)
            .chuyenCan(DEFAULT_CHUYEN_CAN)
            .kiemTra(DEFAULT_KIEM_TRA)
            .thucHanh(DEFAULT_THUC_HANH)
            .baiTap(DEFAULT_BAI_TAP)
            .thi(DEFAULT_THI);
        return monHoc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MonHoc createUpdatedEntity(EntityManager em) {
        MonHoc monHoc = new MonHoc()
            .maMonHoc(UPDATED_MA_MON_HOC)
            .tenMonHoc(UPDATED_TEN_MON_HOC)
            .soTinChi(UPDATED_SO_TIN_CHI)
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP)
            .thi(UPDATED_THI);
        return monHoc;
    }

    @BeforeEach
    public void initTest() {
        monHoc = createEntity(em);
    }

    @Test
    @Transactional
    void createMonHoc() throws Exception {
        int databaseSizeBeforeCreate = monHocRepository.findAll().size();
        // Create the MonHoc
        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isCreated());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeCreate + 1);
        MonHoc testMonHoc = monHocList.get(monHocList.size() - 1);
        assertThat(testMonHoc.getMaMonHoc()).isEqualTo(DEFAULT_MA_MON_HOC);
        assertThat(testMonHoc.getTenMonHoc()).isEqualTo(DEFAULT_TEN_MON_HOC);
        assertThat(testMonHoc.getSoTinChi()).isEqualTo(DEFAULT_SO_TIN_CHI);
        assertThat(testMonHoc.getChuyenCan()).isEqualTo(DEFAULT_CHUYEN_CAN);
        assertThat(testMonHoc.getKiemTra()).isEqualTo(DEFAULT_KIEM_TRA);
        assertThat(testMonHoc.getThucHanh()).isEqualTo(DEFAULT_THUC_HANH);
        assertThat(testMonHoc.getBaiTap()).isEqualTo(DEFAULT_BAI_TAP);
        assertThat(testMonHoc.getThi()).isEqualTo(DEFAULT_THI);
    }

    @Test
    @Transactional
    void createMonHocWithExistingId() throws Exception {
        // Create the MonHoc with an existing ID
        monHoc.setId(1L);

        int databaseSizeBeforeCreate = monHocRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMaMonHocIsRequired() throws Exception {
        int databaseSizeBeforeTest = monHocRepository.findAll().size();
        // set the field null
        monHoc.setMaMonHoc(null);

        // Create the MonHoc, which fails.

        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenMonHocIsRequired() throws Exception {
        int databaseSizeBeforeTest = monHocRepository.findAll().size();
        // set the field null
        monHoc.setTenMonHoc(null);

        // Create the MonHoc, which fails.

        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSoTinChiIsRequired() throws Exception {
        int databaseSizeBeforeTest = monHocRepository.findAll().size();
        // set the field null
        monHoc.setSoTinChi(null);

        // Create the MonHoc, which fails.

        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChuyenCanIsRequired() throws Exception {
        int databaseSizeBeforeTest = monHocRepository.findAll().size();
        // set the field null
        monHoc.setChuyenCan(null);

        // Create the MonHoc, which fails.

        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkKiemTraIsRequired() throws Exception {
        int databaseSizeBeforeTest = monHocRepository.findAll().size();
        // set the field null
        monHoc.setKiemTra(null);

        // Create the MonHoc, which fails.

        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkThiIsRequired() throws Exception {
        int databaseSizeBeforeTest = monHocRepository.findAll().size();
        // set the field null
        monHoc.setThi(null);

        // Create the MonHoc, which fails.

        restMonHocMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isBadRequest());

        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMonHocs() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList
        restMonHocMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monHoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMonHoc").value(hasItem(DEFAULT_MA_MON_HOC)))
            .andExpect(jsonPath("$.[*].tenMonHoc").value(hasItem(DEFAULT_TEN_MON_HOC)))
            .andExpect(jsonPath("$.[*].soTinChi").value(hasItem(DEFAULT_SO_TIN_CHI)))
            .andExpect(jsonPath("$.[*].chuyenCan").value(hasItem(DEFAULT_CHUYEN_CAN.doubleValue())))
            .andExpect(jsonPath("$.[*].kiemTra").value(hasItem(DEFAULT_KIEM_TRA.doubleValue())))
            .andExpect(jsonPath("$.[*].thucHanh").value(hasItem(DEFAULT_THUC_HANH.doubleValue())))
            .andExpect(jsonPath("$.[*].baiTap").value(hasItem(DEFAULT_BAI_TAP.doubleValue())))
            .andExpect(jsonPath("$.[*].thi").value(hasItem(DEFAULT_THI.doubleValue())));
    }

    @Test
    @Transactional
    void getMonHoc() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get the monHoc
        restMonHocMockMvc
            .perform(get(ENTITY_API_URL_ID, monHoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(monHoc.getId().intValue()))
            .andExpect(jsonPath("$.maMonHoc").value(DEFAULT_MA_MON_HOC))
            .andExpect(jsonPath("$.tenMonHoc").value(DEFAULT_TEN_MON_HOC))
            .andExpect(jsonPath("$.soTinChi").value(DEFAULT_SO_TIN_CHI))
            .andExpect(jsonPath("$.chuyenCan").value(DEFAULT_CHUYEN_CAN.doubleValue()))
            .andExpect(jsonPath("$.kiemTra").value(DEFAULT_KIEM_TRA.doubleValue()))
            .andExpect(jsonPath("$.thucHanh").value(DEFAULT_THUC_HANH.doubleValue()))
            .andExpect(jsonPath("$.baiTap").value(DEFAULT_BAI_TAP.doubleValue()))
            .andExpect(jsonPath("$.thi").value(DEFAULT_THI.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingMonHoc() throws Exception {
        // Get the monHoc
        restMonHocMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMonHoc() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();

        // Update the monHoc
        MonHoc updatedMonHoc = monHocRepository.findById(monHoc.getId()).get();
        // Disconnect from session so that the updates on updatedMonHoc are not directly saved in db
        em.detach(updatedMonHoc);
        updatedMonHoc
            .maMonHoc(UPDATED_MA_MON_HOC)
            .tenMonHoc(UPDATED_TEN_MON_HOC)
            .soTinChi(UPDATED_SO_TIN_CHI)
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP)
            .thi(UPDATED_THI);

        restMonHocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMonHoc.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMonHoc))
            )
            .andExpect(status().isOk());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
        MonHoc testMonHoc = monHocList.get(monHocList.size() - 1);
        assertThat(testMonHoc.getMaMonHoc()).isEqualTo(UPDATED_MA_MON_HOC);
        assertThat(testMonHoc.getTenMonHoc()).isEqualTo(UPDATED_TEN_MON_HOC);
        assertThat(testMonHoc.getSoTinChi()).isEqualTo(UPDATED_SO_TIN_CHI);
        assertThat(testMonHoc.getChuyenCan()).isEqualTo(UPDATED_CHUYEN_CAN);
        assertThat(testMonHoc.getKiemTra()).isEqualTo(UPDATED_KIEM_TRA);
        assertThat(testMonHoc.getThucHanh()).isEqualTo(UPDATED_THUC_HANH);
        assertThat(testMonHoc.getBaiTap()).isEqualTo(UPDATED_BAI_TAP);
        assertThat(testMonHoc.getThi()).isEqualTo(UPDATED_THI);
    }

    @Test
    @Transactional
    void putNonExistingMonHoc() throws Exception {
        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();
        monHoc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMonHocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, monHoc.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(monHoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMonHoc() throws Exception {
        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();
        monHoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonHocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(monHoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMonHoc() throws Exception {
        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();
        monHoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonHocMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMonHocWithPatch() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();

        // Update the monHoc using partial update
        MonHoc partialUpdatedMonHoc = new MonHoc();
        partialUpdatedMonHoc.setId(monHoc.getId());

        partialUpdatedMonHoc
            .maMonHoc(UPDATED_MA_MON_HOC)
            .tenMonHoc(UPDATED_TEN_MON_HOC)
            .soTinChi(UPDATED_SO_TIN_CHI)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP);

        restMonHocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMonHoc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMonHoc))
            )
            .andExpect(status().isOk());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
        MonHoc testMonHoc = monHocList.get(monHocList.size() - 1);
        assertThat(testMonHoc.getMaMonHoc()).isEqualTo(UPDATED_MA_MON_HOC);
        assertThat(testMonHoc.getTenMonHoc()).isEqualTo(UPDATED_TEN_MON_HOC);
        assertThat(testMonHoc.getSoTinChi()).isEqualTo(UPDATED_SO_TIN_CHI);
        assertThat(testMonHoc.getChuyenCan()).isEqualTo(DEFAULT_CHUYEN_CAN);
        assertThat(testMonHoc.getKiemTra()).isEqualTo(DEFAULT_KIEM_TRA);
        assertThat(testMonHoc.getThucHanh()).isEqualTo(UPDATED_THUC_HANH);
        assertThat(testMonHoc.getBaiTap()).isEqualTo(UPDATED_BAI_TAP);
        assertThat(testMonHoc.getThi()).isEqualTo(DEFAULT_THI);
    }

    @Test
    @Transactional
    void fullUpdateMonHocWithPatch() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();

        // Update the monHoc using partial update
        MonHoc partialUpdatedMonHoc = new MonHoc();
        partialUpdatedMonHoc.setId(monHoc.getId());

        partialUpdatedMonHoc
            .maMonHoc(UPDATED_MA_MON_HOC)
            .tenMonHoc(UPDATED_TEN_MON_HOC)
            .soTinChi(UPDATED_SO_TIN_CHI)
            .chuyenCan(UPDATED_CHUYEN_CAN)
            .kiemTra(UPDATED_KIEM_TRA)
            .thucHanh(UPDATED_THUC_HANH)
            .baiTap(UPDATED_BAI_TAP)
            .thi(UPDATED_THI);

        restMonHocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMonHoc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMonHoc))
            )
            .andExpect(status().isOk());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
        MonHoc testMonHoc = monHocList.get(monHocList.size() - 1);
        assertThat(testMonHoc.getMaMonHoc()).isEqualTo(UPDATED_MA_MON_HOC);
        assertThat(testMonHoc.getTenMonHoc()).isEqualTo(UPDATED_TEN_MON_HOC);
        assertThat(testMonHoc.getSoTinChi()).isEqualTo(UPDATED_SO_TIN_CHI);
        assertThat(testMonHoc.getChuyenCan()).isEqualTo(UPDATED_CHUYEN_CAN);
        assertThat(testMonHoc.getKiemTra()).isEqualTo(UPDATED_KIEM_TRA);
        assertThat(testMonHoc.getThucHanh()).isEqualTo(UPDATED_THUC_HANH);
        assertThat(testMonHoc.getBaiTap()).isEqualTo(UPDATED_BAI_TAP);
        assertThat(testMonHoc.getThi()).isEqualTo(UPDATED_THI);
    }

    @Test
    @Transactional
    void patchNonExistingMonHoc() throws Exception {
        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();
        monHoc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMonHocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, monHoc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(monHoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMonHoc() throws Exception {
        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();
        monHoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonHocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(monHoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMonHoc() throws Exception {
        int databaseSizeBeforeUpdate = monHocRepository.findAll().size();
        monHoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonHocMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(monHoc)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MonHoc in the database
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMonHoc() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        int databaseSizeBeforeDelete = monHocRepository.findAll().size();

        // Delete the monHoc
        restMonHocMockMvc
            .perform(delete(ENTITY_API_URL_ID, monHoc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MonHoc> monHocList = monHocRepository.findAll();
        assertThat(monHocList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
