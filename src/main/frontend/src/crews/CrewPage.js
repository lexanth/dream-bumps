import React, {PropTypes} from 'react';
// import {Col, Grid} from 'react-flexbox-grid';
import {Grid, Cell} from 'material-grid/dist';
// import { connect } from 'react-redux';

// import { fetchCrew } from './actions';
import CrewMemberList from './CrewMemberList';
import CrewDetails from './CrewDetails';
import CrewPriceHistory from './CrewPriceHistory';

const CrewPage = ({ params }) => (
  <Grid>
    <Cell col={6}>
      <CrewDetails
        crewId={params.crewId}
      />
    </Cell>
    <Cell col={6}>
      <CrewMemberList
        crewId={params.crewId}
      />
    </Cell>
    <Cell col={12}>
      <CrewPriceHistory
        crewId={params.crewId}
      />
    </Cell>
  </Grid>
);

// class CrewPage extends React.Component {
//   componentWillMount() {
//     if (this.props.params.crewId) {
//       this.props.fetchCrew(this.props.params.crewId);
//     }
//   }
//
//   render() {
//     return (<div>MyComponent</div>);
//   }
// }

CrewPage.propTypes = {
  params: PropTypes.shape({
    crewId: PropTypes.string
  })
};

// const mapStateToProps = (state => ({
// });

// export default connect(null, { fetchCrew })(CrewPage);
export default CrewPage;
