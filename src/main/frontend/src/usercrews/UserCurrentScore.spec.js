import React from 'react';
import { shallow } from 'enzyme';
import { UserCurrentScore } from './UserCurrentScore';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchUserCrewRanking = jest.fn();
  const component = shallow(
    <UserCurrentScore
      fetchUserCrewRanking={fetchUserCrewRanking}
      userId={2}
      sex="female"
      ranking={{bumps: 3, cash: 123.4, value: 1823.43, dividends: 234.23}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});
