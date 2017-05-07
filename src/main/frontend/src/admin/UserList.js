import React, {PropTypes} from 'react';
import {connect} from 'react-redux';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from 'material-ui/Table';
import RaisedButton from 'material-ui/RaisedButton';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import {Link} from 'react-router';
import Checkbox from 'material-ui/Checkbox';
import EditorModeEdit from 'material-ui/svg-icons/editor/mode-edit';

import {getAllUsers} from '../rootReducer';

const UserList = ({users}) => (

  <Card>
    <CardTitle title="Users"/>
    <CardText>
      <Table selectable={false}>
        <TableHeader adjustForCheckbox={false} displaySelectAll={false}>
          <TableRow>
            <TableHeaderColumn>ID</TableHeaderColumn>
            <TableHeaderColumn>Login</TableHeaderColumn>
            <TableHeaderColumn>First Name</TableHeaderColumn>
            <TableHeaderColumn>Last Name</TableHeaderColumn>
            <TableHeaderColumn>Activated</TableHeaderColumn>
            <TableHeaderColumn></TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody
          displayRowCheckbox={false}
        >
          {users.map(user => (
            <TableRow key={user.id}>
              <TableRowColumn>{user.id}</TableRowColumn>
              <TableRowColumn>{user.login}</TableRowColumn>
              <TableRowColumn>{user.firstName}</TableRowColumn>
              <TableRowColumn>{user.lastName}</TableRowColumn>
              <TableRowColumn><Checkbox checked={user.activated} /></TableRowColumn>
              <TableRowColumn><RaisedButton icon={<EditorModeEdit />} containerElement={<Link to={`/admin/users/${user.id}`} />} /></TableRowColumn>
            </TableRow>
          ))}
        </TableBody>
      </Table>

    </CardText>
  </Card>
);

UserList.propTypes = {
  users: PropTypes.array
}

const mapStateToProps = state => ({users: getAllUsers(state)});

export {UserList};

export default connect(mapStateToProps)(UserList);
