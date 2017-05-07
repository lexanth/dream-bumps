import React from 'react';
import { shallow } from 'enzyme';
import { MarketStatusPage } from './MarketStatusPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const updateMarketStatus = jest.fn();
  const component = shallow(
    <MarketStatusPage
      updateMarketStatus={updateMarketStatus}
      status={{day: 3, open: true, dateTime: '2017-05-04T12:51:15.123Z'}}
      day={3}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('Renders properly', () => {
  const updateMarketStatus = jest.fn();
  const component = shallow(
    <MarketStatusPage
      updateMarketStatus={updateMarketStatus}
      status={{day: 4, open: false, dateTime: '2017-05-05T17:51:15.123Z'}}
      day={4}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

// MarketStatusPage.propTypes = {
//   status: PropTypes.object,
//   updateMarketStatus: PropTypes.func,
//   day: PropTypes.number
// };
