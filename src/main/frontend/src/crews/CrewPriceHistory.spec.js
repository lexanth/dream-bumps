import React from 'react';
import { shallow } from 'enzyme';
import { CrewPriceHistory, mapStateToProps } from './CrewPriceHistory';
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

test('mapStateToProps', () => {
  const reduxState = {
    crews: {
      priceHistory: {
        byId: {
          1: {id: 1, price: 100, crewId: 1, dateTime: '2017-06-02T21:51:15.123Z'},
          2: {id: 2, price: 101, crewId: 1, dateTime: '2017-05-02T21:51:15.123Z'},
          3: {id: 3, price: 200, crewId: 2, dateTime: '2017-04-02T21:51:15.123Z'},
          4: {id: 4, price: 300, crewId: 3, dateTime: '2017-07-02T21:51:15.123Z'},
          5: {id: 5, price: 105, crewId: 1, dateTime: '2017-06-02T20:51:15.123Z'},
          6: {id: 6, price: 205, crewId: 2, dateTime: '2017-05-02T21:41:15.123Z'},
        },
        byCrewId: {
          1: [1,2,5],
          2: [3,6],
          3: [4]
        }
      }
    }
  };
  const ownProps = {crewId: '1'};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    crewPriceHistory: [
      {id: 2, price: 101, crewId: 1, dateTime: '2017-05-02T21:51:15.123Z', date: 1493761875123},
      {id: 5, price: 105, crewId: 1, dateTime: '2017-06-02T20:51:15.123Z', date: 1496436675123},
      {id: 1, price: 100, crewId: 1, dateTime: '2017-06-02T21:51:15.123Z', date: 1496440275123}
    ]
  });
  // This is very (dateTime) order sensitive. expect.toEqual is not order sensitive
  expect(componentStateProps.crewPriceHistory[0]).toEqual({id: 2, price: 101, crewId: 1, dateTime: '2017-05-02T21:51:15.123Z', date: 1493761875123});
  expect(componentStateProps.crewPriceHistory[1]).toEqual({id: 5, price: 105, crewId: 1, dateTime: '2017-06-02T20:51:15.123Z', date: 1496436675123});
  expect(componentStateProps.crewPriceHistory[2]).toEqual({id: 1, price: 100, crewId: 1, dateTime: '2017-06-02T21:51:15.123Z', date: 1496440275123});
});
