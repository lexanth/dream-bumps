import React from 'react';
import { shallow } from 'enzyme';
import { UserScoreHistory, mapStateToProps } from './UserScoreHistory';
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

test('mapStateToProps', () => {
  const reduxState = {

    usercrews: {
      history: {
        byId: {
          1: {id: 1, cash: 100, crewId: 1, sex: 'male', dateTime: '2017-06-02T21:51:15.123Z'},
          2: {id: 2, cash: 101, crewId: 1, sex: 'female',  dateTime: '2017-05-02T21:51:15.123Z'},
          3: {id: 3, cash: 200, crewId: 2, sex: 'male',  dateTime: '2017-04-02T21:51:15.123Z'},
          4: {id: 4, cash: 300, crewId: 1, sex: 'male',  dateTime: '2017-07-02T21:51:15.123Z'},
          5: {id: 5, cash: 105, crewId: 2, sex: 'female',  dateTime: '2017-06-02T20:51:15.123Z'},
          6: {id: 6, cash: 205, crewId: 1, sex: 'female',  dateTime: '2017-05-02T21:41:15.123Z'},
          7: {id: 7, cash: 200, crewId: 1, sex: 'male',  dateTime: '2017-04-02T21:51:15.123Z'},
          8: {id: 8, cash: 300, crewId: 2, sex: 'male',  dateTime: '2017-07-02T17:51:15.123Z'},
          9: {id: 9, cash: 105, crewId: 1, sex: 'male', dateTime: '2017-06-02T16:51:15.123Z'},
          10: {id: 10, cash: 205, crewId: 2, sex: 'female',  dateTime: '2017-05-02T12:41:15.123Z'},
        },
        byUserAndSex: {
          1: {
            male: [1,4,7,9],
            female: [2,6]
          },
          2: {
            male: [3,8],
            female: [5,10]
          }
        }
      }
    }
  };

  const ownProps = {sex: 'male', userId: 1};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    scoreHistory: [
      {id: 7, cash: 200, crewId: 1, sex: 'male', dateTime: '2017-04-02T21:51:15.123Z', date: 1491169875123},
      {id: 9, cash: 105, crewId: 1, sex: 'male', dateTime: '2017-06-02T16:51:15.123Z', date: 1496422275123},
      {id: 1, cash: 100, crewId: 1, sex: 'male', dateTime: '2017-06-02T21:51:15.123Z', date: 1496440275123},
      {id: 4, cash: 300, crewId: 1, sex: 'male', dateTime: '2017-07-02T21:51:15.123Z', date: 1499032275123}
    ]
  });
});
