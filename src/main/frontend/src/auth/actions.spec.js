

import * as types from '../actionTypes';
// describe('User Login', () => {
//     it('dispatches MANUAL_LOGIN_USER and LOGIN_SUCCESS_USER when Manual Login returns status of 200 and routes user to /', (done) => {
//       const expectedActions = [
//       {
//         type: types.LOGIN_REQUEST
//       },
//       {
//         type: types.LOGIN_SUCCESS,
//         message: response.data.message
//       },
//       {
//         payload: {
//           args: ['/'],
//           method: 'push'
//         },
//         type: '@@router/CALL_HISTORY_METHOD'
//       }];
//
//       sandbox.stub(axios, 'post').returns(Promise.resolve(response));
//
//       const store = mockStore(initialState);
//       store.dispatch(actions.manualLogin(data))
//         .then(() => {
//           expect(store.getActions()).toEqual(expectedActions);
//         }).then(done)
//         .catch(done);
//     });
//
//     it('dispatches MANUAL_LOGIN_USER and LOGIN_ERROR_USER when Manual Login returns status that is NOT 200', (done) => {
//       const expectedActions = [
//       {
//         type: types.MANUAL_LOGIN_USER
//       },
//       {
//         type: types.LOGIN_ERROR_USER,
//         message: errMsg.response.data.message
//       }];
//
//       sandbox.stub(axios, 'post').returns(Promise.reject(errMsg));
//
//       const store = mockStore(initialState);
//       store.dispatch(actions.manualLogin(data))
//         .then(() => {
//           expect(store.getActions()).toEqual(expectedActions);
//         }).then(done)
//         .catch(done);
//     });
//   });
