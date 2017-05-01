import React, {PropTypes} from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router';
// import {Col} from 'react-flexbox-grid';
import { Cell } from 'material-grid/dist';
import {Field, reduxForm} from 'redux-form';
import {TextField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import {Card, CardTitle, CardText, CardActions} from 'material-ui/Card';

import {login} from './actions';

export const Login = ({handleSubmit}) => (
  <form onSubmit={handleSubmit}>
    <Cell col={6} offset={3}>
      <Card>
        <CardTitle title="Login"/>
        <CardText>
          <div>
            Not got an account yet? <Link to="/register">Register</Link>
          </div>
          <Field name="username" component={TextField} floatingLabelText="Username" fullWidth/>
          <Field name="password" component={TextField} floatingLabelText="Password" type="password" fullWidth/>
        </CardText>
        <CardActions>
          <RaisedButton primary label="Log in" type="submit"/>
        </CardActions>
      </Card>
    </Cell>
  </form>
);

Login.propTypes = {
  handleSubmit: PropTypes.func
};

const LoginForm = reduxForm({
  form: 'login',
  fields: [
    'username', 'password', 'rememberMe'
  ],
  initialValues: {
    rememberMe: true
  }
})(Login);

export default connect(null, {onSubmit: login})(LoginForm);
