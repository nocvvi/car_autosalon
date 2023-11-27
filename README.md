# Car Dealer Application

This Java application, "Car Dealer," is designed to manage information about cars and customers in an automobile salon. It utilizes a graphical user interface (GUI) built with Swing. The application is backed by a SQLite database for data storage.

## Table of Contents
- [Overview](#overview)
- [Classes](#classes)
  - [Main](#main)
  - [Customer](#customer)
  - [Car](#car)
  - [CarDealerGUI](#cardealergui)
  - [SoldCar](#soldcar)
  - [AutoSalon](#autosalon)
  - [SQLiteDatabase](#sqlitedatabase)
- [Features](#features)
- [Usage](#usage)
- [Database](#database)
- [Dependencies](#dependencies)

## Overview

The application consists of three main classes:

### Main
The `Main` class serves as the entry point of the application, initializing the GUI by invoking the `CarDealerGUI` constructor.

### Customer
The `Customer` class represents information about a customer, including full name, age, and gender. It includes methods to retrieve and modify customer details.

### Car
The `Car` class represents information about a car, including a unique ID, brand, model, type, and associated customer name. It includes methods to retrieve and modify car details.

### CarDealerGUI
The `CarDealerGUI` class implements the graphical user interface using Swing. It provides various functionalities such as adding/editing cars and customers, displaying car and customer information, selling cars, and showing sold cars.

### SoldCar
The `SoldCar` class represents a sold car, including the car ID and the customer name. It includes methods to retrieve and modify information about sold cars.

### AutoSalon
The `AutoSalon` class manages interactions with the SQLite database. It provides methods to add/edit cars and customers, retrieve information about cars and customers, sell cars, and get information about sold cars.

### SQLiteDatabase
The `SQLiteDatabase` class manages the SQLite database connection and table creation. It includes methods for connecting, creating tables, and closing connections.


## Features

- Add new cars and customers to the database.
- Edit information about existing cars and customers.
- Display a list of all cars, all customers, or cars sold.
- Display information about a specific customer and their purchased cars.
- Sell a car by associating it with a customer.
- View information about sold cars and their buyers.

## Usage

The application provides a user-friendly GUI with buttons for various actions. Users can navigate through different panels to perform actions such as adding/editing cars and customers, viewing information, and selling cars. The main menu includes options like adding a car, editing car information, editing customer information, showing cars, showing customers, showing customer information, selling a car, and showing sold cars.

## Database

The application uses an SQLite database to store information about cars, customers, and sold cars. Three tables (`cars`, `customers`, and `sold_cars`) are created to manage this data. The `SQLiteDatabase` class handles database connections, table creation, and closing connections.

## Dependencies

- Java Swing (for GUI)
- SQLite JDBC Driver
- SLF4J Logger
