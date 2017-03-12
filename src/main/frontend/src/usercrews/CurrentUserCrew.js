import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import { Table, TableBody } from 'material-ui/Table';

import { getCurrentUserId, getUserCrewMembers, getUserCrewRanking } from '../rootReducer';
import { fetchUserCrewMembers, fetchUserCrewRanking, setBuyMember } from './actions';
import CurrentUserCrewMemberRow from './CurrentUserCrewMemberRow';

/**
 * CurrentUserCrew
 */
class CurrentUserCrew extends Component {
  componentWillReceiveProps(nextProps) {
    if (nextProps.currentUserId && this.props.currentUserId !== nextProps.currentUserId) {
      this.props.fetchUserCrewMembers(nextProps.currentUserId, this.props.sex);
      this.props.fetchUserCrewRanking(nextProps.currentUserId, this.props.sex);
    }
  }

  render() {
    return (
      <Card>
        <CardTitle title="My Crew" />
        <CardText>
          <Table
            selectable={false}
          >
            <TableBody
              displayRowCheckbox={false}
            >
              {this.props.crewMembers.map(member => (
                <CurrentUserCrewMemberRow
                  key={member.id}
                  member={member}
                  onClickBuy={e => this.props.onClickBuy(member.id, this.props.sex)}
                />))
              }
            </TableBody>
          </Table>
        </CardText>
        <CardText>
          <dl className="dl-horizontal">
            <dt>Cash</dt>
            <dd>{this.props.crewRanking.cash}</dd>
            <dt>Crew Value</dt>
            <dd>{this.props.crewRanking.value}</dd>
          </dl>
        </CardText>
      </Card>
    );
  }
}

CurrentUserCrew.propTypes = {
  sex: PropTypes.string,
  currentUserId: PropTypes.number,
  crewMembers: PropTypes.array,
  fetchUserCrewMembers: PropTypes.func
};

const mapStateToProps = (state, ownProps) => {
  const mapping = {currentUserId: getCurrentUserId(state)};
  mapping.crewMembers = getUserCrewMembers(state)(mapping.currentUserId, ownProps.sex);
  mapping.crewRanking = getUserCrewRanking(state)(mapping.currentUserId, ownProps.sex) || {};
  return mapping;
};

export default connect(mapStateToProps, { fetchUserCrewMembers, fetchUserCrewRanking, onClickBuy: setBuyMember })(CurrentUserCrew);
