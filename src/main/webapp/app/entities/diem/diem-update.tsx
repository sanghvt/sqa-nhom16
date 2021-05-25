import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISinhVien } from 'app/shared/model/sinh-vien.model';
import { getEntities as getSinhViens } from 'app/entities/sinh-vien/sinh-vien.reducer';
import { IMonHoc } from 'app/shared/model/mon-hoc.model';
import { getEntities as getMonHocs } from 'app/entities/mon-hoc/mon-hoc.reducer';
import { getEntity, updateEntity, createEntity, reset } from './diem.reducer';
import { IDiem } from 'app/shared/model/diem.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDiemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DiemUpdate = (props: IDiemUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { diemEntity, sinhViens, monHocs, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/diem' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSinhViens();
    props.getMonHocs();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...diemEntity,
        ...values,
        maSinhVien: sinhViens.find(it => it.id.toString() === values.maSinhVienId.toString()),
        maMonHoc: monHocs.find(it => it.id.toString() === values.maMonHocId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="sqanhom16App.diem.home.createOrEditLabel" data-cy="DiemCreateUpdateHeading">
            <Translate contentKey="sqanhom16App.diem.home.createOrEditLabel">Create or edit a Diem</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : diemEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="diem-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="diem-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="chuyenCanLabel" for="diem-chuyenCan">
                  <Translate contentKey="sqanhom16App.diem.chuyenCan">Chuyen Can</Translate>
                </Label>
                <AvField
                  id="diem-chuyenCan"
                  data-cy="chuyenCan"
                  type="string"
                  className="form-control"
                  name="chuyenCan"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="kiemTraLabel" for="diem-kiemTra">
                  <Translate contentKey="sqanhom16App.diem.kiemTra">Kiem Tra</Translate>
                </Label>
                <AvField
                  id="diem-kiemTra"
                  data-cy="kiemTra"
                  type="string"
                  className="form-control"
                  name="kiemTra"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="thucHanhLabel" for="diem-thucHanh">
                  <Translate contentKey="sqanhom16App.diem.thucHanh">Thuc Hanh</Translate>
                </Label>
                <AvField
                  id="diem-thucHanh"
                  data-cy="thucHanh"
                  type="string"
                  className="form-control"
                  name="thucHanh"
                  validate={{
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="baiTapLabel" for="diem-baiTap">
                  <Translate contentKey="sqanhom16App.diem.baiTap">Bai Tap</Translate>
                </Label>
                <AvField
                  id="diem-baiTap"
                  data-cy="baiTap"
                  type="string"
                  className="form-control"
                  name="baiTap"
                  validate={{
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="thiLabel" for="diem-thi">
                  <Translate contentKey="sqanhom16App.diem.thi">Thi</Translate>
                </Label>
                <AvField
                  id="diem-thi"
                  data-cy="thi"
                  type="string"
                  className="form-control"
                  name="thi"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="soLanHocLabel" for="diem-soLanHoc">
                  <Translate contentKey="sqanhom16App.diem.soLanHoc">So Lan Hoc</Translate>
                </Label>
                <AvField
                  id="diem-soLanHoc"
                  data-cy="soLanHoc"
                  type="string"
                  className="form-control"
                  name="soLanHoc"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="tongKetLabel" for="diem-tongKet">
                  <Translate contentKey="sqanhom16App.diem.tongKet">Tong Ket</Translate>
                </Label>
                <AvField
                  id="diem-tongKet"
                  data-cy="tongKet"
                  type="string"
                  className="form-control"
                  name="tongKet"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="tongKetThangChuLabel" for="diem-tongKetThangChu">
                  <Translate contentKey="sqanhom16App.diem.tongKetThangChu">Tong Ket Thang Chu</Translate>
                </Label>
                <AvField
                  id="diem-tongKetThangChu"
                  data-cy="tongKetThangChu"
                  type="text"
                  name="tongKetThangChu"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="ketQuaLabel" for="diem-ketQua">
                  <Translate contentKey="sqanhom16App.diem.ketQua">Ket Qua</Translate>
                </Label>
                <AvInput
                  id="diem-ketQua"
                  data-cy="ketQua"
                  type="select"
                  className="form-control"
                  name="ketQua"
                  value={(!isNew && diemEntity.ketQua) || 'PASS'}
                >
                  <option value="PASS">{translate('sqanhom16App.Status.PASS')}</option>
                  <option value="NOTPASS">{translate('sqanhom16App.Status.NOTPASS')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="diem-maSinhVien">
                  <Translate contentKey="sqanhom16App.diem.maSinhVien">Ma Sinh Vien</Translate>
                </Label>
                <AvInput id="diem-maSinhVien" data-cy="maSinhVien" type="select" className="form-control" name="maSinhVienId">
                  <option value="" key="0" />
                  {sinhViens
                    ? sinhViens.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.maSinhVien}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="diem-maMonHoc">
                  <Translate contentKey="sqanhom16App.diem.maMonHoc">Ma Mon Hoc</Translate>
                </Label>
                <AvInput id="diem-maMonHoc" data-cy="maMonHoc" type="select" className="form-control" name="maMonHocId">
                  <option value="" key="0" />
                  {monHocs
                    ? monHocs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.tenMonHoc}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/diem" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  sinhViens: storeState.sinhVien.entities,
  monHocs: storeState.monHoc.entities,
  diemEntity: storeState.diem.entity,
  loading: storeState.diem.loading,
  updating: storeState.diem.updating,
  updateSuccess: storeState.diem.updateSuccess,
});

const mapDispatchToProps = {
  getSinhViens,
  getMonHocs,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DiemUpdate);
