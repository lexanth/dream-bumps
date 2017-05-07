import React from 'react';
import { shallow } from 'enzyme';
import { RankingGenderTabContent, SortableHeaderColumn } from './RankingGenderTabContent';
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

// sortKey: PropTypes.string,
// label: PropTypes.string,
// onSort: PropTypes.func,
// currentSortKey: PropTypes.string,
// sortDescending: PropTypes.bool
