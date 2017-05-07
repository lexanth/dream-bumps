import React from 'react';
import { shallow } from 'enzyme';
import { AdminCrewDetailsEdit, mapStateToProps } from './AdminCrewDetailsEdit';
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

test('mapStateToProps', () => {
  const reduxState = {
    crews: {
      crews: {
        byId: {
          1: {id: 1, sex: 'male', name: 'Crew 1'},
          2: {id: 2, sex: 'male', name: 'Crew 2'},
          3: {id: 3, sex: 'female', name: 'Crew 3'},
          4: {id: 4, sex: 'male', name: 'Crew 4'},
          5: {id: 5, sex: 'female', name: 'Crew 5'},
          6: {id: 6, sex: 'female', name: 'Crew 6'},
        },
        bySex: {
          male: [1,2,4],
          female: [3,5,6]
        }
      }
    }
  };
  const ownProps = {crewId: '4'};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({initialValues:{id: 4, sex: 'male', name: 'Crew 4'}});
});
