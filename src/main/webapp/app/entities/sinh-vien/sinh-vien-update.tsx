import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sinh-vien.reducer';
import { ISinhVien } from 'app/shared/model/sinh-vien.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISinhVienUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SinhVienUpdate = (props: ISinhVienUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { sinhVienEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/sinh-vien' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...sinhVienEntity,
        ...values,
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
          <h2 id="sqanhom16App.sinhVien.home.createOrEditLabel" data-cy="SinhVienCreateUpdateHeading">
            <Translate contentKey="sqanhom16App.sinhVien.home.createOrEditLabel">Create or edit a SinhVien</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : sinhVienEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="sinh-vien-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="sinh-vien-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="maSinhVienLabel" for="sinh-vien-maSinhVien">
                  <Translate contentKey="sqanhom16App.sinhVien.maSinhVien">Ma Sinh Vien</Translate>
                </Label>
                <AvField
                  id="sinh-vien-maSinhVien"
                  data-cy="maSinhVien"
                  type="text"
                  name="maSinhVien"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="passwordLabel" for="sinh-vien-password">
                  <Translate contentKey="sqanhom16App.sinhVien.password">Password</Translate>
                </Label>
                <AvField
                  id="sinh-vien-password"
                  data-cy="password"
                  type="text"
                  name="password"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="hoTenLabel" for="sinh-vien-hoTen">
                  <Translate contentKey="sqanhom16App.sinhVien.hoTen">Ho Ten</Translate>
                </Label>
                <AvField
                  id="sinh-vien-hoTen"
                  data-cy="hoTen"
                  type="text"
                  name="hoTen"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="ngaySinhLabel" for="sinh-vien-ngaySinh">
                  <Translate contentKey="sqanhom16App.sinhVien.ngaySinh">Ngay Sinh</Translate>
                </Label>
                <AvField
                  id="sinh-vien-ngaySinh"
                  data-cy="ngaySinh"
                  type="date"
                  className="form-control"
                  name="ngaySinh"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="gioiTinhLabel" for="sinh-vien-gioiTinh">
                  <Translate contentKey="sqanhom16App.sinhVien.gioiTinh">Gioi Tinh</Translate>
                </Label>
                <AvInput
                  id="sinh-vien-gioiTinh"
                  data-cy="gioiTinh"
                  type="select"
                  className="form-control"
                  name="gioiTinh"
                  value={(!isNew && sinhVienEntity.gioiTinh) || 'Male'}
                >
                  <option value="Male">{translate('sqanhom16App.Sex.Male')}</option>
                  <option value="Female">{translate('sqanhom16App.Sex.Female')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="sinh-vien-email">
                  <Translate contentKey="sqanhom16App.sinhVien.email">Email</Translate>
                </Label>
                <AvField id="sinh-vien-email" data-cy="email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="soDienThoaiLabel" for="sinh-vien-soDienThoai">
                  <Translate contentKey="sqanhom16App.sinhVien.soDienThoai">So Dien Thoai</Translate>
                </Label>
                <AvField id="sinh-vien-soDienThoai" data-cy="soDienThoai" type="text" name="soDienThoai" />
              </AvGroup>
              <AvGroup>
                <Label id="nganhLabel" for="sinh-vien-nganh">
                  <Translate contentKey="sqanhom16App.sinhVien.nganh">Nganh</Translate>
                </Label>
                <AvInput
                  id="sinh-vien-nganh"
                  data-cy="nganh"
                  type="select"
                  className="form-control"
                  name="nganh"
                  value={(!isNew && sinhVienEntity.nganh) || 'InformationTechnology'}
                >
                  <option value="InformationTechnology">{translate('sqanhom16App.Nganh.InformationTechnology')}</option>
                  <option value="Marketing">{translate('sqanhom16App.Nganh.Marketing')}</option>
                  <option value="MultimediaCommunication">{translate('sqanhom16App.Nganh.MultimediaCommunication')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="khoaHocLabel" for="sinh-vien-khoaHoc">
                  <Translate contentKey="sqanhom16App.sinhVien.khoaHoc">Khoa Hoc</Translate>
                </Label>
                <AvField
                  id="sinh-vien-khoaHoc"
                  data-cy="khoaHoc"
                  type="text"
                  name="khoaHoc"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lopLabel" for="sinh-vien-lop">
                  <Translate contentKey="sqanhom16App.sinhVien.lop">Lop</Translate>
                </Label>
                <AvInput
                  id="sinh-vien-lop"
                  data-cy="lop"
                  type="select"
                  className="form-control"
                  name="lop"
                  value={(!isNew && sinhVienEntity.lop) || 'D17CQCN03'}
                >
                  <option value="D17CQCN03">{translate('sqanhom16App.Lop.D17CQCN03')}</option>
                  <option value="D17CQCN04">{translate('sqanhom16App.Lop.D17CQCN04')}</option>
                  <option value="D17CQCN05">{translate('sqanhom16App.Lop.D17CQCN05')}</option>
                  <option value="D17CQCN06">{translate('sqanhom16App.Lop.D17CQCN06')}</option>
                  <option value="D17CQCN07">{translate('sqanhom16App.Lop.D17CQCN07')}</option>
                  <option value="D17CQCN08">{translate('sqanhom16App.Lop.D17CQCN08')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/sinh-vien" replace color="info">
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
  sinhVienEntity: storeState.sinhVien.entity,
  loading: storeState.sinhVien.loading,
  updating: storeState.sinhVien.updating,
  updateSuccess: storeState.sinhVien.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SinhVienUpdate);
