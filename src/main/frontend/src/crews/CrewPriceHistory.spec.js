import React from 'react';
import { shallow } from 'enzyme';
import { CrewPriceHistory } from './CrewPriceHistory';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchCrewPriceHistory = jest.fn();
  const crewPriceHistory = [
    {price: 100, dateTime: '2017-05-02T21:51:15.123Z'},
    {price: 102, dateTime: '2017-05-02T21:53:15.123Z'},
    {price: 99, dateTime: '2017-05-04T12:51:15.123Z'}
  ];
  const component = shallow(
    <CrewPriceHistory
      crewPriceHistory={crewPriceHistory}
      crewId="2"
      fetchCrewPriceHistory={fetchCrewPriceHistory}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  expect(fetchCrewPriceHistory).toHaveBeenCalled();
  expect(fetchCrewPriceHistory.mock.calls.length).toBe(1);
  expect(fetchCrewPriceHistory.mock.calls[0][0]).toBe('2');
});
