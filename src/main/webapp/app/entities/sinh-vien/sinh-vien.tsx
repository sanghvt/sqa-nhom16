import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sinh-vien.reducer';
import { ISinhVien } from 'app/shared/model/sinh-vien.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface ISinhVienProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SinhVien = (props: ISinhVienProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get('sort');
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { sinhVienList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="sinh-vien-heading" data-cy="SinhVienHeading">
        <Translate contentKey="sqanhom16App.sinhVien.home.title">Sinh Viens</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="sqanhom16App.sinhVien.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="sqanhom16App.sinhVien.home.createLabel">Create new Sinh Vien</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {sinhVienList && sinhVienList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="sqanhom16App.sinhVien.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('maSinhVien')}>
                  <Translate contentKey="sqanhom16App.sinhVien.maSinhVien">Ma Sinh Vien</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('password')}>
                  <Translate contentKey="sqanhom16App.sinhVien.password">Password</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hoTen')}>
                  <Translate contentKey="sqanhom16App.sinhVien.hoTen">Ho Ten</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ngaySinh')}>
                  <Translate contentKey="sqanhom16App.sinhVien.ngaySinh">Ngay Sinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('gioiTinh')}>
                  <Translate contentKey="sqanhom16App.sinhVien.gioiTinh">Gioi Tinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('email')}>
                  <Translate contentKey="sqanhom16App.sinhVien.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('soDienThoai')}>
                  <Translate contentKey="sqanhom16App.sinhVien.soDienThoai">So Dien Thoai</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nganh')}>
                  <Translate contentKey="sqanhom16App.sinhVien.nganh">Nganh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('khoaHoc')}>
                  <Translate contentKey="sqanhom16App.sinhVien.khoaHoc">Khoa Hoc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lop')}>
                  <Translate contentKey="sqanhom16App.sinhVien.lop">Lop</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {sinhVienList.map((sinhVien, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${sinhVien.id}`} color="link" size="sm">
                      {sinhVien.id}
                    </Button>
                  </td>
                  <td>{sinhVien.maSinhVien}</td>
                  <td>{sinhVien.password}</td>
                  <td>{sinhVien.hoTen}</td>
                  <td>{sinhVien.ngaySinh ? <TextFormat type="date" value={sinhVien.ngaySinh} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>
                    <Translate contentKey={`sqanhom16App.Sex.${sinhVien.gioiTinh}`} />
                  </td>
                  <td>{sinhVien.email}</td>
                  <td>{sinhVien.soDienThoai}</td>
                  <td>
                    <Translate contentKey={`sqanhom16App.Nganh.${sinhVien.nganh}`} />
                  </td>
                  <td>{sinhVien.khoaHoc}</td>
                  <td>
                    <Translate contentKey={`sqanhom16App.Lop.${sinhVien.lop}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${sinhVien.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${sinhVien.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${sinhVien.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="sqanhom16App.sinhVien.home.notFound">No Sinh Viens found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={sinhVienList && sinhVienList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={props.totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

const mapStateToProps = ({ sinhVien }: IRootState) => ({
  sinhVienList: sinhVien.entities,
  loading: sinhVien.loading,
  totalItems: sinhVien.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SinhVien);
