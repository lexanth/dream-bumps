// @flow
import requestWithAuth from '../auth/authorisedAxios';
import * as types from '../actionTypes';

const fetchUsersStart = () => ({
  type: types.FETCH_USERS_REQUEST
});

const fetchUsersSuccess = users => ({
  type: types.FETCH_USERS_SUCCESS,
  users
});

const fetchUsersError = errorMessage => ({
  type: types.FETCH_USERS_ERROR,
  errorMessage
});

export const fetchUsers = () => dispatch => {
  dispatch(fetchUsersStart());
  return requestWithAuth.get(`/api/users`)
    .then(response => {
      dispatch(fetchUsersSuccess(response.data));
    },
    error => {
      dispatch(fetchUsersError(error));
    });
}

const updateUserStart = (user) => ({
  type: types.UPDATE_USER_REQUEST,
  user
});

const updateUserSuccess = user => ({
  type: types.UPDATE_USER_SUCCESS,
  user
});

const updateUserError = errorMessage => ({
  type: types.UPDATE_USER_ERROR,
  errorMessage
});

export const updateUser = user => dispatch => {
  dispatch(updateUserStart(user));
  return requestWithAuth.put(`/api/users`, user)
    .then(response => {
      dispatch(updateUserSuccess(response.data));
    },
    error => {
      dispatch(updateUserError(error));
    });
}

const uploadBumpsStart = (bumps) => ({
  type: types.UPLOAD_BUMPS_REQUEST,
  bumps
});

const uploadBumpsSuccess = bumps => ({
  type: types.UPLOAD_BUMPS_SUCCESS,
  bumps
});

const uploadBumpsError = errorMessage => ({
  type: types.UPLOAD_BUMPS_ERROR,
  errorMessage
});

export const uploadBumps = (crews, day) => dispatch => {
  const bumps = crews.map(crew => ({
    day,
    crewId: crew.id,
    position: crew.position,
    bumps: crew.bumps
  }));
  dispatch(uploadBumpsStart(bumps));
  return requestWithAuth.post(`/api/bumps`, bumps)
    .then(response => {
      dispatch(uploadBumpsSuccess(response.data));
    },
    error => {
      dispatch(uploadBumpsError(error));
    });
};
