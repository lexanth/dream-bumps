import React, {PropTypes} from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';

import { fetchCrews } from '../crews/actions';
import { getNumberOfDivisions, getCrewsByDivision, getCurrentDay } from '../rootReducer';
import DivisionTable from './DivisionTable';

class CrewList extends React.Component {
  componentWillMount() {
    this.props.fetchCrews(this.props.sex);
  }

  render() {
    const title = this.props.day >= 4 ? 'Finishing Order' : `Starting Order for Day ${this.props.day + 1}`
    return (
      <Card>
        <CardTitle title={title} />
        <CardText>
          {
            Object.keys(this.props.crews).map(key =>
              (
                <DivisionTable
                  sex={this.props.sex}
                  division={key}
                  key={key}
                  crews={this.props.crews[key]}
                />
              )
            )
          }
        </CardText>
      </Card>
    );
  }
}

CrewList.propTypes = {
  fetchCrews: PropTypes.func,
  sex: PropTypes.string,
  numberOfDivisions: PropTypes.number,
  crews: PropTypes.array,
  day: PropTypes.number
};

const mapStateToProps = (state, ownProps) => ({
  crews: getCrewsByDivision(state)(ownProps.sex),
  numberOfDivisions: getNumberOfDivisions(state)(ownProps.sex),
  day: getCurrentDay(state)
});

export default connect(mapStateToProps, { fetchCrews })(CrewList);

/*
<DivisionTable
  sex={this.props.sex}
  division={key}
  key={key}
  crews={this.props.crews[key]}
/>
*/
