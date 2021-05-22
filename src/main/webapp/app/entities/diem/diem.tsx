import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './diem.reducer';
import { IDiem } from 'app/shared/model/diem.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IDiemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Diem = (props: IDiemProps) => {
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

  const { diemList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="diem-heading" data-cy="DiemHeading">
        <Translate contentKey="sqanhom16App.diem.home.title">Diems</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="sqanhom16App.diem.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="sqanhom16App.diem.home.createLabel">Create new Diem</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {diemList && diemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="sqanhom16App.diem.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('chuyenCan')}>
                  <Translate contentKey="sqanhom16App.diem.chuyenCan">Chuyen Can</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('kiemTra')}>
                  <Translate contentKey="sqanhom16App.diem.kiemTra">Kiem Tra</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('thucHanh')}>
                  <Translate contentKey="sqanhom16App.diem.thucHanh">Thuc Hanh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('baiTap')}>
                  <Translate contentKey="sqanhom16App.diem.baiTap">Bai Tap</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('thi')}>
                  <Translate contentKey="sqanhom16App.diem.thi">Thi</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('soLanHoc')}>
                  <Translate contentKey="sqanhom16App.diem.soLanHoc">So Lan Hoc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tongKet')}>
                  <Translate contentKey="sqanhom16App.diem.tongKet">Tong Ket</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tongKetThangChu')}>
                  <Translate contentKey="sqanhom16App.diem.tongKetThangChu">Tong Ket Thang Chu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ketQua')}>
                  <Translate contentKey="sqanhom16App.diem.ketQua">Ket Qua</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="sqanhom16App.diem.maSinhVien">Ma Sinh Vien</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="sqanhom16App.diem.maMonHoc">Ma Mon Hoc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {diemList.map((diem, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${diem.id}`} color="link" size="sm">
                      {diem.id}
                    </Button>
                  </td>
                  <td>{diem.chuyenCan}</td>
                  <td>{diem.kiemTra}</td>
                  <td>{diem.thucHanh}</td>
                  <td>{diem.baiTap}</td>
                  <td>{diem.thi}</td>
                  <td>{diem.soLanHoc}</td>
                  <td>{diem.tongKet}</td>
                  <td>{diem.tongKetThangChu}</td>
                  <td>
                    <Translate contentKey={`sqanhom16App.Status.${diem.ketQua}`} />
                  </td>
                  <td>{diem.maSinhVien ? <Link to={`sinh-vien/${diem.maSinhVien.id}`}>{diem.maSinhVien.id}</Link> : ''}</td>
                  <td>{diem.maMonHoc ? <Link to={`mon-hoc/${diem.maMonHoc.id}`}>{diem.maMonHoc.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${diem.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${diem.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${diem.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="sqanhom16App.diem.home.notFound">No Diems found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={diemList && diemList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ diem }: IRootState) => ({
  diemList: diem.entities,
  loading: diem.loading,
  totalItems: diem.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Diem);
