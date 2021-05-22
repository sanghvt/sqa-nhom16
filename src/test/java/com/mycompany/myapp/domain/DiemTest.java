package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.mycompany.myapp.domain.enumeration.Status;
import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiemTest {

    private static final String TONG_KET_THANG_CHU = "TONG_KET_THANG_CHU";
    private static final Status KET_QUA = Status.PASS;

    @Mock
    private SinhVien maSinhVien;

    @Mock
    private MonHoc maMonHoc;

    @InjectMocks
    private Diem underTest;

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Diem.class);
        Diem diem1 = new Diem();
        diem1.setId(1L);
        Diem diem2 = new Diem();
        diem2.setId(diem1.getId());
        assertThat(diem1).isEqualTo(diem2);
        diem2.setId(2L);
        assertThat(diem1).isNotEqualTo(diem2);
        diem1.setId(null);
        assertThat(diem1).isNotEqualTo(diem2);
    }

    @Test
    void getId() {}

    @Test
    void setId() {}

    @Test
    void id() {}

    @Test
    void getChuyenCan() {}

    @Test
    void chuyenCan() {}

    @Test
    void setChuyenCan() {}

    @Test
    void getKiemTra() {}

    @Test
    void kiemTra() {}

    @Test
    void setKiemTra() {}

    @Test
    void getThucHanh() {}

    @Test
    void thucHanh() {}

    @Test
    void setThucHanh() {}

    @Test
    void getBaiTap() {}

    @Test
    void baiTap() {}

    @Test
    void setBaiTap() {}

    @Test
    void getThi() {}

    @Test
    void thi() {}

    @Test
    void setThi() {}

    @Test
    void getSoLanHoc() {}

    @Test
    void soLanHoc() {}

    @Test
    void setSoLanHoc() {}

    @Test
    void getTongKet() {}

    @Test
    void tongKet() {}

    @Test
    void setTongKet() {}

    @Test
    void getTongKetThangChu() {}

    @Test
    void tongKetThangChu() {}

    @Test
    void setTongKetThangChu() {}

    @Test
    void getKetQua() {}

    @Test
    void ketQua() {}

    @Test
    void setKetQua() {}

    @Test
    void getMaSinhVien() {}

    @Test
    void maSinhVien() {}

    @Test
    void setMaSinhVien() {}

    @Test
    void getMaMonHoc() {}

    @Test
    void maMonHoc() {}

    @Test
    void setMaMonHoc() {}

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
    class WhenGettingChuyenCan {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenChuyeningCan {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingKiemTra {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenKiemingTra {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingThucHanh {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenThucingHanh {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingBaiTap {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenBaiingTap {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingThi {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenThiing {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingSoLanHoc {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenSoingLanHoc {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingTongKet {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenTongingKet {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingTongKetThangChu {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenTongingKetThangChu {

        private final String TONG_KET_THANG_CHU = "TONG_KET_THANG_CHU";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingKetQua {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenKetingQua {

        private final Status KET_QUA = Status.PASS;

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

        @Mock
        private SinhVien sinhVien;

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingMaMonHoc {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenMaingMonHoc {

        @Mock
        private MonHoc monHoc;

        @BeforeEach
        void setup() {}
    }
}
