package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MonHoc.
 */
@Entity
@Table(name = "mon_hoc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MonHoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma_mon_hoc", nullable = false)
    private String maMonHoc;

    @NotNull
    @Column(name = "ten_mon_hoc", nullable = false)
    private String tenMonHoc;

    @NotNull
    @Min(value = 1)
    @Max(value = 4)
    @Column(name = "so_tin_chi", nullable = false)
    private Integer soTinChi;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "0.1")
    @Column(name = "chuyen_can", nullable = false)
    private Double chuyenCan;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "0.2")
    @Column(name = "kiem_tra", nullable = false)
    private Double kiemTra;

    @DecimalMin(value = "0")
    @DecimalMax(value = "0.2")
    @Column(name = "thuc_hanh")
    private Double thucHanh;

    @DecimalMin(value = "0")
    @DecimalMax(value = "0.2")
    @Column(name = "bai_tap")
    private Double baiTap;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "0.7")
    @Column(name = "thi", nullable = false)
    private Double thi;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    @OneToMany(mappedBy = "maMonHoc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "maSinhVien", "maMonHoc" }, allowSetters = true)
    private Set<Diem> diems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonHoc id(Long id) {
        this.id = id;
        return this;
    }

    public String getMaMonHoc() {
        return this.maMonHoc;
    }

    public MonHoc maMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
        return this;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return this.tenMonHoc;
    }

    public MonHoc tenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
        return this;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public Integer getSoTinChi() {
        return this.soTinChi;
    }

    public MonHoc soTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
        return this;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public Double getChuyenCan() {
        return this.chuyenCan;
    }

    public MonHoc chuyenCan(Double chuyenCan) {
        this.chuyenCan = chuyenCan;
        return this;
    }

    public void setChuyenCan(Double chuyenCan) {
        this.chuyenCan = chuyenCan;
    }

    public Double getKiemTra() {
        return this.kiemTra;
    }

    public MonHoc kiemTra(Double kiemTra) {
        this.kiemTra = kiemTra;
        return this;
    }

    public void setKiemTra(Double kiemTra) {
        this.kiemTra = kiemTra;
    }

    public Double getThucHanh() {
        return this.thucHanh;
    }

    public MonHoc thucHanh(Double thucHanh) {
        this.thucHanh = thucHanh;
        return this;
    }

    public void setThucHanh(Double thucHanh) {
        this.thucHanh = thucHanh;
    }

    public Double getBaiTap() {
        return this.baiTap;
    }

    public MonHoc baiTap(Double baiTap) {
        this.baiTap = baiTap;
        return this;
    }

    public void setBaiTap(Double baiTap) {
        this.baiTap = baiTap;
    }

    public Double getThi() {
        return this.thi;
    }

    public MonHoc thi(Double thi) {
        this.thi = thi;
        return this;
    }

    public void setThi(Double thi) {
        this.thi = thi;
    }

    public Set<Diem> getDiems() {
        return this.diems;
    }

    public MonHoc diems(Set<Diem> diems) {
        this.setDiems(diems);
        return this;
    }

    public MonHoc addDiem(Diem diem) {
        this.diems.add(diem);
        diem.setMaMonHoc(this);
        return this;
    }

    public MonHoc removeDiem(Diem diem) {
        this.diems.remove(diem);
        diem.setMaMonHoc(null);
        return this;
    }

    public void setDiems(Set<Diem> diems) {
        if (this.diems != null) {
            this.diems.forEach(i -> i.setMaMonHoc(null));
        }
        if (diems != null) {
            diems.forEach(i -> i.setMaMonHoc(this));
        }
        this.diems = diems;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonHoc)) {
            return false;
        }
        return id != null && id.equals(((MonHoc) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonHoc{" +
            "id=" + getId() +
            ", maMonHoc='" + getMaMonHoc() + "'" +
            ", tenMonHoc='" + getTenMonHoc() + "'" +
            ", soTinChi=" + getSoTinChi() +
            ", chuyenCan=" + getChuyenCan() +
            ", kiemTra=" + getKiemTra() +
            ", thucHanh=" + getThucHanh() +
            ", baiTap=" + getBaiTap() +
            ", thi=" + getThi() +
            "}";
    }
}
