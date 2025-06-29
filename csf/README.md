# Checkout Staffing Application API

This is a Spring Boot application for managing employee's shifts, including schedule wishes coming from them and schedule overview.

## Features
- 🔐Basic Auth for protected endpoints
- 🗓️ Create and assign schedule wishes
- 🧑‍🤝‍🧑 Create early and late shifts
- 📄 Swagger/OpenAPI documentation
- 🧪 Unit & integration tests
- ⚙️ Database Migration

## Technologies

- Java 17+
- Maven
- Spring
    - _Spring Boot_
    - _Spring Security_
    - _Spring Data JPA_
- PostgreSQL
- H2 (testing)
- Flyway
- Swagger/OpenAPI
- JUnit5 & Mockito (testing)

## Getting started

**OpenAPI Documentation:** http://localhost:8080/swagger-ui.html

**!** Before running the application, set up a postgres server on **localhost:5432** named "**csf**"  
with username = 'postgres' and password = 'root' (or if you do set them up differently, modify the 'application.properties' file)

At first execution, flyway will run 'V1__init_schema.sql' which creates the tables for the entities and then 'V2__seed_users.sql' which populates the database with a total of 7 users (1 admin + 6 employees)

### Authentication & Security
    
The application used Basic Auth, requesting credentials with each request via Swagger UI's "Authorize" button or by making use of a tool like Postman  
There are two types of roles in this application with corresponding endpoints access: 
- ADMIN
  - Wishbook
  - Planning
  - Schedule
- EMPLOYEE
  - Wishbook
  - Schedule

Admin Account: 
- email: admin@admin.com
- password: admin  

Employee Account:
- email: employee<nr>@employee.com (ex. 'employee1@employee.com')
- password: employee

Passwords are hashed before being inserted in the database via 'BCrypt' function.

### Endpoints of interest:
- Wishbook: http://localhost:8080/csf/wishbook
  - Allow employees to enter their desired shifts per date
  - Type: POST
  - Awaits Request Body:   
     {  
      "date": "2025-06-29",  
      "shift": "EARLY"  
     }
- Planning: http://localhost:8080/csf/planning
  - Allow admin users to assign wish book entries to the schedule, ensuring each employee has only one shift per day. 
  The plan should include two employees for each shift  
  - Type: POST
  - Awaits 2x Request Parameters:  
    - wish1: integer
    - wish2: integer
- Schedule: http://localhost:8080/csf/schedule
  - Provide the schedule per day to both employees and admins
  - Type: GET
  - Awaits Request Parameter:
    - date: String (format: yyyy-mm-dd)


### Database Migration

Flyway automatically applies migration on startup, and I've taken the time to seed the tables, meaning the application will start with a base pool of users (both employees and admins), schedules and wishes.

### Testing

For testing purposes, unit and integration tests were developed to both test unit functionality and application flow between services.
Unit Tests: 15  
Integration Tests: 5

For unit tests, JUnit5 was used in combination with Mockito in order to mock outside entities, and they performed at a service & validators layer, while integration tests were performed at the controller layer.  

### Contact

Name: Gheorghe-Andrei Negrea  
Email: [negreaandrei90@proton.me](mailto:negreaandrei90@proton.me)