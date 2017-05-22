// @flow
import React, { PropTypes, Component } from 'react';
import { TableRow, TableRowColumn } from 'material-ui/Table';
import { connect } from 'react-redux';
import { Link } from 'react-router';
import FlatButton from 'material-ui/FlatButton';

import { getUser } from '../rootReducer';
import { getCollegeName } from '../utils/colleges';
import { formatMoney } from '../utils/maths';

const RankingRow = props => {
  if (!props.ranking) {
    return <TableRow>Loading...</TableRow>;
  }
  return (
    <TableRow>
      <TableRowColumn
        style={{ paddingLeft: 0, paddingRight: 0, width: '45px' }}
      >
        {props.number}
      </TableRowColumn>
      {props.user
        ? <TableRowColumn>
            <FlatButton
              containerElement={<Link to={`/rankings/${props.user.id}`} />}
              label={`${props.user.firstName} ${props.user.lastName}`}
              labelStyle={{ textTransform: '', fontSize: '13px' }}
              style={{ paddingLeft: 0, textAlign: '', marginLeft: '-16px' }}
            />
          </TableRowColumn>
        : <TableRowColumn>
            <FlatButton
              containerElement={
                <Link to={`/rankings/${props.ranking.userId}`} />
              }
              label={`User ${props.ranking.userId}`}
              labelStyle={{ textTransform: '', fontSize: '13px' }}
              style={{ paddingLeft: 0, textAlign: '', marginLeft: '-16px' }}
            />
          </TableRowColumn>}
      {props.user
        ? <TableRowColumn>
            {getCollegeName(props.user.college)}
          </TableRowColumn>
        : <TableRowColumn />}
      <TableRowColumn style={{ paddingRight: 0, width: '88px' }}>
        {props.ranking.bumps}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '88px' }}>
        {formatMoney(props.ranking.cash)}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '88px' }}>
        {formatMoney(props.ranking.value)}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '88px' }}>
        {formatMoney(props.ranking.dividends)}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '88px' }}>
        {formatMoney(
          props.ranking.cash +
            props.ranking.value -
            (props.ranking.sex === 'combined' ? 2000 : 1000) -
            props.ranking.dividends
        )}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '88px' }}>
        {formatMoney(props.ranking.cash + props.ranking.value)}
      </TableRowColumn>
    </TableRow>
  );
};

RankingRow.propTypes = {
  number: PropTypes.number,
  ranking: PropTypes.shape({
    userId: PropTypes.number,
    cash: PropTypes.number,
    value: PropTypes.number,
    bumps: PropTypes.number,
    dividends: PropTypes.number,
    sex: PropTypes.string
  }),
  user: PropTypes.shape({
    college: PropTypes.string,
    firstName: PropTypes.string,
    lastName: PropTypes.string,
    id: PropTypes.number
  })
};

export const mapStateToProps = (
  state: Object,
  ownProps: { ranking: { userId: number } }
) => {
  if (ownProps.ranking) {
    return { user: getUser(state)(ownProps.ranking.userId) };
  }
  return { user: {} };
};

export { RankingRow };

export default connect(mapStateToProps)(RankingRow);
