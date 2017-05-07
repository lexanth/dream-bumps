import React from 'react';
import { shallow } from 'enzyme';
import { DivisionTable, mapStateToProps } from './DivisionTable';
import toJson from 'enzyme-to-json';

test('Renders properly not buy', () => {
  const crews = [
    {id: 1, name: 'Crew 1', position: 1, price: 123.4567},
    {id: 3, name: 'Crew 2', position: 2, price: 153.4}
   ]
  const component = shallow(
    <DivisionTable
      sex="male"
      division="2"
      crews={crews}
      buySex="female"
      day={2}
      crewRanking={{cash: 130}}
      numberOfCrews={92}
      crewsPerDivision={13}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('Renders properly buy', () => {
  const crews = [
    {id: 1, name: 'Crew 1', position: 1, price: 123.4567},
    {id: 3, name: 'Crew 2', position: 2, price: 153.4}
   ]
  const component = shallow(
    <DivisionTable
      sex="male"
      division="2"
      crews={crews}
      buySex="male"
      day={2}
      crewRanking={{cash: 130}}
      numberOfCrews={92}
      crewsPerDivision={13}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('mapStateToProps', () => {
  const reduxState = {
    auth: {
      authenticated: true,
      currentUser: {
        authorities: ['ROLE_USER'],
        id: 2
      }
    },
    config: {
      config: {
        crewsPerDivision: 3,
        mensCrews: 13,
        womensCrews: 10
      }
    },
    usercrews: {
      purchase: {
        buyMember: 5,
        buySex: 'male'},
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

  const ownProps = {sex: 'male'};

  const componentStateProps = mapStateToProps(reduxState, ownProps);

  expect(componentStateProps).toEqual({
    currentUserId: 2,
    buySex: 'male',
    buyMemberId: 5,
    crewRanking: {id: 8, cash: 789},
    crewsPerDivision: 3,
    numberOfCrews: 13
  });
});
