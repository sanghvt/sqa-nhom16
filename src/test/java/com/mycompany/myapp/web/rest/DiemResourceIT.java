package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.domain.MonHoc;
import com.mycompany.myapp.domain.SinhVien;
import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.repository.DiemRepository;
import com.mycompany.myapp.service.criteria.DiemCriteria;
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
    private static final Double SMALLER_CHUYEN_CAN = 0D - 1D;

    private static final Double DEFAULT_KIEM_TRA = 0D;
    private static final Double UPDATED_KIEM_TRA = 1D;
    private static final Double SMALLER_KIEM_TRA = 0D - 1D;

    private static final Double DEFAULT_THUC_HANH = 0D;
    private static final Double UPDATED_THUC_HANH = 1D;
    private static final Double SMALLER_THUC_HANH = 0D - 1D;

    private static final Double DEFAULT_BAI_TAP = 0D;
    private static final Double UPDATED_BAI_TAP = 1D;
    private static final Double SMALLER_BAI_TAP = 0D - 1D;

    private static final Double DEFAULT_THI = 0D;
    private static final Double UPDATED_THI = 1D;
    private static final Double SMALLER_THI = 0D - 1D;

    private static final Integer DEFAULT_SO_LAN_HOC = 1;
    private static final Integer UPDATED_SO_LAN_HOC = 2;
    private static final Integer SMALLER_SO_LAN_HOC = 1 - 1;

    private static final Double DEFAULT_TONG_KET = 0D;
    private static final Double UPDATED_TONG_KET = 1D;
    private static final Double SMALLER_TONG_KET = 0D - 1D;

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
    void getDiemsByIdFiltering() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        Long id = diem.getId();

        defaultDiemShouldBeFound("id.equals=" + id);
        defaultDiemShouldNotBeFound("id.notEquals=" + id);

        defaultDiemShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDiemShouldNotBeFound("id.greaterThan=" + id);

        defaultDiemShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDiemShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan equals to DEFAULT_CHUYEN_CAN
        defaultDiemShouldBeFound("chuyenCan.equals=" + DEFAULT_CHUYEN_CAN);

        // Get all the diemList where chuyenCan equals to UPDATED_CHUYEN_CAN
        defaultDiemShouldNotBeFound("chuyenCan.equals=" + UPDATED_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan not equals to DEFAULT_CHUYEN_CAN
        defaultDiemShouldNotBeFound("chuyenCan.notEquals=" + DEFAULT_CHUYEN_CAN);

        // Get all the diemList where chuyenCan not equals to UPDATED_CHUYEN_CAN
        defaultDiemShouldBeFound("chuyenCan.notEquals=" + UPDATED_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan in DEFAULT_CHUYEN_CAN or UPDATED_CHUYEN_CAN
        defaultDiemShouldBeFound("chuyenCan.in=" + DEFAULT_CHUYEN_CAN + "," + UPDATED_CHUYEN_CAN);

        // Get all the diemList where chuyenCan equals to UPDATED_CHUYEN_CAN
        defaultDiemShouldNotBeFound("chuyenCan.in=" + UPDATED_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan is not null
        defaultDiemShouldBeFound("chuyenCan.specified=true");

        // Get all the diemList where chuyenCan is null
        defaultDiemShouldNotBeFound("chuyenCan.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan is greater than or equal to DEFAULT_CHUYEN_CAN
        defaultDiemShouldBeFound("chuyenCan.greaterThanOrEqual=" + DEFAULT_CHUYEN_CAN);

        // Get all the diemList where chuyenCan is greater than or equal to (DEFAULT_CHUYEN_CAN + 1)
        defaultDiemShouldNotBeFound("chuyenCan.greaterThanOrEqual=" + (DEFAULT_CHUYEN_CAN + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan is less than or equal to DEFAULT_CHUYEN_CAN
        defaultDiemShouldBeFound("chuyenCan.lessThanOrEqual=" + DEFAULT_CHUYEN_CAN);

        // Get all the diemList where chuyenCan is less than or equal to SMALLER_CHUYEN_CAN
        defaultDiemShouldNotBeFound("chuyenCan.lessThanOrEqual=" + SMALLER_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan is less than DEFAULT_CHUYEN_CAN
        defaultDiemShouldNotBeFound("chuyenCan.lessThan=" + DEFAULT_CHUYEN_CAN);

        // Get all the diemList where chuyenCan is less than (DEFAULT_CHUYEN_CAN + 1)
        defaultDiemShouldBeFound("chuyenCan.lessThan=" + (DEFAULT_CHUYEN_CAN + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByChuyenCanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where chuyenCan is greater than DEFAULT_CHUYEN_CAN
        defaultDiemShouldNotBeFound("chuyenCan.greaterThan=" + DEFAULT_CHUYEN_CAN);

        // Get all the diemList where chuyenCan is greater than SMALLER_CHUYEN_CAN
        defaultDiemShouldBeFound("chuyenCan.greaterThan=" + SMALLER_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra equals to DEFAULT_KIEM_TRA
        defaultDiemShouldBeFound("kiemTra.equals=" + DEFAULT_KIEM_TRA);

        // Get all the diemList where kiemTra equals to UPDATED_KIEM_TRA
        defaultDiemShouldNotBeFound("kiemTra.equals=" + UPDATED_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra not equals to DEFAULT_KIEM_TRA
        defaultDiemShouldNotBeFound("kiemTra.notEquals=" + DEFAULT_KIEM_TRA);

        // Get all the diemList where kiemTra not equals to UPDATED_KIEM_TRA
        defaultDiemShouldBeFound("kiemTra.notEquals=" + UPDATED_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra in DEFAULT_KIEM_TRA or UPDATED_KIEM_TRA
        defaultDiemShouldBeFound("kiemTra.in=" + DEFAULT_KIEM_TRA + "," + UPDATED_KIEM_TRA);

        // Get all the diemList where kiemTra equals to UPDATED_KIEM_TRA
        defaultDiemShouldNotBeFound("kiemTra.in=" + UPDATED_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra is not null
        defaultDiemShouldBeFound("kiemTra.specified=true");

        // Get all the diemList where kiemTra is null
        defaultDiemShouldNotBeFound("kiemTra.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra is greater than or equal to DEFAULT_KIEM_TRA
        defaultDiemShouldBeFound("kiemTra.greaterThanOrEqual=" + DEFAULT_KIEM_TRA);

        // Get all the diemList where kiemTra is greater than or equal to (DEFAULT_KIEM_TRA + 1)
        defaultDiemShouldNotBeFound("kiemTra.greaterThanOrEqual=" + (DEFAULT_KIEM_TRA + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra is less than or equal to DEFAULT_KIEM_TRA
        defaultDiemShouldBeFound("kiemTra.lessThanOrEqual=" + DEFAULT_KIEM_TRA);

        // Get all the diemList where kiemTra is less than or equal to SMALLER_KIEM_TRA
        defaultDiemShouldNotBeFound("kiemTra.lessThanOrEqual=" + SMALLER_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra is less than DEFAULT_KIEM_TRA
        defaultDiemShouldNotBeFound("kiemTra.lessThan=" + DEFAULT_KIEM_TRA);

        // Get all the diemList where kiemTra is less than (DEFAULT_KIEM_TRA + 1)
        defaultDiemShouldBeFound("kiemTra.lessThan=" + (DEFAULT_KIEM_TRA + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByKiemTraIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where kiemTra is greater than DEFAULT_KIEM_TRA
        defaultDiemShouldNotBeFound("kiemTra.greaterThan=" + DEFAULT_KIEM_TRA);

        // Get all the diemList where kiemTra is greater than SMALLER_KIEM_TRA
        defaultDiemShouldBeFound("kiemTra.greaterThan=" + SMALLER_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh equals to DEFAULT_THUC_HANH
        defaultDiemShouldBeFound("thucHanh.equals=" + DEFAULT_THUC_HANH);

        // Get all the diemList where thucHanh equals to UPDATED_THUC_HANH
        defaultDiemShouldNotBeFound("thucHanh.equals=" + UPDATED_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh not equals to DEFAULT_THUC_HANH
        defaultDiemShouldNotBeFound("thucHanh.notEquals=" + DEFAULT_THUC_HANH);

        // Get all the diemList where thucHanh not equals to UPDATED_THUC_HANH
        defaultDiemShouldBeFound("thucHanh.notEquals=" + UPDATED_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh in DEFAULT_THUC_HANH or UPDATED_THUC_HANH
        defaultDiemShouldBeFound("thucHanh.in=" + DEFAULT_THUC_HANH + "," + UPDATED_THUC_HANH);

        // Get all the diemList where thucHanh equals to UPDATED_THUC_HANH
        defaultDiemShouldNotBeFound("thucHanh.in=" + UPDATED_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh is not null
        defaultDiemShouldBeFound("thucHanh.specified=true");

        // Get all the diemList where thucHanh is null
        defaultDiemShouldNotBeFound("thucHanh.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh is greater than or equal to DEFAULT_THUC_HANH
        defaultDiemShouldBeFound("thucHanh.greaterThanOrEqual=" + DEFAULT_THUC_HANH);

        // Get all the diemList where thucHanh is greater than or equal to (DEFAULT_THUC_HANH + 1)
        defaultDiemShouldNotBeFound("thucHanh.greaterThanOrEqual=" + (DEFAULT_THUC_HANH + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh is less than or equal to DEFAULT_THUC_HANH
        defaultDiemShouldBeFound("thucHanh.lessThanOrEqual=" + DEFAULT_THUC_HANH);

        // Get all the diemList where thucHanh is less than or equal to SMALLER_THUC_HANH
        defaultDiemShouldNotBeFound("thucHanh.lessThanOrEqual=" + SMALLER_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh is less than DEFAULT_THUC_HANH
        defaultDiemShouldNotBeFound("thucHanh.lessThan=" + DEFAULT_THUC_HANH);

        // Get all the diemList where thucHanh is less than (DEFAULT_THUC_HANH + 1)
        defaultDiemShouldBeFound("thucHanh.lessThan=" + (DEFAULT_THUC_HANH + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByThucHanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thucHanh is greater than DEFAULT_THUC_HANH
        defaultDiemShouldNotBeFound("thucHanh.greaterThan=" + DEFAULT_THUC_HANH);

        // Get all the diemList where thucHanh is greater than SMALLER_THUC_HANH
        defaultDiemShouldBeFound("thucHanh.greaterThan=" + SMALLER_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap equals to DEFAULT_BAI_TAP
        defaultDiemShouldBeFound("baiTap.equals=" + DEFAULT_BAI_TAP);

        // Get all the diemList where baiTap equals to UPDATED_BAI_TAP
        defaultDiemShouldNotBeFound("baiTap.equals=" + UPDATED_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap not equals to DEFAULT_BAI_TAP
        defaultDiemShouldNotBeFound("baiTap.notEquals=" + DEFAULT_BAI_TAP);

        // Get all the diemList where baiTap not equals to UPDATED_BAI_TAP
        defaultDiemShouldBeFound("baiTap.notEquals=" + UPDATED_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap in DEFAULT_BAI_TAP or UPDATED_BAI_TAP
        defaultDiemShouldBeFound("baiTap.in=" + DEFAULT_BAI_TAP + "," + UPDATED_BAI_TAP);

        // Get all the diemList where baiTap equals to UPDATED_BAI_TAP
        defaultDiemShouldNotBeFound("baiTap.in=" + UPDATED_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap is not null
        defaultDiemShouldBeFound("baiTap.specified=true");

        // Get all the diemList where baiTap is null
        defaultDiemShouldNotBeFound("baiTap.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap is greater than or equal to DEFAULT_BAI_TAP
        defaultDiemShouldBeFound("baiTap.greaterThanOrEqual=" + DEFAULT_BAI_TAP);

        // Get all the diemList where baiTap is greater than or equal to (DEFAULT_BAI_TAP + 1)
        defaultDiemShouldNotBeFound("baiTap.greaterThanOrEqual=" + (DEFAULT_BAI_TAP + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap is less than or equal to DEFAULT_BAI_TAP
        defaultDiemShouldBeFound("baiTap.lessThanOrEqual=" + DEFAULT_BAI_TAP);

        // Get all the diemList where baiTap is less than or equal to SMALLER_BAI_TAP
        defaultDiemShouldNotBeFound("baiTap.lessThanOrEqual=" + SMALLER_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap is less than DEFAULT_BAI_TAP
        defaultDiemShouldNotBeFound("baiTap.lessThan=" + DEFAULT_BAI_TAP);

        // Get all the diemList where baiTap is less than (DEFAULT_BAI_TAP + 1)
        defaultDiemShouldBeFound("baiTap.lessThan=" + (DEFAULT_BAI_TAP + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByBaiTapIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where baiTap is greater than DEFAULT_BAI_TAP
        defaultDiemShouldNotBeFound("baiTap.greaterThan=" + DEFAULT_BAI_TAP);

        // Get all the diemList where baiTap is greater than SMALLER_BAI_TAP
        defaultDiemShouldBeFound("baiTap.greaterThan=" + SMALLER_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi equals to DEFAULT_THI
        defaultDiemShouldBeFound("thi.equals=" + DEFAULT_THI);

        // Get all the diemList where thi equals to UPDATED_THI
        defaultDiemShouldNotBeFound("thi.equals=" + UPDATED_THI);
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi not equals to DEFAULT_THI
        defaultDiemShouldNotBeFound("thi.notEquals=" + DEFAULT_THI);

        // Get all the diemList where thi not equals to UPDATED_THI
        defaultDiemShouldBeFound("thi.notEquals=" + UPDATED_THI);
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi in DEFAULT_THI or UPDATED_THI
        defaultDiemShouldBeFound("thi.in=" + DEFAULT_THI + "," + UPDATED_THI);

        // Get all the diemList where thi equals to UPDATED_THI
        defaultDiemShouldNotBeFound("thi.in=" + UPDATED_THI);
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi is not null
        defaultDiemShouldBeFound("thi.specified=true");

        // Get all the diemList where thi is null
        defaultDiemShouldNotBeFound("thi.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi is greater than or equal to DEFAULT_THI
        defaultDiemShouldBeFound("thi.greaterThanOrEqual=" + DEFAULT_THI);

        // Get all the diemList where thi is greater than or equal to (DEFAULT_THI + 1)
        defaultDiemShouldNotBeFound("thi.greaterThanOrEqual=" + (DEFAULT_THI + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi is less than or equal to DEFAULT_THI
        defaultDiemShouldBeFound("thi.lessThanOrEqual=" + DEFAULT_THI);

        // Get all the diemList where thi is less than or equal to SMALLER_THI
        defaultDiemShouldNotBeFound("thi.lessThanOrEqual=" + SMALLER_THI);
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi is less than DEFAULT_THI
        defaultDiemShouldNotBeFound("thi.lessThan=" + DEFAULT_THI);

        // Get all the diemList where thi is less than (DEFAULT_THI + 1)
        defaultDiemShouldBeFound("thi.lessThan=" + (DEFAULT_THI + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByThiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where thi is greater than DEFAULT_THI
        defaultDiemShouldNotBeFound("thi.greaterThan=" + DEFAULT_THI);

        // Get all the diemList where thi is greater than SMALLER_THI
        defaultDiemShouldBeFound("thi.greaterThan=" + SMALLER_THI);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc equals to DEFAULT_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.equals=" + DEFAULT_SO_LAN_HOC);

        // Get all the diemList where soLanHoc equals to UPDATED_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.equals=" + UPDATED_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc not equals to DEFAULT_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.notEquals=" + DEFAULT_SO_LAN_HOC);

        // Get all the diemList where soLanHoc not equals to UPDATED_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.notEquals=" + UPDATED_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc in DEFAULT_SO_LAN_HOC or UPDATED_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.in=" + DEFAULT_SO_LAN_HOC + "," + UPDATED_SO_LAN_HOC);

        // Get all the diemList where soLanHoc equals to UPDATED_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.in=" + UPDATED_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc is not null
        defaultDiemShouldBeFound("soLanHoc.specified=true");

        // Get all the diemList where soLanHoc is null
        defaultDiemShouldNotBeFound("soLanHoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc is greater than or equal to DEFAULT_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.greaterThanOrEqual=" + DEFAULT_SO_LAN_HOC);

        // Get all the diemList where soLanHoc is greater than or equal to UPDATED_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.greaterThanOrEqual=" + UPDATED_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc is less than or equal to DEFAULT_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.lessThanOrEqual=" + DEFAULT_SO_LAN_HOC);

        // Get all the diemList where soLanHoc is less than or equal to SMALLER_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.lessThanOrEqual=" + SMALLER_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc is less than DEFAULT_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.lessThan=" + DEFAULT_SO_LAN_HOC);

        // Get all the diemList where soLanHoc is less than UPDATED_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.lessThan=" + UPDATED_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsBySoLanHocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where soLanHoc is greater than DEFAULT_SO_LAN_HOC
        defaultDiemShouldNotBeFound("soLanHoc.greaterThan=" + DEFAULT_SO_LAN_HOC);

        // Get all the diemList where soLanHoc is greater than SMALLER_SO_LAN_HOC
        defaultDiemShouldBeFound("soLanHoc.greaterThan=" + SMALLER_SO_LAN_HOC);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet equals to DEFAULT_TONG_KET
        defaultDiemShouldBeFound("tongKet.equals=" + DEFAULT_TONG_KET);

        // Get all the diemList where tongKet equals to UPDATED_TONG_KET
        defaultDiemShouldNotBeFound("tongKet.equals=" + UPDATED_TONG_KET);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet not equals to DEFAULT_TONG_KET
        defaultDiemShouldNotBeFound("tongKet.notEquals=" + DEFAULT_TONG_KET);

        // Get all the diemList where tongKet not equals to UPDATED_TONG_KET
        defaultDiemShouldBeFound("tongKet.notEquals=" + UPDATED_TONG_KET);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet in DEFAULT_TONG_KET or UPDATED_TONG_KET
        defaultDiemShouldBeFound("tongKet.in=" + DEFAULT_TONG_KET + "," + UPDATED_TONG_KET);

        // Get all the diemList where tongKet equals to UPDATED_TONG_KET
        defaultDiemShouldNotBeFound("tongKet.in=" + UPDATED_TONG_KET);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet is not null
        defaultDiemShouldBeFound("tongKet.specified=true");

        // Get all the diemList where tongKet is null
        defaultDiemShouldNotBeFound("tongKet.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet is greater than or equal to DEFAULT_TONG_KET
        defaultDiemShouldBeFound("tongKet.greaterThanOrEqual=" + DEFAULT_TONG_KET);

        // Get all the diemList where tongKet is greater than or equal to (DEFAULT_TONG_KET + 1)
        defaultDiemShouldNotBeFound("tongKet.greaterThanOrEqual=" + (DEFAULT_TONG_KET + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet is less than or equal to DEFAULT_TONG_KET
        defaultDiemShouldBeFound("tongKet.lessThanOrEqual=" + DEFAULT_TONG_KET);

        // Get all the diemList where tongKet is less than or equal to SMALLER_TONG_KET
        defaultDiemShouldNotBeFound("tongKet.lessThanOrEqual=" + SMALLER_TONG_KET);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsLessThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet is less than DEFAULT_TONG_KET
        defaultDiemShouldNotBeFound("tongKet.lessThan=" + DEFAULT_TONG_KET);

        // Get all the diemList where tongKet is less than (DEFAULT_TONG_KET + 1)
        defaultDiemShouldBeFound("tongKet.lessThan=" + (DEFAULT_TONG_KET + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetIsGreaterThanSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKet is greater than DEFAULT_TONG_KET
        defaultDiemShouldNotBeFound("tongKet.greaterThan=" + DEFAULT_TONG_KET);

        // Get all the diemList where tongKet is greater than SMALLER_TONG_KET
        defaultDiemShouldBeFound("tongKet.greaterThan=" + SMALLER_TONG_KET);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetThangChuIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKetThangChu equals to DEFAULT_TONG_KET_THANG_CHU
        defaultDiemShouldBeFound("tongKetThangChu.equals=" + DEFAULT_TONG_KET_THANG_CHU);

        // Get all the diemList where tongKetThangChu equals to UPDATED_TONG_KET_THANG_CHU
        defaultDiemShouldNotBeFound("tongKetThangChu.equals=" + UPDATED_TONG_KET_THANG_CHU);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetThangChuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKetThangChu not equals to DEFAULT_TONG_KET_THANG_CHU
        defaultDiemShouldNotBeFound("tongKetThangChu.notEquals=" + DEFAULT_TONG_KET_THANG_CHU);

        // Get all the diemList where tongKetThangChu not equals to UPDATED_TONG_KET_THANG_CHU
        defaultDiemShouldBeFound("tongKetThangChu.notEquals=" + UPDATED_TONG_KET_THANG_CHU);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetThangChuIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKetThangChu in DEFAULT_TONG_KET_THANG_CHU or UPDATED_TONG_KET_THANG_CHU
        defaultDiemShouldBeFound("tongKetThangChu.in=" + DEFAULT_TONG_KET_THANG_CHU + "," + UPDATED_TONG_KET_THANG_CHU);

        // Get all the diemList where tongKetThangChu equals to UPDATED_TONG_KET_THANG_CHU
        defaultDiemShouldNotBeFound("tongKetThangChu.in=" + UPDATED_TONG_KET_THANG_CHU);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetThangChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKetThangChu is not null
        defaultDiemShouldBeFound("tongKetThangChu.specified=true");

        // Get all the diemList where tongKetThangChu is null
        defaultDiemShouldNotBeFound("tongKetThangChu.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetThangChuContainsSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKetThangChu contains DEFAULT_TONG_KET_THANG_CHU
        defaultDiemShouldBeFound("tongKetThangChu.contains=" + DEFAULT_TONG_KET_THANG_CHU);

        // Get all the diemList where tongKetThangChu contains UPDATED_TONG_KET_THANG_CHU
        defaultDiemShouldNotBeFound("tongKetThangChu.contains=" + UPDATED_TONG_KET_THANG_CHU);
    }

    @Test
    @Transactional
    void getAllDiemsByTongKetThangChuNotContainsSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where tongKetThangChu does not contain DEFAULT_TONG_KET_THANG_CHU
        defaultDiemShouldNotBeFound("tongKetThangChu.doesNotContain=" + DEFAULT_TONG_KET_THANG_CHU);

        // Get all the diemList where tongKetThangChu does not contain UPDATED_TONG_KET_THANG_CHU
        defaultDiemShouldBeFound("tongKetThangChu.doesNotContain=" + UPDATED_TONG_KET_THANG_CHU);
    }

    @Test
    @Transactional
    void getAllDiemsByKetQuaIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where ketQua equals to DEFAULT_KET_QUA
        defaultDiemShouldBeFound("ketQua.equals=" + DEFAULT_KET_QUA);

        // Get all the diemList where ketQua equals to UPDATED_KET_QUA
        defaultDiemShouldNotBeFound("ketQua.equals=" + UPDATED_KET_QUA);
    }

    @Test
    @Transactional
    void getAllDiemsByKetQuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where ketQua not equals to DEFAULT_KET_QUA
        defaultDiemShouldNotBeFound("ketQua.notEquals=" + DEFAULT_KET_QUA);

        // Get all the diemList where ketQua not equals to UPDATED_KET_QUA
        defaultDiemShouldBeFound("ketQua.notEquals=" + UPDATED_KET_QUA);
    }

    @Test
    @Transactional
    void getAllDiemsByKetQuaIsInShouldWork() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where ketQua in DEFAULT_KET_QUA or UPDATED_KET_QUA
        defaultDiemShouldBeFound("ketQua.in=" + DEFAULT_KET_QUA + "," + UPDATED_KET_QUA);

        // Get all the diemList where ketQua equals to UPDATED_KET_QUA
        defaultDiemShouldNotBeFound("ketQua.in=" + UPDATED_KET_QUA);
    }

    @Test
    @Transactional
    void getAllDiemsByKetQuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);

        // Get all the diemList where ketQua is not null
        defaultDiemShouldBeFound("ketQua.specified=true");

        // Get all the diemList where ketQua is null
        defaultDiemShouldNotBeFound("ketQua.specified=false");
    }

    @Test
    @Transactional
    void getAllDiemsByMaSinhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);
        SinhVien maSinhVien = SinhVienResourceIT.createEntity(em);
        em.persist(maSinhVien);
        em.flush();
        diem.setMaSinhVien(maSinhVien);
        diemRepository.saveAndFlush(diem);
        Long maSinhVienId = maSinhVien.getId();

        // Get all the diemList where maSinhVien equals to maSinhVienId
        defaultDiemShouldBeFound("maSinhVienId.equals=" + maSinhVienId);

        // Get all the diemList where maSinhVien equals to (maSinhVienId + 1)
        defaultDiemShouldNotBeFound("maSinhVienId.equals=" + (maSinhVienId + 1));
    }

    @Test
    @Transactional
    void getAllDiemsByMaMonHocIsEqualToSomething() throws Exception {
        // Initialize the database
        diemRepository.saveAndFlush(diem);
        MonHoc maMonHoc = MonHocResourceIT.createEntity(em);
        em.persist(maMonHoc);
        em.flush();
        diem.setMaMonHoc(maMonHoc);
        diemRepository.saveAndFlush(diem);
        Long maMonHocId = maMonHoc.getId();

        // Get all the diemList where maMonHoc equals to maMonHocId
        defaultDiemShouldBeFound("maMonHocId.equals=" + maMonHocId);

        // Get all the diemList where maMonHoc equals to (maMonHocId + 1)
        defaultDiemShouldNotBeFound("maMonHocId.equals=" + (maMonHocId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDiemShouldBeFound(String filter) throws Exception {
        restDiemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restDiemMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDiemShouldNotBeFound(String filter) throws Exception {
        restDiemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDiemMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
