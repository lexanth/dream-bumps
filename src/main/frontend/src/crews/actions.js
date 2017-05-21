// @flow
import requestWithAuth from '../auth/authorisedAxios';
import * as types from '../actionTypes';

const fetchCrewStart = id => ({
  type: types.FETCH_CREW_REQUEST,
  id
});

const fetchCrewSuccess = crew => ({
  type: types.FETCH_CREW_SUCCESS,
  crew
});

const fetchCrewError = errorMessage => ({
  type: types.FETCH_CREW_ERROR,
  errorMessage
});


export const fetchCrew = (crewId: number) => (dispatch: Function) => {
  dispatch(fetchCrewStart(crewId));
  return requestWithAuth.get(`/api/crews/${crewId}`)
    .then(response => {
      dispatch(fetchCrewSuccess(response.data));
    },
    error => {
      dispatch(fetchCrewError(error));
    });
};

const fetchCrewsStart = sex => ({
  type: types.FETCH_CREWS_REQUEST,
  sex
});

const fetchCrewsSuccess = crews => ({
  type: types.FETCH_CREWS_SUCCESS,
  crews
});

const fetchCrewsError = errorMessage => ({
  type: types.FETCH_CREWS_ERROR,
  errorMessage
});


export const fetchCrews = (sex: string) => (dispatch: Function) => {
  dispatch(fetchCrewsStart(sex));
  return requestWithAuth.get(`/api/crews?sex=${sex}`)
    .then(response => {
      dispatch(fetchCrewsSuccess(response.data));
    },
    error => {
      dispatch(fetchCrewsError(error));
    });
};

const fetchCrewMembersStart = id => ({
  type: types.FETCH_CREW_MEMBERS_REQUEST,
  id
});

const fetchCrewMembersSuccess = crewMembers => ({
  type: types.FETCH_CREW_MEMBERS_SUCCESS,
  crewMembers
});

const fetchCrewMembersError = errorMessage => ({
  type: types.FETCH_CREW_MEMBERS_ERROR,
  errorMessage
});


export const fetchCrewMembers = (crewId: number) => (dispatch: Function) => {
  dispatch(fetchCrewMembersStart(crewId));
  return requestWithAuth.get(`/api/crews/${crewId}/members`)
    .then(response => {
      dispatch(fetchCrewMembersSuccess(response.data));
    },
    error => {
      dispatch(fetchCrewMembersError(error));
    });
};

const fetchCrewPriceHistoryStart = id => ({
  type: types.FETCH_CREW_PRICE_HISTORY_REQUEST,
  id
});

const fetchCrewPriceHistorySuccess = priceHistories => ({
  type: types.FETCH_CREW_PRICE_HISTORY_SUCCESS,
  priceHistories
});

const fetchCrewPriceHistoryError = errorMessage => ({
  type: types.FETCH_CREW_PRICE_HISTORY_ERROR,
  errorMessage
});

export const fetchCrewPriceHistory = (crewId: number) => (dispatch: Function) => {
  dispatch(fetchCrewPriceHistoryStart(crewId));
  return requestWithAuth.get(`/api/crews/${crewId}/price-history`)
    .then(response => {
      dispatch(fetchCrewPriceHistorySuccess(response.data));
    },
    error => {
      dispatch(fetchCrewPriceHistoryError(error));
    });
};

export const updateCrew = (crew: Object) => (dispatch: Function) => {
  console.log(crew);
}

const updateCrewMembersStart = members => ({
  type: types.UPDATE_CREW_MEMBERS_REQUEST,
  members
});

const updateCrewMembersSuccess = crewMembers => ({
  type: types.UPDATE_CREW_MEMBERS_SUCCESS,
  crewMembers
});

const updateCrewMembersError = errorMessage => ({
  type: types.UPDATE_CREW_MEMBERS_ERROR,
  errorMessage: errorMessage || 'Error updating crew members'
});


export const updateCrewMembers = (crew:Object) => (dispatch: Function) => {
  dispatch(updateCrewMembersStart(crew.members));
  return requestWithAuth.put(`/api/crews/${crew.id}/members`, crew.members)
    .then(response => {
      dispatch(updateCrewMembersSuccess(response.data));
    },
    error => {
      dispatch(updateCrewMembersError(error));
    });
};
