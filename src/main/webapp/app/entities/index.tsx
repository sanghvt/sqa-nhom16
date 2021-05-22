import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SinhVien from './sinh-vien';
import MonHoc from './mon-hoc';
import Diem from './diem';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}sinh-vien`} component={SinhVien} />
      <ErrorBoundaryRoute path={`${match.url}mon-hoc`} component={MonHoc} />
      <ErrorBoundaryRoute path={`${match.url}diem`} component={Diem} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
