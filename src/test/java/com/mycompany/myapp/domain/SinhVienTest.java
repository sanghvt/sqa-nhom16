package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.mycompany.myapp.domain.enumeration.Lop;
import com.mycompany.myapp.domain.enumeration.Nganh;
import com.mycompany.myapp.domain.enumeration.Sex;
import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SinhVienTest {

    private static final String MA_SINH_VIEN = "MA_SINH_VIEN";
    private static final String PASSWORD = "PASSWORD";
    private static final String HO_TEN = "HO_TEN";
    private static final Sex GIOI_TINH = Sex.Male;
    private static final String EMAIL = "EMAIL";
    private static final String SO_DIEN_THOAI = "SO_DIEN_THOAI";
    private static final Nganh NGANH = Nganh.InformationTechnology;
    private static final String KHOA_HOC = "KHOA_HOC";
    private static final Lop LOP = Lop.D17CQCN03;

    @Mock
    private Diem diem;

    @InjectMocks
    private SinhVien underTest;

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SinhVien.class);
        SinhVien sinhVien1 = new SinhVien();
        sinhVien1.setId(1L);
        SinhVien sinhVien2 = new SinhVien();
        sinhVien2.setId(sinhVien1.getId());
        assertThat(sinhVien1).isEqualTo(sinhVien2);
        sinhVien2.setId(2L);
        assertThat(sinhVien1).isNotEqualTo(sinhVien2);
        sinhVien1.setId(null);
        assertThat(sinhVien1).isNotEqualTo(sinhVien2);
    }

    @Test
    void getId() {}

    @Test
    void setId() {}

    @Test
    void id() {}

    @Test
    void getMaSinhVien() {}

    @Test
    void maSinhVien() {}

    @Test
    void setMaSinhVien() {}

    @Test
    void getPassword() {}

    @Test
    void password() {}

    @Test
    void setPassword() {}

    @Test
    void getHoTen() {}

    @Test
    void hoTen() {}

    @Test
    void setHoTen() {}

    @Test
    void getNgaySinh() {}

    @Test
    void ngaySinh() {}

    @Test
    void setNgaySinh() {}

    @Test
    void getGioiTinh() {}

    @Test
    void gioiTinh() {}

    @Test
    void setGioiTinh() {}

    @Test
    void getEmail() {}

    @Test
    void email() {}

    @Test
    void setEmail() {}

    @Test
    void getSoDienThoai() {}

    @Test
    void soDienThoai() {}

    @Test
    void setSoDienThoai() {}

    @Test
    void getNganh() {}

    @Test
    void nganh() {}

    @Test
    void setNganh() {}

    @Test
    void getKhoaHoc() {}

    @Test
    void khoaHoc() {}

    @Test
    void setKhoaHoc() {}

    @Test
    void getLop() {}

    @Test
    void lop() {}

    @Test
    void setLop() {}

    @Test
    void getDiems() {}

    @Test
    void diems() {}

    @Test
    void addDiem() {}

    @Test
    void removeDiem() {}

    @Test
    void setDiems() {}

    @Nested
    class WhenGettingId {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenIding {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingMaSinhVien {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenMaingSinhVien {

        private final String MA_SINH_VIEN = "MA_SINH_VIEN";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingPassword {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenPasswording {

        private final String PASSWORD = "PASSWORD";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingHoTen {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenHoingTen {

        private final String HO_TEN = "HO_TEN";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingNgaySinh {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenNgayingSinh {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingGioiTinh {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGioiingTinh {

        private final Sex GIOI_TINH = Sex.Male;

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingEmail {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenEmailing {

        private final String EMAIL = "EMAIL";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingSoDienThoai {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenSoingDienThoai {

        private final String SO_DIEN_THOAI = "SO_DIEN_THOAI";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingNganh {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenNganhing {

        private final Nganh NGANH = Nganh.InformationTechnology;

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingKhoaHoc {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenKhoaingHoc {

        private final String KHOA_HOC = "KHOA_HOC";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingLop {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenLoping {

        private final Lop LOP = Lop.D17CQCN03;

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingDiems {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenDiemsing {

        @Mock
        private Diem diem;

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenAddingDiem {

        @Mock
        private Diem diem;

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenRemovingDiem {

        @Mock
        private Diem diem;

        @BeforeEach
        void setup() {}
    }
}
