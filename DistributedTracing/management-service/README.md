# Management Service

**Management service** is a simple RESTful web service built using Spring Boot.

Feign is a tool that you can use for microservices communication.

The management service demonstrates the use of Feign client by communicating with [employee-service](https://github.com/nileshwaani/tutorials/tree/master/Spring-REST/employee-service).

## Running the service

Follow the below instructions to set up and start the management service on your local machine:

1. Setup the employee service by following the steps mentioned [here](https://github.com/nileshwaani/tutorials/blob/master/Spring-REST/employee-service/README.md).

2. Make sure that employee service is up and running.

3. Clone the management service repository on your machine.

4. Make sure that you specify the right port in **management-service/src/main/resources/application.properties**.

5. Open the command prompt and change to the directory management-service.

6. Start the service using `gradle bootRun`

## Endpoints Documentation
### GET /management/employees

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
### GET /management/employees/{employeeId}

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
### GET /management/employees/search?firstName={firstName}

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
### POST /management/employees

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
### PUT /management/employees/{employeeId}

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
