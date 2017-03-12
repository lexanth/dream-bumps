import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import {Field, reduxForm} from 'redux-form';
import {TextField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import {Cell} from 'material-grid/dist';

import { getUser } from '../rootReducer';
import { saveUser } from './actions';

const AdminEditUser = ({user, handleSubmit, params}) => (
  <Cell col="6" offset={3} >
  <Card>
    <CardTitle title={`Edit User - ${params.userId}`} />
    <CardText>
      <form onSubmit={handleSubmit}>
          <Field
            name="login"
            component={TextField}
            floatingLabelText="Login"
            fullWidth
          />
          <Field
            name="firstName"
            component={TextField}
            floatingLabelText="First Name"
            fullWidth
          />
          <Field
            name="lastName"
            component={TextField}
            floatingLabelText="Last Name"
            fullWidth
          />
          <Field
            name="email"
            component={TextField}
            floatingLabelText="E-Mail"
            fullWidth
          />
        <RaisedButton primary type="submit" label="Save" />
      </form>
    </CardText>
  </Card>
  </Cell>
);

// todo - activated and authorities

const mapStateToProps = (state, {params}) => ({
  initialValues: getUser(state)(params.userId)
});

export default connect(mapStateToProps, {onSubmit: saveUser})(reduxForm({form: 'edit-user', enableReinitialize: true})(AdminEditUser));
