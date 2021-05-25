package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.Lop;
import com.mycompany.myapp.domain.enumeration.Nganh;
import com.mycompany.myapp.domain.enumeration.Sex;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.SinhVien} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.SinhVienResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sinh-viens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SinhVienCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Sex
     */
    public static class SexFilter extends Filter<Sex> {

        public SexFilter() {}

        public SexFilter(SexFilter filter) {
            super(filter);
        }

        @Override
        public SexFilter copy() {
            return new SexFilter(this);
        }
    }

    /**
     * Class for filtering Nganh
     */
    public static class NganhFilter extends Filter<Nganh> {

        public NganhFilter() {}

        public NganhFilter(NganhFilter filter) {
            super(filter);
        }

        @Override
        public NganhFilter copy() {
            return new NganhFilter(this);
        }
    }

    /**
     * Class for filtering Lop
     */
    public static class LopFilter extends Filter<Lop> {

        public LopFilter() {}

        public LopFilter(LopFilter filter) {
            super(filter);
        }

        @Override
        public LopFilter copy() {
            return new LopFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maSinhVien;

    private StringFilter password;

    private StringFilter hoTen;

    private LocalDateFilter ngaySinh;

    private SexFilter gioiTinh;

    private StringFilter email;

    private StringFilter soDienThoai;

    private NganhFilter nganh;

    private StringFilter khoaHoc;

    private LopFilter lop;

    private LongFilter diemId;

    public SinhVienCriteria() {}

    public SinhVienCriteria(SinhVienCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maSinhVien = other.maSinhVien == null ? null : other.maSinhVien.copy();
        this.password = other.password == null ? null : other.password.copy();
        this.hoTen = other.hoTen == null ? null : other.hoTen.copy();
        this.ngaySinh = other.ngaySinh == null ? null : other.ngaySinh.copy();
        this.gioiTinh = other.gioiTinh == null ? null : other.gioiTinh.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.soDienThoai = other.soDienThoai == null ? null : other.soDienThoai.copy();
        this.nganh = other.nganh == null ? null : other.nganh.copy();
        this.khoaHoc = other.khoaHoc == null ? null : other.khoaHoc.copy();
        this.lop = other.lop == null ? null : other.lop.copy();
        this.diemId = other.diemId == null ? null : other.diemId.copy();
    }

    @Override
    public SinhVienCriteria copy() {
        return new SinhVienCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaSinhVien() {
        return maSinhVien;
    }

    public StringFilter maSinhVien() {
        if (maSinhVien == null) {
            maSinhVien = new StringFilter();
        }
        return maSinhVien;
    }

    public void setMaSinhVien(StringFilter maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public StringFilter getPassword() {
        return password;
    }

    public StringFilter password() {
        if (password == null) {
            password = new StringFilter();
        }
        return password;
    }

    public void setPassword(StringFilter password) {
        this.password = password;
    }

    public StringFilter getHoTen() {
        return hoTen;
    }

    public StringFilter hoTen() {
        if (hoTen == null) {
            hoTen = new StringFilter();
        }
        return hoTen;
    }

    public void setHoTen(StringFilter hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDateFilter getNgaySinh() {
        return ngaySinh;
    }

    public LocalDateFilter ngaySinh() {
        if (ngaySinh == null) {
            ngaySinh = new LocalDateFilter();
        }
        return ngaySinh;
    }

    public void setNgaySinh(LocalDateFilter ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public SexFilter getGioiTinh() {
        return gioiTinh;
    }

    public SexFilter gioiTinh() {
        if (gioiTinh == null) {
            gioiTinh = new SexFilter();
        }
        return gioiTinh;
    }

    public void setGioiTinh(SexFilter gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getSoDienThoai() {
        return soDienThoai;
    }

    public StringFilter soDienThoai() {
        if (soDienThoai == null) {
            soDienThoai = new StringFilter();
        }
        return soDienThoai;
    }

    public void setSoDienThoai(StringFilter soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public NganhFilter getNganh() {
        return nganh;
    }

    public NganhFilter nganh() {
        if (nganh == null) {
            nganh = new NganhFilter();
        }
        return nganh;
    }

    public void setNganh(NganhFilter nganh) {
        this.nganh = nganh;
    }

    public StringFilter getKhoaHoc() {
        return khoaHoc;
    }

    public StringFilter khoaHoc() {
        if (khoaHoc == null) {
            khoaHoc = new StringFilter();
        }
        return khoaHoc;
    }

    public void setKhoaHoc(StringFilter khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public LopFilter getLop() {
        return lop;
    }

    public LopFilter lop() {
        if (lop == null) {
            lop = new LopFilter();
        }
        return lop;
    }

    public void setLop(LopFilter lop) {
        this.lop = lop;
    }

    public LongFilter getDiemId() {
        return diemId;
    }

    public LongFilter diemId() {
        if (diemId == null) {
            diemId = new LongFilter();
        }
        return diemId;
    }

    public void setDiemId(LongFilter diemId) {
        this.diemId = diemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SinhVienCriteria that = (SinhVienCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maSinhVien, that.maSinhVien) &&
            Objects.equals(password, that.password) &&
            Objects.equals(hoTen, that.hoTen) &&
            Objects.equals(ngaySinh, that.ngaySinh) &&
            Objects.equals(gioiTinh, that.gioiTinh) &&
            Objects.equals(email, that.email) &&
            Objects.equals(soDienThoai, that.soDienThoai) &&
            Objects.equals(nganh, that.nganh) &&
            Objects.equals(khoaHoc, that.khoaHoc) &&
            Objects.equals(lop, that.lop) &&
            Objects.equals(diemId, that.diemId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maSinhVien, password, hoTen, ngaySinh, gioiTinh, email, soDienThoai, nganh, khoaHoc, lop, diemId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SinhVienCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maSinhVien != null ? "maSinhVien=" + maSinhVien + ", " : "") +
            (password != null ? "password=" + password + ", " : "") +
            (hoTen != null ? "hoTen=" + hoTen + ", " : "") +
            (ngaySinh != null ? "ngaySinh=" + ngaySinh + ", " : "") +
            (gioiTinh != null ? "gioiTinh=" + gioiTinh + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (soDienThoai != null ? "soDienThoai=" + soDienThoai + ", " : "") +
            (nganh != null ? "nganh=" + nganh + ", " : "") +
            (khoaHoc != null ? "khoaHoc=" + khoaHoc + ", " : "") +
            (lop != null ? "lop=" + lop + ", " : "") +
            (diemId != null ? "diemId=" + diemId + ", " : "") +
            "}";
    }
}
