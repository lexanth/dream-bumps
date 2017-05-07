import React from 'react';
import { shallow } from 'enzyme';
import { UserCrew } from './UserCrew';
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
