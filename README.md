<h2 align="center">Ticket system Backend </h3>


This is not a completed project.

## Outstanding work
* move to RDBMS as this is transaction system
* use vault solution for password/jwt shared key (e.g. jboss vault)
* remove in-build tomcat and host under middleware(e.g. jboss/websphere)
* code clean up, refactoring & unit testing
* security code scanning(e.g. sonarqube)



## Highlevel design 
Spring Web application
    
### Database (SQLITE)
* Table for Admin setup (Movie, Movie_Time, Hall)
* Transaction Table(Booking, Booking_Seat)
* Booking.status 
1. DRF - draft, when no seat is selected
2. PEN - pending, after seat is selected but pending final submission
3. COM - submitted
* Scheduler is implemented to housekeep stalled bookings(e.g. user leave after halfway of booking)

### Security 
* https shall be enabled to ensure channel encryption
* jwt(one way) is employed to ensure the request is un-altered by MITM attack
* Use only data from user when it is absolutely required.
* Data validation is performed at the api level to ensure no malicious input
* Front end and backend shall be deployed under single domain and crossorigin shall be disabled
* reverse proxy shall be implemented for multi tier deployment architeture
* load balance is possible if backend upgraded to use MQ/RDBMS
* record state validation are implemented(DRF->PEN->COM) to ensure the integrity of application flow, this can be extended when there are payment incorperated
    
   
## Development & Deployment
* Compile and package with maven, with JDK 11 or above.
* Package command
```sh
mvn package 
```
    
* A WebMvcConfigurerAdapter is included to made easy for development, developer can deploy the frontend reactjs app into ./web/ folder, tomcat will serve the html int he folder at root, e.g. http://localstho:8080/index.html
* A testing record method is included to help environment setup, once the backend is up, trigger below url to create dummy records
```sh
curl <url>/api/movie/v1/dummy
```
* for running in standalone environment with all default parameters(warning : same db crendential & jwt secret)
```sh
java -jar ticketbackend-0.1.jar --server.port=8080
```


### Application variables
	    
* spring.jpa.database-platform=com.hackit.sqlite.SQLDialect
* spring.jpa.hibernate.ddl-auto=update 
* spring.datasource.url = jdbc:sqlite:<path to sqlite db file>
* spring.datasource.driver-class-name = org.sqlite.JDBC
* spring.datasource.username = <db user name>
* spring.datasource.password = <db password>
* hackit.jwt.secret=<jwt secret>
* hackit.jwt.validity.seconds=300
* hackit.jwt.disabled=<disable jwt validation>
* hackit.bookingseat.housekeeping.miliseconds=30000
* hackit.booking.expiry.seconds=500
* spring.jpa.show-sql=false
* logging.level.com.hackit.controller.MovieController=DEBUG 
* logging.level.com.hackit.controller.BookingController=DEBUG 
* logging.level.com.hackit.sc## Reference sourceheduler.SeatHousekeeping=DEBUG 
* logging.level.com.hackit.util.SecurityHelper=DEBUG 

## Reference source 
* https://spring.io/guides/gs/rest-service/
* https://mobiarch.wordpress.com/2020/11/25/restful-web-service-using-spring-boot-and-vs-code/
* https://fullstackdeveloper.guru/2020/05/01/how-to-integrate-sqlite-database-with-spring-boot/
* https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c


