import React from 'react';
import { shallow } from 'enzyme';
import { BunglinesPage } from './BunglinesPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <BunglinesPage />
  );
  expect(component).toBeDefined();
  expect(component.find('Tab').length).toEqual(2);
  expect(toJson(component)).toMatchSnapshot()

});
