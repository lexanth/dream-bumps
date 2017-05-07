import React from 'react';
import { shallow } from 'enzyme';
import NotFoundPage from './NotFoundPage'
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <NotFoundPage />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
