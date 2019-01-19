## MUZIX

### Spring Boot  REST Api using the Spring Boot,  H2DB and postman 

### Problem Statement

 To develop Muzix Application and perform the basic CRUD Operations and store in the H2 Database


### Expected solution
Open the Postman and perform the various CRUD Operations in postman  and check the H2 Database to check if the data is stored Consistently




###   * Built Using
              * [Java] - The Programming Language used
	      * [Spring-Boot] - The web framework used
	      * [H2DB] - The database framework used
              * [Maven] - Dependency Management 
              * [Postman] - To test the Api


###  Muzix Application TASK

    Tasks to be Done 
 1. Complete all the endpoints for CRUD operations on Muzix2.
 2. Use h2-console to view in-memory data3.
 3. Add an endpoint to search trackByName. Understand @Query and parameter passing to@Query
 4. Generate API documentation using Swagger 25.
 5. Create custom exceptions TrackNotFoundException, TrackAlreadyExistsException in acom.stack....exceptions package. Perform appropriate exception handling and propagationBack.
 6. Running Logic on Startup in Spring. Create seed data to pre-fill the database with trackinformation whenever the application starts. Use both approaches:Approach 1: ApplicationListener<ContextRefreshedEvent>Approach 2: CommandLineRunner (Find out how it differs from ApplicationRunner)

 7. Global exception using Controller advice
 8. Remove all hard coded data from the application code to application.propertiesa)by using @Value.b)by using @PropertySourcec)by using ​​ Environment(https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/env/Environment.html)
 9. Add @Lombok

###  Completed all the task of Muzix Application followed the below approaches
This is a Maven project with:
Java version:11.0.1 and Spring Boot: 2.1.2

In this Project i have completed all the CRUD operations on Tracks.
Database used is H2DB.
Custom Exceptions are also included.
Swagger for api documenting in spring is also included. 
Lombok is used to minimize domain entity code.
Seed data to Pre fill DB on spring startup is included.
Have used H2 console to view the data in database.
Completed the logic to find track by name.



#### To use this project, you can follow these steps

1. Clone the folder ***Muzix-Rest-H2DB*** to your  local machine
     
2. Navigate to Muzix-Rest-H2DB folder

    `cd Muzix-Rest-H2DB`

3. Open it to the IntelliJ Idea and wait for the dependencies to be downloaded

4. Change the H2 Database in the application properties file

5. Now execute the  ***TrackMain.java*** file 

6. Open the Postman and Perform the various CRUD Operations 

7. Open the H2 console and check the database, tables and data inserted into it through the various CRUD operations of the Rest Api 



***Thats It***
