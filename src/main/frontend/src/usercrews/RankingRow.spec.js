import React from 'react';
import { shallow } from 'enzyme';
import { RankingRow } from './RankingRow';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const ranking = {
    userId: 5,
    cash: 11.01,
    value: 1036.5,
    bumps: 3,
    dividends: 12.67,
    sex: 'male'
  };
  const user = {
    id: 5,
    college: null,
    firstName: 'ThisIsAUserName',
    lastName: ''
  };
  const component = shallow(
    <RankingRow
      number={3}
      ranking={ranking}
      user={user}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
