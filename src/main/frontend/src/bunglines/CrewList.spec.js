import React from 'react';
import { shallow } from 'enzyme';
import { CrewList, mapStateToProps } from './CrewList';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchCrews = jest.fn();
  const component = shallow(
    <CrewList
      fetchCrews={fetchCrews}
      crews={{0: [{}, {}], 1: [{}, {}]}}
      day={3}
      marketOpen
      numberOfDivisions={7}
      sex="male"
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  expect(fetchCrews).toHaveBeenCalled();
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
        crewsPerDivision: 3,
        mensCrews: 13,
        womensCrews: 10
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

  const ownProps = {sex: 'male'};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    crews: {
      0: [{id: 3, sex: 'male', name: 'Crew 3', position: 1}, {id: 5, sex: 'male', name: 'Crew 5', position: 2}, {id: 4, sex: 'male', name: 'Crew 4', position: 3}],
      1: [{id: 2, sex: 'male', name: 'Crew 2', position: 4}, {id: 1, sex: 'male', name: 'Crew 1', position: 5}]
    },
    numberOfDivisions: 4,
    day: 3,
    marketOpen: true
  });
});
