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

const saveUsersStart = (user) => ({
  type: types.SAVE_USERS_REQUEST,
  user
});

const saveUsersSuccess = user => ({
  type: types.SAVE_USERS_SUCCESS,
  user
});

const saveUsersError = errorMessage => ({
  type: types.SAVE_USERS_ERROR,
  errorMessage
});

export const saveUser = user => dispatch => {
  dispatch(saveUsersStart(user));
  return requestWithAuth.post(`/api/users`, user)
    .then(response => {
      dispatch(saveUsersSuccess(response.data));
    },
    error => {
      dispatch(saveUsersError(error));
    });
}
