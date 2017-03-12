import React, { PropTypes } from 'react';
import { IndexLink, Link } from 'react-router';
import { connect } from 'react-redux';
import { Toolbar, ToolbarGroup } from 'material-ui/Toolbar';
import FlatButton from 'material-ui/FlatButton';

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
            <FlatButton containerElement={<Link to="/admin" />} label="Manage" />
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
