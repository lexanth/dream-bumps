import React from 'react';
import { shallow } from 'enzyme';
import { UserList } from './UserList';
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
