# Car Dealer Application

This Java application, "Car Dealer," is designed to manage information about cars and customers in an automobile salon. It utilizes a graphical user interface (GUI) built with Swing. The application is backed by a SQLite database for data storage.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Usage](#usage)
- [Database](#database)
- [Dependencies](#dependencies)

## Overview

The application consists of three main classes:

1. **Main**: The entry point of the application that initializes the GUI.

2. **Customer**: Represents information about a customer, including full name, age, and gender.

...

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
