# Fundraise Application
Fund collecting application for events using blockchain technology


## [setup local environment and run the application](http://gitlab.monstar-lab.com.bd/mahfuj/fund-raise/wikis/home#set-up-local-environment)

## [Swagger ui](http://localhost:8080/swagger-ui.html) is available for the api endpoints.

## After [deploying contract](http://localhost:8081/swagger-ui.html#/contract-controllers/deployUsingPOST) the contract address from the response should be provided to the `contract.fundmanager.address` field in the `fund-raise/src/main/resources/config.properties` file and application should be restarted.