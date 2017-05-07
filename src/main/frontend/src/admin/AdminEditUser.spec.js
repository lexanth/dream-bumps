import React from 'react';
import { shallow } from 'enzyme';
import { AdminEditUser } from './AdminEditUser';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const submit = jest.fn();
  const component = shallow(
    <AdminEditUser
      params={{userId: '21'}}
      handleSubmit={submit}
      initialValues={{id: 21, login: 'username', firstName: 'Joe', lastName: 'Bloggs', email: 'joe@bloggs.com', authorities: ['ROLE_USER'], college: null}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

// AdminEditUser.propTypes = {
//   user: PropTypes.object,
//   handleSubmit: PropTypes.func,
//   params: PropTypes.object,
//   initialValues: PropTypes.object
// }
