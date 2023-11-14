package org.app;

import javax.swing.*;
import java.util.List;

public class CarDealerGUI {
    private final AutoSalon autoSalon;

    public CarDealerGUI() {
        SQLiteDatabase db = new SQLiteDatabase();
        autoSalon = new AutoSalon(db);

        JFrame frame = new JFrame("Car Dealer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton addCarButton = new JButton("Добавить автомобиль");
        JButton editCarButton = new JButton("Редактировать информацию об автомобиле");
        JButton editCustomerButton = new JButton("Редактировать информацию о покупателе");
        JButton showCarsButton = new JButton("Показать автомобили");
        JButton showCustomersButton = new JButton("Показать покупателей");
        JButton showCustomerInfoButton = new JButton("Показать информацию о покупателе");
        JButton sellCarByIdButton = new JButton("Продать автомобиль по ID");

        panel.add(addCarButton);
        panel.add(editCarButton);
        panel.add(editCustomerButton);
        panel.add(showCarsButton);
        panel.add(showCustomersButton);
        panel.add(showCustomerInfoButton);
        panel.add(sellCarByIdButton);

        addCarButton.addActionListener(e -> openAddCarDialog());
        editCarButton.addActionListener(e -> openEditCarDialog());
        editCustomerButton.addActionListener(e -> openEditCustomerDialog());
        showCarsButton.addActionListener(e -> showCars());
        showCustomersButton.addActionListener(e -> showCustomers());
        showCustomerInfoButton.addActionListener(e -> openCustomerInfoDialog());
        sellCarByIdButton.addActionListener(e -> openSellCarDialog());

        frame.setVisible(true);
    }

    private void openAddCarDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Добавить автомобиль");
        dialog.setSize(600, 600);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel addCarPanel = new JPanel();
        dialog.add(addCarPanel);

        JTextField carIdField = new JTextField(10);
        JTextField brandField = new JTextField(10);
        JTextField modelField = new JTextField(10);
        JTextField typeField = new JTextField(10);
        JTextField customerNameField = new JTextField(10);
        JButton addButton = new JButton("Добавить");

        addCarPanel.add(new JLabel("Car ID:"));
        addCarPanel.add(carIdField);
        addCarPanel.add(new JLabel("Марка:"));
        addCarPanel.add(brandField);
        addCarPanel.add(new JLabel("Модель:"));
        addCarPanel.add(modelField);
        addCarPanel.add(new JLabel("Тип:"));
        addCarPanel.add(typeField);
        addCarPanel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                String brand = brandField.getText();
                String model = modelField.getText();
                String type = typeField.getText();
                //String customerName = customerNameField.getText();
                Car car = new Car(carId, brand, model, type);
                autoSalon.addCar(car);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для Car ID", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void openEditCarDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Редактировать информацию об автомобиле");
        dialog.setSize(600, 600);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel editCarPanel = new JPanel();
        dialog.add(editCarPanel);

        JTextField carIdField = new JTextField(10);
        JTextField brandField = new JTextField(10);
        JTextField modelField = new JTextField(10);
        JTextField typeField = new JTextField(10);
        JButton editButton = new JButton("Редактировать");

        editCarPanel.add(new JLabel("Car ID:"));
        editCarPanel.add(carIdField);
        editCarPanel.add(new JLabel("Марка:"));
        editCarPanel.add(brandField);
        editCarPanel.add(new JLabel("Модель:"));
        editCarPanel.add(modelField);
        editCarPanel.add(new JLabel("Тип:"));
        editCarPanel.add(typeField);
        editCarPanel.add(editButton);

        editButton.addActionListener(e -> {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                String brand = brandField.getText();
                String model = modelField.getText();
                String type = typeField.getText();
                autoSalon.editCarInfo(carId, brand, model, type);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для Car ID", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void openEditCustomerDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Редактировать информацию о покупателе");
        dialog.setSize(600, 600);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel editCustomerPanel = new JPanel();
        dialog.add(editCustomerPanel);

        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(10);
        JTextField genderField = new JTextField(10);
        JButton editButton = new JButton("Редактировать");

        editCustomerPanel.add(new JLabel("ФИО:"));
        editCustomerPanel.add(nameField);
        editCustomerPanel.add(new JLabel("Возраст:"));
        editCustomerPanel.add(ageField);
        editCustomerPanel.add(new JLabel("Пол:"));
        editCustomerPanel.add(genderField);
        editCustomerPanel.add(editButton);

        editButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                autoSalon.editCustomerInfo(name, age, gender);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для возраста", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void openSellCarDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Продать автомобиль по ID");
        dialog.setSize(600, 600);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel sellCarPanel = new JPanel();
        dialog.add(sellCarPanel);

        JTextField carIdField = new JTextField(10);
        JTextField customerNameField = new JTextField(10);
        JButton sellButton = new JButton("Продать");

        sellCarPanel.add(new JLabel("Car ID:"));
        sellCarPanel.add(carIdField);
        sellCarPanel.add(new JLabel("ФИО покупателя:"));
        sellCarPanel.add(customerNameField);
        sellCarPanel.add(sellButton);

        sellButton.addActionListener(e -> {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                String customerName = customerNameField.getText();
                Customer customer = new Customer(customerName, 0, "");
                autoSalon.sellCar(carId, customer);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для Car ID", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void openCustomerInfoDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Информация о покупателе");
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel customerInfoPanel = new JPanel();
        dialog.add(customerInfoPanel);

        JTextField customerNameField = new JTextField(10);
        JButton showInfoButton = new JButton("Показать информацию");

        customerInfoPanel.add(new JLabel("ФИО покупателя:"));
        customerInfoPanel.add(customerNameField);
        customerInfoPanel.add(showInfoButton);

        JTextArea outputArea = new JTextArea(30, 30);
        customerInfoPanel.add(outputArea);

        showInfoButton.addActionListener(e -> {
            String customerName = customerNameField.getText();
            String customerInfo = getCustomerInfo(customerName);
            outputArea.setText(customerInfo);
        });

        dialog.setVisible(true);
    }

    private void showCars() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Список автомобилей");
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel carsPanel = new JPanel();
        dialog.add(carsPanel);

        JTextArea outputArea = new JTextArea(30, 30);
        carsPanel.add(outputArea);

        List<Car> cars = autoSalon.getAllCars();
        outputArea.setText("Автомобили в салоне:\n");
        for (Car car : cars) {
            outputArea.append(car.getCarId() + " - " + car.getBrand() + " " + car.getModel() + " (" + car.getType() + ")\n");
        }

        dialog.setVisible(true);
    }

    private void showCustomers() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Список покупателей");
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel customersPanel = new JPanel();
        dialog.add(customersPanel);

        JTextArea outputArea = new JTextArea(30, 30);
        customersPanel.add(outputArea);

        List<Customer> customers = autoSalon.getAllCustomers();
        outputArea.setText("Покупатели в системе:\n");
        for (Customer customer : customers) {
            outputArea.append(customer.getFullName() + " (Возраст: " + customer.getAge() + ", Пол: " + customer.getGender() + ")\n");
        }

        dialog.setVisible(true);
    }


    private String getCustomerInfo(String customerName) {
        Customer customer = findCustomerByName(customerName);
        if (customer != null) {
            StringBuilder info = new StringBuilder("Информация о покупателе:\n");
            info.append("ФИО: ").append(customer.getFullName()).append("\n");
            info.append("Возраст: ").append(customer.getAge()).append(" ");
            info.append("Пол: ").append(customer.getGender()).append("\n");
            info.append("Купленные машины:\n");

            List<Car> soldCars = autoSalon.getCustomerCars(customer.getFullName());
            for (Car car : soldCars) {
                info.append(car.getBrand()).append(" ").append(car.getModel()).append(" (Car ID: ").append(car.getCarId()).append(")\n");
            }

            return info.toString();
        } else {
            return "Покупатель не найден!";
        }
    }

    private Customer findCustomerByName(String customerName) {
        for (Customer customer : autoSalon.getAllCustomers()) {
            if (customer.getFullName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }
}