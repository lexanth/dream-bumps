// @flow
import { combineReducers } from 'redux';
import { routerReducer as routing } from 'react-router-redux';
import { reducer as form } from 'redux-form';

import auth, {
  _getCurrentUserId,
  _getCurrentUser,
  _isAdmin
} from './auth/reducer';
import crews, {
  _getCrewMembers,
  _getCrew,
  _getCrewPriceHistory,
  _getCrewName,
  _getCrewMemberName,
  _getCrewPrice,
  _getCrewsForSex
} from './crews/reducer';
import config, {
  _getNumberOfDivisions,
  _getNumberOfCrews
} from './config/reducer';
import usercrews, {
  _getUserCrewMembers,
  _getUserCrewRanking,
  _getBuyMemberId,
  _getBuySex,
  _getUserCrewRankings,
  _getUserScoreHistory
} from './usercrews/reducer';
import users, { _getAllUsers, _getUser } from './admin/userReducer';
import status, {
  _isMarketOpen,
  _getCurrentDay,
  _getCurrentStatus
} from './status/reducer';
import app from './app/reducer';
import * as types from './actionTypes';

const rootReducer = combineReducers({
  auth,
  form,
  routing,
  crews,
  config,
  usercrews,
  users,
  status,
  app
});

export default rootReducer;

export const isLoading = (state: Object) =>
  state.auth.loading || state.config.loading || state.status.loading;

// auth
export const getCurrentUserId = (state: Object) =>
  _getCurrentUserId(state.auth);
export const getCurrentUser = (state: Object) => _getCurrentUser(state.auth);
export const isAdmin = (state: Object) => _isAdmin(state.auth);
// crews
export const getCrewMembers = (state: Object) => _getCrewMembers(state.crews);
export const getCrew = (state: Object) => _getCrew(state.crews);
export const getCrewPriceHistory = (state: Object) =>
  _getCrewPriceHistory(state.crews);
export const getCrewName = (state: Object) => _getCrewName(state.crews);
export const getCrewMemberName = (state: Object) =>
  _getCrewMemberName(state.crews);
export const getCrewPrice = (state: Object) => _getCrewPrice(state.crews);
export const getCrewsForSex = (state: Object) => _getCrewsForSex(state.crews);

// config
export const getNumberOfDivisions = (state: Object) =>
  _getNumberOfDivisions(state.config);
export const getNumberOfCrews = (state: Object) =>
  _getNumberOfCrews(state.config);
export const getCrewsPerDivision = (state: Object) => () =>
  state.config.config.crewsPerDivision;

export const getCrewsByDivision = (state: Object) => (sex: string) => {
  const crewsForSex = getCrewsForSex(state)(sex).sort(
    (a, b) => a.position - b.position
  );
  const result = { [0]: [] };
  let currentDivision = 0;
  const crewsPerDivision = state.config.config.crewsPerDivision;
  for (let i = 0; i < crewsForSex.length; i++) {
    result[currentDivision].push(crewsForSex[i]);
    if (
      result[currentDivision].length >= crewsPerDivision &&
      i < crewsForSex.length - 2
    ) {
      currentDivision++;
      result[currentDivision] = [];
    }
  }
  return result;
};

export const getUserCrewMembers = (state: Object) =>
  _getUserCrewMembers(state.usercrews);
export const getUserCrewRanking = (state: Object) =>
  _getUserCrewRanking(state.usercrews);
export const getUserCrewRankings = (state: Object) =>
  _getUserCrewRankings(state.usercrews);
export const getBuyMemberId = (state: Object) =>
  _getBuyMemberId(state.usercrews);
export const getBuySex = (state: Object) => _getBuySex(state.usercrews);

export const getAllUsers = (state: Object) => _getAllUsers(state.users);
export const getUser = (state: Object) => _getUser(state.users);

export const getCurrentDay = (state: Object) => _getCurrentDay(state.status);
export const isMarketOpen = (state: Object) => _isMarketOpen(state.status);
export const getCurrentStatus = (state: Object) =>
  _getCurrentStatus(state.status);

export const getUserScoreHistory = (state: Object) =>
  _getUserScoreHistory(state.usercrews);
