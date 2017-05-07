import React from 'react';
import { shallow } from 'enzyme';
import { DivisionTable } from './DivisionTable';
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
