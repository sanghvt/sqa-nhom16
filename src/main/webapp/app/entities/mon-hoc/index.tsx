import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MonHoc from './mon-hoc';
import MonHocDetail from './mon-hoc-detail';
import MonHocUpdate from './mon-hoc-update';
import MonHocDeleteDialog from './mon-hoc-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MonHocUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MonHocUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MonHocDetail} />
      <ErrorBoundaryRoute path={match.url} component={MonHoc} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MonHocDeleteDialog} />
  </>
);

export default Routes;
