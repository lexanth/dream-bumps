import React from 'react';
import { shallow } from 'enzyme';
import { CrewDetails } from './CrewDetails';
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
