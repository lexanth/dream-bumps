import React from 'react';
import { shallow } from 'enzyme';
import { BunglineGenderTabContent } from './BunglineGenderTabContent';
import toJson from 'enzyme-to-json';

test('Renders properly when authenticated', () => {
  const component = shallow(
    <BunglineGenderTabContent
      authenticated
      sex="male"
    />
  );
  expect(component).toBeDefined();
  expect(component.find('Cell').length).toEqual(2);
  expect(component.find('Connect(CurrentUserCrew)').length).toEqual(1);
  expect(toJson(component)).toMatchSnapshot();
});

test('Renders properly when not authenticated', () => {
  const component = shallow(
    <BunglineGenderTabContent
      authenticated={false}
      sex="female"
    />
  );
  expect(component).toBeDefined();
  expect(component.find('Cell').length).toEqual(1);
  expect(component.find('Connect(CurrentUserCrew)').length).toEqual(0);
  expect(toJson(component)).toMatchSnapshot();
});
