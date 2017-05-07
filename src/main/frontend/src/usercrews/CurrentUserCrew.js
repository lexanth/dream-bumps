import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
// import {Card, CardTitle, CardText} from 'material-ui/Card';
// import { Table, TableBody } from 'material-ui/Table';

import { getCurrentUserId } from '../rootReducer';
// import { fetchUserCrewMembers, fetchUserCrewRanking, setBuyMember } from './actions';
// import CurrentUserCrewMemberRow from './CurrentUserCrewMemberRow';
import UserCrew from './UserCrew';

const CurrentUserCrew = ({userId, sex}) => (
  <UserCrew
    userId={userId}
    canBuySell
    sex={sex}
    header="My Crew"
    showCashAndValue
  />
);

CurrentUserCrew.propTypes = {
  userId: PropTypes.number,
  sex: PropTypes.string
}

const mapStateToProps = (state) => ({
  userId: getCurrentUserId(state)
});

export {CurrentUserCrew};

export default connect(mapStateToProps)(CurrentUserCrew);

//
//
// /**
//  * CurrentUserCrew
//  */
// class CurrentUserCrew extends Component {
//   componentDidMount() {
//     if (this.props.currentUserId) {
//       this.loadCrew(this.props);
//     }
//   }
//
//   componentWillReceiveProps(nextProps) {
//     if (nextProps.currentUserId && this.props.currentUserId !== nextProps.currentUserId) {
//       this.loadCrew(nextProps);
//     }
//   }
//
//   loadCrew(props) {
//     this.props.fetchUserCrewMembers(props.currentUserId, this.props.sex);
//     this.props.fetchUserCrewRanking(props.currentUserId, this.props.sex);
//   }
//
//   render() {
//     return (
//       <Card>
//         <CardTitle title="My Crew" />
//         <CardText>
//           <Table
//             selectable={false}
//           >
//             <TableBody
//               displayRowCheckbox={false}
//             >
//               {this.props.crewMembers.map(member => (
//                 <CurrentUserCrewMemberRow
//                   key={member.id}
//                   member={member}
//                   onClickBuy={e => this.props.onClickBuy(member.id, this.props.sex)}
//                 />))
//               }
//             </TableBody>
//           </Table>
//         </CardText>
//         <CardText>
//           <dl className="dl-horizontal">
//             <dt>Cash</dt>
//             <dd>{this.props.crewRanking.cash}</dd>
//             <dt>Crew Value</dt>
//             <dd>{this.props.crewRanking.value}</dd>
//           </dl>
//         </CardText>
//       </Card>
//     );
//   }
// }
//
// CurrentUserCrew.propTypes = {
//   sex: PropTypes.string,
//   currentUserId: PropTypes.number,
//   crewMembers: PropTypes.array,
//   fetchUserCrewMembers: PropTypes.func,
//   fetchUserCrewRanking: PropTypes.func,
//   onClickBuy: PropTypes.func,
//   crewRanking: PropTypes.object
// };
//
// const mapStateToProps = (state, ownProps) => {
//   const mapping = {currentUserId: getCurrentUserId(state)};
//   mapping.crewMembers = getUserCrewMembers(state)(mapping.currentUserId, ownProps.sex);
//   mapping.crewRanking = getUserCrewRanking(state)(mapping.currentUserId, ownProps.sex) || {};
//   return mapping;
// };
//
// export default connect(mapStateToProps, { fetchUserCrewMembers, fetchUserCrewRanking, onClickBuy: setBuyMember })(CurrentUserCrew);
