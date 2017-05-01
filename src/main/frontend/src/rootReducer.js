import { combineReducers } from 'redux';
import { routerReducer as routing } from 'react-router-redux';
import { reducer as form } from 'redux-form';

import auth, { _getCurrentUserId, _getCurrentUser, _isAdmin } from './auth/reducer';
import crews, { _getCrewMembers, _getCrew, _getCrewPriceHistory, _getCrewName, _getCrewMemberName, _getCrewPrice, _getCrewsForSex } from './crews/reducer';
import config, { _getNumberOfDivisions, _getNumberOfCrews } from './config/reducer';
import usercrews, { _getUserCrewMembers, _getUserCrewRanking, _getBuyMemberId, _getBuySex, _getUserCrewRankings, _getUserScoreHistory } from './usercrews/reducer';
import users, {_getAllUsers, _getUser } from './admin/userReducer';
import status, {_isMarketOpen, _getCurrentDay, _getCurrentStatus} from './status/reducer';

const rootReducer = combineReducers({
  auth,
  form,
  routing,
  crews,
  config,
  usercrews,
  users,
  status
});

export default rootReducer;

export const isLoading = (state) => (
  state.auth.loading || state.config.loading || state.status.loading
);

// auth
export const getCurrentUserId = state => _getCurrentUserId(state.auth);
export const getCurrentUser = state => _getCurrentUser(state.auth);
export const isAdmin = state => _isAdmin(state.auth);
// crews
export const getCrewMembers = state => _getCrewMembers(state.crews);
export const getCrew = state => _getCrew(state.crews);
export const getCrewPriceHistory = state => _getCrewPriceHistory(state.crews);
export const getCrewName = state => _getCrewName(state.crews);
export const getCrewMemberName = state => _getCrewMemberName(state.crews);
export const getCrewPrice = state => _getCrewPrice(state.crews);
export const getCrewsForSex = state => _getCrewsForSex(state.crews);

// config
export const getNumberOfDivisions = state => _getNumberOfDivisions(state.config);

export const getCrewsForDivision = state => (sex, division) => {
  const crewsPerDivision = state.config.config.crewsPerDivision;
  const totalNumberOfCrews = _getNumberOfCrews(state.config)(sex);
  const minPos = division * crewsPerDivision + 1;
  let maxPos = (division + 1) * crewsPerDivision;
  if (maxPos + 1 === totalNumberOfCrews) {
    maxPos = totalNumberOfCrews;
  }
  const crewsForSex = state.crews.crews.bySex[sex] || [];
  return crewsForSex.map(id => state.crews.crews.byId[id]).filter(crew => crew.position >= minPos && crew.position <= maxPos).sort((a,b) => a.position - b.position);
};

export const getCrewsByDivision = state => sex => {
  const crewsForSex = getCrewsForSex(state)(sex).sort((a,b) => a.position - b.position);
  const result = {0:[]};
  let currentDivision = 0;
  const crewsPerDivision = state.config.config.crewsPerDivision;
  for (let i = 0; i < crewsForSex.length; i++) {
    result[currentDivision].push(crewsForSex[i]);
    if (result[currentDivision].length >= crewsPerDivision + 1) {
      currentDivision = currentDivision++;
      result[currentDivision] = [];
    }
  }
  return result;
};

export const getUserCrewMembers = state => _getUserCrewMembers(state.usercrews);
export const getUserCrewRanking = state => _getUserCrewRanking(state.usercrews);
export const getUserCrewRankings = state => _getUserCrewRankings(state.usercrews);
export const getBuyMemberId = state => _getBuyMemberId(state.usercrews);
export const getBuySex = state => _getBuySex(state.usercrews);

export const getAllUsers = state => _getAllUsers(state.users);
export const getUser = state => _getUser(state.users);

export const getCurrentDay = state => _getCurrentDay(state.status);
export const isMarketOpen = state => _isMarketOpen(state.status);
export const getCurrentStatus = state => _getCurrentStatus(state.status);

export const getUserScoreHistory = state => _getUserScoreHistory(state.usercrews);
