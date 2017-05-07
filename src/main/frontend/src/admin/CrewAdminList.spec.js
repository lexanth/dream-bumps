import React from 'react';
import { shallow } from 'enzyme';
import { CrewAdminList } from './CrewAdminList';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const crews = [{name: 'crew1', id: 1}, {name: 'Crew 2', id: 2}];
  const component = shallow(
    <CrewAdminList
      crews={crews}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
