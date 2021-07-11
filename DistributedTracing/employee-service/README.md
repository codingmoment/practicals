# Employee Service

**Employee service** is a simple RESTful web service built using Spring Boot. You can perform basic CRUD operations for employees using this service. The service does not use any persistent storage to save the employees, it just stores the data in-memory.

## Running the service

Follow the below instructions to set up and start the employee service on your local machine:

1. Clone the repository on your machine.

2. By default, the service uses the port 8080. To change the port, add below property in **application.properties**.

2. Open the command prompt and change to the directory employee-service.

3. Start the service using `gradle bootRun`

## Endpoints Documentation
### GET /employees

Get all the employees.

**Response**

List of all employees with their details.

```
[
  {
    "employeeId":1
    "firstName":"John",
    "lastName":"Williams",
    "emailId":"j.williams@test.com",
    "birthDate":"1980-01-01"
  },
  {
    "employeeId":2
    "firstName":"Peter",
    "lastName":"Jackson",
    "emailId":"p.jackson@test.com",
    "birthDate":"1988-05-04"
  },
]
```
---
### GET /employees/{employeeId}

Get the specific employee.

**Request**

_Parameter_

employeeId - The ID of the employee to fetch.

**Response**

Details about the requested employee.

```
{
  "employeeId":1
  "firstName":"John",
  "lastName":"Williams",
  "emailId":"j.williams@test.com",
  "birthDate":"1980-01-01"
}
```
---
### GET /employees/search?firstName={firstName}

Search the employees for the given first name.

**Request**

_Parameter_

firstName - First name to search for.

**Response**

List of the employees matching the first name.

```
[
  {
    "employeeId":1
    "firstName":"John",
    "lastName":"Williams",
    "emailId":"j.williams@test.com",
    "birthDate":"1980-01-01"
  },
  {
    "employeeId":6
    "firstName":"John",
    "lastName":"Abraham",
    "emailId":"j.abraham@test.com",
    "birthDate":"1988-05-04"
  },
]
```
---
### POST /employees

Create a new employee.

**Request**

_Body_

Details of employee to be created.
```
{
  "firstName":"John",
  "lastName":"Williams",
  "emailId":"j.williams@test.com",
  "birthDate":"1980-01-01"
},
```

**Response**

Details of newly created employees with auto-generated Id.

```
{
  "employeeId":1
  "firstName":"John",
  "lastName":"Williams",
  "emailId":"j.williams@test.com",
  "birthDate":"1980-01-01"
}
```
---
### PUT /employees/{employeeId}

Update existing employee.

**Request**

_Parameters_

employeeId - The ID of the employee to be updated.

_Body_

New details about the employee.
```
{
  "firstName":"John",
  "lastName":"Williams",
  "emailId":"j.williams@test.com",
  "birthDate":"1980-01-01"
},
```

**Response**

Details of the updated employees.

```
{
  "employeeId":1
  "firstName":"John",
  "lastName":"Williams",
  "emailId":"j.williams@test.com",
  "birthDate":"1980-01-01"
}
```
