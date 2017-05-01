import React from 'react';
import renderer from 'react-test-renderer';
import { Login } from './Login';

test('Renders properly', () => {
  const component = renderer.create(
    <Login />
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();

});
