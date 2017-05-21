// @flow
import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Card, CardTitle, CardText } from 'material-ui/Card';
import { Table, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import { getUser } from '../rootReducer';
import { fetchUsers } from '../admin/actions';
import { getCollegeName } from '../utils/colleges';

/**
 * CurrentUserCrew
 */
class UserDetail extends Component {
  componentDidMount() {
    this.props.fetchUsers();
  }

  render() {
    return (
      <Card>
        <CardTitle title="User Details" />
        <CardText>
          <Table selectable={false}>
            <TableBody displayRowCheckbox={false}>
              <TableRow>
                <TableRowColumn>Username</TableRowColumn>
                <TableRowColumn>{this.props.user.firstName}</TableRowColumn>
              </TableRow>
              <TableRow>
                <TableRowColumn>College</TableRowColumn>
                <TableRowColumn>
                  {getCollegeName(this.props.user.college)}
                </TableRowColumn>
              </TableRow>
            </TableBody>
          </Table>
        </CardText>
      </Card>
    );
  }
}

UserDetail.propTypes = {
  userId: PropTypes.number,
  fetchUser: PropTypes.func,
  user: PropTypes.shape({
    firstName: PropTypes.string,
    college: PropTypes.string
  })
};

export const mapStateToProps = (
  state: Object,
  ownProps: { userId: number, sex: string }
) => ({
  user: getUser(state)(ownProps.userId)
});

export { UserDetail };

export default connect(mapStateToProps, { fetchUsers })(UserDetail);
