package org.app;

public class SoldCar {
    private int carId;
    private String customerName;

    public SoldCar(int carId, String customerName) {
        this.carId = carId;
        this.customerName = customerName;
    }
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return
                "Id:" + carId;
    }
}
