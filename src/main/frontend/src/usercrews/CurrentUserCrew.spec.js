import React from 'react';
import { shallow } from 'enzyme';
import { CurrentUserCrew, mapStateToProps } from './CurrentUserCrew';
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

test('mapStateToProps', () => {
  const reduxState = {
    auth: {
      authenticated: true,
      currentUser: {
        authorities: ['ROLE_USER'],
        id: 2
      }
    }
  };

  const componentStateProps = mapStateToProps(reduxState);

  expect(componentStateProps).toEqual({
    userId: 2
  });
});
