Cake Manager Micro Service (fictitious)
=======================================

The system now consists of two projects:
    cake-manager - provides restful endpoints to provide cake information and enable the addition of cakes
    https://github.com/nikcross/cake-manager

    cake-manager-web-client - an implementation of a human-readable client for managing cakes
    https://github.com/nikcross/cake-manager-web-client

The service is based on Spring Boot and can be started by running the CakeApplication class

The service can alternatively be run in a docker container:
    open a terminal
    build the project by running 'mvn clean install' from the root of the project
    change to the docker directory
    run build-and-run-cake-manager.sh

Once the cake-manager service has been started, run the cake-manager-web-client
    open a terminal in the root of the cake-manager-web-client project
    run 'npm run serve'
    open localhost:8081
