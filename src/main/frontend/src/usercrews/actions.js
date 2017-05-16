// @flow
import requestWithAuth from '../auth/authorisedAxios';
import * as types from '../actionTypes';

import { fetchCrew } from '../crews/actions';

const fetchUserCrewMembersStart = (userId, sex) => ({
  type: types.FETCH_USER_CREW_MEMBERS_REQUEST,
  userId,
  sex
});

const fetchUserCrewMembersSuccess = members => ({
  type: types.FETCH_USER_CREW_MEMBERS_SUCCESS,
  members
});

const fetchUserCrewMembersError = errorMessage => ({
  type: types.FETCH_USER_CREW_MEMBERS_ERROR,
  errorMessage
});

export const fetchUserCrewMembers = (userId: number, sex: string) => (dispatch:Function) => {
  dispatch(fetchUserCrewMembersStart(userId, sex));
  return requestWithAuth.get(`/api/users/${userId}/members?sex=${sex}`)
    .then(response => {
      dispatch(fetchUserCrewMembersSuccess(response.data));
    },
    error => {
      dispatch(fetchUserCrewMembersError(error));
    });
};

const fetchUserCrewRankingStart = (userId, sex) => ({
  type: types.FETCH_USER_CREW_RANKING_REQUEST,
  userId,
  sex
});

const fetchUserCrewRankingSuccess = ranking => ({
  type: types.FETCH_USER_CREW_RANKING_SUCCESS,
  ranking
});

const fetchUserCrewRankingError = errorMessage => ({
  type: types.FETCH_USER_CREW_RANKING_ERROR,
  errorMessage
});

export const fetchUserCrewRanking = (userId:number, sex:string) => (dispatch:Function) => {
  dispatch(fetchUserCrewRankingStart(userId, sex));
  return requestWithAuth.get(`/api/users/${userId}/ranking?sex=${sex}`)
    .then(response => {
      dispatch(fetchUserCrewRankingSuccess(response.data));
    },
    error => {
      dispatch(fetchUserCrewRankingError(error));
    });
};

const fetchUserCrewRankingsStart = (sex) => ({
  type: types.FETCH_USER_CREW_RANKINGS_REQUEST,
  sex
});

const fetchUserCrewRankingsSuccess = rankings => ({
  type: types.FETCH_USER_CREW_RANKINGS_SUCCESS,
  rankings
});

const fetchUserCrewRankingsError = errorMessage => ({
  type: types.FETCH_USER_CREW_RANKINGS_ERROR,
  errorMessage
});

export const fetchUserCrewRankings = (sex:string) => (dispatch:Function) => {
  dispatch(fetchUserCrewRankingsStart(sex));
  return requestWithAuth.get(`/api/rankings?sex=${sex}`)
    .then(response => {
      dispatch(fetchUserCrewRankingsSuccess(response.data));
    },
    error => {
      dispatch(fetchUserCrewRankingsError(error));
    });
};

export const setBuyMember = (memberId:number, sex:string) => ({
  type: types.SET_BUY_MEMBER,
  memberId,
  sex
});

export const cancelBuyMember = () => ({
  type: types.CANCEL_BUY_MEMBER
});

export const buyRowerStart = (crewId:number, userCrewMemberId:number) => ({
  type: types.BUY_ROWER_REQUEST,
  crewId,
  userCrewMemberId
});

export const buyRowerSuccess = (userCrewMember:Object) => ({
  type: types.BUY_ROWER_SUCCESS,
  userCrewMember
});

export const buyRowerError = (errorMessage:string) => ({
  type: types.BUY_ROWER_ERROR,
  errorMessage
});

export const doBuyRower = (crewId:number, userCrewMemberId:number) => (dispatch:Function) => {
  dispatch(buyRowerStart(crewId, userCrewMemberId));
  return requestWithAuth.post('/api/buy', {crewId, userCrewMemberId})
    .then(response => {
      dispatch(buyRowerSuccess(response.data));
      dispatch(fetchCrew(crewId));
      dispatch(fetchUserCrewRanking(response.data.userId, response.data.sex));
    },
    error => {
      dispatch(buyRowerError(error));
    });
};

const sellRowerStart = (crewId:number, userCrewMemberId:number) => ({
  type: types.SELL_ROWER_REQUEST,
  crewId,
  userCrewMemberId
});

const sellRowerSuccess = (userCrewMember:number) => ({
  type: types.SELL_ROWER_SUCCESS,
  userCrewMember
});

const sellRowerError = (errorMessage:number) => ({
  type: types.SELL_ROWER_ERROR,
  errorMessage
});

export const doSellRower = (crewId:number, userCrewMemberId:number) => (dispatch:Function) => {
  dispatch(sellRowerStart(crewId, userCrewMemberId));
  return requestWithAuth.post('/api/sell', {crewId, userCrewMemberId})
    .then(response => {
      dispatch(sellRowerSuccess(response.data));
      dispatch(fetchCrew(crewId));
      dispatch(fetchUserCrewRanking(response.data.userId, response.data.sex));
    },
    error => {
      dispatch(sellRowerError(error));
    });
};

const fetchUserCrewPriceHistoryStart = (userId:number, sex:string) => ({
  type: types.FETCH_USER_CREW_PRICE_HISTORY_REQUEST,
  userId,
  sex
});

const fetchUserCrewPriceHistorySuccess = (userCrewPriceHistories:Array<Object>) => ({
  type: types.FETCH_USER_CREW_PRICE_HISTORY_SUCCESS,
  userCrewPriceHistories
});

const fetchUserCrewPriceHistoryError = (errorMessage:string) => ({
  type: types.FETCH_USER_CREW_PRICE_HISTORY_ERROR,
  errorMessage
});

export const fetchUserCrewPriceHistory = (userId:number, sex:string) => (dispatch:Function) => {
  dispatch(fetchUserCrewPriceHistoryStart(userId, sex));
  return requestWithAuth.get(`/api/users/${userId}/price-history?sex=${sex}`)
    .then(response => {
      dispatch(fetchUserCrewPriceHistorySuccess(response.data));
    },
    error => {
      dispatch(fetchUserCrewPriceHistoryError(error));
    });
};
