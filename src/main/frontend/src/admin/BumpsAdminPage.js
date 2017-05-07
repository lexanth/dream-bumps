import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {Card, CardTitle, CardText, CardActions} from 'material-ui/Card';
import {Grid, Cell} from 'material-grid/dist';
import {SortableContainer, SortableElement, arrayMove} from 'react-sortable-hoc';
import RaisedButton from 'material-ui/RaisedButton';
// import {List, ListItem} from 'material-ui/List';
// import Subheader from 'material-ui/Subheader';

import { fetchCrews } from '../crews/actions';
import { getCrewsByDivision, getCurrentDay } from '../rootReducer';
import { uploadBumps } from './actions';
/**
 * BumpsAdminPage
 */

const SortableCrewItem = SortableElement(({crew, showBumps}) => {
  // TODO: Make it look good. Including a display of the bumps score
  if (crew.header) {
    return (
      <Card style={{backgroundColor: 'whitesmoke', margin: '4px'}}>
        <CardText style={{paddingLeft: '16px', paddingRight: '16px', paddingTop: '8px', paddingBottom: '0'}}>
          {crew.name}
        </CardText>
      </Card>);
  }
  let bumpColour;
  if (crew.bumps > 0) { bumpColour = 'green';}
  else if (crew.bumps < 0) {bumpColour = 'red';}
  else {bumpColour = 'rgb(0, 188, 212)';}
  return (
    <Card style={{margin: '4px'}}>
      <CardText style={{paddingLeft: '16px', paddingRight: '16px', paddingTop: '8px', paddingBottom: '0'}}>
        <div style={{display: 'inline'}}>
          <div style={{display: 'inline'}}>{`${crew.position} - ${crew.name}`}</div>
          {
            showBumps ?
            <div style={{float: 'right', display: 'inline', borderRadius: '50%', backgroundColor: bumpColour, color: '#fff', height: '30px', width: '30px', marginTop: '-7px', lineHeight: '30px', textAlign: 'center'}} >
              <span style={{fontWeight: 'bold', }}>{crew.bumps > 0 ? `+${crew.bumps}` : crew.bumps === 0 ? '-' : crew.bumps}</span>
            </div>
            : null
          }
        </div>
      </CardText>
    </Card>);
})

const SortableCrewList = SortableContainer(({crews, disabled, showBumps}) => (
  <div>
    {crews.map((crew, index) => (
      <SortableCrewItem key={`item-${index}`} index={index} crew={crew} disabled={disabled || crew.header} showBumps={showBumps} />
    ))}
  </div>
))

// const SortableCrewItem = SortableElement(({crew}) => {
//   if (crew.header) {
//     return <Subheader>{crew.name}</Subheader>;
//   }
//   return (
//     <ListItem
//       primaryText={`${crew.position} - ${crew.name}`}
//     />
//   );
// })
//
// const SortableCrewList = SortableContainer(({crews, disabled}) => (
//   <List>
//     {crews.map((crew, index) => (
//       <SortableCrewItem key={`item-${index}`} index={index} crew={crew} disabled={disabled || crew.header} />
//     ))}
//   </List>
// ))

class BumpsAdminPage extends Component {
  componentDidMount() {
    this.props.fetchCrews(this.props.params.sex);
  }

  setCrews(props) {
    const crewListWithDivisions = [];
    Object.keys(props.crews).forEach(division => {
      crewListWithDivisions.push({name: `Division ${parseInt(division, 10)+1}`, header: true});
      props.crews[division].map(crew => Object.assign({}, crew)).forEach(crew => {
        crew.originalPosition = crew.position;
        crew.bumps = 0;
        crewListWithDivisions.push(crew);
      });
    });
    this.setState({crewListWithDivisions, startCrewListWithDivisions: crewListWithDivisions.map(crew => Object.assign({}, crew))});
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.crews) {
      this.setCrews(nextProps);
    }
  }

  onReset() {
    this.setCrews(this.props);
  }

  onSave() {
    this.props.uploadBumps(this.state.crewListWithDivisions.filter(crew => !crew.header), this.props.day + 1);
  }

  onSortEnd(params) {
    const {oldIndex, newIndex} = params;
    // TODO: Find all crews between these indexes and update their positions
    // console.log(params);
    // console.log(this.state.crewListWithDivisions);
    // const lowerAffectedIndex = oldIndex < newIndex ? oldIndex : newIndex;

    // Positive movement is movement down (higher position, negative bumps)
    const movement = newIndex - oldIndex;

    const affectedCrews = this.state.crewListWithDivisions.slice(movement > 0 ? oldIndex : newIndex, movement > 0 ? newIndex+1 : oldIndex+1);
    // console.log(affectedCrews);

    // All other crews move in the opposite direction, and can only move one place
    affectedCrews.forEach(crew => {
      if (crew === this.state.crewListWithDivisions[oldIndex]) {
        crew.bumps -= movement;
        crew.position += movement;
      } else {
        if (movement > 0) {
          crew.position -= 1;
          crew.bumps += 1;
        } else {
          crew.position += 1;
          crew.bumps -= 1;
        }
      }
    })

    // TODO - handle moves across divisions

    this.setState({
      crewListWithDivisions: arrayMove(this.state.crewListWithDivisions, oldIndex, newIndex),
    });
  };

  render() {
    if (!this.props.crews || !this.state || !this.state.crewListWithDivisions || !this.state.startCrewListWithDivisions) {
      return <div>Loading...</div>
    }
    return (
      <Grid>
        <Cell col={3} >
          <Card>
            <CardTitle title={`Day ${this.props.day + 1} Start Order`} />
            <CardText>
              <SortableCrewList crews={this.state.startCrewListWithDivisions} disabled showBumps={false}/>
            </CardText>
          </Card>
        </Cell>
        <Cell col={9} >
          <Card>
            <CardTitle title={`Day ${this.props.day + 1} Finish Order`}/>
            <CardText>
              <SortableCrewList crews={this.state.crewListWithDivisions} onSortEnd={this.onSortEnd.bind(this)} showBumps />
            </CardText>
            <CardActions>
              <RaisedButton primary label="Save" onClick={this.onSave.bind(this)}/>
              <RaisedButton label="Reset" onClick={this.onReset.bind(this)} />
            </CardActions>
          </Card>
        </Cell>
      </Grid>
    );
  }
}

BumpsAdminPage.propTypes = {
  sex: PropTypes.string,
  day: PropTypes.number,
  crews: PropTypes.object,
  uploadBumps: PropTypes.func,
  fetchCrews: PropTypes.func,
  params: PropTypes.object
}

const mapStateToProps = (state, {params}) => ({
  crews: getCrewsByDivision(state)(params.sex),
  day: getCurrentDay(state)
});

export {BumpsAdminPage};

export default connect(mapStateToProps, {fetchCrews, uploadBumps})(BumpsAdminPage);
