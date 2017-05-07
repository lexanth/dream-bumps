import React from 'react';
import { shallow } from 'enzyme';
import DocsPage from './DocsPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <DocsPage />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();
});
