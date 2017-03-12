import requestWithAuth from '../auth/authorisedAxios';
import * as types from '../actionTypes';

const fetchConfigStart = () => ({
  type: types.FETCH_CONFIG_REQUEST
});

const fetchConfigSuccess = config => ({
  type: types.FETCH_CONFIG_SUCCESS,
  config
});

const fetchConfigError = errorMessage => ({
  type: types.FETCH_CONFIG_ERROR,
  errorMessage
});

export const fetchConfig = () => dispatch => {
  dispatch(fetchConfigStart());
  return requestWithAuth.get('/api/config')
    .then(response => {
      dispatch(fetchConfigSuccess(response.data));
    },
    error => {
      dispatch(fetchConfigError(error));
    });
};
