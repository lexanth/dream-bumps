import React, {PropTypes} from 'react';
import {Grid, Cell} from 'material-grid/dist';

import CrewList from './CrewList';
import CurrentUserCrew from '../usercrews/CurrentUserCrew';

const BunglineGenderTabContent = ({ sex }) => (
  <Grid>
    <Cell col={4}>
      <CurrentUserCrew sex={sex} />
    </Cell>
    <Cell col={8}>
      <CrewList sex={sex} />
    </Cell>
  </Grid>
);

BunglineGenderTabContent.propTypes = {
  sex: PropTypes.string
};

export default BunglineGenderTabContent;
// export default connect(null, { null })(BunglineGenderTabContent);
