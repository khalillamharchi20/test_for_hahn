# Employee Management Application

## Overview
This application is an Employee Management System designed to handle employee records, roles, and departments. It is built with Spring Boot and uses MySQL as its database. The application supports role-based access control and provides RESTful API endpoints documented with Swagger.

## Features
- **Employee Management:** Create, update, delete, and retrieve employee data.
- **Role-Based Access Control:** Access is restricted based on roles (`ADMINISTRATOR`, `HR`, `MANAGER`).
- **Search and Filter:** Search employees by name, ID, department, or job title. Filter by employment status, department, and hire date.
- **API Documentation:** Swagger documentation is available at [http://localhost:6868/swagger-ui/index.html#/](http://localhost:6868/swagger-ui/index.html#/).

## Running the Application with Docker Compose

### Prerequisites
Ensure you have the following installed on your system:
- Docker
- Docker Compose

### Steps to Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>