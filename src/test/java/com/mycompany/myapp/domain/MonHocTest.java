package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MonHocTest {

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
}
