import React, {PropTypes, Component} from 'react';
import { connect } from 'react-redux';
import {Tabs, Tab} from 'material-ui/Tabs';

import UserGenderTabContent from './UserGenderTabContent';

class UserPage extends Component {
  render() {
    return (
      <Tabs>
        <Tab label="Men">
          <UserGenderTabContent sex="male" userId={parseInt(this.props.params.userId, 10)} />
        </Tab>
        <Tab label="Women">
          <UserGenderTabContent sex="female" userId={parseInt(this.props.params.userId, 10)} />
        </Tab>
      </Tabs>
    );
  }
}

UserPage.propTypes = {
  params: PropTypes.shape({
    userId: PropTypes.string
  })
}

export default connect()(UserPage);
