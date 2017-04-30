import { combineReducers } from 'redux';

import * as types from '../actionTypes';
import copyAndAddIfNotPresent from '../utils/copyAndAddIfNotPresent';

const membersById = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_MEMBERS_SUCCESS:
      action.members.forEach(member => {newState[member.id] = member;});
      return newState;
    case types.BUY_ROWER_SUCCESS:
    case types.SELL_ROWER_SUCCESS:
      return {...state, [action.userCrewMember.id]: action.userCrewMember};
    default:
      return state;
  }
};

const membersByUserId = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_MEMBERS_SUCCESS:
      action.members.forEach(member => {newState[member.userId] = copyAndAddIfNotPresent(newState[member.userId], member.id);});
      return newState;
    default:
      return state;
  }
};

const members = combineReducers({
  byId: membersById,
  byUserId: membersByUserId
});

const rankingsById = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_RANKING_SUCCESS:
      return {...state, [action.ranking.id]: action.ranking};
    case types.FETCH_USER_CREW_RANKINGS_SUCCESS:
      action.rankings.forEach(ranking => newState[ranking.id] = ranking);
      return newState;
    default:
      return state;
  }
};

const rankingsBySex = (state = {}, action, ranking) => {
  switch (action.type) {
    case types.FETCH_USER_CREW_RANKING_SUCCESS:
      return {...state, [action.ranking.sex]: action.ranking.id};
    case types.FETCH_USER_CREW_RANKINGS_SUCCESS:
      return {...state, [ranking.sex]: ranking.id}
    default:
      return state;
  }
}

const rankingsByUserId = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_RANKING_SUCCESS:
      return {...state, [action.ranking.userId]: rankingsBySex(state[action.ranking.userId], action)};
    case types.FETCH_USER_CREW_RANKINGS_SUCCESS:
      action.rankings.forEach(ranking => newState[ranking.userId] = rankingsBySex(state[ranking.userId], action, ranking));
      return newState;
    default:
      return state;
  }
};

const rankings = combineReducers({
  byId: rankingsById,
  byUserId: rankingsByUserId
})

const buyMember = (state = null, action) => {
  switch (action.type) {
    case types.SET_BUY_MEMBER:
      return action.memberId;
    case types.CANCEL_BUY_MEMBER:
    case types.BUY_ROWER_SUCCESS:
      return null;
    default:
      return state;
  }
}

const buySex = (state = null, action) => {
  switch (action.type) {
    case types.SET_BUY_MEMBER:
      return action.sex;
    case types.CANCEL_BUY_MEMBER:
    case types.BUY_ROWER_SUCCESS:
      return null;
    default:
      return state;
  }
}

const purchase = combineReducers({
  buyMember,
  buySex
})

const historyByUser = (state = {}, history) => {
  return {...state, [history.sex]: copyAndAddIfNotPresent(state[history.sex], history.id)};
}

const byUserAndSex = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_PRICE_HISTORY_SUCCESS:
      action.userCrewPriceHistories.forEach(history => newState[history.user.id] = historyByUser(newState[history.user.id], history));
      return newState;
    default:
      return state;
  }
}

const historyById = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_PRICE_HISTORY_SUCCESS:
      action.userCrewPriceHistories.forEach(history => newState[history.id] = history);
      return newState;
    default:
      return state;
  }
}

const history = combineReducers({
  byUserAndSex,
  byId: historyById
});

export default combineReducers({
  members,
  rankings,
  purchase,
  history
});

export const _getUserCrewMembers = state => (userId, sex) => {
  const membersForUser = state.members.byUserId[userId] || [];
  return membersForUser
    .map(id => state.members.byId[id])
    .filter(member => member.sex === sex)
    .sort((a,b) => (a.seat - b.seat));
};

export const _getUserCrewRanking = state => (userId, sex) => {
  if (!userId || !state.rankings.byUserId[userId]) return {};
  return state.rankings.byId[state.rankings.byUserId[userId][sex]];
}
export const _getBuyMemberId = state => state.purchase.buyMember;
export const _getBuySex = state => state.purchase.buySex;
export const _getUserCrewRankings = state => sex => {
  const rankings = [];
  Object.keys(state.rankings.byUserId).forEach(userId => {
    rankings.push(state.rankings.byUserId[userId][sex]);
  });
  return rankings.map(id => state.rankings.byId[id]);
}

export const _getUserScoreHistory = state => (userId, sex) => {
  if (!userId || !state.history.byUserAndSex[userId]) return [];
  const historyIds = state.history.byUserAndSex[userId][sex] || [];
  return historyIds.map(id => state.history.byId[id]);
}
