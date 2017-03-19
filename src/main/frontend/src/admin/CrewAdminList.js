import React, {PropTypes} from 'react';
import {Grid, Cell} from 'material-grid/dist';
import RaisedButton from 'material-ui/RaisedButton';
import { Link } from 'react-router';
import { connect } from 'react-redux';

import {getCrewsForSex} from '../rootReducer';

const CrewAdminList = ({crews}) => (
  <Grid>
    {crews.map(crew =>
      <Cell key={crew.id} col={4}>
        <RaisedButton label={crew.name} containerElement={<Link to={`/admin/crews/${crew.id}`} />} />
      </Cell>
    )}
  </Grid>
)

const mapStateToProps = (state, ownProps) => ({
  crews: getCrewsForSex(state)(ownProps.sex)
});

export default connect(mapStateToProps)(CrewAdminList);
