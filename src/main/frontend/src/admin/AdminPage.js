// @flow
import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import { Link } from 'react-router';
import {Grid, Cell} from 'material-grid/dist';

const AdminPage = () => (
  <Grid>
    <Cell col={4}>
      <RaisedButton label="Users" containerElement={<Link to="/admin/users" />} />
    </Cell>
    <Cell col={4}>
      <RaisedButton label="Crews" containerElement={<Link to="/admin/crews" />} />
    </Cell>
    <Cell col={4}>
      <RaisedButton label="Market Status" containerElement={<Link to="/admin/market" />} />
    </Cell>
    <Cell col={4}>
      <RaisedButton label="Bumps - Men" containerElement={<Link to="/admin/bumps/male" />} />
    </Cell>
    <Cell col={4}>
      <RaisedButton label="Bumps - Women" containerElement={<Link to="/admin/bumps/female" />} />
    </Cell>
  </Grid>
)

export default AdminPage;
