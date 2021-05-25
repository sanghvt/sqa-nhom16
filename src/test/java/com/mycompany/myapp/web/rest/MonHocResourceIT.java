package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.domain.MonHoc;
import com.mycompany.myapp.repository.MonHocRepository;
import com.mycompany.myapp.service.criteria.MonHocCriteria;
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
    private static final Integer SMALLER_SO_TIN_CHI = 1 - 1;

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
    void getMonHocsByIdFiltering() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        Long id = monHoc.getId();

        defaultMonHocShouldBeFound("id.equals=" + id);
        defaultMonHocShouldNotBeFound("id.notEquals=" + id);

        defaultMonHocShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMonHocShouldNotBeFound("id.greaterThan=" + id);

        defaultMonHocShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMonHocShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllMonHocsByMaMonHocIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where maMonHoc equals to DEFAULT_MA_MON_HOC
        defaultMonHocShouldBeFound("maMonHoc.equals=" + DEFAULT_MA_MON_HOC);

        // Get all the monHocList where maMonHoc equals to UPDATED_MA_MON_HOC
        defaultMonHocShouldNotBeFound("maMonHoc.equals=" + UPDATED_MA_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByMaMonHocIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where maMonHoc not equals to DEFAULT_MA_MON_HOC
        defaultMonHocShouldNotBeFound("maMonHoc.notEquals=" + DEFAULT_MA_MON_HOC);

        // Get all the monHocList where maMonHoc not equals to UPDATED_MA_MON_HOC
        defaultMonHocShouldBeFound("maMonHoc.notEquals=" + UPDATED_MA_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByMaMonHocIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where maMonHoc in DEFAULT_MA_MON_HOC or UPDATED_MA_MON_HOC
        defaultMonHocShouldBeFound("maMonHoc.in=" + DEFAULT_MA_MON_HOC + "," + UPDATED_MA_MON_HOC);

        // Get all the monHocList where maMonHoc equals to UPDATED_MA_MON_HOC
        defaultMonHocShouldNotBeFound("maMonHoc.in=" + UPDATED_MA_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByMaMonHocIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where maMonHoc is not null
        defaultMonHocShouldBeFound("maMonHoc.specified=true");

        // Get all the monHocList where maMonHoc is null
        defaultMonHocShouldNotBeFound("maMonHoc.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByMaMonHocContainsSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where maMonHoc contains DEFAULT_MA_MON_HOC
        defaultMonHocShouldBeFound("maMonHoc.contains=" + DEFAULT_MA_MON_HOC);

        // Get all the monHocList where maMonHoc contains UPDATED_MA_MON_HOC
        defaultMonHocShouldNotBeFound("maMonHoc.contains=" + UPDATED_MA_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByMaMonHocNotContainsSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where maMonHoc does not contain DEFAULT_MA_MON_HOC
        defaultMonHocShouldNotBeFound("maMonHoc.doesNotContain=" + DEFAULT_MA_MON_HOC);

        // Get all the monHocList where maMonHoc does not contain UPDATED_MA_MON_HOC
        defaultMonHocShouldBeFound("maMonHoc.doesNotContain=" + UPDATED_MA_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByTenMonHocIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where tenMonHoc equals to DEFAULT_TEN_MON_HOC
        defaultMonHocShouldBeFound("tenMonHoc.equals=" + DEFAULT_TEN_MON_HOC);

        // Get all the monHocList where tenMonHoc equals to UPDATED_TEN_MON_HOC
        defaultMonHocShouldNotBeFound("tenMonHoc.equals=" + UPDATED_TEN_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByTenMonHocIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where tenMonHoc not equals to DEFAULT_TEN_MON_HOC
        defaultMonHocShouldNotBeFound("tenMonHoc.notEquals=" + DEFAULT_TEN_MON_HOC);

        // Get all the monHocList where tenMonHoc not equals to UPDATED_TEN_MON_HOC
        defaultMonHocShouldBeFound("tenMonHoc.notEquals=" + UPDATED_TEN_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByTenMonHocIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where tenMonHoc in DEFAULT_TEN_MON_HOC or UPDATED_TEN_MON_HOC
        defaultMonHocShouldBeFound("tenMonHoc.in=" + DEFAULT_TEN_MON_HOC + "," + UPDATED_TEN_MON_HOC);

        // Get all the monHocList where tenMonHoc equals to UPDATED_TEN_MON_HOC
        defaultMonHocShouldNotBeFound("tenMonHoc.in=" + UPDATED_TEN_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByTenMonHocIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where tenMonHoc is not null
        defaultMonHocShouldBeFound("tenMonHoc.specified=true");

        // Get all the monHocList where tenMonHoc is null
        defaultMonHocShouldNotBeFound("tenMonHoc.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByTenMonHocContainsSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where tenMonHoc contains DEFAULT_TEN_MON_HOC
        defaultMonHocShouldBeFound("tenMonHoc.contains=" + DEFAULT_TEN_MON_HOC);

        // Get all the monHocList where tenMonHoc contains UPDATED_TEN_MON_HOC
        defaultMonHocShouldNotBeFound("tenMonHoc.contains=" + UPDATED_TEN_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsByTenMonHocNotContainsSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where tenMonHoc does not contain DEFAULT_TEN_MON_HOC
        defaultMonHocShouldNotBeFound("tenMonHoc.doesNotContain=" + DEFAULT_TEN_MON_HOC);

        // Get all the monHocList where tenMonHoc does not contain UPDATED_TEN_MON_HOC
        defaultMonHocShouldBeFound("tenMonHoc.doesNotContain=" + UPDATED_TEN_MON_HOC);
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi equals to DEFAULT_SO_TIN_CHI
        defaultMonHocShouldBeFound("soTinChi.equals=" + DEFAULT_SO_TIN_CHI);

        // Get all the monHocList where soTinChi equals to UPDATED_SO_TIN_CHI
        defaultMonHocShouldNotBeFound("soTinChi.equals=" + UPDATED_SO_TIN_CHI);
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi not equals to DEFAULT_SO_TIN_CHI
        defaultMonHocShouldNotBeFound("soTinChi.notEquals=" + DEFAULT_SO_TIN_CHI);

        // Get all the monHocList where soTinChi not equals to UPDATED_SO_TIN_CHI
        defaultMonHocShouldBeFound("soTinChi.notEquals=" + UPDATED_SO_TIN_CHI);
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi in DEFAULT_SO_TIN_CHI or UPDATED_SO_TIN_CHI
        defaultMonHocShouldBeFound("soTinChi.in=" + DEFAULT_SO_TIN_CHI + "," + UPDATED_SO_TIN_CHI);

        // Get all the monHocList where soTinChi equals to UPDATED_SO_TIN_CHI
        defaultMonHocShouldNotBeFound("soTinChi.in=" + UPDATED_SO_TIN_CHI);
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi is not null
        defaultMonHocShouldBeFound("soTinChi.specified=true");

        // Get all the monHocList where soTinChi is null
        defaultMonHocShouldNotBeFound("soTinChi.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi is greater than or equal to DEFAULT_SO_TIN_CHI
        defaultMonHocShouldBeFound("soTinChi.greaterThanOrEqual=" + DEFAULT_SO_TIN_CHI);

        // Get all the monHocList where soTinChi is greater than or equal to (DEFAULT_SO_TIN_CHI + 1)
        defaultMonHocShouldNotBeFound("soTinChi.greaterThanOrEqual=" + (DEFAULT_SO_TIN_CHI + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi is less than or equal to DEFAULT_SO_TIN_CHI
        defaultMonHocShouldBeFound("soTinChi.lessThanOrEqual=" + DEFAULT_SO_TIN_CHI);

        // Get all the monHocList where soTinChi is less than or equal to SMALLER_SO_TIN_CHI
        defaultMonHocShouldNotBeFound("soTinChi.lessThanOrEqual=" + SMALLER_SO_TIN_CHI);
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsLessThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi is less than DEFAULT_SO_TIN_CHI
        defaultMonHocShouldNotBeFound("soTinChi.lessThan=" + DEFAULT_SO_TIN_CHI);

        // Get all the monHocList where soTinChi is less than (DEFAULT_SO_TIN_CHI + 1)
        defaultMonHocShouldBeFound("soTinChi.lessThan=" + (DEFAULT_SO_TIN_CHI + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsBySoTinChiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where soTinChi is greater than DEFAULT_SO_TIN_CHI
        defaultMonHocShouldNotBeFound("soTinChi.greaterThan=" + DEFAULT_SO_TIN_CHI);

        // Get all the monHocList where soTinChi is greater than SMALLER_SO_TIN_CHI
        defaultMonHocShouldBeFound("soTinChi.greaterThan=" + SMALLER_SO_TIN_CHI);
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan equals to DEFAULT_CHUYEN_CAN
        defaultMonHocShouldBeFound("chuyenCan.equals=" + DEFAULT_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan equals to UPDATED_CHUYEN_CAN
        defaultMonHocShouldNotBeFound("chuyenCan.equals=" + UPDATED_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan not equals to DEFAULT_CHUYEN_CAN
        defaultMonHocShouldNotBeFound("chuyenCan.notEquals=" + DEFAULT_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan not equals to UPDATED_CHUYEN_CAN
        defaultMonHocShouldBeFound("chuyenCan.notEquals=" + UPDATED_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan in DEFAULT_CHUYEN_CAN or UPDATED_CHUYEN_CAN
        defaultMonHocShouldBeFound("chuyenCan.in=" + DEFAULT_CHUYEN_CAN + "," + UPDATED_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan equals to UPDATED_CHUYEN_CAN
        defaultMonHocShouldNotBeFound("chuyenCan.in=" + UPDATED_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan is not null
        defaultMonHocShouldBeFound("chuyenCan.specified=true");

        // Get all the monHocList where chuyenCan is null
        defaultMonHocShouldNotBeFound("chuyenCan.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan is greater than or equal to DEFAULT_CHUYEN_CAN
        defaultMonHocShouldBeFound("chuyenCan.greaterThanOrEqual=" + DEFAULT_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan is greater than or equal to (DEFAULT_CHUYEN_CAN + 1)
        defaultMonHocShouldNotBeFound("chuyenCan.greaterThanOrEqual=" + (DEFAULT_CHUYEN_CAN + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan is less than or equal to DEFAULT_CHUYEN_CAN
        defaultMonHocShouldBeFound("chuyenCan.lessThanOrEqual=" + DEFAULT_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan is less than or equal to SMALLER_CHUYEN_CAN
        defaultMonHocShouldNotBeFound("chuyenCan.lessThanOrEqual=" + SMALLER_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsLessThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan is less than DEFAULT_CHUYEN_CAN
        defaultMonHocShouldNotBeFound("chuyenCan.lessThan=" + DEFAULT_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan is less than (DEFAULT_CHUYEN_CAN + 1)
        defaultMonHocShouldBeFound("chuyenCan.lessThan=" + (DEFAULT_CHUYEN_CAN + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByChuyenCanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where chuyenCan is greater than DEFAULT_CHUYEN_CAN
        defaultMonHocShouldNotBeFound("chuyenCan.greaterThan=" + DEFAULT_CHUYEN_CAN);

        // Get all the monHocList where chuyenCan is greater than SMALLER_CHUYEN_CAN
        defaultMonHocShouldBeFound("chuyenCan.greaterThan=" + SMALLER_CHUYEN_CAN);
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra equals to DEFAULT_KIEM_TRA
        defaultMonHocShouldBeFound("kiemTra.equals=" + DEFAULT_KIEM_TRA);

        // Get all the monHocList where kiemTra equals to UPDATED_KIEM_TRA
        defaultMonHocShouldNotBeFound("kiemTra.equals=" + UPDATED_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra not equals to DEFAULT_KIEM_TRA
        defaultMonHocShouldNotBeFound("kiemTra.notEquals=" + DEFAULT_KIEM_TRA);

        // Get all the monHocList where kiemTra not equals to UPDATED_KIEM_TRA
        defaultMonHocShouldBeFound("kiemTra.notEquals=" + UPDATED_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra in DEFAULT_KIEM_TRA or UPDATED_KIEM_TRA
        defaultMonHocShouldBeFound("kiemTra.in=" + DEFAULT_KIEM_TRA + "," + UPDATED_KIEM_TRA);

        // Get all the monHocList where kiemTra equals to UPDATED_KIEM_TRA
        defaultMonHocShouldNotBeFound("kiemTra.in=" + UPDATED_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra is not null
        defaultMonHocShouldBeFound("kiemTra.specified=true");

        // Get all the monHocList where kiemTra is null
        defaultMonHocShouldNotBeFound("kiemTra.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra is greater than or equal to DEFAULT_KIEM_TRA
        defaultMonHocShouldBeFound("kiemTra.greaterThanOrEqual=" + DEFAULT_KIEM_TRA);

        // Get all the monHocList where kiemTra is greater than or equal to (DEFAULT_KIEM_TRA + 1)
        defaultMonHocShouldNotBeFound("kiemTra.greaterThanOrEqual=" + (DEFAULT_KIEM_TRA + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra is less than or equal to DEFAULT_KIEM_TRA
        defaultMonHocShouldBeFound("kiemTra.lessThanOrEqual=" + DEFAULT_KIEM_TRA);

        // Get all the monHocList where kiemTra is less than or equal to SMALLER_KIEM_TRA
        defaultMonHocShouldNotBeFound("kiemTra.lessThanOrEqual=" + SMALLER_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsLessThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra is less than DEFAULT_KIEM_TRA
        defaultMonHocShouldNotBeFound("kiemTra.lessThan=" + DEFAULT_KIEM_TRA);

        // Get all the monHocList where kiemTra is less than (DEFAULT_KIEM_TRA + 1)
        defaultMonHocShouldBeFound("kiemTra.lessThan=" + (DEFAULT_KIEM_TRA + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByKiemTraIsGreaterThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where kiemTra is greater than DEFAULT_KIEM_TRA
        defaultMonHocShouldNotBeFound("kiemTra.greaterThan=" + DEFAULT_KIEM_TRA);

        // Get all the monHocList where kiemTra is greater than SMALLER_KIEM_TRA
        defaultMonHocShouldBeFound("kiemTra.greaterThan=" + SMALLER_KIEM_TRA);
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh equals to DEFAULT_THUC_HANH
        defaultMonHocShouldBeFound("thucHanh.equals=" + DEFAULT_THUC_HANH);

        // Get all the monHocList where thucHanh equals to UPDATED_THUC_HANH
        defaultMonHocShouldNotBeFound("thucHanh.equals=" + UPDATED_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh not equals to DEFAULT_THUC_HANH
        defaultMonHocShouldNotBeFound("thucHanh.notEquals=" + DEFAULT_THUC_HANH);

        // Get all the monHocList where thucHanh not equals to UPDATED_THUC_HANH
        defaultMonHocShouldBeFound("thucHanh.notEquals=" + UPDATED_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh in DEFAULT_THUC_HANH or UPDATED_THUC_HANH
        defaultMonHocShouldBeFound("thucHanh.in=" + DEFAULT_THUC_HANH + "," + UPDATED_THUC_HANH);

        // Get all the monHocList where thucHanh equals to UPDATED_THUC_HANH
        defaultMonHocShouldNotBeFound("thucHanh.in=" + UPDATED_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh is not null
        defaultMonHocShouldBeFound("thucHanh.specified=true");

        // Get all the monHocList where thucHanh is null
        defaultMonHocShouldNotBeFound("thucHanh.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh is greater than or equal to DEFAULT_THUC_HANH
        defaultMonHocShouldBeFound("thucHanh.greaterThanOrEqual=" + DEFAULT_THUC_HANH);

        // Get all the monHocList where thucHanh is greater than or equal to (DEFAULT_THUC_HANH + 1)
        defaultMonHocShouldNotBeFound("thucHanh.greaterThanOrEqual=" + (DEFAULT_THUC_HANH + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh is less than or equal to DEFAULT_THUC_HANH
        defaultMonHocShouldBeFound("thucHanh.lessThanOrEqual=" + DEFAULT_THUC_HANH);

        // Get all the monHocList where thucHanh is less than or equal to SMALLER_THUC_HANH
        defaultMonHocShouldNotBeFound("thucHanh.lessThanOrEqual=" + SMALLER_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsLessThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh is less than DEFAULT_THUC_HANH
        defaultMonHocShouldNotBeFound("thucHanh.lessThan=" + DEFAULT_THUC_HANH);

        // Get all the monHocList where thucHanh is less than (DEFAULT_THUC_HANH + 1)
        defaultMonHocShouldBeFound("thucHanh.lessThan=" + (DEFAULT_THUC_HANH + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByThucHanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thucHanh is greater than DEFAULT_THUC_HANH
        defaultMonHocShouldNotBeFound("thucHanh.greaterThan=" + DEFAULT_THUC_HANH);

        // Get all the monHocList where thucHanh is greater than SMALLER_THUC_HANH
        defaultMonHocShouldBeFound("thucHanh.greaterThan=" + SMALLER_THUC_HANH);
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap equals to DEFAULT_BAI_TAP
        defaultMonHocShouldBeFound("baiTap.equals=" + DEFAULT_BAI_TAP);

        // Get all the monHocList where baiTap equals to UPDATED_BAI_TAP
        defaultMonHocShouldNotBeFound("baiTap.equals=" + UPDATED_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap not equals to DEFAULT_BAI_TAP
        defaultMonHocShouldNotBeFound("baiTap.notEquals=" + DEFAULT_BAI_TAP);

        // Get all the monHocList where baiTap not equals to UPDATED_BAI_TAP
        defaultMonHocShouldBeFound("baiTap.notEquals=" + UPDATED_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap in DEFAULT_BAI_TAP or UPDATED_BAI_TAP
        defaultMonHocShouldBeFound("baiTap.in=" + DEFAULT_BAI_TAP + "," + UPDATED_BAI_TAP);

        // Get all the monHocList where baiTap equals to UPDATED_BAI_TAP
        defaultMonHocShouldNotBeFound("baiTap.in=" + UPDATED_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap is not null
        defaultMonHocShouldBeFound("baiTap.specified=true");

        // Get all the monHocList where baiTap is null
        defaultMonHocShouldNotBeFound("baiTap.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap is greater than or equal to DEFAULT_BAI_TAP
        defaultMonHocShouldBeFound("baiTap.greaterThanOrEqual=" + DEFAULT_BAI_TAP);

        // Get all the monHocList where baiTap is greater than or equal to (DEFAULT_BAI_TAP + 1)
        defaultMonHocShouldNotBeFound("baiTap.greaterThanOrEqual=" + (DEFAULT_BAI_TAP + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap is less than or equal to DEFAULT_BAI_TAP
        defaultMonHocShouldBeFound("baiTap.lessThanOrEqual=" + DEFAULT_BAI_TAP);

        // Get all the monHocList where baiTap is less than or equal to SMALLER_BAI_TAP
        defaultMonHocShouldNotBeFound("baiTap.lessThanOrEqual=" + SMALLER_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsLessThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap is less than DEFAULT_BAI_TAP
        defaultMonHocShouldNotBeFound("baiTap.lessThan=" + DEFAULT_BAI_TAP);

        // Get all the monHocList where baiTap is less than (DEFAULT_BAI_TAP + 1)
        defaultMonHocShouldBeFound("baiTap.lessThan=" + (DEFAULT_BAI_TAP + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByBaiTapIsGreaterThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where baiTap is greater than DEFAULT_BAI_TAP
        defaultMonHocShouldNotBeFound("baiTap.greaterThan=" + DEFAULT_BAI_TAP);

        // Get all the monHocList where baiTap is greater than SMALLER_BAI_TAP
        defaultMonHocShouldBeFound("baiTap.greaterThan=" + SMALLER_BAI_TAP);
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi equals to DEFAULT_THI
        defaultMonHocShouldBeFound("thi.equals=" + DEFAULT_THI);

        // Get all the monHocList where thi equals to UPDATED_THI
        defaultMonHocShouldNotBeFound("thi.equals=" + UPDATED_THI);
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi not equals to DEFAULT_THI
        defaultMonHocShouldNotBeFound("thi.notEquals=" + DEFAULT_THI);

        // Get all the monHocList where thi not equals to UPDATED_THI
        defaultMonHocShouldBeFound("thi.notEquals=" + UPDATED_THI);
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsInShouldWork() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi in DEFAULT_THI or UPDATED_THI
        defaultMonHocShouldBeFound("thi.in=" + DEFAULT_THI + "," + UPDATED_THI);

        // Get all the monHocList where thi equals to UPDATED_THI
        defaultMonHocShouldNotBeFound("thi.in=" + UPDATED_THI);
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsNullOrNotNull() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi is not null
        defaultMonHocShouldBeFound("thi.specified=true");

        // Get all the monHocList where thi is null
        defaultMonHocShouldNotBeFound("thi.specified=false");
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi is greater than or equal to DEFAULT_THI
        defaultMonHocShouldBeFound("thi.greaterThanOrEqual=" + DEFAULT_THI);

        // Get all the monHocList where thi is greater than or equal to (DEFAULT_THI + 1)
        defaultMonHocShouldNotBeFound("thi.greaterThanOrEqual=" + (DEFAULT_THI + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi is less than or equal to DEFAULT_THI
        defaultMonHocShouldBeFound("thi.lessThanOrEqual=" + DEFAULT_THI);

        // Get all the monHocList where thi is less than or equal to SMALLER_THI
        defaultMonHocShouldNotBeFound("thi.lessThanOrEqual=" + SMALLER_THI);
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsLessThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi is less than DEFAULT_THI
        defaultMonHocShouldNotBeFound("thi.lessThan=" + DEFAULT_THI);

        // Get all the monHocList where thi is less than (DEFAULT_THI + 1)
        defaultMonHocShouldBeFound("thi.lessThan=" + (DEFAULT_THI + 1));
    }

    @Test
    @Transactional
    void getAllMonHocsByThiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);

        // Get all the monHocList where thi is greater than DEFAULT_THI
        defaultMonHocShouldNotBeFound("thi.greaterThan=" + DEFAULT_THI);

        // Get all the monHocList where thi is greater than SMALLER_THI
        defaultMonHocShouldBeFound("thi.greaterThan=" + SMALLER_THI);
    }

    @Test
    @Transactional
    void getAllMonHocsByDiemIsEqualToSomething() throws Exception {
        // Initialize the database
        monHocRepository.saveAndFlush(monHoc);
        Diem diem = DiemResourceIT.createEntity(em);
        em.persist(diem);
        em.flush();
        monHoc.addDiem(diem);
        monHocRepository.saveAndFlush(monHoc);
        Long diemId = diem.getId();

        // Get all the monHocList where diem equals to diemId
        defaultMonHocShouldBeFound("diemId.equals=" + diemId);

        // Get all the monHocList where diem equals to (diemId + 1)
        defaultMonHocShouldNotBeFound("diemId.equals=" + (diemId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMonHocShouldBeFound(String filter) throws Exception {
        restMonHocMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restMonHocMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMonHocShouldNotBeFound(String filter) throws Exception {
        restMonHocMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMonHocMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
