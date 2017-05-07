import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import {Grid, Cell} from 'material-grid/dist';

import { fetchUsers } from './actions';
/**
 * UserAdminPage
 */
class UserAdminPage extends Component {
  componentWillMount() {
    this.props.fetchUsers();
  }
  render() {
    return (
      <Grid>
        <Cell col={12}>
          {this.props.children}
        </Cell>
      </Grid>
    );
  }
}

UserAdminPage.propTypes = {
  fetchUsers: PropTypes.func,
  children: PropTypes.node
}

export {UserAdminPage};

export default connect(null, { fetchUsers })(UserAdminPage);
