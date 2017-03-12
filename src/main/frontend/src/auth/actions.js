import request from 'axios';
import { push } from 'react-router-redux';
import requestWithAuth, { getToken } from './authorisedAxios';
import * as types from '../actionTypes';
import { tokenName } from '../constants/config';

// Action Creators
const beginLogin = login => ({
  type: types.LOGIN_REQUEST,
  login
});

const loginSuccess = (token) => {
  localStorage.setItem(tokenName, token);
  return {
    type: types.LOGIN_SUCCESS,
    token
  };
};

const loginError = (message) => ({
  type: types.LOGIN_ERROR,
  errorMessage: message || 'Failed to log in'
});

// Sign Up Action Creators
const signUpError = (message) => ({
  type: types.SIGNUP_ERROR,
  errorMessage: message || 'Sign up failed'
});

const beginSignUp = () => ({ type: types.SIGNUP_REQUEST });

const signUpSuccess = message => ({
  type: types.SIGNUP_SUCCESS,
  message: message || 'Registered successfully'
});

// Log Out Action Creators
const beginLogout = () => ({ type: types.LOGOUT_REQUEST });

const logoutSuccess = () => ({ type: types.LOGOUT_SUCCESS, message: 'Logged out' });

const logoutError = error => ({ type: types.LOGOUT_ERROR, errorMessage: error || 'Failed to log out' });

// Current user details action Creators
const requestCurrentUser = () => ({
  type: types.FETCH_CURRENT_USER_REQUEST
});

const receiveCurrentUser = (response) => ({
  type: types.FETCH_CURRENT_USER_SUCCESS,
  user: response.data
});

const errorCurrentUser = (error) => ({
  type: types.FETCH_CURRENT_USER_ERROR,
  errorMessage: error.message || 'Failed to fetch current user'
});

export const login = (loginDetails) => (dispatch) => {
  dispatch(beginLogin(loginDetails));
  return request.post('/api/authenticate', loginDetails)
    .then(response => {
      if (response.status === 200) {
        dispatch(loginSuccess(response.data.id_token));
      } else {
        dispatch(loginError('Log in failed'));
      }
    })
    .catch(err => {
      dispatch(loginError(err.response && err.response.data && err.response.data.message));
    });
};

export const signUp = (loginDetails) => (dispatch) => {
  dispatch(beginSignUp(loginDetails));
  return request.post('/api/register', loginDetails)
    .then(response => {
        if (response.status === 201) {
          dispatch(signUpSuccess(response.data.message));
          // TODO - redirect
          dispatch(push('/'));
        } else {
          dispatch(signUpError('Oops! Something went wrong'));
        }
      })
      .catch(err => {
        dispatch(signUpError(err.response && err.response.data && err.response.data.message));
      });
};

export const logOut = () => dispatch => {
  dispatch(beginLogout());

  localStorage.removeItem('token');
  dispatch(logoutSuccess());

  // somewhere presumably we could get a log out error?
};

export const fetchCurrentUser = () => (dispatch) => {
  const token = getToken();
  // if (token === undefined || token === null) {
  //   return dispatch(errorCurrentUser('Not logged in'));
  //   // return null;
  // }
  dispatch(requestCurrentUser());
  return requestWithAuth.get('/api/account')
    .then(response => {
      dispatch(receiveCurrentUser(response));
    }, error => {
      localStorage.removeItem(tokenName);
      dispatch(errorCurrentUser(error));
    });
};
