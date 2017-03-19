import React, {PropTypes} from 'react';
import {Cell, Grid} from 'material-grid/dist';

import AdminCrewDetailsEdit from './AdminCrewDetailsEdit';
import AdminCrewMemberEdit from './AdminCrewMemberEdit';

const AdminCrewEdit = ({params}) => (
  <Grid>
    <Cell col="6">
      <AdminCrewDetailsEdit crewId={params.crewId}/>
    </Cell>
    <Cell col="6">
      <AdminCrewMemberEdit crewId={params.crewId}/>
    </Cell>
  </Grid>
);

AdminCrewEdit.propTypes = {
  params: PropTypes.object
}
// todo - activated and authorities


export default AdminCrewEdit;
