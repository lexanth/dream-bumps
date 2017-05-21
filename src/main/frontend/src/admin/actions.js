// @flow
import requestWithAuth from '../auth/authorisedAxios';
import * as types from '../actionTypes';

const fetchUsersStart = () => ({
  type: types.FETCH_USERS_REQUEST
});

const fetchUsersSuccess = (users: Array<Object>) => ({
  type: types.FETCH_USERS_SUCCESS,
  users
});

const fetchUsersError = (errorMessage: string) => ({
  type: types.FETCH_USERS_ERROR,
  errorMessage
});

export const fetchUsers = () => (dispatch: Function) => {
  dispatch(fetchUsersStart());
  return requestWithAuth.get(`/api/users`)
    .then(response => {
      dispatch(fetchUsersSuccess(response.data));
    },
    error => {
      dispatch(fetchUsersError(error));
    });
}

const updateUserStart = (user: Object) => ({
  type: types.UPDATE_USER_REQUEST,
  user
});

const updateUserSuccess = (user: Object) => ({
  type: types.UPDATE_USER_SUCCESS,
  user
});

const updateUserError = (errorMessage: string) => ({
  type: types.UPDATE_USER_ERROR,
  errorMessage: errorMessage || 'Error updating user'
});

export const updateUser = (user: Object) => (dispatch: Function) => {
  dispatch(updateUserStart(user));
  return requestWithAuth.put(`/api/users`, user)
    .then(response => {
      dispatch(updateUserSuccess(response.data));
    },
    error => {
      dispatch(updateUserError(error));
    });
}

const uploadBumpsStart = (bumps: Array<Object>) => ({
  type: types.UPLOAD_BUMPS_REQUEST,
  bumps
});

const uploadBumpsSuccess = (bumps: Array<Object>) => ({
  type: types.UPLOAD_BUMPS_SUCCESS,
  bumps
});

const uploadBumpsError = (errorMessage: string) => ({
  type: types.UPLOAD_BUMPS_ERROR,
  errorMessage: errorMessage || 'Bump upload error'
});

export const uploadBumps = (crews: Array<Object>, day: number) => (dispatch: Function) => {
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
