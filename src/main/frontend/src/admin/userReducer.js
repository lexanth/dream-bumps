// @flow
import { combineReducers } from 'redux';

import * as types from '../actionTypes';
// import copyAndAddIfNotPresent from '../utils/copyAndAddIfNotPresent';

const all = (state = [], action) => {
  switch (action.type) {
    case types.FETCH_USERS_SUCCESS:
      return action.users.map(user => user.id);
    default:
      return state;
  }
};

const byId = (state = {}, action) => {
  switch (action.type) {
    case types.FETCH_USERS_SUCCESS:
      const newState = {...state};
      action.users.forEach(user => newState[user.id] = user);
      return newState;
    case types.UPDATE_USER_SUCCESS:
      return {...state, [action.user.id]: action.user}
    default:
      return state;
  }
}

export default combineReducers({byId, all});

export const _getAllUsers = (state: Object) => state.all.map(id => state.byId[id]);
export const _getUser = (state: Object) => (userId: number) => state.byId[userId];
