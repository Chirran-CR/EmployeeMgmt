# Employee Management System

This Spring Boot application provides a comprehensive solution for managing employee records. It utilizes MongoDB for data storage and implements CRUD (Create, Read, Update, Delete) operations to manage employee data efficiently. The application adheres to the MVC (Model-View-Controller) pattern and employs best practices for logging and testing.

## Features

- **Create Employee:** Add new employee records to the database.
- **Get All Employees:** Retrieve a list of all employees.
- **Get Employee by ID:** Fetch employee details based on a unique identifier.
- **Get Employee by Name:** Search for employees by their name.
- **Update Employee Data:** Modify specific fields of an employee record.
- **Delete Employee by ID:** Remove an employee record using its ID.
- **Delete All Employees by Name:** Delete all employee records that match a given name.

## Technologies

- **Spring Boot:** Framework for building the application.
- **MongoDB:** NoSQL database used for storing employee data.
- **SLF4J:** Logging framework used for tracking application events.
- **JUnit & Mockito:** Libraries used for writing unit tests.
- **MockMvc:** Tool for testing Spring MVC controllers.

## Architecture

- **MVC Pattern:** Separation of concerns with distinct Model, View, and Controller layers.
- **Service Layer:** Contains business logic and data manipulation.
- **Controller Layer:** Handles HTTP requests and responses, mapping them to service methods.

## Testing

- Comprehensive unit tests for both service and controller layers.
- Mocking dependencies with Mockito for isolated tests.
- Validation of API endpoints using MockMvc.

## Getting Started

1. Clone the repository: `git clone https://github.com/chirran-CR/employee-management-system.git`
2. Navigate to the project directory: `cd employee-management-system`
3. Build and run the application: `./mvnw spring-boot:run`
4. Access the API endpoints as described in the [API Documentation](link-to-api-docs).
