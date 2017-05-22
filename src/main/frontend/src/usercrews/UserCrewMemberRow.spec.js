import React from 'react';
import { shallow } from 'enzyme';
import { UserCrewMemberRow, mapStateToProps } from './UserCrewMemberRow';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const cancelBuyMember = jest.fn();
  const onClickBuy = jest.fn();
  const doSellRower = jest.fn();
  const member = { id: 5, seat: 9, crewId: 8 };
  const crew = { name: 'Crew Name', id: 8, price: 123.56 };
  const component = shallow(
    <UserCrewMemberRow
      member={member}
      crew={crew}
      crewMemberName="Something"
      onClickBuy={onClickBuy}
      buyMemberId={5}
      cancelBuyMember={cancelBuyMember}
      doSellRower={doSellRower}
      marketOpen
      canBuySell
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});

test('Renders properly', () => {
  const cancelBuyMember = jest.fn();
  const onClickBuy = jest.fn();
  const doSellRower = jest.fn();
  const member = { id: 5, seat: 9, crewId: 8 };
  const crew = { name: 'Crew Name', id: 8, price: 123.56 };
  const component = shallow(
    <UserCrewMemberRow
      member={member}
      crew={crew}
      crewMemberName="Something"
      onClickBuy={onClickBuy}
      cancelBuyMember={cancelBuyMember}
      doSellRower={doSellRower}
      marketOpen={false}
      canBuySell={false}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});

test('Renders properly', () => {
  const cancelBuyMember = jest.fn();
  const onClickBuy = jest.fn();
  const doSellRower = jest.fn();
  const member = { id: 5, seat: 9, crewId: 8 };
  const crew = { name: 'Crew Name', id: 8, price: 123.56 };
  const component = shallow(
    <UserCrewMemberRow
      member={member}
      crew={crew}
      crewMemberName="Something"
      onClickBuy={onClickBuy}
      cancelBuyMember={cancelBuyMember}
      doSellRower={doSellRower}
      marketOpen
      canBuySell
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot();
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});

test('mapStateToProps', () => {
  const reduxState = {
    status: {
      status: {
        open: true,
        day: 3
      }
    },
    usercrews: {
      purchase: {
        buyMember: 5,
        buySex: 'male'
      },
      rankings: {
        byId: {
          6: {
            id: 6,
            sex: 'male',
            userId: 1,
            cash: 123,
            value: 321,
            bumps: 3,
            dividends: 50.12
          },
          7: {
            id: 7,
            sex: 'female',
            userId: 1,
            cash: 456,
            value: 456,
            bumps: 6,
            dividends: 123.45
          },
          8: {
            id: 8,
            sex: 'male',
            userId: 2,
            cash: 789,
            value: 1023,
            bumps: 8,
            dividends: 40.12
          },
          9: {
            id: 9,
            sex: 'female',
            userId: 2,
            cash: 987,
            value: 120,
            bumps: 9,
            dividends: 93.45
          }
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
          1: { id: 1, userId: 1, sex: 'male' },
          2: { id: 2, userId: 1, sex: 'female' },
          3: { id: 3, userId: 2, sex: 'male' },
          4: { id: 4, userId: 1, sex: 'female' },
          5: { id: 5, userId: 2, sex: 'female' }
        },
        byUserId: {
          1: [1, 2, 4],
          2: [3, 5]
        }
      }
    },
    crews: {
      members: {
        byId: {
          1: { id: 1, name: 'Member 1', crewId: 5, seat: 1 },
          2: { id: 2, name: 'Member 2', crewId: 5, seat: 2 },
          3: { id: 3, name: 'Member 3', crewId: 6, seat: 3 },
          4: { id: 4, name: 'Member 4', crewId: 5, seat: 4 },
          5: { id: 5, name: 'Member 5', crewId: 6, seat: 5 },
          6: { id: 6, name: 'Member 6', crewId: 6, seat: 6 }
        },
        byCrewId: {
          5: [1, 2, 4],
          6: [3, 5, 6]
        }
      },
      crews: {
        byId: {
          1: { id: 1, sex: 'male', name: 'Crew 1' },
          2: { id: 2, sex: 'male', name: 'Crew 2' },
          3: { id: 3, sex: 'female', name: 'Crew 3' },
          4: { id: 4, sex: 'male', name: 'Crew 4' },
          5: { id: 5, sex: 'female', name: 'Crew 5' },
          6: { id: 6, sex: 'female', name: 'Crew 6' }
        },
        bySex: {
          male: [1, 2, 4],
          female: [3, 5, 6]
        }
      }
    }
  };

  const ownProps = { member: { crewId: 5, seat: 2 } };
  const componentStateProps = mapStateToProps(reduxState, ownProps);
  expect(componentStateProps).toEqual({
    crew: { id: 5, sex: 'female', name: 'Crew 5' },
    crewMemberName: 'Member 2',
    buyMemberId: 5,
    marketOpen: true
  });
});
