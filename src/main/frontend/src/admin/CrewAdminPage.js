// @flow
import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';

/**
 * UserAdminPage
 */
const CrewAdminPage = props => (
  <div>
    {props.children}
  </div>
);

CrewAdminPage.propTypes = {
  children: PropTypes.node
};

export { CrewAdminPage };

export default CrewAdminPage;
