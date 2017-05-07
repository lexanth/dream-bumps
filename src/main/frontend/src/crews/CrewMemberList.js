import React, {PropTypes} from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';

import { fetchCrewMembers } from './actions';
import { getCrewMembers } from '../rootReducer';
import seatNumberToName from '../utils/seatNumberToName';


class CrewMemberList extends React.Component {
    componentWillMount() {
      if (this.props.crewId) {
        this.props.fetchCrewMembers(this.props.crewId);
      }
    }

  render() {
    if (this.props.crewMembers === undefined) {
      return null;
    }
    return (
      <Card>
        <CardTitle title="Crew Members"/>
        <CardText>
          <div>
            {
              this.props.crewMembers.map(member => (
                <dl key={member.id} className="dl-horizontal">
                  <dt>{seatNumberToName(member.seat)}</dt>
                  <dd>{member.name}</dd>
                </dl>
              ))
            }
          </div>
        </CardText>
      </Card>
    );
  }
}

CrewMemberList.propTypes = {
  crewId: PropTypes.string,
  fetchCrewMembers: PropTypes.func,
  crewMembers: PropTypes.array
};

const mapStateToProps = (state, ownProps) => ({
  crewMembers: getCrewMembers(state)(ownProps.crewId)
});

export {CrewMemberList};

export default connect(mapStateToProps, { fetchCrewMembers })(CrewMemberList);
