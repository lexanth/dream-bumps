import React, {PropTypes} from 'react';
import {Grid, Cell} from 'material-grid/dist';
import { connect } from 'react-redux';

import CrewList from './CrewList';
import CurrentUserCrew from '../usercrews/CurrentUserCrew';

const BunglineGenderTabContent = ({ sex, authenticated }) => (
  <Grid>
    { authenticated &&
      <Cell col={4}>
        <CurrentUserCrew sex={sex} />
      </Cell>
    }
    <Cell col={8} offset={authenticated ? 0 : 2}>
      <CrewList sex={sex} />
    </Cell>
  </Grid>
);

BunglineGenderTabContent.propTypes = {
  sex: PropTypes.string,
  authenticated: PropTypes.bool
};

const mapStateToProps = state => ({
  authenticated: state.auth.authenticated
});

export default connect(mapStateToProps)(BunglineGenderTabContent);
// export default connect(null, { null })(BunglineGenderTabContent);
