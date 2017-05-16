// @flow
import * as types from '../actionTypes';
import { tokenName } from '../constants/config';

const saveToken = (store:Object) => (next: Function) => (action:{type:string,token:string}) => {
  switch (action.type) {
    case types.LOGIN_SUCCESS:
      localStorage.setItem(tokenName, action.token);
      break;
    case types.LOGOUT_SUCCESS:
      localStorage.removeItem(tokenName);
      break;
    default:

  }
  return next(action);
}

export default saveToken;
