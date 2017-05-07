import React from 'react';
import { shallow } from 'enzyme';
import { UserScoreHistory } from './UserScoreHistory';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchUserCrewPriceHistory = jest.fn();
  const scoreHistories = [
    {cash: 100.12, value: 1027.8, dateTime: '2017-05-02T21:51:15.123Z'},
    {cash: 102.34, value: 1025.78, dateTime: '2017-05-02T21:53:15.123Z'},
    {cash: 99.23, value: 1023.45, dateTime: '2017-05-04T12:51:15.123Z'}
  ];
  const component = shallow(
    <UserScoreHistory
      userId={7}
      sex="male"
      style={{marginTop: '16px'}}
      fetchUserCrewPriceHistory={fetchUserCrewPriceHistory}
      scoreHistory={scoreHistories}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});


// UserScoreHistory.propTypes = {
//   scoreHistory: PropTypes.array,
//   userId: PropTypes.number,
//   fetchUserCrewPriceHistory: PropTypes.func,
//   sex: PropTypes.string,
//   style: PropTypes.object
// };
