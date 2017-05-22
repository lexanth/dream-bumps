// @flow
import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { Card, CardTitle, CardText } from 'material-ui/Card';

import {
  getNumberOfDivisions,
  getCrewsByDivision,
  getCurrentDay,
  isMarketOpen
} from '../rootReducer';
import DivisionTable from './DivisionTable';

const CrewList = props => {
  const dayTitle = props.day >= 4
    ? 'Finishing Order'
    : `Starting Order for Day ${props.day + 1}`;
  const statusSuffix = props.marketOpen ? '' : ' - Market is Closed';
  return (
    <Card>
      <CardTitle title={`${dayTitle}${statusSuffix}`} />
      <CardText>
        {Object.keys(props.crews).map(key => (
          <DivisionTable
            sex={props.sex}
            division={key}
            key={key}
            crews={props.crews[key]}
            day={props.day}
          />
        ))}
      </CardText>
    </Card>
  );
};

CrewList.propTypes = {
  sex: PropTypes.string,
  numberOfDivisions: PropTypes.number,
  crews: PropTypes.shape({
    [0]: PropTypes.arrayOf(
      PropTypes.shape({
        id: PropTypes.number,
        position: PropTypes.number,
        name: PropTypes.string
      })
    )
  }),
  day: PropTypes.number,
  marketOpen: PropTypes.bool
};

export const mapStateToProps = (state: Object, ownProps: Object) => ({
  crews: getCrewsByDivision(state)(ownProps.sex),
  numberOfDivisions: getNumberOfDivisions(state)(ownProps.sex),
  day: getCurrentDay(state),
  marketOpen: isMarketOpen(state)
});

export { CrewList };

export default connect(mapStateToProps)(CrewList);
