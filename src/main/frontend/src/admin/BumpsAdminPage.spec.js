import React from 'react';
import { shallow } from 'enzyme';
import { BumpsAdminPage, mapStateToProps } from './BumpsAdminPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const submit = jest.fn();
  const fetch = jest.fn();
  const component = shallow(
    <BumpsAdminPage
      day={2}
      crews={{0: [{position: 1}, {position: 2}, {position: 3}], 1: [{position: 4}, {position: 5}, {position: 6}]}}
      uploadBumps={submit}
      fetchCrews={fetch}
      params={{sex: 'male'}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});


test('mapStateToProps', () => {
  const reduxState = {
    status: {
      status: {
        open: true,
        day: 3
      }
    },
    config: {
      config: {
        crewsPerDivision: 3
      }
    },
    crews: {
      crews: {
        byId: {
          1: {id: 1, sex: 'male', name: 'Crew 1', position: 5},
          2: {id: 2, sex: 'male', name: 'Crew 2', position: 4},
          3: {id: 3, sex: 'male', name: 'Crew 3', position: 1},
          4: {id: 4, sex: 'male', name: 'Crew 4', position: 3},
          5: {id: 5, sex: 'male', name: 'Crew 5', position: 2},
          6: {id: 6, sex: 'female', name: 'Crew 6', position: 1},
        },
        bySex: {
          male: [1,2,3,4,5],
          female: [6]
        }
      }
    }
  };
  const ownProps = {params: {sex: 'male'}};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    day: 3,
    crews: {
      0: [{id: 3, sex: 'male', name: 'Crew 3', position: 1}, {id: 5, sex: 'male', name: 'Crew 5', position: 2}, {id: 4, sex: 'male', name: 'Crew 4', position: 3}],
      1: [{id: 2, sex: 'male', name: 'Crew 2', position: 4}, {id: 1, sex: 'male', name: 'Crew 1', position: 5}]
    }
  });
});
