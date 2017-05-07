// import expect from 'expect';
// import deepFreeze from 'deep-freeze';

import reducer from './reducer';
// import * as types from '../actionTypes';
import LocalStorageMock from '../testutils/LocalStorageMock';

describe('Auth reducer', () => {
  beforeEach(() => {
    global.localStorage = new LocalStorageMock;
  });

  it('Should initially be unauthenticated and not loading', () => {
    expect(reducer(undefined, {})).toEqual({
      loading: false,
      authenticated: false,
      currentUser: {}
    });
  });
});
