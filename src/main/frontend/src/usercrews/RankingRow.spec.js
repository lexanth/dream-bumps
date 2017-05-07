import React from 'react';
import { shallow } from 'enzyme';
import { RankingRow, mapStateToProps } from './RankingRow';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const ranking = {
    userId: 5,
    cash: 11.01,
    value: 1036.5,
    bumps: 3,
    dividends: 12.67,
    sex: 'male'
  };
  const user = {
    id: 5,
    college: null,
    firstName: 'ThisIsAUserName',
    lastName: ''
  };
  const component = shallow(
    <RankingRow
      number={3}
      ranking={ranking}
      user={user}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('mapStateToProps', () => {
  const reduxState = {
    users: {
      all: [1,4,5],
      byId: {
        1: {id: 1, login: 'Login1', firstName: 'First Name 1', lastName: 'Last Name 1', email: 'email@1.com', authorities: ['ROLE_USER'], college: 'newc'},
        4: {id: 4, login: 'Login4', firstName: 'First Name 4', lastName: 'Last Name 4', email: 'email@4.com', authorities: ['ROLE_USER'], college: 'balliol'},
        5: {id: 5, login: 'Login5', firstName: 'First Name 5', lastName: 'Last Name 5', email: 'email@5.com', authorities: ['ROLE_USER'], college: 'newc'}
      }
    }
  };
  const ownProps = {ranking: {userId: 4}};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({user: {id: 4, login: 'Login4', firstName: 'First Name 4', lastName: 'Last Name 4', email: 'email@4.com', authorities: ['ROLE_USER'], college: 'balliol'}});
});
