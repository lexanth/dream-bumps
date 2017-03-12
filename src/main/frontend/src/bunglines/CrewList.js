import React, {PropTypes} from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText} from 'material-ui/Card';

import { fetchCrews } from '../crews/actions';
import { getNumberOfDivisions, getCrewsByDivision } from '../rootReducer';
import DivisionTable from './DivisionTable';

class CrewList extends React.Component {
  componentWillMount() {
    this.props.fetchCrews(this.props.sex);
  }

  render() {
    return (
      <Card>
        <CardTitle title="Starting order for Torpids 2017" />
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
  numberOfDivisions: PropTypes.number
};

const mapStateToProps = (state, ownProps) => ({
  crews: getCrewsByDivision(state)(ownProps.sex),
  numberOfDivisions: getNumberOfDivisions(state)(ownProps.sex)
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
