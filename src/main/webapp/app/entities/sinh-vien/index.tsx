import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SinhVien from './sinh-vien';
import SinhVienDetail from './sinh-vien-detail';
import SinhVienUpdate from './sinh-vien-update';
import SinhVienDeleteDialog from './sinh-vien-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SinhVienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SinhVienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SinhVienDetail} />
      <ErrorBoundaryRoute path={match.url} component={SinhVien} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SinhVienDeleteDialog} />
  </>
);

export default Routes;
