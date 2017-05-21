// @flow
import requestWithAuth from '../auth/authorisedAxios';
import * as types from '../actionTypes';

const fetchStatusStart = () => ({
  type: types.FETCH_STATUS_REQUEST
});

const fetchStatusSuccess = status => ({
  type: types.FETCH_STATUS_SUCCESS,
  status
});

const fetchStatusError = errorMessage => ({
  type: types.FETCH_STATUS_ERROR,
  errorMessage
});

export const fetchStatus = () => (dispatch: Function) => {
  dispatch(fetchStatusStart());
  return requestWithAuth.get('/api/market-status')
    .then(response => {
      dispatch(fetchStatusSuccess(response.data));
    },
    error => {
      dispatch(fetchStatusError(error));
    });
};

const updateMarketStatusStart = status => ({
  type: types.UPDATE_STATUS_REQUEST,
  status
});

const updateMarketStatusSuccess = status => ({
  type: types.UPDATE_STATUS_SUCCESS,
  status
});

const updateMarketStatusError = errorMessage => ({
  type: types.UPDATE_STATUS_ERROR,
  errorMessage: errorMessage || 'Error updating market status'
});

export const updateMarketStatus = (status: {day: number, open: boolean}) => (dispatch: Function) => {
  dispatch(updateMarketStatusStart(status));
  // Update is actually a post
  return requestWithAuth.post(`/api/market-status`, status)
    .then(response => {
      dispatch(updateMarketStatusSuccess(response.data));
    },
    error => {
      dispatch(updateMarketStatusError(error));
    });
}
