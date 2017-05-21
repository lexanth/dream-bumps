import * as types from '../actionTypes';

/*
 * Message store for global messages, i.e. Network messages / Redirect messages
 * that need to be communicated on the page itself. Ideally
 * messages/notifications should appear within the component to give the user
 * more context. - My 2 cents.
 */
export default (state = '', action) => {
  switch (action.type) {
    case types.SIGNUP_SUCCESS:
      return 'Registered successfully.';
    case types.LOGIN_SUCCESS:
      return 'Logged in successfully.';
    case types.BUY_ROWER_SUCCESS:
      return 'Bought rower successfully';
    case types.SELL_ROWER_SUCCESS:
      return 'Sold rower successfully';
    case types.LOGIN_ERROR:
    case types.SIGNUP_ERROR:
    case types.UPDATE_USER_ERROR:
    case types.UPDATE_CREW_MEMBERS_ERROR:
    case types.UPDATE_STATUS_ERROR:
      return action.errorMessage;
    case types.BUY_ROWER_ERROR:
      return 'Failed to buy rower. There\'s a known bug, try refreshing the page.';
    case types.SELL_ROWER_ERROR:
      return 'Failed to sell rower';
    case types.UPDATE_USER_SUCCESS:
      return 'User updated';
    case types.UPLOAD_BUMPS_SUCCESS:
      return 'Bumps uploaded successfully.';
    case types.UPDATE_CREW_MEMBERS_SUCCESS:
      return 'Crew members updated.';
    case types.UPDATE_STATUS_SUCCESS:
      return 'Market status updated';
    case types.DISMISS_MESSAGE:
      return '';
    default:
      return state;
  }
};
