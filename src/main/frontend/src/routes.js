// @flow
import React from 'react';
import { Route, IndexRoute } from 'react-router';
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
//import AdminPage from './admin/AdminPage';
import UserAdminPage from './admin/UserAdminPage';
import CrewAdminPage from './admin/CrewAdminPage';
import UserList from './admin/UserList';
import AdminEditUser from './admin/AdminEditUser';
import CrewAdminLists from './admin/CrewAdminLists';
import AdminCrewEdit from './admin/AdminCrewEdit';
import BumpsAdminPage from './admin/BumpsAdminPage';
import MarketStatusPage from './admin/MarketStatusPage';
import RankingsPage from './usercrews/RankingsPage';
import UserPage from './usercrews/UserPage';

export default () => {

  const UserIsAuthenticated = UserAuthWrapper({
    authSelector: state => state.auth,
    wrapperDisplayName: 'UserIsAuthenticated',
    predicate: auth => auth.authenticated
  });

  const UserIsAdmin = UserAuthWrapper({
    authSelector: state => state.auth,
    wrapperDisplayName: 'UserIsAdmin',
    failureRedirectPath: '/',
    allowRedirectBack: false,
    predicate: auth => auth.currentUser && auth.currentUser.authorities && auth.currentUser.authorities.indexOf('ROLE_ADMIN') > -1
  })

  const UserIsNotAuthenticated = UserAuthWrapper({
    authSelector: state => state.auth,
    wrapperDisplayName: 'UserIsNotAuthenticated',
    // Want to redirect the user when they are done loading and authenticated
    predicate: auth => !auth.authenticated && auth.loading === false,
    failureRedirectPath: '/',
    allowRedirectBack: false
  });

  return (
    <Route path="/" component={App}>
      <IndexRoute component={HomePage}/>
      <Route path="docs" component={UserIsAdmin(DocsPage)} />
      <Route path="login" component={UserIsNotAuthenticated(Login)} />
      <Route path="logout" component={Logout} />
      <Route path="register" component={UserIsNotAuthenticated(Register)} />
      <Route path="bunglines" component={BunglinesPage} />
      <Route path="crews/:crewId" component={CrewPage} />
      <Route path="admin/users" component={UserIsAdmin(UserAdminPage)}>
        <IndexRoute component={UserList} />
        <Route path=":userId" component={AdminEditUser} />
      </Route>
      <Route path="admin/crews" component={UserIsAdmin(CrewAdminPage)}>
        <IndexRoute component={CrewAdminLists} />
        <Route path=":crewId" component={AdminCrewEdit} />
      </Route>
      <Route path="admin/bumps/:sex" component={UserIsAdmin(BumpsAdminPage)} />
      <Route path="admin/market" component={UserIsAdmin(MarketStatusPage)} />
      <Route path="rankings" component={RankingsPage} />
      <Route path="rankings/:userId" component={UserPage} />
      <Route path="*" component={NotFoundPage}/>
    </Route>
  );
};
