// @flow
import React, {PropTypes, Component} from 'react';
import {Grid, Cell} from 'material-grid/dist';

import UserCrew from './UserCrew';
import UserCurrentScore from './UserCurrentScore';
import UserScoreHistory from './UserScoreHistory';

class UserGenderTabContent extends Component {
  render() {
    return (
      <Grid>
        <Cell col={6}>
          <UserCrew
            userId={this.props.userId}
            header="Current Lineup"
            canBuySell={false}
            sex={this.props.sex}
            showCashAndValue={false}
          />
        </Cell>
        <Cell col={6}>
          <UserCurrentScore
            userId={this.props.userId}
            sex={this.props.sex}
          />
          <UserScoreHistory
            userId={this.props.userId}
            sex={this.props.sex}
            style={{marginTop: '16px'}}
          />
        </Cell>
      </Grid>
    );
  }
}

UserGenderTabContent.propTypes = {
  userId: PropTypes.number,
  sex: PropTypes.string
}

export {UserGenderTabContent};

export default UserGenderTabContent;
