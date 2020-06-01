# Car Warehouse Service

This service developed using SpringBoot 2 framework, Main purpose of this service is to cater backend functionality for car warehouse users 

    This service running on port 50010, below are endpoints
    
    GET /getVehicleDetailList

### Prerequisites to run service

Need below listed software/package installed on machine to run this service

    Java 8
    MongoDB

### Interaction with other services

Below service will consume this service API 

    car-warehouse-ui

### Service configuration

Service configuration is located in below YAML file

    config/application.yml
    
### Build service

To build service, use below mentioned command

```
gradlew clean build
```

To skip the test while building, use below mentioned command

```
gradlew clean build -x test
```

### Run service

To run the service, use below mentioned command

```
gradlew clean build bootRun
```
 
### Run unit test

To run unit test, use below mentioned command
```
gradlew test
```

### Run pitest

To run pitest, use below mentioned command
```
gradlew pitest
```

### Test coverage

To run test coverage, run below command

```
gradlew testCoverage
```

### Build vulnerability check

To make sure library we used in service not containing any vulnerability. Please run below task

```
gradlew dependencyCheck
```


### Authors

* **Dipakkumar Bhoova** (https://github.com/bhuva3)
