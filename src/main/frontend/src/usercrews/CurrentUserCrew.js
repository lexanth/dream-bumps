// @flow
import React, { PropTypes } from 'react';
import { connect } from 'react-redux';

import { getCurrentUserId } from '../rootReducer';
import UserCrew from './UserCrew';

const CurrentUserCrew = ({userId, sex}: {userId: number, sex: string}) => (
  <UserCrew
    userId={userId}
    canBuySell
    sex={sex}
    header="My Crew"
    showCashAndValue
  />
);

CurrentUserCrew.propTypes = {
  userId: PropTypes.number,
  sex: PropTypes.string
}

export const mapStateToProps = (state:Object) => ({
  userId: getCurrentUserId(state)
});

export {CurrentUserCrew};

export default connect(mapStateToProps)(CurrentUserCrew);
