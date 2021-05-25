package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.MonHoc} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.MonHocResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /mon-hocs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MonHocCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maMonHoc;

    private StringFilter tenMonHoc;

    private IntegerFilter soTinChi;

    private DoubleFilter chuyenCan;

    private DoubleFilter kiemTra;

    private DoubleFilter thucHanh;

    private DoubleFilter baiTap;

    private DoubleFilter thi;

    private LongFilter diemId;

    public MonHocCriteria() {}

    public MonHocCriteria(MonHocCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maMonHoc = other.maMonHoc == null ? null : other.maMonHoc.copy();
        this.tenMonHoc = other.tenMonHoc == null ? null : other.tenMonHoc.copy();
        this.soTinChi = other.soTinChi == null ? null : other.soTinChi.copy();
        this.chuyenCan = other.chuyenCan == null ? null : other.chuyenCan.copy();
        this.kiemTra = other.kiemTra == null ? null : other.kiemTra.copy();
        this.thucHanh = other.thucHanh == null ? null : other.thucHanh.copy();
        this.baiTap = other.baiTap == null ? null : other.baiTap.copy();
        this.thi = other.thi == null ? null : other.thi.copy();
        this.diemId = other.diemId == null ? null : other.diemId.copy();
    }

    @Override
    public MonHocCriteria copy() {
        return new MonHocCriteria(this);
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

    public StringFilter getMaMonHoc() {
        return maMonHoc;
    }

    public StringFilter maMonHoc() {
        if (maMonHoc == null) {
            maMonHoc = new StringFilter();
        }
        return maMonHoc;
    }

    public void setMaMonHoc(StringFilter maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public StringFilter getTenMonHoc() {
        return tenMonHoc;
    }

    public StringFilter tenMonHoc() {
        if (tenMonHoc == null) {
            tenMonHoc = new StringFilter();
        }
        return tenMonHoc;
    }

    public void setTenMonHoc(StringFilter tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public IntegerFilter getSoTinChi() {
        return soTinChi;
    }

    public IntegerFilter soTinChi() {
        if (soTinChi == null) {
            soTinChi = new IntegerFilter();
        }
        return soTinChi;
    }

    public void setSoTinChi(IntegerFilter soTinChi) {
        this.soTinChi = soTinChi;
    }

    public DoubleFilter getChuyenCan() {
        return chuyenCan;
    }

    public DoubleFilter chuyenCan() {
        if (chuyenCan == null) {
            chuyenCan = new DoubleFilter();
        }
        return chuyenCan;
    }

    public void setChuyenCan(DoubleFilter chuyenCan) {
        this.chuyenCan = chuyenCan;
    }

    public DoubleFilter getKiemTra() {
        return kiemTra;
    }

    public DoubleFilter kiemTra() {
        if (kiemTra == null) {
            kiemTra = new DoubleFilter();
        }
        return kiemTra;
    }

    public void setKiemTra(DoubleFilter kiemTra) {
        this.kiemTra = kiemTra;
    }

    public DoubleFilter getThucHanh() {
        return thucHanh;
    }

    public DoubleFilter thucHanh() {
        if (thucHanh == null) {
            thucHanh = new DoubleFilter();
        }
        return thucHanh;
    }

    public void setThucHanh(DoubleFilter thucHanh) {
        this.thucHanh = thucHanh;
    }

    public DoubleFilter getBaiTap() {
        return baiTap;
    }

    public DoubleFilter baiTap() {
        if (baiTap == null) {
            baiTap = new DoubleFilter();
        }
        return baiTap;
    }

    public void setBaiTap(DoubleFilter baiTap) {
        this.baiTap = baiTap;
    }

    public DoubleFilter getThi() {
        return thi;
    }

    public DoubleFilter thi() {
        if (thi == null) {
            thi = new DoubleFilter();
        }
        return thi;
    }

    public void setThi(DoubleFilter thi) {
        this.thi = thi;
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
        final MonHocCriteria that = (MonHocCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maMonHoc, that.maMonHoc) &&
            Objects.equals(tenMonHoc, that.tenMonHoc) &&
            Objects.equals(soTinChi, that.soTinChi) &&
            Objects.equals(chuyenCan, that.chuyenCan) &&
            Objects.equals(kiemTra, that.kiemTra) &&
            Objects.equals(thucHanh, that.thucHanh) &&
            Objects.equals(baiTap, that.baiTap) &&
            Objects.equals(thi, that.thi) &&
            Objects.equals(diemId, that.diemId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maMonHoc, tenMonHoc, soTinChi, chuyenCan, kiemTra, thucHanh, baiTap, thi, diemId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonHocCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maMonHoc != null ? "maMonHoc=" + maMonHoc + ", " : "") +
            (tenMonHoc != null ? "tenMonHoc=" + tenMonHoc + ", " : "") +
            (soTinChi != null ? "soTinChi=" + soTinChi + ", " : "") +
            (chuyenCan != null ? "chuyenCan=" + chuyenCan + ", " : "") +
            (kiemTra != null ? "kiemTra=" + kiemTra + ", " : "") +
            (thucHanh != null ? "thucHanh=" + thucHanh + ", " : "") +
            (baiTap != null ? "baiTap=" + baiTap + ", " : "") +
            (thi != null ? "thi=" + thi + ", " : "") +
            (diemId != null ? "diemId=" + diemId + ", " : "") +
            "}";
    }
}
