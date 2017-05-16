// @flow
import React, { Component, PropTypes } from 'react';
import {connect} from 'react-redux';
import {Field, reduxForm} from 'redux-form';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import {TextField, SelectField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import MenuItem from 'material-ui/MenuItem';

import {getCrew} from '../rootReducer';
import {updateCrew} from '../crews/actions';
/**
 * AdminCrewDetailsEdit
 */
class AdminCrewDetailsEdit extends Component {
  render() {
    return (
      <Card>
        <CardTitle title={`Edit Crew - ${this.props.crewId}`}/>
        <CardText>
          <form onSubmit={this.props.handleSubmit}>
            <Field
              name="name"
              floatingLabelText="Name"
              component={TextField}
              fullWidth
            />
            <Field
              name="sex"
              component={SelectField}
              floatingLabelText="Sex"
              fullWidth
            >
              <MenuItem value="male" primaryText="Male" />
              <MenuItem value="female" primaryText="Female" />
            </Field>
            <RaisedButton primary type="submit" label="Save"/>
          </form>
        </CardText>
      </Card>
    );
  }
}

AdminCrewDetailsEdit.propTypes = {
  crewId: PropTypes.string,
  initialValues: PropTypes.shape({
    name: PropTypes.string,
    sex: PropTypes.string
  }),
  handleSubmit: PropTypes.func
}

export const mapStateToProps = (state: Object, {crewId}: {crewId: string} ) =>(
  {
    initialValues: getCrew(state)(parseInt(crewId,10))
  }
);

export {AdminCrewDetailsEdit};

export default connect(
  mapStateToProps,
  {onSubmit: updateCrew}
)(
  reduxForm(
    {form: 'edit-crew-details', enableReinitialize: true}
  )(AdminCrewDetailsEdit));
