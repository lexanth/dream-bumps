import React, { Component, PropTypes } from 'react';
import {connect} from 'react-redux';
import {Field, FieldArray, reduxForm, formValueSelector } from 'redux-form';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import {TextField, SelectField} from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';
import MenuItem from 'material-ui/MenuItem';

import {getCrewMembers} from '../rootReducer';
import {fetchCrewMembers, updateCrewMembers} from '../crews/actions';
import seatNumberToName from '../utils/seatNumberToName';
/**
 * AdminCrewDetailsEdit
 */
const renderMembers = ({ fields, current }) => (
  <div>
  {fields.map((member, index) =>
    <Field
      name={`${member}.name`}
      component={TextField}
      floatingLabelText={current ? seatNumberToName(current[index].seat) : ''}
      fullWidth
      key={index}
    />
  )}
  </div>
)

class AdminCrewMemberEdit extends Component {
  componentWillMount() {
    this.props.fetchCrewMembers(this.props.crewId);
  }

  render() {
    return (
      <Card>
        <CardTitle title={`Crew Members - ${this.props.crewId}`}/>
        <CardText>
          <form onSubmit={this.props.handleSubmit}>
            <FieldArray name="members" component={renderMembers} props={{current: this.props.currentMembers}} />
            <RaisedButton primary type="submit" label="Save"/>
          </form>
        </CardText>
      </Card>
    );
  }
}

AdminCrewMemberEdit.propTypes = {
}

const selector = formValueSelector('edit-crew-members');

const mapStateToProps = (state, {crewId}) => ({
  initialValues: {members: getCrewMembers(state)(crewId)},
  currentMembers: selector(state, 'members')
});

export default connect(mapStateToProps, {onSubmit: updateCrewMembers, fetchCrewMembers})(reduxForm({form: 'edit-crew-members', enableReinitialize: true})(AdminCrewMemberEdit));
