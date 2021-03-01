# MyRetail Product API

This project will create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. It also provides an API to store and update Product price information in NoSQL database.

## Project Submodules
### [Controller](./src/main/java/com/myretail/product/api/controller)
Defines all the RESTful APIs for this service
### [Service](./src/main/java/com/myretail/product/api/service)
Defines all the Service API which will be called from controller
### [Repository](./src/main/java/com/myretail/product/api/repository)
Defines Repository which interacts with NoSQL Database
### [Model](./src/main/java/com/myretail/product/api/model)
Defines all the concept for Product API

# Instruction for running this application
### Install NoSQL Mongodb Database (For Windows)
- Download Mongodb from below [here](https://www.mongodb.com/try/download/community), based on the OS you are using.
- Install the downloaded file
- Verify installation, by navigating to C:\Program Files\MongoDB\Server\3.6\bin, and see if mongod and mongod files are present
- Open command window by typing 'cmd', and navigate to C:\Program Files\MongoDB\Server\3.6\bin folder and type "mongod"
- Verify mongo db is ready to accept connection on it's default port i.e. 27017
### Setting up database
- From windows run, find "MongoDB Compass" application and open it
- Click on Create Database
- And enter "ProductInventory" in Database name and "ProductPrice" in Collection Name
### Running the application on localhost
- Download this spring boot project in your IDE (I used STS which is eclipse but comes with all spring features)
- Once project is loaded in your IDE it can be run by executing the main class "MyretailProductApiApplication.java"
- This will deploy this project on tomcat server in local host on port 8080
- From here we can use PostMan tool for running all the APIs. PostMan can be download [here](https://www.postman.com/downloads/)

## API Resources
### MyRetail Product REST API
Includes ReST end-points, requests, responses.
- [`POST` / `GET` / `PUT` MyRetail Product](RESTAPI.md)
 
