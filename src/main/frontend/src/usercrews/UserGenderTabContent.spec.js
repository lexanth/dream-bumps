import React from 'react';
import { shallow } from 'enzyme';
import { UserGenderTabContent } from './UserGenderTabContent';
import toJson from 'enzyme-to-json';

test('Renders properly', () => {
  const component = shallow(
    <UserGenderTabContent
      userId={2}
      sex="female"
    />
  );
  expect(component).toBeDefined();
  expect(toJson(component)).toMatchSnapshot()
  // We don't actually mount, so these don't get called...
  // expect(fetchUsers).toHaveBeenCalled();
  // expect(fetchUserCrewRankings).toHaveBeenCalled()
});


// UserGenderTabContent.propTypes = {
//   userId: PropTypes.number,
//   sex: PropTypes.string
// }
