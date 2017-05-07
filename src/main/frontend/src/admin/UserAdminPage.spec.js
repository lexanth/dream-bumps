import React from 'react';
import { shallow } from 'enzyme';
import { UserAdminPage } from './UserAdminPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchUsers = jest.fn();
  const component = shallow(
    <UserAdminPage
      fetchUsers={fetchUsers}
    >
      <div className="random div" />
    </UserAdminPage>
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
