import React from 'react';
import { shallow } from 'enzyme';
import HomePage from './HomePage'
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <HomePage />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
