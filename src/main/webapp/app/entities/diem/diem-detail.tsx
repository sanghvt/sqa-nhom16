import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './diem.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DiemDetail = (props: IDiemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { diemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="diemDetailsHeading">
          <Translate contentKey="sqanhom16App.diem.detail.title">Diem</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{diemEntity.id}</dd>
          <dt>
            <span id="chuyenCan">
              <Translate contentKey="sqanhom16App.diem.chuyenCan">Chuyen Can</Translate>
            </span>
          </dt>
          <dd>{diemEntity.chuyenCan}</dd>
          <dt>
            <span id="kiemTra">
              <Translate contentKey="sqanhom16App.diem.kiemTra">Kiem Tra</Translate>
            </span>
          </dt>
          <dd>{diemEntity.kiemTra}</dd>
          <dt>
            <span id="thucHanh">
              <Translate contentKey="sqanhom16App.diem.thucHanh">Thuc Hanh</Translate>
            </span>
          </dt>
          <dd>{diemEntity.thucHanh}</dd>
          <dt>
            <span id="baiTap">
              <Translate contentKey="sqanhom16App.diem.baiTap">Bai Tap</Translate>
            </span>
          </dt>
          <dd>{diemEntity.baiTap}</dd>
          <dt>
            <span id="thi">
              <Translate contentKey="sqanhom16App.diem.thi">Thi</Translate>
            </span>
          </dt>
          <dd>{diemEntity.thi}</dd>
          <dt>
            <span id="soLanHoc">
              <Translate contentKey="sqanhom16App.diem.soLanHoc">So Lan Hoc</Translate>
            </span>
          </dt>
          <dd>{diemEntity.soLanHoc}</dd>
          <dt>
            <span id="tongKet">
              <Translate contentKey="sqanhom16App.diem.tongKet">Tong Ket</Translate>
            </span>
          </dt>
          <dd>{diemEntity.tongKet}</dd>
          <dt>
            <span id="tongKetThangChu">
              <Translate contentKey="sqanhom16App.diem.tongKetThangChu">Tong Ket Thang Chu</Translate>
            </span>
          </dt>
          <dd>{diemEntity.tongKetThangChu}</dd>
          <dt>
            <span id="ketQua">
              <Translate contentKey="sqanhom16App.diem.ketQua">Ket Qua</Translate>
            </span>
          </dt>
          <dd>{diemEntity.ketQua}</dd>
          <dt>
            <Translate contentKey="sqanhom16App.diem.maSinhVien">Ma Sinh Vien</Translate>
          </dt>
          <dd>{diemEntity.maSinhVien ? diemEntity.maSinhVien.id : ''}</dd>
          <dt>
            <Translate contentKey="sqanhom16App.diem.maMonHoc">Ma Mon Hoc</Translate>
          </dt>
          <dd>{diemEntity.maMonHoc ? diemEntity.maMonHoc.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/diem" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/diem/${diemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ diem }: IRootState) => ({
  diemEntity: diem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DiemDetail);
