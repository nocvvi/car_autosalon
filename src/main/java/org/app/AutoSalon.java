package org.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;



public class AutoSalon {
    private final Connection connection;

    public AutoSalon(SQLiteDatabase db) {
        this.connection = db.getConnection();
    }

    public void addCar(Car car) {
        String sql = "INSERT INTO cars (car_id, brand, model, type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, car.getCarId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setString(4, car.getType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (full_name, age, gender) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setString(3, customer.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }


    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");

                cars.add(new Car(carId, brand, model, type));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return cars;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String selectAllCustomers = "SELECT * FROM customers";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllCustomers)) {
            while (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                customers.add(new Customer(fullName, age, gender));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return customers;
    }


    public List<Car> getCustomerCars(String customerName) {
        List<Car> customerCars = new ArrayList<>();
        String selectCustomerCars = "SELECT c.* FROM cars c " +
                "INNER JOIN sold_cars sc ON c.car_id = sc.car_id " +
                "WHERE LOWER(sc.customer_name) = LOWER(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectCustomerCars)) {
            preparedStatement.setString(1, customerName.toLowerCase());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int carId = resultSet.getInt("car_id");
                    String brand = resultSet.getString("brand");
                    String model = resultSet.getString("model");
                    String type = resultSet.getString("type");
                    customerCars.add(new Car(carId, brand, model, type));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCars;
    }




    public List<SoldCar> getSoldCars() {
        List<SoldCar> soldCars = new ArrayList<>();
        String selectSoldCars = "SELECT * FROM sold_cars";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSoldCars)) {
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String customerName = resultSet.getString("customer_name");
                soldCars.add(new SoldCar(carId, customerName));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return soldCars;
    }

    public void editCarInfo(int carId, String brand, String model, String type) {
        String sql = "UPDATE cars SET brand=?, model=?, type=? WHERE car_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, type);
            preparedStatement.setInt(4, carId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void editCustomerInfo(String name, int age, String gender) {
        String sql = "UPDATE customers SET age=?, gender=? WHERE full_name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void sellCar(int carId, Customer customer) {
        String insertCarSql = "INSERT INTO sold_cars (car_id, customer_name) VALUES (?, ?)";
        String deleteCarSql = "DELETE FROM cars WHERE car_id=?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement insertCarStatement = connection.prepareStatement(insertCarSql);
                 PreparedStatement deleteCarStatement = connection.prepareStatement(deleteCarSql)) {

                addCustomer(customer);

                insertCarStatement.setInt(1, carId);
                insertCarStatement.setString(2, customer.getFullName());
                insertCarStatement.executeUpdate();

                // Set the customer_name in the cars table
                String updateCarCustomerSql = "UPDATE cars SET customer_name=? WHERE car_id=?";
                try (PreparedStatement updateCarCustomerStatement = connection.prepareStatement(updateCarCustomerSql)) {
                    updateCarCustomerStatement.setString(1, customer.getFullName());
                    updateCarCustomerStatement.setInt(2, carId);
                    updateCarCustomerStatement.executeUpdate();
                }

                deleteCarStatement.setInt(1, carId);
                deleteCarStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                handleSQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }





    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void handleSQLException(SQLException e) {
        System.err.println("Error executing SQL query: " + e.getMessage());
        e.printStackTrace();
    }
}
