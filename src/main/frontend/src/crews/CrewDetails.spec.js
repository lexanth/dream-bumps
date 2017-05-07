import React from 'react';
import { shallow } from 'enzyme';
import { CrewDetails, mapStateToProps } from './CrewDetails';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchCrew = jest.fn();
  const component = shallow(
    <CrewDetails
      fetchCrew={fetchCrew}
      crewId="1"
      crew={{id: 1, price: 100, startPrice: 76.34, position: 5, startPosition: 8}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  expect(fetchCrew).toHaveBeenCalled();
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

  expect(componentStateProps).toEqual({crew:{id: 4, sex: 'male', name: 'Crew 4'}});
});
