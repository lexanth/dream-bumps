import React from 'react';
import { shallow } from 'enzyme';
import { UserPage } from './UserPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <UserPage
      params={{userId: "5"}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});
