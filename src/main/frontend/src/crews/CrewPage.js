// @flow
import React, { PropTypes } from 'react';
import { Grid, Cell } from 'material-grid/dist';

import CrewMemberList from './CrewMemberList';
import CrewDetails from './CrewDetails';
import CrewPriceHistory from './CrewPriceHistory';

const CrewPage = ({ params }: { params: Object }) => (
  <Grid>
    <Cell col={6} tablet={12}>
      <CrewDetails crewId={params.crewId} />
    </Cell>
    <Cell col={6} tablet={12}>
      <CrewMemberList crewId={params.crewId} />
    </Cell>
    <Cell col={12} tablet={12}>
      <CrewPriceHistory crewId={params.crewId} />
    </Cell>
  </Grid>
);

CrewPage.propTypes = {
  params: PropTypes.shape({
    crewId: PropTypes.string
  })
};
export { CrewPage };

export default CrewPage;
