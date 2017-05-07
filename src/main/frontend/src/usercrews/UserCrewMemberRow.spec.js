import React from 'react';
import { shallow } from 'enzyme';
import { UserCrewMemberRow } from './UserCrewMemberRow';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const cancelBuyMember = jest.fn();
  const fetchCrewMembers = jest.fn();
  const onClickBuy = jest.fn();
  const doSellRower = jest.fn();
  const fetchCrew = jest.fn();
  const member = {id: 5, seat: 9, crewId: 8};
  const crew = {name: 'Crew Name', id: 8, price: 123.56};
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
      fetchCrewMembers={fetchCrewMembers}
      fetchCrew={fetchCrew}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});

test('Renders properly', () => {
  const cancelBuyMember = jest.fn();
  const fetchCrewMembers = jest.fn();
  const onClickBuy = jest.fn();
  const doSellRower = jest.fn();
  const fetchCrew = jest.fn();
  const member = {id: 5, seat: 9, crewId: 8};
  const crew = {name: 'Crew Name', id: 8, price: 123.56};
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
      fetchCrewMembers={fetchCrewMembers}
      fetchCrew={fetchCrew}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});

test('Renders properly', () => {
  const cancelBuyMember = jest.fn();
  const fetchCrewMembers = jest.fn();
  const onClickBuy = jest.fn();
  const doSellRower = jest.fn();
  const fetchCrew = jest.fn();
  const member = {id: 5, seat: 9, crewId: 8};
  const crew = {name: 'Crew Name', id: 8, price: 123.56};
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
      fetchCrewMembers={fetchCrewMembers}
      fetchCrew={fetchCrew}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});
