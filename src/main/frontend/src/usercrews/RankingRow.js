// @flow
import React, {PropTypes, Component} from 'react';
import { TableRow, TableRowColumn } from 'material-ui/Table';
import { connect } from 'react-redux';
import { Link } from 'react-router';
import FlatButton from 'material-ui/FlatButton';

import { getUser } from '../rootReducer';
import { getCollegeName } from '../utils/colleges';

class RankingRow extends Component {
  render() {
    if (!this.props.ranking) {
      return <TableRow>Loading...</TableRow>
    }
    return (
      <TableRow>
        <TableRowColumn style={{paddingLeft:0, paddingRight:0, width: '45px'}}>{this.props.number}</TableRowColumn>
        {this.props.user ?
          <TableRowColumn>
            <FlatButton
              containerElement={<Link to={`/rankings/${this.props.user.id}`}/>}
              label={`${this.props.user.firstName} ${this.props.user.lastName}`}
              labelStyle={{textTransform: '', fontSize: '13px'}}
              style={{paddingLeft: 0, textAlign: '', marginLeft: '-16px'}}
            />
          </TableRowColumn>
        :
          <TableRowColumn>
            <FlatButton
              containerElement={<Link to={`/rankings/${this.props.ranking.userId}`}/>}
              label={`User ${this.props.ranking.userId}`}
              labelStyle={{textTransform: '', fontSize: '13px'}}
              style={{paddingLeft: 0, textAlign: '', marginLeft: '-16px'}}
            />
          </TableRowColumn>
        }
        {this.props.user ?
          <TableRowColumn>{getCollegeName(this.props.user.college)}</TableRowColumn>
        :
          <TableRowColumn />
        }
        <TableRowColumn style={{paddingRight:0, width: '88px'}}>{this.props.ranking.bumps}</TableRowColumn>
        <TableRowColumn style={{paddingRight:0, width: '88px'}}>{this.props.ranking.cash.toFixed(2)}</TableRowColumn>
        <TableRowColumn style={{paddingRight:0, width: '88px'}}>{this.props.ranking.value.toFixed(2)}</TableRowColumn>
        <TableRowColumn style={{paddingRight:0, width: '88px'}}>{this.props.ranking.dividends.toFixed(2)}</TableRowColumn>
        <TableRowColumn style={{paddingRight:0, width: '88px'}}>{(this.props.ranking.cash + this.props.ranking.value - (this.props.ranking.sex === 'combined' ? 2000 : 1000) - this.props.ranking.dividends).toFixed(2)}</TableRowColumn>
        <TableRowColumn style={{paddingRight:0, width: '88px'}}>{(this.props.ranking.cash + this.props.ranking.value).toFixed(2)}</TableRowColumn>
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
    dividends: PropTypes.number,
    sex: PropTypes.string
  }),
  user: PropTypes.shape({
    college: PropTypes.string,
    firstName: PropTypes.string,
    lastName: PropTypes.string,
    id: PropTypes.number
  })
}

export const mapStateToProps = (state:Object, ownProps:{ranking:{userId:number}}) => {
  if (ownProps.ranking) {
    return {user: getUser(state)(ownProps.ranking.userId)}
  }
  return {user: {}};
}

export {RankingRow};

export default connect(mapStateToProps)(RankingRow);
