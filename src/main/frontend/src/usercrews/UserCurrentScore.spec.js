import React from 'react';
import { shallow } from 'enzyme';
import { UserCurrentScore, mapStateToProps } from './UserCurrentScore';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchUserCrewRanking = jest.fn();
  const component = shallow(
    <UserCurrentScore
      fetchUserCrewRanking={fetchUserCrewRanking}
      userId={2}
      sex="female"
      ranking={{bumps: 3, cash: 123.4, value: 1823.43, dividends: 234.23}}
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
      rankings: {
        byId: {
          6: {id: 6, cash: 123},
          7: {id: 7, cash: 456},
          8: {id: 8, cash: 789},
          9: {id: 9, cash: 987}
        },
        byUserId: {
          1: {
            male: 6,
            female: 7
          },
          2: {
            male: 8,
            female: 9
          }
        }
      }
    }
  };

  const ownProps = {sex: 'male', userId: 1};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    ranking: {id: 6, cash: 123}
  });
});
