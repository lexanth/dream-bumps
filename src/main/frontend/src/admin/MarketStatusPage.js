// @flow
import React, {PropTypes} from 'react';
import { connect } from 'react-redux';
import {Card, CardText} from 'material-ui/Card';
import {Grid, Cell} from 'material-grid/dist';
import RaisedButton from 'material-ui/RaisedButton';
import Timestamp from 'react-timestamp';

import { updateMarketStatus } from '../status/actions';
import { getCurrentStatus } from '../rootReducer';

class MarketStatusPage extends React.Component {
  onToggleStatusClicked() {
    this.props.updateMarketStatus({open: !this.props.status.open, day: this.props.status.day});
  }

  onNextDayClicked() {
    this.props.updateMarketStatus({open: this.props.status.open, day: this.props.status.day+1});
  }

  render() {
    return (
      <Grid>
        <Cell col={6}>
          <Card>
            <CardText>
              <h3>The market is currently </h3>
              <h1>{this.props.status.open ? 'Open': 'Closed'}</h1>
              <RaisedButton
                primary
                label={this.props.status.open ? 'Close Market':'Open Market'}
                onClick={this.onToggleStatusClicked.bind(this)}
              />
            </CardText>
          </Card>
        </Cell>
        <Cell col={6}>
          <Card>
            <CardText>
              <h3>The market is currently showing</h3>
              <h1>{this.props.status.day >= 4 ? 'Final Finish Order' : `Start Order for Day ${this.props.status.day+1}`}</h1>
              <RaisedButton
                primary
                disabled={this.props.day >= 4}
                label="Next Day"
                onClick={this.onNextDayClicked.bind(this)}
              />
              <p>This cannot be reversed easily. This will also crystallise the dividends for crews. Make sure you have entered all bumps before this.</p>
            </CardText>
          </Card>
        </Cell>
        <Cell col={6}>
          <Card>
            <CardText>
              <p>Market status last updated: <Timestamp time={this.props.status.dateTime} /></p>
            </CardText>
          </Card>
        </Cell>
      </Grid>
    );
  }
}

MarketStatusPage.propTypes = {
  status: PropTypes.object,
  updateMarketStatus: PropTypes.func,
  day: PropTypes.number
};

const mapStateToProps: Object = (state: Object) => ({
  status: getCurrentStatus(state)
});

export default connect(mapStateToProps, { updateMarketStatus })(MarketStatusPage);
