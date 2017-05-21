// @flow
import { combineReducers } from 'redux';
import jwtDecode from 'jwt-decode';

import * as types from '../actionTypes';

const authenticated = (
  state = false,
  action
) => {
  switch (action.type) {
    case types.LOGIN_SUCCESS:
    case types.LOGOUT_ERROR:
    case types.FETCH_CURRENT_USER_SUCCESS:
    case types.SIGNUP_SUCCESS:
      return true;
    case types.LOGIN_ERROR:
    case types.SIGNUP_ERROR:
    case types.LOGOUT_SUCCESS:
    case types.FETCH_CURRENT_USER_ERROR:
      return false;
    default:
      return state;
  }
};

const currentUser = (state = {}, action) => {
  switch (action.type) {
    case types.FETCH_CURRENT_USER_SUCCESS:
      return action.user;
    case types.LOGOUT_SUCCESS:
      return {};
    case types.SIGNUP_SUCCESS:
    case types.LOGIN_SUCCESS:
      // console.log(jwtDecode(action.token));
      const token = jwtDecode(action.token);
      return {
        id: token.id,
        authorities: token.auth.split(','),
        login: token.sub
      }
    default:
      return state;
  }
};

const loading = (state = false, action) => {
  switch (action.type) {
    case types.FETCH_CURRENT_USER_SUCCESS:
    case types.FETCH_CURRENT_USER_ERROR:
      return false;
    case types.FETCH_CURRENT_USER_REQUEST:
      return true;
    default:
      return state;
  }
};

const userReducer = combineReducers({
  loading,
  authenticated,
  currentUser
});

export default userReducer;

export const getCurrentUserLogin = (state: Object) => {
  if (state.user.currentUser !== undefined) {
    return state.currentUser.login;
  }
  return '';
};

export const _getCurrentUserId = (state: Object) => {
  if (state.currentUser !== undefined) {
    return state.currentUser.id;
  }
  return 0;
};

export const _getCurrentUser = (state: Object) => state.currentUser;

export const _isAdmin = (state: Object) => () => (state.currentUser && state.currentUser.authorities && state.currentUser.authorities.indexOf('ROLE_ADMIN') > -1);
