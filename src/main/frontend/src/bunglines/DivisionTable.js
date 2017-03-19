import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn } from 'material-ui/Table';
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import { Link } from 'react-router';
import ActionInfo from 'material-ui/svg-icons/action/info';

import { getCurrentUserId, getUserCrewRanking, getBuySex, getBuyMemberId } from '../rootReducer';
import { doBuyRower } from '../usercrews/actions';
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
              <TableRowColumn>{crew.position}</TableRowColumn>
              <TableRowColumn>{crew.name}</TableRowColumn>
              <TableRowColumn>{crew.price}</TableRowColumn>
              <TableRowColumn><FlatButton containerElement={<Link to={`/crews/${crew.id}`} />} icon={<ActionInfo />} /></TableRowColumn>
              {
                this.props.buySex === this.props.sex ?
                <TableRowColumn>
                  <RaisedButton label="Buy" disabled={crew.price > this.props.crewRanking.cash} onClick={e => this.props.doBuyRower(crew.id, this.props.buyMemberId)} />
                </TableRowColumn>
                :
                null
              }
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
  crews: PropTypes.array,
  buySex: PropTypes.string,
  crewRanking: PropTypes.array,
  doBuyRower: PropTypes.func,
  buyMemberId: PropTypes.number
};

const mapStateToProps = (state, ownProps) => {
  const mapping = {
    // crews: getCrewsForDivision(state)(ownProps.sex, ownProps.division),
    currentUserId: getCurrentUserId(state),
    buySex: getBuySex(state),
    buyMemberId: getBuyMemberId(state)
  };
  mapping.crewRanking = getUserCrewRanking(state)(mapping.currentUserId, ownProps.sex) || {};
  return mapping;
};

export default connect(mapStateToProps, {doBuyRower})(DivisionTable);
