package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Diem;
import com.mycompany.myapp.domain.SinhVien;
import com.mycompany.myapp.domain.enumeration.Lop;
import com.mycompany.myapp.domain.enumeration.Nganh;
import com.mycompany.myapp.domain.enumeration.Sex;
import com.mycompany.myapp.repository.SinhVienRepository;
import com.mycompany.myapp.service.criteria.SinhVienCriteria;
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
    private static final LocalDate SMALLER_NGAY_SINH = LocalDate.ofEpochDay(-1L);

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

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
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

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
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
    void getSinhViensByIdFiltering() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        Long id = sinhVien.getId();

        defaultSinhVienShouldBeFound("id.equals=" + id);
        defaultSinhVienShouldNotBeFound("id.notEquals=" + id);

        defaultSinhVienShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSinhVienShouldNotBeFound("id.greaterThan=" + id);

        defaultSinhVienShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSinhVienShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllSinhViensByMaSinhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where maSinhVien equals to DEFAULT_MA_SINH_VIEN
        defaultSinhVienShouldBeFound("maSinhVien.equals=" + DEFAULT_MA_SINH_VIEN);

        // Get all the sinhVienList where maSinhVien equals to UPDATED_MA_SINH_VIEN
        defaultSinhVienShouldNotBeFound("maSinhVien.equals=" + UPDATED_MA_SINH_VIEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByMaSinhVienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where maSinhVien not equals to DEFAULT_MA_SINH_VIEN
        defaultSinhVienShouldNotBeFound("maSinhVien.notEquals=" + DEFAULT_MA_SINH_VIEN);

        // Get all the sinhVienList where maSinhVien not equals to UPDATED_MA_SINH_VIEN
        defaultSinhVienShouldBeFound("maSinhVien.notEquals=" + UPDATED_MA_SINH_VIEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByMaSinhVienIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where maSinhVien in DEFAULT_MA_SINH_VIEN or UPDATED_MA_SINH_VIEN
        defaultSinhVienShouldBeFound("maSinhVien.in=" + DEFAULT_MA_SINH_VIEN + "," + UPDATED_MA_SINH_VIEN);

        // Get all the sinhVienList where maSinhVien equals to UPDATED_MA_SINH_VIEN
        defaultSinhVienShouldNotBeFound("maSinhVien.in=" + UPDATED_MA_SINH_VIEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByMaSinhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where maSinhVien is not null
        defaultSinhVienShouldBeFound("maSinhVien.specified=true");

        // Get all the sinhVienList where maSinhVien is null
        defaultSinhVienShouldNotBeFound("maSinhVien.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByMaSinhVienContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where maSinhVien contains DEFAULT_MA_SINH_VIEN
        defaultSinhVienShouldBeFound("maSinhVien.contains=" + DEFAULT_MA_SINH_VIEN);

        // Get all the sinhVienList where maSinhVien contains UPDATED_MA_SINH_VIEN
        defaultSinhVienShouldNotBeFound("maSinhVien.contains=" + UPDATED_MA_SINH_VIEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByMaSinhVienNotContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where maSinhVien does not contain DEFAULT_MA_SINH_VIEN
        defaultSinhVienShouldNotBeFound("maSinhVien.doesNotContain=" + DEFAULT_MA_SINH_VIEN);

        // Get all the sinhVienList where maSinhVien does not contain UPDATED_MA_SINH_VIEN
        defaultSinhVienShouldBeFound("maSinhVien.doesNotContain=" + UPDATED_MA_SINH_VIEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where password equals to DEFAULT_PASSWORD
        defaultSinhVienShouldBeFound("password.equals=" + DEFAULT_PASSWORD);

        // Get all the sinhVienList where password equals to UPDATED_PASSWORD
        defaultSinhVienShouldNotBeFound("password.equals=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllSinhViensByPasswordIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where password not equals to DEFAULT_PASSWORD
        defaultSinhVienShouldNotBeFound("password.notEquals=" + DEFAULT_PASSWORD);

        // Get all the sinhVienList where password not equals to UPDATED_PASSWORD
        defaultSinhVienShouldBeFound("password.notEquals=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllSinhViensByPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where password in DEFAULT_PASSWORD or UPDATED_PASSWORD
        defaultSinhVienShouldBeFound("password.in=" + DEFAULT_PASSWORD + "," + UPDATED_PASSWORD);

        // Get all the sinhVienList where password equals to UPDATED_PASSWORD
        defaultSinhVienShouldNotBeFound("password.in=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllSinhViensByPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where password is not null
        defaultSinhVienShouldBeFound("password.specified=true");

        // Get all the sinhVienList where password is null
        defaultSinhVienShouldNotBeFound("password.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByPasswordContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where password contains DEFAULT_PASSWORD
        defaultSinhVienShouldBeFound("password.contains=" + DEFAULT_PASSWORD);

        // Get all the sinhVienList where password contains UPDATED_PASSWORD
        defaultSinhVienShouldNotBeFound("password.contains=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllSinhViensByPasswordNotContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where password does not contain DEFAULT_PASSWORD
        defaultSinhVienShouldNotBeFound("password.doesNotContain=" + DEFAULT_PASSWORD);

        // Get all the sinhVienList where password does not contain UPDATED_PASSWORD
        defaultSinhVienShouldBeFound("password.doesNotContain=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    void getAllSinhViensByHoTenIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where hoTen equals to DEFAULT_HO_TEN
        defaultSinhVienShouldBeFound("hoTen.equals=" + DEFAULT_HO_TEN);

        // Get all the sinhVienList where hoTen equals to UPDATED_HO_TEN
        defaultSinhVienShouldNotBeFound("hoTen.equals=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByHoTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where hoTen not equals to DEFAULT_HO_TEN
        defaultSinhVienShouldNotBeFound("hoTen.notEquals=" + DEFAULT_HO_TEN);

        // Get all the sinhVienList where hoTen not equals to UPDATED_HO_TEN
        defaultSinhVienShouldBeFound("hoTen.notEquals=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByHoTenIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where hoTen in DEFAULT_HO_TEN or UPDATED_HO_TEN
        defaultSinhVienShouldBeFound("hoTen.in=" + DEFAULT_HO_TEN + "," + UPDATED_HO_TEN);

        // Get all the sinhVienList where hoTen equals to UPDATED_HO_TEN
        defaultSinhVienShouldNotBeFound("hoTen.in=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByHoTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where hoTen is not null
        defaultSinhVienShouldBeFound("hoTen.specified=true");

        // Get all the sinhVienList where hoTen is null
        defaultSinhVienShouldNotBeFound("hoTen.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByHoTenContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where hoTen contains DEFAULT_HO_TEN
        defaultSinhVienShouldBeFound("hoTen.contains=" + DEFAULT_HO_TEN);

        // Get all the sinhVienList where hoTen contains UPDATED_HO_TEN
        defaultSinhVienShouldNotBeFound("hoTen.contains=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByHoTenNotContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where hoTen does not contain DEFAULT_HO_TEN
        defaultSinhVienShouldNotBeFound("hoTen.doesNotContain=" + DEFAULT_HO_TEN);

        // Get all the sinhVienList where hoTen does not contain UPDATED_HO_TEN
        defaultSinhVienShouldBeFound("hoTen.doesNotContain=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh equals to DEFAULT_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.equals=" + DEFAULT_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh equals to UPDATED_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.equals=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh not equals to DEFAULT_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.notEquals=" + DEFAULT_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh not equals to UPDATED_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.notEquals=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh in DEFAULT_NGAY_SINH or UPDATED_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.in=" + DEFAULT_NGAY_SINH + "," + UPDATED_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh equals to UPDATED_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.in=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh is not null
        defaultSinhVienShouldBeFound("ngaySinh.specified=true");

        // Get all the sinhVienList where ngaySinh is null
        defaultSinhVienShouldNotBeFound("ngaySinh.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh is greater than or equal to DEFAULT_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.greaterThanOrEqual=" + DEFAULT_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh is greater than or equal to UPDATED_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.greaterThanOrEqual=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh is less than or equal to DEFAULT_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.lessThanOrEqual=" + DEFAULT_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh is less than or equal to SMALLER_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.lessThanOrEqual=" + SMALLER_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsLessThanSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh is less than DEFAULT_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.lessThan=" + DEFAULT_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh is less than UPDATED_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.lessThan=" + UPDATED_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNgaySinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where ngaySinh is greater than DEFAULT_NGAY_SINH
        defaultSinhVienShouldNotBeFound("ngaySinh.greaterThan=" + DEFAULT_NGAY_SINH);

        // Get all the sinhVienList where ngaySinh is greater than SMALLER_NGAY_SINH
        defaultSinhVienShouldBeFound("ngaySinh.greaterThan=" + SMALLER_NGAY_SINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByGioiTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where gioiTinh equals to DEFAULT_GIOI_TINH
        defaultSinhVienShouldBeFound("gioiTinh.equals=" + DEFAULT_GIOI_TINH);

        // Get all the sinhVienList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultSinhVienShouldNotBeFound("gioiTinh.equals=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByGioiTinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where gioiTinh not equals to DEFAULT_GIOI_TINH
        defaultSinhVienShouldNotBeFound("gioiTinh.notEquals=" + DEFAULT_GIOI_TINH);

        // Get all the sinhVienList where gioiTinh not equals to UPDATED_GIOI_TINH
        defaultSinhVienShouldBeFound("gioiTinh.notEquals=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByGioiTinhIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where gioiTinh in DEFAULT_GIOI_TINH or UPDATED_GIOI_TINH
        defaultSinhVienShouldBeFound("gioiTinh.in=" + DEFAULT_GIOI_TINH + "," + UPDATED_GIOI_TINH);

        // Get all the sinhVienList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultSinhVienShouldNotBeFound("gioiTinh.in=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    void getAllSinhViensByGioiTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where gioiTinh is not null
        defaultSinhVienShouldBeFound("gioiTinh.specified=true");

        // Get all the sinhVienList where gioiTinh is null
        defaultSinhVienShouldNotBeFound("gioiTinh.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where email equals to DEFAULT_EMAIL
        defaultSinhVienShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the sinhVienList where email equals to UPDATED_EMAIL
        defaultSinhVienShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllSinhViensByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where email not equals to DEFAULT_EMAIL
        defaultSinhVienShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the sinhVienList where email not equals to UPDATED_EMAIL
        defaultSinhVienShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllSinhViensByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultSinhVienShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the sinhVienList where email equals to UPDATED_EMAIL
        defaultSinhVienShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllSinhViensByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where email is not null
        defaultSinhVienShouldBeFound("email.specified=true");

        // Get all the sinhVienList where email is null
        defaultSinhVienShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByEmailContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where email contains DEFAULT_EMAIL
        defaultSinhVienShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the sinhVienList where email contains UPDATED_EMAIL
        defaultSinhVienShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllSinhViensByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where email does not contain DEFAULT_EMAIL
        defaultSinhVienShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the sinhVienList where email does not contain UPDATED_EMAIL
        defaultSinhVienShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllSinhViensBySoDienThoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where soDienThoai equals to DEFAULT_SO_DIEN_THOAI
        defaultSinhVienShouldBeFound("soDienThoai.equals=" + DEFAULT_SO_DIEN_THOAI);

        // Get all the sinhVienList where soDienThoai equals to UPDATED_SO_DIEN_THOAI
        defaultSinhVienShouldNotBeFound("soDienThoai.equals=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllSinhViensBySoDienThoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where soDienThoai not equals to DEFAULT_SO_DIEN_THOAI
        defaultSinhVienShouldNotBeFound("soDienThoai.notEquals=" + DEFAULT_SO_DIEN_THOAI);

        // Get all the sinhVienList where soDienThoai not equals to UPDATED_SO_DIEN_THOAI
        defaultSinhVienShouldBeFound("soDienThoai.notEquals=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllSinhViensBySoDienThoaiIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where soDienThoai in DEFAULT_SO_DIEN_THOAI or UPDATED_SO_DIEN_THOAI
        defaultSinhVienShouldBeFound("soDienThoai.in=" + DEFAULT_SO_DIEN_THOAI + "," + UPDATED_SO_DIEN_THOAI);

        // Get all the sinhVienList where soDienThoai equals to UPDATED_SO_DIEN_THOAI
        defaultSinhVienShouldNotBeFound("soDienThoai.in=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllSinhViensBySoDienThoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where soDienThoai is not null
        defaultSinhVienShouldBeFound("soDienThoai.specified=true");

        // Get all the sinhVienList where soDienThoai is null
        defaultSinhVienShouldNotBeFound("soDienThoai.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensBySoDienThoaiContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where soDienThoai contains DEFAULT_SO_DIEN_THOAI
        defaultSinhVienShouldBeFound("soDienThoai.contains=" + DEFAULT_SO_DIEN_THOAI);

        // Get all the sinhVienList where soDienThoai contains UPDATED_SO_DIEN_THOAI
        defaultSinhVienShouldNotBeFound("soDienThoai.contains=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllSinhViensBySoDienThoaiNotContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where soDienThoai does not contain DEFAULT_SO_DIEN_THOAI
        defaultSinhVienShouldNotBeFound("soDienThoai.doesNotContain=" + DEFAULT_SO_DIEN_THOAI);

        // Get all the sinhVienList where soDienThoai does not contain UPDATED_SO_DIEN_THOAI
        defaultSinhVienShouldBeFound("soDienThoai.doesNotContain=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllSinhViensByNganhIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where nganh equals to DEFAULT_NGANH
        defaultSinhVienShouldBeFound("nganh.equals=" + DEFAULT_NGANH);

        // Get all the sinhVienList where nganh equals to UPDATED_NGANH
        defaultSinhVienShouldNotBeFound("nganh.equals=" + UPDATED_NGANH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNganhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where nganh not equals to DEFAULT_NGANH
        defaultSinhVienShouldNotBeFound("nganh.notEquals=" + DEFAULT_NGANH);

        // Get all the sinhVienList where nganh not equals to UPDATED_NGANH
        defaultSinhVienShouldBeFound("nganh.notEquals=" + UPDATED_NGANH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNganhIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where nganh in DEFAULT_NGANH or UPDATED_NGANH
        defaultSinhVienShouldBeFound("nganh.in=" + DEFAULT_NGANH + "," + UPDATED_NGANH);

        // Get all the sinhVienList where nganh equals to UPDATED_NGANH
        defaultSinhVienShouldNotBeFound("nganh.in=" + UPDATED_NGANH);
    }

    @Test
    @Transactional
    void getAllSinhViensByNganhIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where nganh is not null
        defaultSinhVienShouldBeFound("nganh.specified=true");

        // Get all the sinhVienList where nganh is null
        defaultSinhVienShouldNotBeFound("nganh.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByKhoaHocIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where khoaHoc equals to DEFAULT_KHOA_HOC
        defaultSinhVienShouldBeFound("khoaHoc.equals=" + DEFAULT_KHOA_HOC);

        // Get all the sinhVienList where khoaHoc equals to UPDATED_KHOA_HOC
        defaultSinhVienShouldNotBeFound("khoaHoc.equals=" + UPDATED_KHOA_HOC);
    }

    @Test
    @Transactional
    void getAllSinhViensByKhoaHocIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where khoaHoc not equals to DEFAULT_KHOA_HOC
        defaultSinhVienShouldNotBeFound("khoaHoc.notEquals=" + DEFAULT_KHOA_HOC);

        // Get all the sinhVienList where khoaHoc not equals to UPDATED_KHOA_HOC
        defaultSinhVienShouldBeFound("khoaHoc.notEquals=" + UPDATED_KHOA_HOC);
    }

    @Test
    @Transactional
    void getAllSinhViensByKhoaHocIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where khoaHoc in DEFAULT_KHOA_HOC or UPDATED_KHOA_HOC
        defaultSinhVienShouldBeFound("khoaHoc.in=" + DEFAULT_KHOA_HOC + "," + UPDATED_KHOA_HOC);

        // Get all the sinhVienList where khoaHoc equals to UPDATED_KHOA_HOC
        defaultSinhVienShouldNotBeFound("khoaHoc.in=" + UPDATED_KHOA_HOC);
    }

    @Test
    @Transactional
    void getAllSinhViensByKhoaHocIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where khoaHoc is not null
        defaultSinhVienShouldBeFound("khoaHoc.specified=true");

        // Get all the sinhVienList where khoaHoc is null
        defaultSinhVienShouldNotBeFound("khoaHoc.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByKhoaHocContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where khoaHoc contains DEFAULT_KHOA_HOC
        defaultSinhVienShouldBeFound("khoaHoc.contains=" + DEFAULT_KHOA_HOC);

        // Get all the sinhVienList where khoaHoc contains UPDATED_KHOA_HOC
        defaultSinhVienShouldNotBeFound("khoaHoc.contains=" + UPDATED_KHOA_HOC);
    }

    @Test
    @Transactional
    void getAllSinhViensByKhoaHocNotContainsSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where khoaHoc does not contain DEFAULT_KHOA_HOC
        defaultSinhVienShouldNotBeFound("khoaHoc.doesNotContain=" + DEFAULT_KHOA_HOC);

        // Get all the sinhVienList where khoaHoc does not contain UPDATED_KHOA_HOC
        defaultSinhVienShouldBeFound("khoaHoc.doesNotContain=" + UPDATED_KHOA_HOC);
    }

    @Test
    @Transactional
    void getAllSinhViensByLopIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where lop equals to DEFAULT_LOP
        defaultSinhVienShouldBeFound("lop.equals=" + DEFAULT_LOP);

        // Get all the sinhVienList where lop equals to UPDATED_LOP
        defaultSinhVienShouldNotBeFound("lop.equals=" + UPDATED_LOP);
    }

    @Test
    @Transactional
    void getAllSinhViensByLopIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where lop not equals to DEFAULT_LOP
        defaultSinhVienShouldNotBeFound("lop.notEquals=" + DEFAULT_LOP);

        // Get all the sinhVienList where lop not equals to UPDATED_LOP
        defaultSinhVienShouldBeFound("lop.notEquals=" + UPDATED_LOP);
    }

    @Test
    @Transactional
    void getAllSinhViensByLopIsInShouldWork() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where lop in DEFAULT_LOP or UPDATED_LOP
        defaultSinhVienShouldBeFound("lop.in=" + DEFAULT_LOP + "," + UPDATED_LOP);

        // Get all the sinhVienList where lop equals to UPDATED_LOP
        defaultSinhVienShouldNotBeFound("lop.in=" + UPDATED_LOP);
    }

    @Test
    @Transactional
    void getAllSinhViensByLopIsNullOrNotNull() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList where lop is not null
        defaultSinhVienShouldBeFound("lop.specified=true");

        // Get all the sinhVienList where lop is null
        defaultSinhVienShouldNotBeFound("lop.specified=false");
    }

    @Test
    @Transactional
    void getAllSinhViensByDiemIsEqualToSomething() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);
        Diem diem = DiemResourceIT.createEntity(em);
        em.persist(diem);
        em.flush();
        sinhVien.addDiem(diem);
        sinhVienRepository.saveAndFlush(sinhVien);
        Long diemId = diem.getId();

        // Get all the sinhVienList where diem equals to diemId
        defaultSinhVienShouldBeFound("diemId.equals=" + diemId);

        // Get all the sinhVienList where diem equals to (diemId + 1)
        defaultSinhVienShouldNotBeFound("diemId.equals=" + (diemId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSinhVienShouldBeFound(String filter) throws Exception {
        restSinhVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restSinhVienMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSinhVienShouldNotBeFound(String filter) throws Exception {
        restSinhVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSinhVienMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
