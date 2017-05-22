// @flow
import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Field, FieldArray, reduxForm, formValueSelector } from 'redux-form';
import { Card, CardTitle, CardText } from 'material-ui/Card';
import { TextField } from 'redux-form-material-ui';
import RaisedButton from 'material-ui/RaisedButton';

import { getCrewMembers } from '../rootReducer';
import { updateCrewMembers } from '../crews/actions';
import seatNumberToName from '../utils/seatNumberToName';
/**
 * AdminCrewDetailsEdit
 */
const renderMembers = ({ fields, current }) => (
  <div>
    {fields.map((member, index) => (
      <Field
        name={`${member}.name`}
        component={TextField}
        floatingLabelText={current ? seatNumberToName(current[index].seat) : ''}
        fullWidth
        key={index}
      />
    ))}
  </div>
);

renderMembers.propTypes = {
  fields: PropTypes.arrayOf(PropTypes.string),
  current: PropTypes.arrayOf(
    PropTypes.shape({
      seat: PropTypes.number,
      name: PropTypes.string
    })
  )
};

const AdminCrewMemberEdit = props => (
  <Card>
    <CardTitle title={`Crew Members - ${props.crewId}`} />
    <CardText>
      <form onSubmit={props.handleSubmit}>
        <FieldArray
          name="members"
          component={renderMembers}
          props={{ current: props.currentMembers }}
        />
        <RaisedButton primary type="submit" label="Save" />
      </form>
    </CardText>
  </Card>
);

AdminCrewMemberEdit.propTypes = {
  crewId: PropTypes.string,
  handleSubmit: PropTypes.func,
  currentMembers: PropTypes.arrayOf(
    PropTypes.shape({
      seat: PropTypes.number,
      name: PropTypes.string,
      id: PropTypes.number
    })
  ),
  initialValues: PropTypes.shape({
    members: PropTypes.arrayOf(
      PropTypes.shape({
        seat: PropTypes.number,
        name: PropTypes.string,
        id: PropTypes.number
      })
    )
  })
};

const selector = formValueSelector('edit-crew-members');

export const mapStateToProps = (
  state: Object,
  { crewId }: { crewId: string }
) => ({
  initialValues: { members: getCrewMembers(state)(parseInt(crewId, 10)) },
  currentMembers: selector(state, 'members')
});

export { AdminCrewMemberEdit };

export default connect(mapStateToProps, { onSubmit: updateCrewMembers })(
  reduxForm({ form: 'edit-crew-members', enableReinitialize: true })(
    AdminCrewMemberEdit
  )
);
