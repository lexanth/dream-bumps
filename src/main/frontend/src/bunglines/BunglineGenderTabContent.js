// @flow
import React, {PropTypes} from 'react';
import {Grid, Cell} from 'material-grid/dist';
import { connect } from 'react-redux';

import CrewList from './CrewList';
import CurrentUserCrew from '../usercrews/CurrentUserCrew';

export const BunglineGenderTabContent = ({ sex, authenticated }:{sex:string, authenticated:boolean}) => (
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

export const mapStateToProps = (state: Object) => ({
  authenticated: state.auth.authenticated
});

export default connect(mapStateToProps)(BunglineGenderTabContent);
