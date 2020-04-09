# Example to Demostrate Mircoservice with WebFLUX Task

How to run application 

-- Backend--
- The project is compose of 4 Service

    1. Discovery-layer- Build using Eureka
    2. API Gateway- Build Spring cloud gateway
    3. Product-service- Build using Springboot Webflux and Swagger
    4. Category-service- Build using Springboot WebFlux and swagger

- Integration test and unit Test are done in product-service.
- For Integration test Citrus-framework is used.
- To execute integration test run following command `mvn clean integration-test -P it`

