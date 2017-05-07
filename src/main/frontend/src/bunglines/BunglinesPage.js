import React, {PropTypes} from 'react';
import { connect } from 'react-redux';
import {Tabs, Tab} from 'material-ui/Tabs';

import BunglineGenderTabContent from './BunglineGenderTabContent';
import { cancelBuyMember } from '../usercrews/actions';

export const BunglinesPage = ({ cancelBuyMember }) => (
  <Tabs onChange={cancelBuyMember}>
    <Tab label="Men">
      <BunglineGenderTabContent sex="male" />
    </Tab>
    <Tab label="Women">
      <BunglineGenderTabContent sex="female" />
    </Tab>
  </Tabs>
);

BunglinesPage.propTypes = {
  cancelBuyMember: PropTypes.func
}

export default connect(null, { cancelBuyMember })(BunglinesPage);
