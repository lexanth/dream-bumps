import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';
import { Table, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import {getUserCrewRanking} from '../rootReducer';
import {fetchUserCrewRanking} from './actions';

class UserCurrentScore extends Component {
  componentDidMount() {
    this.props.fetchUserCrewRanking(this.props.userId, this.props.sex);
  }

  render() {
    const ranking = this.props.ranking || {};
    return (
      <Card>
        <CardTitle title="Current Score" />
        <CardText>
          <Table
            selectable={false}
          >
            <TableBody
              displayRowCheckbox={false}
            >
              <TableRow>
                <TableRowColumn>Bumps</TableRowColumn>
                <TableRowColumn>{ranking.bumps}</TableRowColumn>
              </TableRow>
              <TableRow>
                <TableRowColumn>Cash</TableRowColumn>
                <TableRowColumn>{ranking.cash}</TableRowColumn>
              </TableRow>
              <TableRow>
                <TableRowColumn>Crew Value</TableRowColumn>
                <TableRowColumn>{ranking.value}</TableRowColumn>
              </TableRow>
              <TableRow>
                <TableRowColumn>Dividends</TableRowColumn>
                <TableRowColumn>{ranking.dividends}</TableRowColumn>
              </TableRow>
              <TableRow>
                <TableRowColumn>Score</TableRowColumn>
                <TableRowColumn>{(ranking.cash + ranking.value).toFixed(2)}</TableRowColumn>
              </TableRow>
            </TableBody>
          </Table>
        </CardText>
      </Card>
    );
  }
}

UserCurrentScore.propTypes = {
  ranking: PropTypes.shape({
    bumps: PropTypes.number,
    cash: PropTypes.number,
    value: PropTypes.number,
    dividends: PropTypes.number
  }),
  fetchUserCrewRanking: PropTypes.func,
  userId: PropTypes.number,
  sex: PropTypes.string
};

export const mapStateToProps = (state, ownProps) => ({
  ranking: getUserCrewRanking(state)(ownProps.userId, ownProps.sex)
});

export {UserCurrentScore};

export default connect(mapStateToProps, {fetchUserCrewRanking})(UserCurrentScore);
