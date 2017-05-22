// @flow
import React, { PropTypes } from 'react';
import { IndexLink, Link } from 'react-router';
import { connect } from 'react-redux';
import { Toolbar, ToolbarGroup } from 'material-ui/Toolbar';
import FlatButton from 'material-ui/FlatButton';
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import Divider from 'material-ui/Divider';
import MediaQuery from 'react-responsive';
import IconMenu from 'material-ui/IconMenu';
import IconButton from 'material-ui/IconButton';
import FontIcon from 'material-ui/FontIcon';
import NavigationExpandMoreIcon
  from 'material-ui/svg-icons/navigation/expand-more';

import { isAdmin } from '../rootReducer';

export const Navigation = ({
  authenticated,
  isAdmin
}: {
  authenticated: boolean,
  isAdmin: boolean
}) => (
  <Toolbar>
    <ToolbarGroup>
      <FlatButton
        containerElement={<IndexLink to="/" onlyActiveOnIndex />}
        label="Dream Bumps"
        style={{ color: '#fff' }}
      />
    </ToolbarGroup>
    <MediaQuery minWidth={800}>
      <ToolbarGroup lastChild>
        {authenticated
          ? <FlatButton
              containerElement={<Link to="/logout" />}
              label="Log out"
              style={{ color: '#fff' }}
            />
          : <FlatButton
              containerElement={<Link to="/login" />}
              label="Log in"
              style={{ color: '#fff' }}
            />}
        {isAdmin &&
          <FlatButton
            containerElement={<Link to="/docs" />}
            label="API"
            style={{ color: '#fff' }}
          />}
        {isAdmin &&
          <DropDownMenu
            value={1}
            style={{ textTransform: 'uppercase', top: '-4px' }}
            labelStyle={{ color: '#fff' }}
          >
            <MenuItem
              value={1}
              label="Manage"
              primaryText="Users"
              containerElement={<Link to="/admin/users" />}
            />
            <MenuItem
              value={2}
              label="Manage"
              primaryText="Crews"
              containerElement={<Link to="/admin/crews" />}
            />
            <MenuItem
              value={3}
              label="Manage"
              primaryText="Market Status"
              containerElement={<Link to="/admin/market" />}
            />
            <Divider />
            <MenuItem
              value={4}
              label="Manage"
              primaryText="Bumps - Men"
              containerElement={<Link to="/admin/bumps/male" />}
            />
            <MenuItem
              value={5}
              label="Manage"
              primaryText="Bumps - Women"
              containerElement={<Link to="/admin/bumps/female" />}
            />
          </DropDownMenu>}

        <FlatButton
          containerElement={<Link to="/bunglines" />}
          label="Bunglines"
          style={{ color: '#fff' }}
        />
        <FlatButton
          containerElement={<Link to="/rankings" />}
          label="Rankings"
          style={{ color: '#fff' }}
        />

      </ToolbarGroup>
    </MediaQuery>
    <MediaQuery maxWidth={799}>
      <IconMenu
        iconButtonElement={
          <IconButton touch={true}>
            <NavigationExpandMoreIcon color="#fff" />
          </IconButton>
        }
        anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
        style={{ marginTop: '4px' }}
      >
        {authenticated
          ? <MenuItem
              containerElement={<Link to="/logout" />}
              primaryText="Log out"
            />
          : <MenuItem
              containerElement={<Link to="/login" />}
              primaryText="Log in"
            />}
        {isAdmin && <Divider />}
        {isAdmin &&
          <MenuItem primaryText="API" containerElement={<Link to="/docs" />} />}
        {isAdmin && <Divider />}
        {isAdmin &&
          <MenuItem
            primaryText="Users"
            containerElement={<Link to="/admin/users" />}
          />}
        {isAdmin &&
          <MenuItem
            primaryText="Crews"
            containerElement={<Link to="/admin/crews" />}
          />}
        {isAdmin &&
          <MenuItem
            primaryText="Market Status"
            containerElement={<Link to="/admin/market" />}
          />}
        {isAdmin && <Divider />}
        {isAdmin &&
          <MenuItem
            primaryText="Bumps - Men"
            containerElement={<Link to="/admin/bumps/male" />}
          />}
        {isAdmin &&
          <MenuItem
            primaryText="Bumps - Women"
            containerElement={<Link to="/admin/bumps/female" />}
          />}
        {isAdmin && <Divider />}
        <MenuItem
          containerElement={<Link to="/bunglines" />}
          primaryText="Bunglines"
        />
        <MenuItem
          containerElement={<Link to="/rankings" />}
          primaryText="Rankings"
        />
      </IconMenu>
    </MediaQuery>
  </Toolbar>
);

Navigation.propTypes = {
  authenticated: PropTypes.bool,
  isAdmin: PropTypes.bool
};

export const mapStateToProps = (state: Object) => ({
  authenticated: state.auth.authenticated,
  isAdmin: isAdmin(state)()
});

export default connect(mapStateToProps)(Navigation);
