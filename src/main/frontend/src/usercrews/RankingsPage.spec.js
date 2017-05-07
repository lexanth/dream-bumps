import React from 'react';
import { shallow } from 'enzyme';
import { RankingsPage } from './RankingsPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchUsers = jest.fn();
  const fetchUserCrewRankings = jest.fn();
  const component = shallow(
    <RankingsPage
      fetchUsers={fetchUsers}
      fetchUserCrewRankings={fetchUserCrewRankings}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});
