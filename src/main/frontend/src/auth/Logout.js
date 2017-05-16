// @flow
import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import { logOut } from './actions';

class Logout extends Component {

  componentWillMount() {
    this.props.onLogOut();
  }

  componentDidMount() {
    this.props.router.replace('/');
  }

  render() {
    return <div>Logging out...</div>;
  }
}

Logout.propTypes = {
  onLogOut: PropTypes.func.isRequired,
  router: PropTypes.shape({replace: PropTypes.func}).isRequired
};

export default withRouter(connect(null, { onLogOut: logOut })(Logout));
