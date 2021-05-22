package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MonHocTest {

    private static final String MA_MON_HOC = "MA_MON_HOC";
    private static final String TEN_MON_HOC = "TEN_MON_HOC";

    @Mock
    private Diem diem;

    @InjectMocks
    private MonHoc underTest;

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonHoc.class);
        MonHoc monHoc1 = new MonHoc();
        monHoc1.setId(1L);
        MonHoc monHoc2 = new MonHoc();
        monHoc2.setId(monHoc1.getId());
        assertThat(monHoc1).isEqualTo(monHoc2);
        monHoc2.setId(2L);
        assertThat(monHoc1).isNotEqualTo(monHoc2);
        monHoc1.setId(null);
        assertThat(monHoc1).isNotEqualTo(monHoc2);
    }

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @Test
    void getId() {}

    @Test
    void setId() {}

    @Test
    void id() {}

    @Test
    void getMaMonHoc() {}

    @Test
    void maMonHoc() {}

    @Test
    void setMaMonHoc() {}

    @Test
    void getTenMonHoc() {}

    @Test
    void tenMonHoc() {}

    @Test
    void setTenMonHoc() {}

    @Test
    void getSoTinChi() {}

    @Test
    void soTinChi() {}

    @Test
    void setSoTinChi() {}

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
    class WhenGettingMaMonHoc {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenMaingMonHoc {

        private final String MA_MON_HOC = "MA_MON_HOC";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingTenMonHoc {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenTeningMonHoc {

        private final String TEN_MON_HOC = "TEN_MON_HOC";

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingSoTinChi {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenSoingTinChi {

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
