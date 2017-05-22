import React from 'react';
import { shallow } from 'enzyme';
import { CrewAdminPage } from './CrewAdminPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <CrewAdminPage>
      <div className="random div" />
    </CrewAdminPage>
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();
});
