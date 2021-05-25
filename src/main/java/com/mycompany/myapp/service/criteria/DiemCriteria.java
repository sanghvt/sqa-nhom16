package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.Status;
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
 * Criteria class for the {@link com.mycompany.myapp.domain.Diem} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DiemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /diems?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DiemCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {

        public StatusFilter() {}

        public StatusFilter(StatusFilter filter) {
            super(filter);
        }

        @Override
        public StatusFilter copy() {
            return new StatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter chuyenCan;

    private DoubleFilter kiemTra;

    private DoubleFilter thucHanh;

    private DoubleFilter baiTap;

    private DoubleFilter thi;

    private IntegerFilter soLanHoc;

    private DoubleFilter tongKet;

    private StringFilter tongKetThangChu;

    private StatusFilter ketQua;

    private LongFilter maSinhVienId;

    private LongFilter maMonHocId;

    public DiemCriteria() {}

    public DiemCriteria(DiemCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.chuyenCan = other.chuyenCan == null ? null : other.chuyenCan.copy();
        this.kiemTra = other.kiemTra == null ? null : other.kiemTra.copy();
        this.thucHanh = other.thucHanh == null ? null : other.thucHanh.copy();
        this.baiTap = other.baiTap == null ? null : other.baiTap.copy();
        this.thi = other.thi == null ? null : other.thi.copy();
        this.soLanHoc = other.soLanHoc == null ? null : other.soLanHoc.copy();
        this.tongKet = other.tongKet == null ? null : other.tongKet.copy();
        this.tongKetThangChu = other.tongKetThangChu == null ? null : other.tongKetThangChu.copy();
        this.ketQua = other.ketQua == null ? null : other.ketQua.copy();
        this.maSinhVienId = other.maSinhVienId == null ? null : other.maSinhVienId.copy();
        this.maMonHocId = other.maMonHocId == null ? null : other.maMonHocId.copy();
    }

    @Override
    public DiemCriteria copy() {
        return new DiemCriteria(this);
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

    public IntegerFilter getSoLanHoc() {
        return soLanHoc;
    }

    public IntegerFilter soLanHoc() {
        if (soLanHoc == null) {
            soLanHoc = new IntegerFilter();
        }
        return soLanHoc;
    }

    public void setSoLanHoc(IntegerFilter soLanHoc) {
        this.soLanHoc = soLanHoc;
    }

    public DoubleFilter getTongKet() {
        return tongKet;
    }

    public DoubleFilter tongKet() {
        if (tongKet == null) {
            tongKet = new DoubleFilter();
        }
        return tongKet;
    }

    public void setTongKet(DoubleFilter tongKet) {
        this.tongKet = tongKet;
    }

    public StringFilter getTongKetThangChu() {
        return tongKetThangChu;
    }

    public StringFilter tongKetThangChu() {
        if (tongKetThangChu == null) {
            tongKetThangChu = new StringFilter();
        }
        return tongKetThangChu;
    }

    public void setTongKetThangChu(StringFilter tongKetThangChu) {
        this.tongKetThangChu = tongKetThangChu;
    }

    public StatusFilter getKetQua() {
        return ketQua;
    }

    public StatusFilter ketQua() {
        if (ketQua == null) {
            ketQua = new StatusFilter();
        }
        return ketQua;
    }

    public void setKetQua(StatusFilter ketQua) {
        this.ketQua = ketQua;
    }

    public LongFilter getMaSinhVienId() {
        return maSinhVienId;
    }

    public LongFilter maSinhVienId() {
        if (maSinhVienId == null) {
            maSinhVienId = new LongFilter();
        }
        return maSinhVienId;
    }

    public void setMaSinhVienId(LongFilter maSinhVienId) {
        this.maSinhVienId = maSinhVienId;
    }

    public LongFilter getMaMonHocId() {
        return maMonHocId;
    }

    public LongFilter maMonHocId() {
        if (maMonHocId == null) {
            maMonHocId = new LongFilter();
        }
        return maMonHocId;
    }

    public void setMaMonHocId(LongFilter maMonHocId) {
        this.maMonHocId = maMonHocId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DiemCriteria that = (DiemCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(chuyenCan, that.chuyenCan) &&
            Objects.equals(kiemTra, that.kiemTra) &&
            Objects.equals(thucHanh, that.thucHanh) &&
            Objects.equals(baiTap, that.baiTap) &&
            Objects.equals(thi, that.thi) &&
            Objects.equals(soLanHoc, that.soLanHoc) &&
            Objects.equals(tongKet, that.tongKet) &&
            Objects.equals(tongKetThangChu, that.tongKetThangChu) &&
            Objects.equals(ketQua, that.ketQua) &&
            Objects.equals(maSinhVienId, that.maSinhVienId) &&
            Objects.equals(maMonHocId, that.maMonHocId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            chuyenCan,
            kiemTra,
            thucHanh,
            baiTap,
            thi,
            soLanHoc,
            tongKet,
            tongKetThangChu,
            ketQua,
            maSinhVienId,
            maMonHocId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DiemCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (chuyenCan != null ? "chuyenCan=" + chuyenCan + ", " : "") +
            (kiemTra != null ? "kiemTra=" + kiemTra + ", " : "") +
            (thucHanh != null ? "thucHanh=" + thucHanh + ", " : "") +
            (baiTap != null ? "baiTap=" + baiTap + ", " : "") +
            (thi != null ? "thi=" + thi + ", " : "") +
            (soLanHoc != null ? "soLanHoc=" + soLanHoc + ", " : "") +
            (tongKet != null ? "tongKet=" + tongKet + ", " : "") +
            (tongKetThangChu != null ? "tongKetThangChu=" + tongKetThangChu + ", " : "") +
            (ketQua != null ? "ketQua=" + ketQua + ", " : "") +
            (maSinhVienId != null ? "maSinhVienId=" + maSinhVienId + ", " : "") +
            (maMonHocId != null ? "maMonHocId=" + maMonHocId + ", " : "") +
            "}";
    }
}
