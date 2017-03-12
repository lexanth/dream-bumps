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
  // let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_RANKING_SUCCESS:
      return {...state, [action.ranking.id]: action.ranking};
    default:
      return state;
  }
};

const rankingsBySex = (state = {}, action) => {
  switch (action.type) {
    case types.FETCH_USER_CREW_RANKING_SUCCESS:
      return {...state, [action.ranking.sex]: action.ranking.id};
    default:
      return state;
  }
}

const rankingsByUserId = (state = {}, action) => {
  // let newState = {...state};
  switch (action.type) {
    case types.FETCH_USER_CREW_RANKING_SUCCESS:
      return {...state, [action.ranking.userId]: rankingsBySex(state[action.ranking.userId], action)};
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

export default combineReducers({
  members,
  rankings,
  purchase
});

export const _getUserCrewMembers = state => (userId, sex) => {
  const membersForUser = state.members.byUserId[userId] || [];
  return membersForUser.map(id => state.members.byId[id]).filter(member => member.sex === sex).sort((a,b) => (a.seat - b.seat));
};

export const _getUserCrewRanking = state => (userId, sex) => {
  if (!userId || !state.rankings.byUserId[userId]) return {};
  return state.rankings.byId[state.rankings.byUserId[userId][sex]];
}
export const _getBuyMemberId = state => state.purchase.buyMember;
export const _getBuySex = state => state.purchase.buySex;
