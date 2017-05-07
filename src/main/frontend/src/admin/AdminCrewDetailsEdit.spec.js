import React from 'react';
import { shallow } from 'enzyme';
import { AdminCrewDetailsEdit } from './AdminCrewDetailsEdit';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const submit = jest.fn();
  const component = shallow(
    <AdminCrewDetailsEdit
      crewId="15"
      initialValues={{name: "Crew Name", sex: "male"}}
      handleSubmit={submit}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});
