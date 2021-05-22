import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mon-hoc.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMonHocDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MonHocDetail = (props: IMonHocDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { monHocEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="monHocDetailsHeading">
          <Translate contentKey="sqanhom16App.monHoc.detail.title">MonHoc</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.id}</dd>
          <dt>
            <span id="maMonHoc">
              <Translate contentKey="sqanhom16App.monHoc.maMonHoc">Ma Mon Hoc</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.maMonHoc}</dd>
          <dt>
            <span id="tenMonHoc">
              <Translate contentKey="sqanhom16App.monHoc.tenMonHoc">Ten Mon Hoc</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.tenMonHoc}</dd>
          <dt>
            <span id="soTinChi">
              <Translate contentKey="sqanhom16App.monHoc.soTinChi">So Tin Chi</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.soTinChi}</dd>
          <dt>
            <span id="chuyenCan">
              <Translate contentKey="sqanhom16App.monHoc.chuyenCan">Chuyen Can</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.chuyenCan}</dd>
          <dt>
            <span id="kiemTra">
              <Translate contentKey="sqanhom16App.monHoc.kiemTra">Kiem Tra</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.kiemTra}</dd>
          <dt>
            <span id="thucHanh">
              <Translate contentKey="sqanhom16App.monHoc.thucHanh">Thuc Hanh</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.thucHanh}</dd>
          <dt>
            <span id="baiTap">
              <Translate contentKey="sqanhom16App.monHoc.baiTap">Bai Tap</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.baiTap}</dd>
          <dt>
            <span id="thi">
              <Translate contentKey="sqanhom16App.monHoc.thi">Thi</Translate>
            </span>
          </dt>
          <dd>{monHocEntity.thi}</dd>
        </dl>
        <Button tag={Link} to="/mon-hoc" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/mon-hoc/${monHocEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ monHoc }: IRootState) => ({
  monHocEntity: monHoc.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MonHocDetail);
