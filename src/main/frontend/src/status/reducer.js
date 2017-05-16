// @flow
import { combineReducers } from 'redux';

import * as types from '../actionTypes';

const status = (state = {}, action) => {
  switch (action.type) {
    case types.FETCH_STATUS_SUCCESS:
    case types.UPDATE_STATUS_SUCCESS:
      return action.status;
    default:
      return state;
  }
};

const loading = (state = false, action) => {
  switch (action.type) {
    case types.FETCH_STATUS_REQUEST:
    case types.UPDATE_STATUS_REQUEST:
      return true;
    case types.FETCH_STATUS_SUCCESS:
    case types.FETCH_STATUS_ERROR:
    case types.UPDATE_STATUS_SUCCESS:
    case types.UPDATE_STATUS_ERROR:
      return false;
    default:
      return state;
  }
};

export default combineReducers({
  status,
  loading
});

export const _getCurrentDay = (state:{status:{day:number}}) => state.status.day;
export const _isMarketOpen = (state:{status:{open:boolean}}) => state.status.open;
export const _getCurrentStatus = (state:{status:{day:number,open:boolean,dateTime:string}}) => state.status;
