import React from 'react';
import { shallow } from 'enzyme';
import { CrewMemberList } from './CrewMemberList';
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
