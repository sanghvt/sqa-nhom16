package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "diem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Diem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "chuyen_can", nullable = false)
    private Double chuyenCan;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "kiem_tra", nullable = false)
    private Double kiemTra;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "thuc_hanh")
    private Double thucHanh;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "bai_tap")
    private Double baiTap;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "thi", nullable = false)
    private Double thi;

    @NotNull
    @Column(name = "so_lan_hoc", nullable = false)
    private Integer soLanHoc;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "tong_ket", nullable = false)
    private Double tongKet;

    @NotNull
    @Column(name = "tong_ket_thang_chu", nullable = false)
    private String tongKetThangChu;

    @Enumerated(EnumType.STRING)
    @Column(name = "ket_qua")
    private Status ketQua;

    @ManyToOne
    @JsonIgnoreProperties(value = { "diems" }, allowSetters = true)
    private SinhVien maSinhVien;

    /**
     * Another side of the same relationship
     */
    @ApiModelProperty(value = "Another side of the same relationship")
    @ManyToOne
    @JsonIgnoreProperties(value = { "diems" }, allowSetters = true)
    private MonHoc maMonHoc;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diem id(Long id) {
        this.id = id;
        return this;
    }

    public Double getChuyenCan() {
        return this.chuyenCan;
    }

    public Diem chuyenCan(Double chuyenCan) {
        this.chuyenCan = chuyenCan;
        return this;
    }

    public void setChuyenCan(Double chuyenCan) {
        this.chuyenCan = chuyenCan;
    }

    public Double getKiemTra() {
        return this.kiemTra;
    }

    public Diem kiemTra(Double kiemTra) {
        this.kiemTra = kiemTra;
        return this;
    }

    public void setKiemTra(Double kiemTra) {
        this.kiemTra = kiemTra;
    }

    public Double getThucHanh() {
        return this.thucHanh;
    }

    public Diem thucHanh(Double thucHanh) {
        this.thucHanh = thucHanh;
        return this;
    }

    public void setThucHanh(Double thucHanh) {
        this.thucHanh = thucHanh;
    }

    public Double getBaiTap() {
        return this.baiTap;
    }

    public Diem baiTap(Double baiTap) {
        this.baiTap = baiTap;
        return this;
    }

    public void setBaiTap(Double baiTap) {
        this.baiTap = baiTap;
    }

    public Double getThi() {
        return this.thi;
    }

    public Diem thi(Double thi) {
        this.thi = thi;
        return this;
    }

    public void setThi(Double thi) {
        this.thi = thi;
    }

    public Integer getSoLanHoc() {
        return this.soLanHoc;
    }

    public Diem soLanHoc(Integer soLanHoc) {
        this.soLanHoc = soLanHoc;
        return this;
    }

    public void setSoLanHoc(Integer soLanHoc) {
        this.soLanHoc = soLanHoc;
    }

    public Double getTongKet() {
        return this.tongKet;
    }

    public Diem tongKet(Double tongKet) {
        this.tongKet = tongKet;
        return this;
    }

    public void setTongKet(Double tongKet) {
        this.tongKet = tongKet;
    }

    public String getTongKetThangChu() {
        return this.tongKetThangChu;
    }

    public Diem tongKetThangChu(String tongKetThangChu) {
        this.tongKetThangChu = tongKetThangChu;
        return this;
    }

    public void setTongKetThangChu(String tongKetThangChu) {
        this.tongKetThangChu = tongKetThangChu;
    }

    public Status getKetQua() {
        return this.ketQua;
    }

    public Diem ketQua(Status ketQua) {
        this.ketQua = ketQua;
        return this;
    }

    public void setKetQua(Status ketQua) {
        this.ketQua = ketQua;
    }

    public SinhVien getMaSinhVien() {
        return this.maSinhVien;
    }

    public Diem maSinhVien(SinhVien sinhVien) {
        this.setMaSinhVien(sinhVien);
        return this;
    }

    public void setMaSinhVien(SinhVien sinhVien) {
        this.maSinhVien = sinhVien;
    }

    public MonHoc getMaMonHoc() {
        return this.maMonHoc;
    }

    public Diem maMonHoc(MonHoc monHoc) {
        this.setMaMonHoc(monHoc);
        return this;
    }

    public void setMaMonHoc(MonHoc monHoc) {
        this.maMonHoc = monHoc;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diem)) {
            return false;
        }
        return id != null && id.equals(((Diem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Diem{" +
            "id=" + getId() +
            ", chuyenCan=" + getChuyenCan() +
            ", kiemTra=" + getKiemTra() +
            ", thucHanh=" + getThucHanh() +
            ", baiTap=" + getBaiTap() +
            ", thi=" + getThi() +
            ", soLanHoc=" + getSoLanHoc() +
            ", tongKet=" + getTongKet() +
            ", tongKetThangChu='" + getTongKetThangChu() + "'" +
            ", ketQua='" + getKetQua() + "'" +
            "}";
    }
}
