import { combineReducers } from 'redux';

import * as types from '../actionTypes';
import { tokenName } from '../constants/config';

const authenticated = (
  state = localStorage.getItem(tokenName) !== null,
  action
) => {
  switch (action.type) {
    case types.LOGIN_SUCCESS:
    case types.SIGNUP_SUCCESS:
    case types.LOGOUT_ERROR:
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

const token = (state = localStorage.getItem(tokenName), action) => {
  switch (action.type) {
    case types.LOGIN_SUCCESS:
      return action.token;
    case types.LOGIN_ERROR:
    case types.LOGOUT_SUCCESS:
      return null;
    default:
      return state;
  }
};

const currentUser = (state = {}, action) => {
  switch (action.type) {
    case types.FETCH_CURRENT_USER_SUCCESS:
      return action.user;
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
  token,
  currentUser
});

export default userReducer;

export const getCurrentUserLogin = (state) => {
  if (state.user.currentUser !== undefined) {
    return state.currentUser.login;
  }
  return '';
};

export const _getCurrentUserId = (state) => {
  if (state.currentUser !== undefined) {
    return state.currentUser.id;
  }
  return 0;
};

export const _getCurrentUser = state => state.currentUser;
