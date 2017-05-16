// @flow
import React, {PropTypes} from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router';
import {Field, reduxForm} from 'redux-form';
import {TextField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import {Card, CardTitle, CardText, CardActions} from 'material-ui/Card';
import {Cell} from 'material-grid/dist';

import {signUp} from './actions';

export const Register = ({handleSubmit}:{handleSubmit:Function}) => (
  <form onSubmit={handleSubmit}>
    <Cell col={6} offset={3}>
      <Card>
        <CardTitle title="Register"/>
        <CardText>
          <div>
            Already have an account? <Link to="/login">Login</Link>
          </div>
          <Field name="login" component={TextField} floatingLabelText="Username" fullWidth/>
          <Field name="email" component={TextField} floatingLabelText="E-Mail" fullWidth/>
          <Field name="password" component={TextField} floatingLabelText="Password" type="password" fullWidth/>
          <Field name="confirmPassword" component={TextField} floatingLabelText="Confirm Password" type="password" fullWidth/>
        </CardText>
        <CardActions>
          <RaisedButton primary label="Register" type="submit"/>
        </CardActions>
      </Card>
    </Cell>
  </form>
);

Register.propTypes = {
  handleSubmit: PropTypes.func
};

const RegisterForm = reduxForm({
  form: 'register',
  initialValues: {
    langKey: 'en'
  }
})(Register);

export default connect(null, {onSubmit: signUp})(RegisterForm);
