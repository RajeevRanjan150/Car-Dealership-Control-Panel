# Car-Dealership-Control-Panel
A console based Car-Dealership-Control-Panel made to store data about cars and employees - through MySQL.



To run this program you need to have MySQL database and it's drivers installed and uploaded to dependencies.
Inside MySQL create database, CarDealership with tables cars and employees; The schema of the following tables are as follows:

cars: CREATE TABLE cars(
      id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
      brand VARCHAR(255),
      model VARCHAR(255),
      price INT NOT NULL
      );

employees: CREATE TABLE employees (
          id INT PRIMARY KEY NOT NULL,
          name VARCHAR(255) NOT NULL,
          role VARCHAR(255) NOT NULL,
          salary VARCHAR(255),
          contactNumber VARCHAR(20)
          );


After which all you have to do is to Load the code into your local machine and execute:
Compile: javac CarDealership.java
Run: java CarDealership
