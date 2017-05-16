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
      return 'Registration succeeded! Please refresh to log in...';
    case types.LOGIN_SUCCESS:
      return 'Logged in successfully.';
    case types.BUY_ROWER_SUCCESS:
      return 'Bought rower successfully';
    case types.SELL_ROWER_SUCCESS:
      return 'Sold rower successfully';
    case types.LOGIN_ERROR:
    case types.SIGNUP_ERROR:
      return action.errorMessage;
    default:
      return state;
  }
};
