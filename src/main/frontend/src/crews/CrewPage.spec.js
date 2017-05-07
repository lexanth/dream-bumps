import React from 'react';
import { shallow } from 'enzyme';
import { CrewPage } from './CrewPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <CrewPage
      params={{crewId: '1'}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
