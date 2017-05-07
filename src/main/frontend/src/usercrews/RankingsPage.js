// @flow
import React, {Component, PropTypes} from 'react';
import { connect } from 'react-redux';
import {Tabs, Tab} from 'material-ui/Tabs';

import RankingGenderTabContent from './RankingGenderTabContent';
import { fetchUserCrewRankings } from './actions';
import { fetchUsers } from '../admin/actions';

class RankingsPage extends Component {
  componentDidMount() {
    this.props.fetchUserCrewRankings('male');
    this.props.fetchUserCrewRankings('female');
    this.props.fetchUsers();
  }

  render() {
    return (
      <Tabs>
        <Tab label="Combined">
          <RankingGenderTabContent sex="combined" />
        </Tab>
        <Tab label="Men">
          <RankingGenderTabContent sex="male" />
        </Tab>
        <Tab label="Women">
          <RankingGenderTabContent sex="female" />
        </Tab>
      </Tabs>
    );
  }
}

RankingsPage.propTypes = {
  fetchUsers: PropTypes.func,
  fetchUserCrewRankings: PropTypes.func
}

export {RankingsPage};

export default connect(null, {fetchUserCrewRankings, fetchUsers})(RankingsPage);
