package org.app;

public class Car {
    private int carId;
    private String brand;
    private String model;
    private String type;
    private String customerName;

    public Car(int carId, String brand, String model, String type) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.customerName = null;
    }

    public String toString() {
        return "Марка: " + brand + ", Модель: " + model + ", Тип: " + type;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
