import React, {PropTypes} from 'react';
import {Grid, Cell} from 'material-grid/dist';

import CrewMemberList from './CrewMemberList';
import CrewDetails from './CrewDetails';
import CrewPriceHistory from './CrewPriceHistory';

const CrewPage = ({ params }) => (
  <Grid>
    <Cell col={6}>
      <CrewDetails
        crewId={params.crewId}
      />
    </Cell>
    <Cell col={6}>
      <CrewMemberList
        crewId={params.crewId}
      />
    </Cell>
    <Cell col={12}>
      <CrewPriceHistory
        crewId={params.crewId}
      />
    </Cell>
  </Grid>
);

CrewPage.propTypes = {
  params: PropTypes.shape({
    crewId: PropTypes.string
  })
};

export default CrewPage;
