import React from 'react';
import { shallow } from 'enzyme';
import { UserCrew, mapStateToProps } from './UserCrew';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const fetchUserCrewMembers = jest.fn();
  const fetchUserCrewRanking = jest.fn();
  const onClickBuy = jest.fn();
  const crewMembers = [{id: 3}, {id: 7}];
  const component = shallow(
    <UserCrew
      sex="male"
      userId={17}
      crewMembers={crewMembers}
      fetchUserCrewMembers={fetchUserCrewMembers}
      fetchUserCrewRanking={fetchUserCrewRanking}
      onClickBuy={onClickBuy}
      crewRanking={{cash: 123.4, value: 1067.12}}
      header="UserCrew"
      showCashAndValue
      canBuySell
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});

test('Renders properly - not buy/sell', () => {
  const fetchUserCrewMembers = jest.fn();
  const fetchUserCrewRanking = jest.fn();
  const crewMembers = [{id: 3}, {id: 7}];
  const component = shallow(
    <UserCrew
      sex="female"
      userId={17}
      crewMembers={crewMembers}
      fetchUserCrewMembers={fetchUserCrewMembers}
      fetchUserCrewRanking={fetchUserCrewRanking}
      crewRanking={{cash: 123.4, value: 1067.12}}
      header="Other Header"
      showCashAndValue={false}
      canBuySell={false}
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
          6: {id: 6, sex: 'male', userId: 1, cash: 123, value: 321, bumps: 3, dividends: 50.12},
          7: {id: 7, sex: 'female', userId: 1, cash: 456, value: 456, bumps: 6, dividends: 123.45},
          8: {id: 8, sex: 'male', userId: 2, cash: 789, value: 1023, bumps: 8, dividends: 40.12},
          9: {id: 9, sex: 'female', userId: 2, cash: 987, value: 120, bumps: 9, dividends: 93.45}
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
      },
      members: {
        byId: {
          1: {id: 1, userId: 1, sex: 'male'},
          2: {id: 2, userId: 1, sex: 'female'},
          3: {id: 3, userId: 2, sex: 'male'},
          4: {id: 4, userId: 1, sex: 'female'},
          5: {id: 5, userId: 2, sex: 'female'},
        },
        byUserId: {
          1: [1,2,4],
          2: [3,5]
        }
      }
    }
  };

    const ownProps = {sex: 'female', userId: 1};

    const femaleComponentStateProps = mapStateToProps(reduxState, ownProps);

    expect(femaleComponentStateProps).toEqual({
      crewMembers: [{id: 2, userId: 1, sex: 'female'}, {id: 4, userId: 1, sex: 'female'}],
      crewRanking: {id: 7, sex: 'female', userId: 1, cash: 456, value: 456, bumps: 6, dividends: 123.45}
    });

  });
