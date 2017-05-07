import React from 'react';
import { shallow } from 'enzyme';
import { Register } from './Register';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <Register />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();

});
