import React, {PropTypes} from 'react';
import {connect} from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import {Field, reduxForm} from 'redux-form';
import {TextField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import {Cell, Grid} from 'material-grid/dist';
import Select from 'react-select';
import {SelectField} from 'redux-form-material-ui';
import MenuItem from 'material-ui/MenuItem';

import AdminCrewDetailsEdit from './AdminCrewDetailsEdit';
import AdminCrewMemberEdit from './AdminCrewMemberEdit';

const AdminCrewEdit = ({user, handleSubmit, params}) => (
  <Grid>
    <Cell col="6">
      <AdminCrewDetailsEdit crewId={params.crewId}/>
    </Cell>
    <Cell col="6">
      <AdminCrewMemberEdit crewId={params.crewId}/>
    </Cell>
  </Grid>
);

// todo - activated and authorities


export default AdminCrewEdit;
