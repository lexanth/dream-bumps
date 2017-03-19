import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';

import { fetchCrews } from '../crews/actions';
/**
 * UserAdminPage
 */
class CrewAdminPage extends Component {
  componentWillMount() {
    this.props.fetchCrews('male');
    this.props.fetchCrews('female');
  }
  render() {
    return (
      <div>
        {this.props.children}
      </div>
    );
  }
}

CrewAdminPage.propTypes = {
  fetchCrews: PropTypes.func,
  children: PropTypes.node
}

export default connect(null, { fetchCrews })(CrewAdminPage);
