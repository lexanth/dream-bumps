// @flow
import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';

import Navigation from './Navigation';
import MySnackbar from './MySnackbar';
import { fetchCurrentUser } from '../auth/actions';
import { fetchConfig } from '../config/actions';
import { fetchStatus } from '../status/actions';
import { isLoading } from '../rootReducer';
import { fetchCrews, fetchAllMembers } from '../crews/actions';

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

const OXFORD_BLUE = '#002147';

const muiTheme = getMuiTheme({
  palette: {
    primary1Color: OXFORD_BLUE
  },
  toolbar: {
    backgroundColor: OXFORD_BLUE,
    color: '#fff'
  }
});

/*
 * React-router's <Router> component renders <Route>'s
 * and replaces `this.props.children` with the proper React Component.
 *
 * Please refer to `routes.jsx` for the route config.
 *
 * A better explanation of react-router is available here:
 * https://github.com/rackt/react-router/blob/latest/docs/Introduction.md
 */
class App extends Component {
  componentDidMount() {
    this.props.fetchCurrentUser();
    this.props.fetchConfig();
    this.props.fetchStatus();
    this.props.fetchCrews('male');
    this.props.fetchCrews('female');
    this.props.fetchAllMembers();
  }

  render() {
    return (
      <MuiThemeProvider muiTheme={muiTheme}>
        <div>
          {/* <Meta /> */}
          <Navigation />
          <div>
            {this.props.children}
            <MySnackbar />
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

App.propTypes = {
  children: PropTypes.node,
  fetchCurrentUser: PropTypes.func,
  loading: PropTypes.bool,
  fetchConfig: PropTypes.func,
  fetchStatus: PropTypes.func,
  fetchCrews: PropTypes.func,
  fetchAllMembers: PropTypes.func
};

const mapStateToProps = (state: Object) => ({
  loading: isLoading(state)
});

export default connect(mapStateToProps, {
  fetchCurrentUser,
  fetchConfig,
  fetchStatus,
  fetchCrews,
  fetchAllMembers
})(App);
