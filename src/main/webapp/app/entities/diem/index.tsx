import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Diem from './diem';
import DiemDetail from './diem-detail';
import DiemUpdate from './diem-update';
import DiemDeleteDialog from './diem-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DiemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DiemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DiemDetail} />
      <ErrorBoundaryRoute path={match.url} component={Diem} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DiemDeleteDialog} />
  </>
);

export default Routes;
