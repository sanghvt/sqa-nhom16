import dayjs from 'dayjs';
import { IDiem } from 'app/shared/model/diem.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';
import { Nganh } from 'app/shared/model/enumerations/nganh.model';
import { Lop } from 'app/shared/model/enumerations/lop.model';

export interface ISinhVien {
  id?: number;
  maSinhVien?: string;
  password?: string;
  hoTen?: string;
  ngaySinh?: string;
  gioiTinh?: Sex | null;
  email?: string | null;
  soDienThoai?: string | null;
  nganh?: Nganh;
  khoaHoc?: string;
  lop?: Lop | null;
  diems?: IDiem[] | null;
}

export const defaultValue: Readonly<ISinhVien> = {};
