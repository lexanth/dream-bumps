// @flow
import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import { TableRow, TableRowColumn } from 'material-ui/Table';
import RaisedButton from 'material-ui/RaisedButton';

import seatNumberToName from '../utils/seatNumberToName';
import { cancelBuyMember, doSellRower } from './actions';
import {
  getCrew,
  getCrewMemberName,
  getBuyMemberId,
  isMarketOpen
} from '../rootReducer';
import { formatMoney } from '../utils/maths';

const UserCrewMemberRow = ({
  member,
  crew,
  crewMemberName,
  onClickBuy,
  buyMemberId,
  cancelBuyMember,
  doSellRower,
  marketOpen,
  canBuySell
}) => (
  <TableRow>
    <TableRowColumn
      style={{
        paddingLeft: 0,
        paddingRight: 0,
        width: canBuySell ? '30px' : '40px'
      }}
    >
      {seatNumberToName(member.seat)}
    </TableRowColumn>
    <TableRowColumn style={{ paddingLeft: 0, paddingRight: '5px' }}>
      {crew ? crew.name : '-'}
    </TableRowColumn>
    <TableRowColumn style={{ paddingLeft: 0, paddingRight: 0 }}>
      {crewMemberName}
    </TableRowColumn>
    <TableRowColumn
      style={{
        paddingLeft: 0,
        paddingRight: '5px',
        width: '46px',
        textAlign: 'right'
      }}
    >
      {crew ? formatMoney(crew.price) : ''}
    </TableRowColumn>
    {marketOpen && canBuySell
      ? <TableRowColumn
          style={{ paddingLeft: 0, paddingRight: 0, width: '68px' }}
        >
          {member.crewId && !buyMemberId
            ? <RaisedButton
                primary
                label="Sell"
                style={{ minWidth: '68px' }}
                onClick={e => doSellRower(member.crewId, member.id)}
              />
            : member.id === buyMemberId
                ? <RaisedButton
                    primary
                    label="Cancel"
                    style={{ minWidth: '68px' }}
                    labelStyle={{ paddingLeft: '0px', paddingRight: 0 }}
                    onClick={cancelBuyMember}
                  />
                : buyMemberId
                    ? null
                    : <RaisedButton
                        primary
                        label="Buy"
                        style={{ minWidth: '68px' }}
                        onClick={onClickBuy}
                      />}
        </TableRowColumn>
      : null}
  </TableRow>
);

UserCrewMemberRow.propTypes = {
  member: PropTypes.shape({
    id: PropTypes.number,
    crewId: PropTypes.number,
    seat: PropTypes.number
  }),
  crew: PropTypes.shape({
    id: PropTypes.number
  }),
  crewMemberName: PropTypes.string,
  onClickBuy: PropTypes.func,
  buyMemberId: PropTypes.number,
  cancelBuyMember: PropTypes.func,
  doSellRower: PropTypes.func,
  marketOpen: PropTypes.bool,
  canBuySell: PropTypes.bool
};

export const mapStateToProps = (
  state: Object,
  { member }: { member: Object }
) => ({
  crew: getCrew(state)(member.crewId),
  crewMemberName: getCrewMemberName(state)(member.crewId, member.seat) || '',
  buyMemberId: getBuyMemberId(state),
  marketOpen: isMarketOpen(state)
});

export { UserCrewMemberRow };

export default connect(mapStateToProps, {
  cancelBuyMember,
  doSellRower
})(UserCrewMemberRow);
