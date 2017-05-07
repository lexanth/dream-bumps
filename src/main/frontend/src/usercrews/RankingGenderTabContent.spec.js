import React from 'react';
import { shallow } from 'enzyme';
import { RankingGenderTabContent, SortableHeaderColumn, mapStateToProps } from './RankingGenderTabContent';
import toJson from 'enzyme-to-json';

test('Renders properly - tab content', () => {
  const component = shallow(
    <RankingGenderTabContent
      sex="female"
      rankings={[{}, {}, {}]}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('Renders properly - header, current key', () => {
  const onSort = jest.fn();
  const component = shallow(
    <SortableHeaderColumn
      sortKey="BUMPS"
      label="Bumps"
      onSort={onSort}
      sortDescending={true}
      currentSortKey="BUMPS"
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('Renders properly - header, not current key', () => {
  const onSort = jest.fn();
  const component = shallow(
    <SortableHeaderColumn
      sortKey="BUMPS"
      label="Bumps"
      onSort={onSort}
      sortDescending={true}
      currentSortKey="SCORE"
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

test('mapStateToProps', () => {
  const reduxState = {
    usercrews: {
      purchase: {
        buyMember: 5,
        buySex: 'male'
      },
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
      }
      }
    };

    const ownProps = {sex: 'female'};

    const femaleComponentStateProps = mapStateToProps(reduxState, ownProps);

    expect(femaleComponentStateProps).toEqual({
      rankings: [
        {id: 7, sex: 'female', userId: 1, cash: 456, value: 456, bumps: 6, dividends: 123.45},
        {id: 9, sex: 'female', userId: 2, cash: 987, value: 120, bumps: 9, dividends: 93.45}]
    });

    const combinedComponentStateProps = mapStateToProps(reduxState, {sex: 'combined'});
    expect(combinedComponentStateProps).toEqual({
      rankings: [
        {id: 6, sex: 'combined', userId: 1, cash: 579, value: 777, bumps: 9, dividends: 173.57},
        {id: 8, sex: 'combined', userId: 2, cash: 1776, value: 1143, bumps: 17, dividends: 133.57}
      ]
    })
  });
