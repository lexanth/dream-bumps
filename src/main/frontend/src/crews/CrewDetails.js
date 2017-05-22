// @flow
import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { Card, CardTitle, CardText } from 'material-ui/Card';

import { getCrew } from '../rootReducer';

const percentChange = (now, original) => {
  const change = Math.trunc(100 * (now - original) / original, 2);
  if (change < 0) {
    return `${change}%`;
  }
  return `+${change}%`;
};

const stepChange = (now, original) => {
  // Looks slightly backwards, because lower position is upwards
  if (now - original <= 0) {
    return `+${original - now}`;
  }
  return `-${now - original}`;
};

const CrewDetails = ({ crew }) => {
  if (crew === undefined) {
    return <div>Loading...</div>;
  }
  return (
    <Card>
      <CardTitle title={crew.name} />
      <CardText>
        <div>
          <dl className="dl-horizontal">
            <dt>Start Price</dt>
            <dd>{crew.startPrice}</dd>
          </dl>
          <dl className="dl-horizontal">
            <dt>Current Price</dt>
            <dd
            >{`${crew.price} (${percentChange(crew.price, crew.startPrice)})`}</dd>
          </dl>
          <dl className="dl-horizontal">
            <dt>Start Position</dt>
            <dd>{crew.startPosition}</dd>
          </dl>
          <dl className="dl-horizontal">
            <dt>Current Position</dt>
            <dd
            >{`${crew.position} (${stepChange(crew.position, crew.startPosition)})`}</dd>
          </dl>
          <dl className="dl-horizontal">
            <dt>Held</dt>
            <dd>{crew.holding || '_'}</dd>
          </dl>
        </div>
      </CardText>
    </Card>
  );
};

CrewDetails.propTypes = {
  crewId: PropTypes.string,
  crew: PropTypes.shape({
    name: PropTypes.string,
    startPrice: PropTypes.number,
    price: PropTypes.number,
    startPosition: PropTypes.number,
    position: PropTypes.number,
    holding: PropTypes.number
  })
};

export const mapStateToProps = (state: Object, ownProps: Object) => ({
  crew: getCrew(state)(ownProps.crewId)
});

export { CrewDetails };
export default connect(mapStateToProps)(CrewDetails);
