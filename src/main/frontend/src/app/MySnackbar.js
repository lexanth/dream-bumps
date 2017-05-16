import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import Snackbar from 'material-ui/Snackbar';
import { dismissMessage } from '../actionTypes';

const MySnackbar = ({ message, onDismissMessage }) => (
  <Snackbar
    open={message !== ''}
    message={message}
    onRequestClose={onDismissMessage}
  />
);

const mapStateToProps = (state) => (
  { message: state.app }
);

MySnackbar.propTypes = {
  message: PropTypes.string,
  onDismissMessage: PropTypes.func
};

export default connect(mapStateToProps, { onDismissMessage: dismissMessage })(MySnackbar);
