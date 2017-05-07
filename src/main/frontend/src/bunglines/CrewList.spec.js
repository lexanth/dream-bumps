import React from 'react';
import { shallow } from 'enzyme';
import { CrewList } from './CrewList';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchCrews = jest.fn();
  const component = shallow(
    <CrewList
      fetchCrews={fetchCrews}
      crews={{0: [{}, {}], 1: [{}, {}]}}
      day={3}
      marketOpen
      numberOfDivisions={7}
      sex="male"
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  expect(fetchCrews).toHaveBeenCalled();
});
