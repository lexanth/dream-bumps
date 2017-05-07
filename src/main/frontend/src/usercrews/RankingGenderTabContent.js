import React, {PropTypes, Component} from 'react';
import { connect } from 'react-redux';
import {Grid, Cell} from 'material-grid/dist';
import {Card, CardText} from 'material-ui/Card';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow } from 'material-ui/Table';
import UpArrow from 'material-ui/svg-icons/navigation/arrow-upward';
import DownArrow from 'material-ui/svg-icons/navigation/arrow-downward';

import { getUserCrewRankings } from '../rootReducer';
import RankingRow from './RankingRow';

const SortableHeaderColumn = ({sortKey, label, onSort, currentSortKey, sortDescending}) => (
  <TableHeaderColumn
    onMouseUp={onSort}
    style={currentSortKey === sortKey ? {fontWeight: 'bold',paddingRight:0, width: '88px'} :  {paddingRight:0, width: '88px'}}
  >
    {label}
    {currentSortKey === sortKey && (sortDescending ? <DownArrow color="rgb(158, 158, 158)"/>:<UpArrow color="rgb(158, 158, 158)"/>)}
  </TableHeaderColumn>
)

SortableHeaderColumn.propTypes = {
  sortKey: PropTypes.string,
  label: PropTypes.string,
  onSort: PropTypes.func,
  currentSortKey: PropTypes.string,
  sortDescending: PropTypes.bool
};

class RankingGenderTabContent extends Component {
  // state: {
  //   sortBy: string,
  //   sortDescending: boolean
  // }
  constructor(props) {
    super(props);
    this.state = {sortBy: 'SCORE', sortDescending: true};
    // this.onSortByCash = this.onSortByCash.bind(this);
    // this.onSortByBumps = this.onSortByBumps.bind(this);
    // this.onSortByCrewValue = this.onSortByCrewValue.bind(this);
    // this.onSortByScore = this.onSortByScore.bind(this);
  }

  onSortByBumps() {
    this.setState({sortBy: 'BUMPS', sortDescending: !(this.state.sortBy === 'BUMPS' && this.state.sortDescending) });
  }

  onSortByCash() {
    this.setState({sortBy: 'CASH', sortDescending: !(this.state.sortBy === 'CASH' && this.state.sortDescending) });
  }

  onSortByCrewValue() {
    this.setState({sortBy: 'VALUE', sortDescending: !(this.state.sortBy === 'VALUE' && this.state.sortDescending) });
  }

  onSortByScore() {
    this.setState({sortBy: 'SCORE', sortDescending: !(this.state.sortBy === 'SCORE' && this.state.sortDescending)});
  }

  onSortByDividends() {
    this.setState({sortBy: 'DIVIDEND', sortDescending: !(this.state.sortBy === 'DIVIDEND' && this.state.sortDescending)});
  }

  onSortByTradingProfit() {
    this.setState({sortBy: 'TRADING', sortDescending: !(this.state.sortBy === 'TRADING' && this.state.sortDescending)})
  }

  render() {
    let sortedRankings = this.props.rankings || [];
    switch (this.state.sortBy) {
      case 'BUMPS':
        sortedRankings = sortedRankings.sort((r1, r2) => (r2.bumps - r1.bumps));
        break;
      case 'CASH':
        sortedRankings = sortedRankings.sort((r1, r2) => (r2.cash - r1.cash));
        break;
      case 'VALUE':
        sortedRankings = sortedRankings.sort((r1, r2) => (r2.value - r1.value));
        break;
      case 'SCORE':
        sortedRankings = sortedRankings.sort((r1, r2) => (r2.value + r2.cash - r1.value - r1.cash));
        break;
      case 'DIVIDEND':
        sortedRankings = sortedRankings.sort((r1, r2) => (r2.dividends - r1.dividends));
        break;
      case 'TRADING':
        sortedRankings = sortedRankings.sort((r1, r2) => (r2.value + r2.cash - r2.dividends - r1.value - r1.cash + r1.dividends));
        break;
      default:
        break;
    }
    if (!this.state.sortDescending) {
      sortedRankings.reverse();
    }
    return (
      <Grid>
        <Cell col={12}>
          <Card>
            <CardText>
              <Table
                selectable={false}
              >
                <TableHeader
                  adjustForCheckbox={false}
                  displaySelectAll={false}
                >
                  <TableRow>
                    <TableHeaderColumn style={{paddingLeft:0, paddingRight:0, width: '45px'}}>#</TableHeaderColumn>
                    <TableHeaderColumn>Name</TableHeaderColumn>
                    <TableHeaderColumn>College</TableHeaderColumn>
                    <SortableHeaderColumn
                      label="Bumps"
                      sortKey="BUMPS"
                      currentSortKey={this.state.sortBy}
                      sortDescending={this.state.sortDescending}
                      onSort={this.onSortByBumps.bind(this)}
                    />
                    <SortableHeaderColumn
                      label="Cash"
                      sortKey="CASH"
                      currentSortKey={this.state.sortBy}
                      sortDescending={this.state.sortDescending}
                      onSort={this.onSortByCash.bind(this)}
                    />
                    <SortableHeaderColumn
                      label="Crew Value"
                      sortKey="VALUE"
                      currentSortKey={this.state.sortBy}
                      sortDescending={this.state.sortDescending}
                      onSort={this.onSortByCrewValue.bind(this)}
                    />
                    <SortableHeaderColumn
                      label="Dividends"
                      sortKey="DIVIDEND"
                      currentSortKey={this.state.sortBy}
                      sortDescending={this.state.sortDescending}
                      onSort={this.onSortByDividends.bind(this)}
                    />
                    <SortableHeaderColumn
                      label="Trading Profit"
                      sortKey="TRADING"
                      currentSortKey={this.state.sortBy}
                      sortDescending={this.state.sortDescending}
                      onSort={this.onSortByTradingProfit.bind(this)}
                    />
                    <SortableHeaderColumn
                      label="Score"
                      sortKey="SCORE"
                      currentSortKey={this.state.sortBy}
                      sortDescending={this.state.sortDescending}
                      onSort={this.onSortByScore.bind(this)}
                    />
                  </TableRow>
                </TableHeader>
                <TableBody
                  displayRowCheckbox={false}
                >
                  {sortedRankings.map((ranking, index) =>
                    <RankingRow
                      number={index + 1}
                      ranking={ranking}
                      key={index}
                    />
                  )}
                </TableBody>
              </Table>
            </CardText>
          </Card>
        </Cell>
      </Grid>
    );
  }
};

RankingGenderTabContent.propTypes = {
  sex: PropTypes.string,
  rankings: PropTypes.array
}

const mapStateToProps = (state, ownProps) => {
  if (ownProps.sex === 'combined') {
    const maleRankings = getUserCrewRankings(state)('male');
    const femaleRankings = getUserCrewRankings(state)('female');
    return {
      rankings: maleRankings.map(maleRanking => {
        const ranking = {...maleRanking};
        ranking.sex = 'combined';
        const userId = maleRanking && maleRanking.userId ? maleRanking.userId : -1;
        const femaleRanking = femaleRankings.find(rank => userId === (rank ? rank.userId : -1) );
        if (femaleRanking) {
          ranking.value += femaleRanking.value;
          ranking.cash += femaleRanking.cash;
          ranking.bumps += femaleRanking.bumps;
          ranking.dividends += femaleRanking.dividends;
        }
        return ranking;
      })
    }
  }
  return {
    rankings: getUserCrewRankings(state)(ownProps.sex)
  }
};

export {SortableHeaderColumn, RankingGenderTabContent};

export default connect(mapStateToProps)(RankingGenderTabContent);
