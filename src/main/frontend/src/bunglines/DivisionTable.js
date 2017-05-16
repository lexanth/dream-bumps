// @flow
import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn } from 'material-ui/Table';
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import { Link } from 'react-router';
import UpArrow from 'material-ui/svg-icons/navigation/arrow-upward';
import RightArrow from 'material-ui/svg-icons/navigation/arrow-forward';

import { getCurrentUserId, getUserCrewRanking, getBuySex, getBuyMemberId, getNumberOfCrews, getCrewsPerDivision } from '../rootReducer';
import { doBuyRower } from '../usercrews/actions';
import {calculateRowOverDividendForPosition, calculateBumpDividend} from '../utils/dividends';
/**
 * DivisionTable
 */
class DivisionTable extends Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <Table
        selectable={false}
      >
        <TableHeader
          adjustForCheckbox={false}
          displaySelectAll={false}
        >
          <TableRow>
            <TableHeaderColumn>
              <h2>{`Division ${parseInt(this.props.division, 10)+1}`}</h2>
            </TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody
          displayRowCheckbox={false}
        >
          {this.props.crews.map(crew => (
            <TableRow
              key={crew.id}
            >
              <TableRowColumn style={{paddingLeft:0, paddingRight:0, width: '45px'}}>{crew.position}</TableRowColumn>
              <TableRowColumn>
                <FlatButton
                  containerElement={<Link to={`/crews/${crew.id}`} />}
                  label={crew.name}
                  labelStyle={{textTransform: '', fontSize: '13px'}}
                  style={{paddingLeft: 0, textAlign: '', marginLeft: '-16px'}}
                />
              </TableRowColumn>
              {
                this.props.buySex === this.props.sex ?
                <TableRowColumn>
                  <RaisedButton label="Buy" primary disabled={crew.price > this.props.crewRanking.cash} onClick={e => this.props.doBuyRower(crew.id, this.props.buyMemberId)} />
                </TableRowColumn>
                :
                null
              }
              <TableRowColumn style={{width: '100px'}}>{crew.price && crew.price.toFixed ? crew.price.toFixed(2) : ''}</TableRowColumn>
              <TableRowColumn style={{width: '100px'}}>{crew.position > 1 && <UpArrow />}{ crew.position > 1 && calculateBumpDividend(crew.position, this.props.numberOfCrews, this.props.crewsPerDivision, this.props.day)}</TableRowColumn>
              <TableRowColumn style={{width: '100px'}}><RightArrow /> { calculateRowOverDividendForPosition(crew.position, this.props.day)}</TableRowColumn>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    );
  }
}

DivisionTable.propTypes = {
  sex: PropTypes.string,
  division: PropTypes.string,
  crews: PropTypes.arrayOf(PropTypes.shape(
    {id: PropTypes.number, position: PropTypes.number, name: PropTypes.string})),
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

export const mapStateToProps = (state:Object, ownProps:Object) => {
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
  mapping.crewRanking = getUserCrewRanking(state)(mapping.currentUserId, ownProps.sex) || {};
  mapping.crewsPerDivision = getCrewsPerDivision(state)();
  mapping.numberOfCrews = getNumberOfCrews(state)(ownProps.sex)
  return mapping;
};

export {DivisionTable};

export default connect(mapStateToProps, {doBuyRower})(DivisionTable);
