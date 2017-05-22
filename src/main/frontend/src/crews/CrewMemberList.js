// @flow
import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { Card, CardTitle, CardText } from 'material-ui/Card';

import { getCrewMembers } from '../rootReducer';
import seatNumberToName from '../utils/seatNumberToName';

const CrewMemberList = props => {
  if (props.crewMembers === undefined) {
    return null;
  }
  return (
    <Card>
      <CardTitle title="Crew Members" />
      <CardText>
        <div>
          {props.crewMembers.map(member => (
            <dl key={member.id} className="dl-horizontal">
              <dt>{seatNumberToName(member.seat)}</dt>
              <dd>{member.name}</dd>
            </dl>
          ))}
        </div>
      </CardText>
    </Card>
  );
};

CrewMemberList.propTypes = {
  crewId: PropTypes.string,
  crewMembers: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      seat: PropTypes.number,
      name: PropTypes.string
    })
  )
};

export const mapStateToProps = (state: Object, ownProps: Object) => ({
  crewMembers: getCrewMembers(state)(ownProps.crewId)
});

export { CrewMemberList };

export default connect(mapStateToProps)(CrewMemberList);
