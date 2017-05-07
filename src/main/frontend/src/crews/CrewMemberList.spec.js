import React from 'react';
import { shallow } from 'enzyme';
import { CrewMemberList, mapStateToProps } from './CrewMemberList';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchCrewMembers = jest.fn();
  const component = shallow(
    <CrewMemberList
      fetchCrewMembers={fetchCrewMembers}
      crewId="1"
      crewMembers={[{id: 1, seat: 1, name: 'John'}, {id: 2, seat: 2, name: 'Bob'}]}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  expect(fetchCrewMembers).toHaveBeenCalled();
  expect(fetchCrewMembers.mock.calls.length).toBe(1);
  expect(fetchCrewMembers.mock.calls[0][0]).toBe('1');
});

test('mapStateToProps', () => {
  const reduxState = {
    crews: {
      members: {
        byId: {
          1: {id: 1, seat: 0, crewId: 1, name: 'Member 1'},
          2: {id: 2, seat: 2, crewId: 1, name: 'Member 2'},
          3: {id: 3, seat: 3, crewId: 2, name: 'Member 3'},
          4: {id: 4, seat: 4, crewId: 3, name: 'Member 4'},
          5: {id: 5, seat: 5, crewId: 1, name: 'Member 5'},
          6: {id: 6, seat: 6, crewId: 2, name: 'Member 6'},
        },
        byCrewId: {
          1: [1,2,5],
          2: [3,6],
          3: [4]
        }
      }
    }
  };
  const ownProps = {crewId: '2'};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    crewMembers: [
      {id: 3, seat: 3, crewId: 2, name: 'Member 3'},
      {id: 6, seat: 6, crewId: 2, name: 'Member 6'}
    ]
  });
});
