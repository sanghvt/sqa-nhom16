package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DiemTest {

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
}
