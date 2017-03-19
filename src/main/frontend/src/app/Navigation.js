import React, { PropTypes } from 'react';
import { IndexLink, Link } from 'react-router';
import { connect } from 'react-redux';
import { Toolbar, ToolbarGroup } from 'material-ui/Toolbar';
import FlatButton from 'material-ui/FlatButton';
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import Divider from 'material-ui/Divider';

import { getCurrentUser } from '../rootReducer';

const Navigation = ({ authenticated }) => (
      <Toolbar style={{ backgroundColor: 'rgb(0, 188, 212)', color: '#fff' }}>
        <ToolbarGroup>
          <FlatButton
            containerElement={<IndexLink to="/" onlyActiveOnIndex />} label="Dream Bumps"
          />
        </ToolbarGroup>
        <ToolbarGroup lastChild>
            { authenticated ?
              <FlatButton
                containerElement={<Link to="/logout" />} label="Log out"
              />
               :
              <FlatButton
                containerElement={<Link to="/login" />} label="Log in"
              />
            }
            <FlatButton containerElement={<Link to="/docs" />} label="API" />

            <DropDownMenu value={1} style={{textTransform: 'uppercase', top: '-4px'}}>
              <MenuItem value={1} label="Manage" primaryText="Users" containerElement={<Link to="/admin/users" />} />
              <MenuItem value={2} label="Manage" primaryText="Crews" containerElement={<Link to="/admin/crews" />}  />
              <MenuItem value={3} label="Manage" primaryText="Market Status" containerElement={<Link to="/admin/market" />}  />
              <Divider />
              <MenuItem value={4} label="Manage" primaryText="Bumps - Men" containerElement={<Link to="/admin/bumps/male" />}  />
              <MenuItem value={5} label="Manage" primaryText="Bumps - Women" containerElement={<Link to="/admin/bumps/female" />}  />
            </DropDownMenu>

            <FlatButton containerElement={<Link to="/bunglines" />} label="Bunglines" />
            <FlatButton containerElement={<Link to="/rankings" />} label="Rankings" />

        </ToolbarGroup>
      </Toolbar>
    );

Navigation.propTypes = {
  authenticated: PropTypes.bool
};

const mapStateToProps = state => ({
  authenticated: state.auth.authenticated
});

export default connect(mapStateToProps)(Navigation);
