# AWS Project

## Description
This project is a simple web service written in Spring Boot that manages Bosses och Slaves depending on your authority level. It includes CRUD functionality for handling bosses and slaves via Postman or a frondend application.

## Technologies
* Spring Boot
* MySQL Database
* GitHub Actions
* AWS

## Instructions

1. Ensure you have Java 21 installed on your machine.
2. Clone the project from the GitHub repository.
3. Configure your MySQL connection in the 'application.properties' file.
4. Open 'SpringBootApp' and run the proggram to start the application.
5. You can perform various CRUD operations in any REST client such as Postman or if you want to integrate a frondend application.

# API Documentation

## User Authentication

### Register User
* Method: POST
* URL: /auth/register
* Description: Register a new user
* Payload:
  ```json
  {
    "username": "string",
    "password": "string"
  }
* Response: Registred user

### Log in User
* Method: POST
* URL: /auth/login
* Description: Log in user
* Payload:
  ```json
  {
  "username": "string",
  "password": "string"
  }
* Response: User and token (User and JWT)

  ## ADMIN Permission

  ### Get All Users
* Method: GET
* URL: /auth/aukt
* Description: Get all users
* Response:
  * 200 + List of all users
  * 401, 403
 
  ### Upgrade User to Admin
* Method: PUT
* URL: /auth/aukt/{id}
* Description: Upgrade a user to admin
* Response:
  * 200 + The updated user
  * 400 + null (id does not exist)
  * 401, 403 missing permission
 
   ## Boss Controller (ADMIN Permission)

  ### Get all Bosses
* Method: GET
* URL: /bosses
* Description: Get all bosses
* Response:
  * 200 + List of all bosses
 
  ### Get Boss by ID
* Method: GET
* URL: /bosses/{id}
* Description: Get boss based on provided ID in URL
* Path parameters: id (long)
* Response:
  * 200 + Desired boss
  * 404
 
  ### Create a Boss
* Method: POST
* URL: /bosses
* Description: Create a boss
* Payload:
  ```json
  {
  "title": "string"
  }

* Response:
  * 201 - success + the new boss
  * 400 - failure
 
  ### Update Boss Title
* Method: PUT
* URL: /bosses/{id}
* Description: Update an existing boss title
* Payload:
  ```json
  {
  "title": "string"
  }
  
* Response:
  * 200 + The updated Boss
  * 404

 ### Delete a Boss
* Method: DELETE
* URL: /bosses/{id}
* Description: Delete a boss
* Response:
  * 200 + "The boss with ID{id} has been deleted."
  * 404 "The boss with ID{id} could not be found."
 
  ## Slave Controller (ADMIN and USER Permission)

  ### Get All Slaves (USER, ADMIN)
* Method: GET
* URL: /slaves
* Description: Get all slaves ready to work
* Response:
  * 200 + List of slaves
  * No content
 
  ### Get Slave by ID (USER, ADMIN)
* Method: GET
* URL:/slaves/boss/{id}
* Description: Get all slaves for a boss
* Path parameters: id (long)
* Response:
  * 200 + The updated slave
  * 404
 
  ### Get Slaves for a BOSS (USER, ADMIN)
* Method: GET
* URL: /slaves/boss/{bossId}
* Description: Get all slaves for a boss
* Path parameters: bossId (long)
* Response: 
  * 200 + List of slaves
  * No content
 
 ### Create a Slave (USER, ADMIN)
* Method: POST
* URL: /slaves
* Description:** Create a slave
* Payload:
  ```json
  {
  "nationality": "string",
  "age": integer,
  "efficient": boolean,
  "obedient": boolean
  }

* Response:
  * 201 + The new slave
  * 404
 
  ### Update Slave (1) (ADMIN)
* Method: PUT
* URL: /slaves/boss/{id}
* Description: Update which boss own the slave
* Payload:
  ```json
  {
  "bossId": long
  }

* Response:
  * 200 + The updated slave
  * 400

  ### Update Slave (2) (ADMIN)
* Method: POST
* URL: /slaves/update/{id}
* Description: Update a slave's attributes
* Payload:
  ```json
  {
  "nationality": "string",
  "age": integer,
  "efficient": boolean,
  "obedient": boolean
  }

* Response:
  * 201 + The updated slave
  * 404
 
 ### Delete a Slave (ADMIN)
* Method: DELETE
* URL: /slaves/{id}
* Description: Delete a slave
* Response:
  * 200 + "The slave with ID{id} has been deleted."
  * 404 + "The slave with ID{id} could not be found." 
