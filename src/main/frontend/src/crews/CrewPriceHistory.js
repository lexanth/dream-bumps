import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import { Line } from 'react-chartjs-2';

import { fetchCrewPriceHistory } from './actions';
import { getCrewPriceHistory } from '../rootReducer';

const minPrice = priceHistories => priceHistories.reduce((prev, curr) => (prev < curr.price ? prev : curr.price), Number.POSITIVE_INFINITY);

const maxPrice = priceHistories => priceHistories.reduce((prev, curr) => (prev > curr.price ? prev : curr.price), 0);

/**
 * CrewPriceHistory
 */
class CrewPriceHistory extends Component {
  componentWillMount() {
    if (this.props.crewId) {
      this.props.fetchCrewPriceHistory(this.props.crewId);
    }
  }
  render() {
    return (
      <Card>
        <CardTitle title="Price History" />
        <CardText>
          <Line
            data={{datasets: [{data: this.props.crewPriceHistory.map(history => ({x: history.dateTime, y: history.price})),
                      fill: false,
                      steppedLine: true,}]}}
            options={{
                scales: {
                  xAxes: [{type:'time'}],
                  yAxes: [{
                    ticks: {
                      suggestedMin: minPrice(this.props.crewPriceHistory) - 10,
                      suggestedMax: maxPrice(this.props.crewPriceHistory) + 10
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

CrewPriceHistory.propTypes = {
  crewPriceHistory: PropTypes.array,
  crewId: PropTypes.string,
  fetchCrewPriceHistory: PropTypes.func
};

export const mapStateToProps = (state, ownProps) => ({
  crewPriceHistory: getCrewPriceHistory(state)(ownProps.crewId)
});

export {CrewPriceHistory};

export default connect(mapStateToProps, { fetchCrewPriceHistory })(CrewPriceHistory);
