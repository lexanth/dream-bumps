import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import {Field, reduxForm} from 'redux-form';
import {TextField, SelectField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import {Cell} from 'material-grid/dist';
import Select from 'react-select';
import MenuItem from 'material-ui/MenuItem';

// Be sure to include styles at some point, probably during your bootstrapping
import 'react-select/dist/react-select.css';

// import ReduxFormSuperSelectField from '../utils/ReduxFormSuperSelectField';
import { getUser } from '../rootReducer';
import { updateUser } from './actions';
import colleges from '../utils/colleges';

const authOptions = [
  {label: 'ROLE_USER', value: 'ROLE_USER'},
  {label: 'ROLE_ADMIN', value: 'ROLE_ADMIN'}
];

const MyReactSelect = (props) => (
  <Select
    {...props}
    value={props.input.value}
    onChange={(value) => props.input.onChange(value.map(val => val.value))}
    onBlur={() => props.input.onBlur(props.input.value)}
    options={props.options}
  />
);

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
          <Field
            name="authorities"
            component={MyReactSelect}
            multi
            options={authOptions}
          />
          <Field
            name="college"
            fullWidth
            component={SelectField}
          >
            {colleges.map(college =>
              <MenuItem key={college.value} value={college.value} primaryText={college.label} />
            )}
          </Field>
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

export default connect(mapStateToProps, {onSubmit: updateUser})(reduxForm({form: 'edit-user', enableReinitialize: true})(AdminEditUser));
