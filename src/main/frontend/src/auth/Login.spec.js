import React from 'react';
import { shallow } from 'enzyme';
import { Login } from './Login';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <Login />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();

});
