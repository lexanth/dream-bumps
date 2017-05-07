import React from 'react';
import { shallow } from 'enzyme';
import CrewAdminLists from './CrewAdminLists';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <CrewAdminLists />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
