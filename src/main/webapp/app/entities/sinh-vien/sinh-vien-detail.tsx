import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sinh-vien.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISinhVienDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SinhVienDetail = (props: ISinhVienDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { sinhVienEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="sinhVienDetailsHeading">
          <Translate contentKey="sqanhom16App.sinhVien.detail.title">SinhVien</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.id}</dd>
          <dt>
            <span id="maSinhVien">
              <Translate contentKey="sqanhom16App.sinhVien.maSinhVien">Ma Sinh Vien</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.maSinhVien}</dd>
          <dt>
            <span id="password">
              <Translate contentKey="sqanhom16App.sinhVien.password">Password</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.password}</dd>
          <dt>
            <span id="hoTen">
              <Translate contentKey="sqanhom16App.sinhVien.hoTen">Ho Ten</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.hoTen}</dd>
          <dt>
            <span id="ngaySinh">
              <Translate contentKey="sqanhom16App.sinhVien.ngaySinh">Ngay Sinh</Translate>
            </span>
          </dt>
          <dd>
            {sinhVienEntity.ngaySinh ? <TextFormat value={sinhVienEntity.ngaySinh} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="gioiTinh">
              <Translate contentKey="sqanhom16App.sinhVien.gioiTinh">Gioi Tinh</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.gioiTinh}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="sqanhom16App.sinhVien.email">Email</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.email}</dd>
          <dt>
            <span id="soDienThoai">
              <Translate contentKey="sqanhom16App.sinhVien.soDienThoai">So Dien Thoai</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.soDienThoai}</dd>
          <dt>
            <span id="nganh">
              <Translate contentKey="sqanhom16App.sinhVien.nganh">Nganh</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.nganh}</dd>
          <dt>
            <span id="khoaHoc">
              <Translate contentKey="sqanhom16App.sinhVien.khoaHoc">Khoa Hoc</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.khoaHoc}</dd>
          <dt>
            <span id="lop">
              <Translate contentKey="sqanhom16App.sinhVien.lop">Lop</Translate>
            </span>
          </dt>
          <dd>{sinhVienEntity.lop}</dd>
        </dl>
        <Button tag={Link} to="/sinh-vien" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sinh-vien/${sinhVienEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ sinhVien }: IRootState) => ({
  sinhVienEntity: sinhVien.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SinhVienDetail);
