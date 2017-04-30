// @flow
import React, {PropTypes} from 'react';
import {connect} from 'react-redux';
import {TableRow, TableRowColumn} from 'material-ui/Table';
import RaisedButton from 'material-ui/RaisedButton';

import seatNumberToName from '../utils/seatNumberToName';
import {cancelBuyMember, doSellRower} from './actions';
import {getCrew, getCrewMemberName, getBuyMemberId, isMarketOpen} from '../rootReducer';

const CurrentUserCrewMemberRow = ({
  member,
  crew,
  crewMemberName,
  onClickBuy,
  onClickCancel,
  buyMemberId,
  cancelBuyMember,
  doSellRower,
  marketOpen
}) => (
  <TableRow>
    <TableRowColumn>{seatNumberToName(member.seat)}</TableRowColumn>
    <TableRowColumn>
      {
        crew ?
          crew.name
        :
          '-'
      }
    </TableRowColumn>
    {/*<TableRowColumn>{crewMemberName}</TableRowColumn>*/}
    <TableRowColumn>
      {
        crew ?
          crew.price
        :
          ''
      }
    </TableRowColumn>
    {
      marketOpen ?
        <TableRowColumn>
          {
            member.crewId ?
              <RaisedButton label="Sell" onClick={e => doSellRower(member.crewId, member.id)}/>
            :
              member.id === buyMemberId ?
                <RaisedButton label="Cancel" onClick={cancelBuyMember}/>
              :
                buyMemberId ?
                  null
                :
                  <RaisedButton label="Buy" onClick={onClickBuy}/>
          }
        </TableRowColumn>
      :
        null
    }
  </TableRow>
);

CurrentUserCrewMemberRow.propTypes = {
  member: PropTypes.object,
  crew: PropTypes.object,
  crewMemberName: PropTypes.string,
  onClickBuy: PropTypes.func,
  onClickCancel: PropTypes.func,
  buyMemberId: PropTypes.number,
  cancelBuyMember: PropTypes.func,
  doSellRower: PropTypes.func,
  marketOpen: PropTypes.bool
};

const mapStateToProps = (state, {member}) => ({
  crew: getCrew(state)(member.crewId),
  crewMemberName: getCrewMemberName(state)(member.crewId, member.seat) || '',
  buyMemberId: getBuyMemberId(state),
  marketOpen: isMarketOpen(state)
});

export default connect(mapStateToProps, {cancelBuyMember, doSellRower})(CurrentUserCrewMemberRow);
