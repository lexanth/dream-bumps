// @flow
import React, {PropTypes, Component} from 'react';
import { TableRow, TableRowColumn } from 'material-ui/Table';
import { connect } from 'react-redux';

import { getUser } from '../rootReducer';

class RankingRow extends Component {
  render() {
    if (!this.props.ranking) {
      return <TableRow>Loading...</TableRow>
    }
    return (
      <TableRow>
        <TableRowColumn>{this.props.number}</TableRowColumn>
        {this.props.user ?
          <TableRowColumn>{`${this.props.user.firstName} ${this.props.user.lastName}`}</TableRowColumn>
        :
          <TableRowColumn>{`User ${this.props.ranking.userId}`}</TableRowColumn>
        }
        {this.props.user ?
          <TableRowColumn>{this.props.user.college}</TableRowColumn>
        :
          <TableRowColumn />
        }
        <TableRowColumn>{this.props.ranking.bumps}</TableRowColumn>
        <TableRowColumn>{this.props.ranking.cash}</TableRowColumn>
        <TableRowColumn>{this.props.ranking.value}</TableRowColumn>
        <TableRowColumn>{this.props.ranking.dividends}</TableRowColumn>
        <TableRowColumn>{(this.props.ranking.cash + this.props.ranking.value).toFixed(2)}</TableRowColumn>
      </TableRow>
    );
  }
}

RankingRow.propTypes = {
  number: PropTypes.number,
  ranking: PropTypes.shape({
    userId: PropTypes.number,
    cash: PropTypes.number,
    value: PropTypes.number,
    bumps: PropTypes.number,
    dividends: PropTypes.number
  }),
  user: PropTypes.shape({
    college: PropTypes.string,
    firstName: PropTypes.string,
    lastName: PropTypes.string
  })
}

const mapStateToProps = (state, ownProps) => {
  if (ownProps.ranking) {
    return {user: getUser(state)(ownProps.ranking.userId)}
  }
  return {user: {}};
}

export default connect(mapStateToProps)(RankingRow);
