import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import { Link } from 'react-router';
import {Grid, Cell} from 'material-grid/dist';

const AdminPage = () => (
  <Grid>
    <Cell col={6}>
      <RaisedButton label="Users" containerElement={<Link to="/admin/users" />} />
    </Cell>
    <Cell col={6}>
      <RaisedButton label="Crews" containerElement={<Link to="/admin/crews" />} />
    </Cell>
    <Cell col={6}>
      <RaisedButton label="Market Status" containerElement={<Link to="/admin/market" />} />
    </Cell>
    <Cell col={6}>
      <RaisedButton label="Bumps" containerElement={<Link to="/admin/market" />} />
    </Cell>
  </Grid>
)

export default AdminPage;
