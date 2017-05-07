import React from 'react';
import { shallow } from 'enzyme';
import { UserList, mapStateToProps } from './UserList';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const users = [
    {id: 4, login: 'user4', firstName: 'Joe', lastName: 'Bloggs', activated: true},
    {id: 5, login: 'user5', firstName: 'John', lastName: 'Smith', activated: true},
    {id: 7, login: 'user6', firstName: 'Jane', lastName: 'Smith', activated: false},
    {id: 9, login: 'bump9', firstName: 'Jane', lastName: 'Doe', activated: true},
  ]
  const component = shallow(
    <UserList
      users={users}
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

  const componentStateProps = mapStateToProps(reduxState);

  expect(componentStateProps).toEqual({
    users: [
      {id: 1, login: 'Login1', firstName: 'First Name 1', lastName: 'Last Name 1', email: 'email@1.com', authorities: ['ROLE_USER'], college: 'newc'},
      {id: 4, login: 'Login4', firstName: 'First Name 4', lastName: 'Last Name 4', email: 'email@4.com', authorities: ['ROLE_USER'], college: 'balliol'},
      {id: 5, login: 'Login5', firstName: 'First Name 5', lastName: 'Last Name 5', email: 'email@5.com', authorities: ['ROLE_USER'], college: 'newc'}
    ]
  })
});
