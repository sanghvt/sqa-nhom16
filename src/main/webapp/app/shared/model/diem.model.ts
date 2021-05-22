import { ISinhVien } from 'app/shared/model/sinh-vien.model';
import { IMonHoc } from 'app/shared/model/mon-hoc.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDiem {
  id?: number;
  chuyenCan?: number;
  kiemTra?: number;
  thucHanh?: number | null;
  baiTap?: number | null;
  thi?: number;
  soLanHoc?: number;
  tongKet?: number;
  tongKetThangChu?: string;
  ketQua?: Status | null;
  maSinhVien?: ISinhVien | null;
  maMonHoc?: IMonHoc | null;
}

export const defaultValue: Readonly<IDiem> = {};
