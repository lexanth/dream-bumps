// @flow
import { combineReducers } from 'redux';

import * as types from '../actionTypes';

const config = (state = {}, action) => {
  switch (action.type) {
    case types.FETCH_CONFIG_SUCCESS:
      return action.config;
    default:
      return state;
  }
};

const loading = (state = false, action) => {
  switch (action.type) {
    case types.FETCH_CONFIG_REQUEST:
      return true;
    case types.FETCH_CONFIG_SUCCESS:
    case types.FETCH_CONFIG_ERROR:
      return false;
    default:
      return state;
  }
};

export default combineReducers({
  config,
  loading
});

export const _getNumberOfCrews = (state: Object) => (sex:string) => (sex === 'male' ? state.config.mensCrews : state.config.womensCrews);

export const _getNumberOfDivisions = (state: Object) => (sex:string) => (Math.floor(_getNumberOfCrews(state)(sex) / state.config.crewsPerDivision));
