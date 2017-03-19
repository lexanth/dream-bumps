import React from 'react';
import {Grid, Cell} from 'material-grid/dist';

import CrewAdminList from './CrewAdminList';

export default () => (
  <Grid>
    <Cell col={6}>
      <h3>Men</h3>
      <CrewAdminList sex="male" />
    </Cell>
    <Cell col={6}>
      <h3>Women</h3>
      <CrewAdminList sex="female" />
    </Cell>
  </Grid>
)
