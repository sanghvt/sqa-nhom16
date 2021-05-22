import { IDiem } from 'app/shared/model/diem.model';

export interface IMonHoc {
  id?: number;
  maMonHoc?: string;
  tenMonHoc?: string;
  soTinChi?: number;
  chuyenCan?: number;
  kiemTra?: number;
  thucHanh?: number | null;
  baiTap?: number | null;
  thi?: number;
  diems?: IDiem[] | null;
}

export const defaultValue: Readonly<IMonHoc> = {};
