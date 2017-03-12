import React from 'react';
import { Route, IndexRoute } from 'react-router';
import { routerActions } from 'react-router-redux';
import { UserAuthWrapper } from 'redux-auth-wrapper';

import App from './app/App';
import HomePage from './app/HomePage';
import NotFoundPage from './app/NotFoundPage';
import DocsPage from './docs/DocsPage';
import Login from './auth/Login';
import Logout from './auth/Logout';
import Register from './auth/Register';
import CrewPage from './crews/CrewPage';
import BunglinesPage from './bunglines/BunglinesPage';
import AdminPage from './admin/AdminPage';
import UserAdminPage from './admin/UserAdminPage';
import UserList from './admin/UserList';
import AdminEditUser from './admin/AdminEditUser';

export default () => {

  const UserIsAuthenticated = UserAuthWrapper({
    authSelector: state => state.auth,
    redirectAction: routerActions.replace,
    wrapperDisplayName: 'UserIsAuthenticated',
    predicate: user => user.token !== null
  });

  const UserIsNotAuthenticated = UserAuthWrapper({
    authSelector: state => state.auth,
    redirectAction: routerActions.replace,
    wrapperDisplayName: 'UserIsNotAuthenticated',
    // Want to redirect the user when they are done loading and authenticated
    predicate: auth => auth.token === null && auth.loading === false,
    failureRedirectPath: (state, ownProps) => ownProps.location.query.redirect || '/',
    allowRedirectBack: false
  });

  return (
    <Route path="/" component={App}>
      <IndexRoute component={HomePage}/>
      <Route path="docs" component={DocsPage} />
      <Route path="login" component={UserIsNotAuthenticated(Login)} />
      <Route path="logout" component={Logout} />
      <Route path="register" component={UserIsNotAuthenticated(Register)} />
      <Route path="bunglines" component={BunglinesPage} />
      <Route path="crews/:crewId" component={CrewPage} />
      <Route path="admin" component={AdminPage} />
      <Route path="admin/users" component={UserAdminPage}>
        <IndexRoute component={UserList} />
        <Route path=":userId" component={AdminEditUser} />
      </Route>
      <Route path="*" component={NotFoundPage}/>
    </Route>
  );
};
