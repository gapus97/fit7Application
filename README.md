#SpringBootFit7App
## Tools that I used
I used gradle, Java 11, MySql and SpringBoot FrameWork. Application is deployed on Heroku cloud, I tried using Google Cloud
for deploying the app, but unfortunately I don't own a credit card so I had to use different solution.

## Building the app
First make a pull request from my github repository, after that go into directory that you have this application. 
If you have IntelliJ Idea open the project with File | Open and select the folder that gradle project is in it. 
Idea will import all the necessary libraries after some time, when it is finished with the import, you have to execute command from terminal: 
```
gradle clean build
```
## Application.yml
Here we have all the configurations for running application on heroku. For now properties
are set to:
```
    spring:
      datasource:
        driverClassName: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_fab8f05aca2364f
        username: b59ee0d8cd0b4a
        password: 01d73b53
      jpa:
        hibernate.ddl-auto: update
        generate-ddl: true
        show-sql: true
    server:
      port: 8082
    externalapi:
      url: https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner/
      username: fun7user
      password: fun7pass
```
App uses database that is deployed on cloud, this can be changed to local database with configuring spring datasource values.
When you run the app local, it is set to port 8082

## Tests
Tests are written under src/test/java and i wrote integration tests and unit test. With unit tests,
I had a problem mocking LocalDataTime so I changed the code so that CustomerSupport class gets localdatetime passed
into constructor from CheckServiceResource when API is called.

## Testing application
To see if the app is working send a post request with url and these parameters:
```
url: https://herokuservice-fit7.herokuapp.com/checkServices
timezone: "Europe/Ljubljana"
userId: "John doe"
cc: "US"
```

## Problems
When you will examine the code, you will se that there is GlobalProperties class Autowired into CheckServicesResource,
the best solution would be that it would be Autowired to the class that uses this. 
Same problem was with LocalDateTime, it would be easier if I could Autowire Clock.class to CustomerService feature and 
with this I could easily set up current time and also with testing CustomerService it would be much easier to mock Clock.
   