import React from 'react';
import { shallow } from 'enzyme';
import { AdminCrewMemberEdit } from './AdminCrewMemberEdit';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const submit = jest.fn();
  const fetchCrewMembers = jest.fn();
  const component = shallow(
    <AdminCrewMemberEdit
      crewId="19"
      handleSubmit={submit}
      currentMembers={[{name: "Member1", seat: 1}, {name: "Member 2", seat: 2}, {name: "Member 3", seat: 3}]}
      initialValues={{members: [{name: "OldName1", seat: 1}, {name: "Old Name 2", seat: 2}, {name: "Member 3", seat: 3}]}}
      fetchCrewMembers={fetchCrewMembers}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});


// AdminCrewMemberEdit.propTypes = {
//   crewId: PropTypes.string,
//   handleSubmit: PropTypes.func,
//   currentMembers: PropTypes.array,
//   initialValues: PropTypes.object,
//   fetchCrewMembers: PropTypes.func
// };
