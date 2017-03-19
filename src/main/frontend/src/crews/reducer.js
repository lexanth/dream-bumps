import { combineReducers } from 'redux';

import * as types from '../actionTypes';
import copyAndAddIfNotPresent from '../utils/copyAndAddIfNotPresent';

const membersById = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_CREW_MEMBERS_SUCCESS:
      action.crewMembers.forEach(member => {newState[member.id] = member;});
      return newState;
    default:
      return state;
  }
};

const membersByCrewId = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_CREW_MEMBERS_SUCCESS:
      action.crewMembers.forEach(member => {newState[member.crewId] = copyAndAddIfNotPresent(newState[member.crewId], member.id);});
      return newState;
    default:
      return state;
  }
};

const members = combineReducers({
  byId: membersById,
  byCrewId: membersByCrewId
});

const crewsById = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_CREW_SUCCESS:
      return {...state, [action.crew.id]: action.crew};
    case types.FETCH_CREWS_SUCCESS:
      action.crews.forEach(crew => newState[crew.id] = crew);
      return newState;
    default:
      return state;
  }
};

const crewsBySex = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_CREW_SUCCESS:
      newState[action.crew.sex] = copyAndAddIfNotPresent(newState[action.crew.sex], action.crew.id);
      return newState;
    case types.FETCH_CREWS_SUCCESS:
      action.crews.forEach(crew => {newState[crew.sex] = copyAndAddIfNotPresent(newState[crew.sex], crew.id);});
      return newState;
    default:
      return state;
  }
};

const crews = combineReducers({
  byId: crewsById,
  bySex: crewsBySex
});

const priceHistoryById = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_CREW_PRICE_HISTORY_SUCCESS:
      action.priceHistories.forEach(history => {newState[history.id] = history;});
      return newState;
    case types.FETCH_CREW_SUCCESS:
      newState[-action.crew.id] = {price: action.crew.price, dateTime: new Date()};
      return newState;
    default:
      return state;
  }
};

const priceHistoryByCrewId = (state = {}, action) => {
  let newState = {...state};
  switch (action.type) {
    case types.FETCH_CREW_PRICE_HISTORY_SUCCESS:
      action.priceHistories.forEach(history => {newState[parseInt(history.crew.id, 10)] = copyAndAddIfNotPresent(newState[parseInt(history.crew.id, 10)], history.id);});
      return newState;
    case types.FETCH_CREW_SUCCESS:
      newState[parseInt(action.crew.id, 10)] = copyAndAddIfNotPresent(newState[parseInt(action.crew.id, 10)], -action.crew.id);
      return newState;
    default:
      return state;
  }
};

const priceHistory = combineReducers({
  byId: priceHistoryById,
  byCrewId: priceHistoryByCrewId
});

export default combineReducers({
  members,
  crews,
  priceHistory
});

export const _getCrewMembers = state => crewId => (state.members.byCrewId[crewId] ? state.members.byCrewId[crewId].map(id => state.members.byId[id]) : []).sort((a,b) => a.seat > b.seat);
export const _getCrew = state => crewId => (state.crews.byId[crewId]);
export const _getCrewPriceHistory = state => crewId => (state.priceHistory.byCrewId[crewId] ? state.priceHistory.byCrewId[crewId].map(id => state.priceHistory.byId[id]) : []);

export const _getCrewName = state => crewId => {
  if (crewId === null) return '';
  const crew = _getCrew(state)(crewId);
  return crew ? crew.name : '';
};

export const _getCrewMemberName = state => (crewId, seat) => {
  const members = _getCrewMembers(state)(crewId);
  const member = members.find(member => member.seat === seat)
  return member ? member.name : '';
};

export const _getCrewPrice = state => crewId => {
  if (crewId === null) return '';
  const crew = _getCrew(state)(crewId);
  return crew ? crew.price : '';
};

export const _getCrewsForSex = state => sex => (state.crews.bySex[sex] || []).map(id => state.crews.byId[id]).sort((a,b) => a.position - b.position);
