package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Lop;
import com.mycompany.myapp.domain.enumeration.Nganh;
import com.mycompany.myapp.domain.enumeration.Sex;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SinhVien.
 */
@Entity
@Table(name = "sinh_vien")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SinhVien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma_sinh_vien", nullable = false)
    private String maSinhVien;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @NotNull
    @Column(name = "ngay_sinh", nullable = false)
    private LocalDate ngaySinh;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    private Sex gioiTinh;

    @Column(name = "email")
    private String email;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nganh", nullable = false)
    private Nganh nganh;

    @NotNull
    @Column(name = "khoa_hoc", nullable = false)
    private String khoaHoc;

    @Enumerated(EnumType.STRING)
    @Column(name = "lop")
    private Lop lop;

    @OneToMany(mappedBy = "maSinhVien")
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

    public SinhVien id(Long id) {
        this.id = id;
        return this;
    }

    public String getMaSinhVien() {
        return this.maSinhVien;
    }

    public SinhVien maSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
        return this;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getPassword() {
        return this.password;
    }

    public SinhVien password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public SinhVien hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return this.ngaySinh;
    }

    public SinhVien ngaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
        return this;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Sex getGioiTinh() {
        return this.gioiTinh;
    }

    public SinhVien gioiTinh(Sex gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(Sex gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return this.email;
    }

    public SinhVien email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public SinhVien soDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Nganh getNganh() {
        return this.nganh;
    }

    public SinhVien nganh(Nganh nganh) {
        this.nganh = nganh;
        return this;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    public String getKhoaHoc() {
        return this.khoaHoc;
    }

    public SinhVien khoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
        return this;
    }

    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public Lop getLop() {
        return this.lop;
    }

    public SinhVien lop(Lop lop) {
        this.lop = lop;
        return this;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public Set<Diem> getDiems() {
        return this.diems;
    }

    public SinhVien diems(Set<Diem> diems) {
        this.setDiems(diems);
        return this;
    }

    public SinhVien addDiem(Diem diem) {
        this.diems.add(diem);
        diem.setMaSinhVien(this);
        return this;
    }

    public SinhVien removeDiem(Diem diem) {
        this.diems.remove(diem);
        diem.setMaSinhVien(null);
        return this;
    }

    public void setDiems(Set<Diem> diems) {
        if (this.diems != null) {
            this.diems.forEach(i -> i.setMaSinhVien(null));
        }
        if (diems != null) {
            diems.forEach(i -> i.setMaSinhVien(this));
        }
        this.diems = diems;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SinhVien)) {
            return false;
        }
        return id != null && id.equals(((SinhVien) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SinhVien{" +
            "id=" + getId() +
            ", maSinhVien='" + getMaSinhVien() + "'" +
            ", password='" + getPassword() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", ngaySinh='" + getNgaySinh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", email='" + getEmail() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", nganh='" + getNganh() + "'" +
            ", khoaHoc='" + getKhoaHoc() + "'" +
            ", lop='" + getLop() + "'" +
            "}";
    }
}
