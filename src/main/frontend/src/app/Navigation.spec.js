import React from 'react';
import { shallow } from 'enzyme';
import { Navigation, mapStateToProps } from './Navigation';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <Navigation
      authenticated
      isAdmin
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('Renders properly', () => {
  const component = shallow(
    <Navigation
      authenticated={false}
      isAdmin={false}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('Renders properly', () => {
  const component = shallow(
    <Navigation
      authenticated
      isAdmin={false}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('mapStateToProps', () => {
  const reduxState = {
    auth: {
      authenticated: true,
      currentUser: {
        authorities: ['ROLE_USER']
      }
    }
  };

  const componentStateProps = mapStateToProps(reduxState);

  expect(componentStateProps).toEqual({authenticated: true, isAdmin: false});
});
