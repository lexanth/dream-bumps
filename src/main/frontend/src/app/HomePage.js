// @flow
import React from 'react';
import { Card, CardTitle, CardText, CardMedia } from 'material-ui/Card';
import { Grid, Cell } from 'material-grid/dist';
// import {Link} from 'react-router';

import bumpImg from '../../static/bump.jpg';

const HomePage = () => {
  return (
    <Grid>
      <Cell col={12}>
        <Card>
          <CardMedia
            overlay={
              <CardTitle
                title="Dream Bumps"
                titleStyle={{ fontSize: '48px' }}
                subtitle="Summer Eights 2017"
                subtitleStyle={{ fontSize: '28px' }}
              />
            }
          >
            <img src={bumpImg} alt="" />
          </CardMedia>

        </Card>
      </Cell>
      <Cell col={6} tablet={12}>
        <Card>
          <CardTitle title="About" />
          <CardText>
            You think your crew is fast. Or slow.<br />
            <br />
            You don't keep your eyes in the boat in training. Always checking out the opposition.
            <br />
            <br />
            You spend your evenings reading the latest gossip on the latest RowChat incarnation.
            <br />
            <br />
            <br />
            <br />
            Put your (fake) money where your mouth is.
          </CardText>
        </Card>
        <Card style={{ marginTop: '16px' }}>
          <CardTitle title="Get in touch" />
          <CardText>
            <p>
              This has been built in a slight rush to be ready for Eights. And in limited spare time. So I'm very confident that there are loads of bugs floating around all over the place.
              <br />
              <br />
              If you spot something, please give me a shout at dreambumps (at) gmail.com. Or get in touch with any questions, or if you'd like to help fix the bugs too!
              <br />
              <br />
              In the mean time, refreshing the page can sometimes fix things.
            </p>
          </CardText>
        </Card>
        <Card style={{ marginTop: '16px' }}>
          <CardTitle title="Credits" />
          <CardText>
            Oliver Clarke - Images
            <br /><br />
            OURCs - Organising Eights
            <br /><br />
            DONALD_BUMP - Testing
          </CardText>
        </Card>
      </Cell>
      <Cell col={6} tablet={12}>
        <Card>
          <CardTitle title="FAQs" />
          <CardText>
            <b>What is Dream Bumps?</b>
            <p>
              Dream Bumps is a fantasy sports game based around Oxford college rowing.
              Players buy and sell rowers to get a strong crew.
              After racing each day, dividends are paid based on the performance of the rowers in that day's racing.
              The extra cash can then be used to upgrade your crew, or you can sell those rowers that have underperformed.
            </p>
            <b>Can I have multiple rowers from the same crew?</b>
            <p>Yes.</p>
            <b>Are the men's and women's games linked?</b>
            <p>
              The games are separate (you can't move money between them). But the overall score is combined across the two games.
            </p>
            <b>What happens during racing?</b>
            <p>
              When racing starts, the market is locked down until bumps are finalised.
            </p>
            <b>Where are the rowing on crews?</b>
            <p>
              The rowing on divisions get finalised later. We'll endeavour to update the crews as soon as possible.
            </p>
            <b>
              My name is spelt wrong! My crew list is wrong! The start list is wrong!
            </b>
            <p>Get in touch so we can update it. dreambumps (at) gmail.com.</p>
            <b>How do the prices work?</b>
            <p>
              Initial prices were set in descending order based on start order. As soon as the market opens, prices move with demand.
            </p>
            <b>How do dividends work?</b>
            <p>
              Your crew gets a dividend for rowing over, based on the position on the river.
              There is an extra bonus for rowing over at head of the river.
              The dividend for bumping is 3 times the dividend for rowing over.
              Overbumps pay more.
            </p>
            <b>
              Where's the Datacentre (or other favourite Fantasy Bumps feature)?
            </b>
            <p>
              Sorry, I haven't got round to doing that yet! I've hacked this together in my spare time, so happy for someone else to give me a hand if they want to add anything.
            </p>
            <b>I'm at Cambridge, can I play?</b>
            <p>
              You can play the Oxford game straight away! But if you want to have Lents/Mays, get in touch and I can help you get set up.
            </p>
            <b>Where is the data from?</b>
            <p>
              All data is freely available at www.ourcs.org.uk. But if you'd like your name removed, let me know.
            </p>
          </CardText>
        </Card>
      </Cell>
    </Grid>
  );
};

export default HomePage;
