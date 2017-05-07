import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import { Line } from 'react-chartjs-2';

import { fetchUserCrewPriceHistory } from './actions';
import { getUserScoreHistory } from '../rootReducer';

const minPrice = scoreHistories => scoreHistories.reduce((prev, curr) => (prev < (curr.value + curr.cash) ? prev : (curr.value + curr.cash)), Number.POSITIVE_INFINITY);

const maxPrice = scoreHistories => scoreHistories.reduce((prev, curr) => (prev > (curr.value + curr.cash) ? prev : (curr.value + curr.cash)), 0);

/**
 * CrewPriceHistory
 */
class UserScoreHistory extends Component {
  componentWillMount() {
    if (this.props.userId) {
      this.props.fetchUserCrewPriceHistory(this.props.userId, this.props.sex);
    }
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.userId && nextProps.userId !== this.props.userId) {
      this.props.fetchUserCrewPriceHistory(nextProps.userId, nextProps.sex);
    }
  }

  render() {
    return (
      <Card style={this.props.style}>
        <CardTitle title="Score History" />
        <CardText>
          <Line
            data={{datasets: [{data: this.props.scoreHistory.map(history => ({x: history.dateTime, y: history.value + history.cash })),
                      fill: false,
                      steppedLine: true,}]}}
            options={{
                scales: {
                  xAxes: [{type:'time'}],
                  yAxes: [{
                    ticks: {
                      suggestedMin: minPrice(this.props.scoreHistory) - 10,
                      suggestedMax: maxPrice(this.props.scoreHistory) + 10
                  }}]
                },
                legend: {
                  display: false
                }
              }}
          />
        </CardText>
      </Card>
    );
  }
}

UserScoreHistory.propTypes = {
  scoreHistory: PropTypes.array,
  userId: PropTypes.number,
  fetchUserCrewPriceHistory: PropTypes.func,
  sex: PropTypes.string,
  style: PropTypes.object
};

export const mapStateToProps = (state, ownProps) => ({
  scoreHistory: getUserScoreHistory(state)(ownProps.userId, ownProps.sex) || []
});

export {UserScoreHistory};

export default connect(mapStateToProps, { fetchUserCrewPriceHistory })(UserScoreHistory);
