import React from 'react';
import { shallow } from 'enzyme';
import { AdminCrewMemberEdit, mapStateToProps } from './AdminCrewMemberEdit';
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


test('mapStateToProps', () => {
  const reduxState = {
    crews: {
      members: {
        byId: {
          1: {id: 1, name: 'Member 1', crewId: 5},
          2: {id: 2, name: 'Member 2', crewId: 5},
          3: {id: 3, name: 'Member 3', crewId: 6},
          4: {id: 4, name: 'Member 4', crewId: 5},
          5: {id: 5, name: 'Member 5', crewId: 6},
          6: {id: 6, name: 'Member 6', crewId: 6},
        },
        byCrewId: {
          5: [1,2,4],
          6: [3,5,6]
        }
      }
    }
  };
  const ownProps = {crewId: '5'};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({initialValues: {members: [{id: 1, name: 'Member 1', crewId: 5},{id: 2, name: 'Member 2', crewId: 5},{id: 4, name: 'Member 4', crewId: 5}]}});
});
