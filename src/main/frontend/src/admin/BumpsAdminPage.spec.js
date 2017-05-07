import React from 'react';
import { shallow } from 'enzyme';
import { BumpsAdminPage } from './BumpsAdminPage';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const submit = jest.fn();
  const fetch = jest.fn();
  const component = shallow(
    <BumpsAdminPage
      day={2}
      crews={{0: [{position: 1}, {position: 2}, {position: 3}], 1: [{position: 4}, {position: 5}, {position: 6}]}}
      uploadBumps={submit}
      fetchCrews={fetch}
      params={{sex: 'male'}}
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
});

// BumpsAdminPage.propTypes = {
//   sex: PropTypes.string,
//   day: PropTypes.number,
//   crews: PropTypes.array,
//   uploadBumps: PropTypes.func,
//   fetchCrews: PropTypes.func,
//   params: PropTypes.object
// }
