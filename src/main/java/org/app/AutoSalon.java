package org.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoSalon {
    private final SQLiteDatabase db;

    public AutoSalon(SQLiteDatabase db) {
        this.db = db;
    }

    public void addCar(Car car) {
        String insertCarQuery = "INSERT INTO cars (brand, model, type, customer_name) VALUES (?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertCarQuery)) {
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getType());
            preparedStatement.setString(4, car.getCustomerName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sellCar(int carId, Customer customer) {
        String updateCarQuery = "UPDATE cars SET customer_name = ? WHERE car_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCarQuery)) {
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setInt(2, carId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCarInfo(int carId, String brand, String model, String type) {
        String updateCarInfoQuery = "UPDATE cars SET brand = ?, model = ?, type = ? WHERE car_id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCarInfoQuery)) {
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, type);
            preparedStatement.setInt(4, carId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerInfo(String fullName, int age, String gender) {
        String updateCustomerInfoQuery = "UPDATE customers SET age = ?, gender = ? WHERE full_name = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCustomerInfoQuery)) {
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, fullName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String selectAllCarsQuery = "SELECT car_id, brand, model, type, customer_name FROM cars";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllCarsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                String customerName = resultSet.getString("customer_name");
                Car car = new Car(carId, brand, model, type);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String selectAllCustomersQuery = "SELECT full_name, age, gender FROM customers";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllCustomersQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Customer customer = new Customer(fullName, age, gender);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Car> getCustomerCars(String customerName) {
        List<Car> customerCars = new ArrayList<>();
        String selectCustomerCarsQuery = "SELECT car_id, brand, model, type, customer_name FROM cars WHERE customer_name = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectCustomerCarsQuery)) {
            preparedStatement.setString(1, customerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                String customerNameFromDB = resultSet.getString("customer_name");
                Car car = new Car(carId, brand, model, type);
                customerCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCars;
    }
}
