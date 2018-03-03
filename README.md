# DreamBumps

An application to make Oxford bumps racing EVEN MORE FUN!

## Development

Run the server:

    ./gradlew

Or run in an IDE (e.g. Eclipse, IntelliJ). This can either be configured to use H2 (in memory database started by the process) or MySQL/MariaDB, which you'll have to run separately.

TODO: provide a more complete start position.

Run the client in webpack-dev-server:

    cd ./src/main/frontend
    yarn
    yarn install

Note that the database is initially pretty empty - it doesn't even start properly to day zero, and definitely has no crews.

## Building for production

Build the front end code to be served by the backend:

    cd ./src/main/frontend
    yarn
    yarn build

Then need to copy the output (everything in dist) to be served by the frontend (where?? Might be src/main/webapp, might be src/main/frontend/static).

To optimize the DreamBumps application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/\*.war

## Deployment to Heroku

Do the production front end build first.
Then follow [these instructions](http://www.jhipster.tech/heroku/).

This is how the app has been deployed so far.
Note that the app will probably overflow the free tier of JawsDB.

## Deployment to AWS

TODO - probably use Docker?

## Deployment with Docker (e.g. AWS)

TODO

## Building for Torpids/Eights

* There's a backend config file (Constants) with number of crews etc. The front end gets this over the API.
* Some string changes are required in the front end. Could move these to being pulled from the API.

## Plans

* Fix some bugs
* Improve error handling
* Build the automatic data collection into the app
* Maybe a new backend? The Spring one is pretty heavy...
