import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './mon-hoc.reducer';
import { IMonHoc } from 'app/shared/model/mon-hoc.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMonHocUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MonHocUpdate = (props: IMonHocUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { monHocEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/mon-hoc' + props.location.search);
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
        ...monHocEntity,
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
          <h2 id="sqanhom16App.monHoc.home.createOrEditLabel" data-cy="MonHocCreateUpdateHeading">
            <Translate contentKey="sqanhom16App.monHoc.home.createOrEditLabel">Create or edit a MonHoc</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : monHocEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="mon-hoc-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="mon-hoc-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="maMonHocLabel" for="mon-hoc-maMonHoc">
                  <Translate contentKey="sqanhom16App.monHoc.maMonHoc">Ma Mon Hoc</Translate>
                </Label>
                <AvField
                  id="mon-hoc-maMonHoc"
                  data-cy="maMonHoc"
                  type="text"
                  name="maMonHoc"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="tenMonHocLabel" for="mon-hoc-tenMonHoc">
                  <Translate contentKey="sqanhom16App.monHoc.tenMonHoc">Ten Mon Hoc</Translate>
                </Label>
                <AvField
                  id="mon-hoc-tenMonHoc"
                  data-cy="tenMonHoc"
                  type="text"
                  name="tenMonHoc"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="soTinChiLabel" for="mon-hoc-soTinChi">
                  <Translate contentKey="sqanhom16App.monHoc.soTinChi">So Tin Chi</Translate>
                </Label>
                <AvField
                  id="mon-hoc-soTinChi"
                  data-cy="soTinChi"
                  type="string"
                  className="form-control"
                  name="soTinChi"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                    max: { value: 4, errorMessage: translate('entity.validation.max', { max: 4 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="chuyenCanLabel" for="mon-hoc-chuyenCan">
                  <Translate contentKey="sqanhom16App.monHoc.chuyenCan">Chuyen Can</Translate>
                </Label>
                <AvField
                  id="mon-hoc-chuyenCan"
                  data-cy="chuyenCan"
                  type="string"
                  className="form-control"
                  name="chuyenCan"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 0.1, errorMessage: translate('entity.validation.max', { max: 0.1 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="kiemTraLabel" for="mon-hoc-kiemTra">
                  <Translate contentKey="sqanhom16App.monHoc.kiemTra">Kiem Tra</Translate>
                </Label>
                <AvField
                  id="mon-hoc-kiemTra"
                  data-cy="kiemTra"
                  type="string"
                  className="form-control"
                  name="kiemTra"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 0.2, errorMessage: translate('entity.validation.max', { max: 0.2 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="thucHanhLabel" for="mon-hoc-thucHanh">
                  <Translate contentKey="sqanhom16App.monHoc.thucHanh">Thuc Hanh</Translate>
                </Label>
                <AvField
                  id="mon-hoc-thucHanh"
                  data-cy="thucHanh"
                  type="string"
                  className="form-control"
                  name="thucHanh"
                  validate={{
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 0.2, errorMessage: translate('entity.validation.max', { max: 0.2 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="baiTapLabel" for="mon-hoc-baiTap">
                  <Translate contentKey="sqanhom16App.monHoc.baiTap">Bai Tap</Translate>
                </Label>
                <AvField
                  id="mon-hoc-baiTap"
                  data-cy="baiTap"
                  type="string"
                  className="form-control"
                  name="baiTap"
                  validate={{
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 0.2, errorMessage: translate('entity.validation.max', { max: 0.2 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="thiLabel" for="mon-hoc-thi">
                  <Translate contentKey="sqanhom16App.monHoc.thi">Thi</Translate>
                </Label>
                <AvField
                  id="mon-hoc-thi"
                  data-cy="thi"
                  type="string"
                  className="form-control"
                  name="thi"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 0.7, errorMessage: translate('entity.validation.max', { max: 0.7 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/mon-hoc" replace color="info">
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
  monHocEntity: storeState.monHoc.entity,
  loading: storeState.monHoc.loading,
  updating: storeState.monHoc.updating,
  updateSuccess: storeState.monHoc.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MonHocUpdate);
