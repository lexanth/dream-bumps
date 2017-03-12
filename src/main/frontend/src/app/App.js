import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
// import { Grid } from 'react-flexbox-grid';
// import { Grid } from 'material-grid/dist';

// import { Meta } from '../components/Meta';
import Navigation from './Navigation';
// import MySnackbar from '../messages/MySnackbar';
import { fetchCurrentUser } from '../auth/actions';
import { fetchConfig } from '../config/actions';
import { isLoading } from '../rootReducer';

// require('../../style/style.css');

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

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
  }
  render() {
    return (
      <MuiThemeProvider>
        <div>
          {/* <Meta /> */}
          <Navigation />
          <div>
            {this.props.children}
            {/* <MySnackbar /> */}
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

App.propTypes = {
  children: PropTypes.object,
  fetchCurrentUser: PropTypes.func,
  loading: PropTypes.bool,
  fetchConfig: PropTypes.func
};

const mapStateToProps = (state) => ({
  loading: isLoading(state)
});

export default connect(mapStateToProps, { fetchCurrentUser, fetchConfig })(App);
