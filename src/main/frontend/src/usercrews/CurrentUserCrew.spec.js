import React from 'react';
import { shallow } from 'enzyme';
import { CurrentUserCrew } from './CurrentUserCrew';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <CurrentUserCrew
      userId={3}
      sex="female"
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
