// @flow
import React, { PropTypes, Component } from 'react';
import { TableRow, TableRowColumn } from 'material-ui/Table';
import { connect } from 'react-redux';
import { Link } from 'react-router';
import FlatButton from 'material-ui/FlatButton';
import MediaQuery from 'react-responsive';

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
        style={{ paddingLeft: 0, paddingRight: 0, width: '30px' }}
      >
        {props.number}
      </TableRowColumn>
      {props.user
        ? <TableRowColumn>
            <FlatButton
              containerElement={<Link to={`/rankings/${props.user.id}`} />}
              label={`${props.user.firstName} ${props.user.lastName}`}
              labelStyle={{ textTransform: '', fontSize: '13px' }}
              style={{
                paddingLeft: 0,
                textAlign: '',
                marginLeft: '-16px',
                minWidth: '120px'
              }}
            />
          </TableRowColumn>
        : <TableRowColumn>
            <FlatButton
              containerElement={
                <Link to={`/rankings/${props.ranking.userId}`} />
              }
              label={`User ${props.ranking.userId}`}
              labelStyle={{ textTransform: '', fontSize: '13px' }}
              style={{
                paddingLeft: 0,
                textAlign: '',
                marginLeft: '-16px',
                minWidth: '120px'
              }}
            />
          </TableRowColumn>}
      {props.user
        ? <MediaQuery minWidth={1000}>
            <TableRowColumn>
              {getCollegeName(props.user.college)}
            </TableRowColumn>
          </MediaQuery>
        : <MediaQuery minWidth={1000}><TableRowColumn /></MediaQuery>}
      <TableRowColumn style={{ paddingRight: 0, width: '70px' }}>
        {props.ranking.bumps}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '70px' }}>
        {formatMoney(props.ranking.cash)}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '70px' }}>
        {formatMoney(props.ranking.value)}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '70px' }}>
        {formatMoney(props.ranking.dividends)}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '70px' }}>
        {formatMoney(
          props.ranking.cash +
            props.ranking.value -
            (props.ranking.sex === 'combined' ? 2000 : 1000) -
            props.ranking.dividends
        )}
      </TableRowColumn>
      <TableRowColumn style={{ paddingRight: 0, width: '70px' }}>
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
