// @flow
import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from 'material-ui/Table';
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import { Link } from 'react-router';
import UpArrow from 'material-ui/svg-icons/navigation/arrow-upward';
import RightArrow from 'material-ui/svg-icons/navigation/arrow-forward';
import MediaQuery from 'react-responsive';

import {
  getCurrentUserId,
  getUserCrewRanking,
  getBuySex,
  getBuyMemberId,
  getNumberOfCrews,
  getCrewsPerDivision
} from '../rootReducer';
import { doBuyRower } from '../usercrews/actions';
import {
  calculateRowOverDividendForPosition,
  calculateBumpDividend
} from '../utils/dividends';
import { formatMoney } from '../utils/maths';
/**
 * DivisionTable
 */
const DivisionTable = props => (
  <Table selectable={false}>
    <TableHeader adjustForCheckbox={false} displaySelectAll={false}>
      <TableRow>
        <TableHeaderColumn>
          <h2>{`Division ${parseInt(props.division, 10) + 1}`}</h2>
        </TableHeaderColumn>
      </TableRow>
    </TableHeader>
    <TableBody displayRowCheckbox={false}>
      {props.crews.map(crew => (
        <TableRow key={crew.id}>
          <MediaQuery minWidth={768}>
            {matches => (
              <TableRowColumn
                style={{
                  paddingLeft: 0,
                  paddingRight: 0,
                  width: matches ? '45px' : '26px'
                }}
              >
                {crew.position}
              </TableRowColumn>
            )}
          </MediaQuery>
          <MediaQuery minWidth={768}>
            {matches => (
              <TableRowColumn
                style={{
                  paddingLeft: matches ? '24px' : '0px'
                }}
              >
                <FlatButton
                  containerElement={<Link to={`/crews/${crew.id}`} />}
                  label={crew.name}
                  labelStyle={{ textTransform: '', fontSize: '13px' }}
                  style={{
                    paddingLeft: 0,
                    textAlign: '',
                    marginLeft: '-16px',
                    minWidth: '80px'
                  }}
                />
              </TableRowColumn>
            )}
          </MediaQuery>
          {props.buySex === props.sex
            ? <MediaQuery minWidth={768}>
                {matches => (
                  <TableRowColumn
                    style={
                      !matches && {
                        width: '68px',
                        paddingLeft: '0px',
                        paddingRight: '8px'
                      }
                    }
                  >
                    <RaisedButton
                      style={
                        !matches && {
                          minWidth: '68px',
                          paddingLeft: '0px',
                          paddingRight: '0px'
                        }
                      }
                      label="Buy"
                      primary
                      disabled={crew.price > props.crewRanking.cash}
                      onClick={e =>
                        props.doBuyRower(crew.id, props.buyMemberId)}
                    />
                  </TableRowColumn>
                )}
              </MediaQuery>
            : null}
          <MediaQuery minWidth={768}>
            {matches => (
              <TableRowColumn
                style={{
                  width: matches ? '60px' : '40px',
                  paddingRight: matches ? '8px' : '4px',
                  paddingLeft: matches ? '8px' : '4px'
                }}
              >
                {formatMoney(crew.price)}
              </TableRowColumn>
            )}
          </MediaQuery>
          <MediaQuery minWidth={768}>
            {matches => (
              <TableRowColumn
                style={{
                  width: matches ? '80px' : '70px',
                  paddingRight: matches ? '8px' : '4px',
                  paddingLeft: matches ? '8px' : '4px',
                  display: matches || props.buySex !== props.sex
                    ? 'table-cell'
                    : 'none'
                }}
              >
                {crew.position > 1 && <UpArrow />}
                {crew.position > 1 &&
                  calculateBumpDividend(
                    crew.position,
                    props.numberOfCrews,
                    props.crewsPerDivision,
                    props.day
                  )}
              </TableRowColumn>
            )}
          </MediaQuery>
          <MediaQuery minWidth={768}>
            {matches => (
              <TableRowColumn
                style={{
                  width: matches ? '80px' : '70px',
                  paddingRight: matches ? '8px' : '4px',
                  paddingLeft: matches ? '8px' : '4px',
                  display: matches || props.buySex !== props.sex
                    ? 'table-cell'
                    : 'none'
                }}
              >
                <RightArrow />
                {' '}
                {calculateRowOverDividendForPosition(crew.position, props.day)}
              </TableRowColumn>
            )}
          </MediaQuery>
        </TableRow>
      ))}
    </TableBody>
  </Table>
);

DivisionTable.propTypes = {
  sex: PropTypes.string,
  division: PropTypes.string,
  crews: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      position: PropTypes.number,
      name: PropTypes.string
    })
  ),
  buySex: PropTypes.string,
  crewRanking: PropTypes.shape({
    cash: PropTypes.number
  }),
  doBuyRower: PropTypes.func,
  buyMemberId: PropTypes.number,
  crewsPerDivision: PropTypes.number,
  numberOfCrews: PropTypes.number,
  day: PropTypes.number
};

export const mapStateToProps = (state: Object, ownProps: Object) => {
  const mapping = {
    // crews: getCrewsForDivision(state)(ownProps.sex, ownProps.division),
    currentUserId: getCurrentUserId(state),
    buySex: getBuySex(state),
    buyMemberId: getBuyMemberId(state),
    // Give flow a hand - objects should be sealed when declared
    crewRanking: {},
    crewsPerDivision: 0,
    numberOfCrews: 0
  };
  mapping.crewRanking = getUserCrewRanking(state)(
    mapping.currentUserId,
    ownProps.sex
  ) || {};
  mapping.crewsPerDivision = getCrewsPerDivision(state)();
  mapping.numberOfCrews = getNumberOfCrews(state)(ownProps.sex);
  return mapping;
};

export { DivisionTable };

export default connect(mapStateToProps, { doBuyRower })(DivisionTable);
