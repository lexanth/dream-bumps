import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import { Table, TableBody } from 'material-ui/Table';

import { getUserCrewMembers, getUserCrewRanking } from '../rootReducer';
import { fetchUserCrewMembers, fetchUserCrewRanking, setBuyMember } from './actions';
import UserCrewMemberRow from './UserCrewMemberRow';

/**
 * CurrentUserCrew
 */
class UserCrew extends Component {
  componentDidMount() {
    if (this.props.userId) {
      this.loadCrew(this.props);
    }
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.userId && this.props.userId !== nextProps.userId) {
      this.loadCrew(nextProps);
    }
  }

  loadCrew(props) {
    this.props.fetchUserCrewMembers(props.userId, this.props.sex);
    this.props.fetchUserCrewRanking(props.userId, this.props.sex);
  }

  render() {
    return (
      <Card>
        <CardTitle title={this.props.header} />
        <CardText>
          <Table
            selectable={false}
          >
            <TableBody
              displayRowCheckbox={false}
            >
              {this.props.crewMembers.map(member => (
                <UserCrewMemberRow
                  key={member.id}
                  member={member}
                  canBuySell={this.props.canBuySell}
                  onClickBuy={e => this.props.onClickBuy(member.id, this.props.sex)}
                />))
              }
            </TableBody>
          </Table>
        </CardText>
        {this.props.showCashAndValue &&
          <CardText>
            <dl className="dl-horizontal">
              <dt>Cash</dt>
              <dd>{this.props.crewRanking.cash ? this.props.crewRanking.cash.toFixed(2) : ''}</dd>
              <dt>Crew Value</dt>
              <dd>{this.props.crewRanking.value ? this.props.crewRanking.value.toFixed(2): ''}</dd>
            </dl>
          </CardText>
        }
      </Card>
    );
  }
}

UserCrew.propTypes = {
  sex: PropTypes.string,
  userId: PropTypes.number,
  crewMembers: PropTypes.array,
  fetchUserCrewMembers: PropTypes.func,
  fetchUserCrewRanking: PropTypes.func,
  onClickBuy: PropTypes.func,
  crewRanking: PropTypes.object,
  canBuySell: PropTypes.bool,
  header: PropTypes.string,
  showCashAndValue: PropTypes.bool
};

export const mapStateToProps = (state, ownProps) => ({
  crewMembers : getUserCrewMembers(state)(ownProps.userId, ownProps.sex),
  crewRanking : getUserCrewRanking(state)(ownProps.userId, ownProps.sex) || {}
});

export {UserCrew};

export default connect(mapStateToProps, { fetchUserCrewMembers, fetchUserCrewRanking, onClickBuy: setBuyMember })(UserCrew);
