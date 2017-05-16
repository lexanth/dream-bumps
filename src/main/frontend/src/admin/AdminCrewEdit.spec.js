// @flow
import React from 'react';
import { shallow } from 'enzyme';
import { AdminCrewEdit } from './AdminCrewEdit';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <AdminCrewEdit
      params={{crewId: "17"}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
