package org.app;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarDealerGUI {
    private final AutoSalon autoSalon;

    private final JPanel cards;
    private final CardLayout cardLayout;

    public CarDealerGUI() {
        SQLiteDatabase db = new SQLiteDatabase();
        autoSalon = new AutoSalon(db);

        JFrame frame = new JFrame("Car Dealer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton addCarButton = new JButton("Добавить автомобиль");
        JButton editCarButton = new JButton("Редактировать информацию об автомобиле");
        JButton editCustomerButton = new JButton("Редактировать информацию о покупателе");
        JButton showCarsButton = new JButton("Показать автомобили");
        JButton showCustomersButton = new JButton("Показать покупателей");
        JButton showCustomerInfoButton = new JButton("Показать информацию о покупателе");
        JButton sellCarByIdButton = new JButton("Продать автомобиль по ID");
        JButton showSoldCarsButton = new JButton("Показать проданные автомобили и покупателей");

        mainPanel.add(addCarButton);
        mainPanel.add(editCarButton);
        mainPanel.add(editCustomerButton);
        mainPanel.add(showCarsButton);
        mainPanel.add(showCustomersButton);
        mainPanel.add(showCustomerInfoButton);
        mainPanel.add(sellCarByIdButton);
        mainPanel.add(showSoldCarsButton);

        cards.add(mainPanel, "main");

        addCarButton.addActionListener(e -> openAddCarPanel());
        editCarButton.addActionListener(e -> openEditCarPanel());
        editCustomerButton.addActionListener(e -> openEditCustomerPanel());
        showCarsButton.addActionListener(e -> showCarsPanel());
        showCustomersButton.addActionListener(e -> showCustomersPanel());
        showCustomerInfoButton.addActionListener(e -> openCustomerInfoPanel());
        sellCarByIdButton.addActionListener(e -> openSellCarPanel());
        showSoldCarsButton.addActionListener(e -> showSoldCarsPanel());

        frame.getContentPane().add(cards);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeApplication();
            }
        });
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> cardLayout.show(cards, "main"));
        return backButton;
    }

    private void openAddCarPanel() {
        JPanel addCarPanel = new JPanel(new GridLayout(2, 2));
        JTextField carIdField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField typeField = new JTextField();
        JButton addButton = new JButton("Добавить");
        JButton backButton = createBackButton();

        addCarPanel.add(new JLabel("Car ID:"));
        addCarPanel.add(carIdField);
        addCarPanel.add(new JLabel("Марка:"));
        addCarPanel.add(brandField);
        addCarPanel.add(new JLabel("Модель:"));
        addCarPanel.add(modelField);
        addCarPanel.add(new JLabel("Тип:"));
        addCarPanel.add(typeField);
        addCarPanel.add(addButton);
        addCarPanel.add(backButton);

        addButton.addActionListener(e -> {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                String brand = brandField.getText();
                String model = modelField.getText();
                String type = typeField.getText();
                Car car = new Car(carId, brand, model, type);
                autoSalon.addCar(car);
                JOptionPane.showMessageDialog(null, "Автомобиль успешно добавлен", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для Car ID", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(addCarPanel, "addCar");
        cardLayout.show(cards, "addCar");
    }

    private void openEditCarPanel() {
        JPanel editCarPanel = new JPanel(new GridLayout(4, 2));
        JTextField carIdField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField typeField = new JTextField();
        JButton editButton = new JButton("Редактировать");
        JButton backButton = createBackButton();

        editCarPanel.add(new JLabel("Car ID:"));
        editCarPanel.add(carIdField);
        editCarPanel.add(new JLabel("Марка:"));
        editCarPanel.add(brandField);
        editCarPanel.add(new JLabel("Модель:"));
        editCarPanel.add(modelField);
        editCarPanel.add(new JLabel("Тип:"));
        editCarPanel.add(typeField);
        editCarPanel.add(editButton);
        editCarPanel.add(backButton);

        editButton.addActionListener(e -> {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                String brand = brandField.getText();
                String model = modelField.getText();
                String type = typeField.getText();
                autoSalon.editCarInfo(carId, brand, model, type);
                JOptionPane.showMessageDialog(null, "Информация об автомобиле успешно отредактирована", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для Car ID", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(editCarPanel, "editCar");
        cardLayout.show(cards, "editCar");
    }

    private void openEditCustomerPanel() {
        JPanel editCustomerPanel = new JPanel(new GridLayout(2, 2));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JButton editButton = new JButton("Редактировать");
        JButton backButton = createBackButton();

        editCustomerPanel.add(new JLabel("ФИО:"));
        editCustomerPanel.add(nameField);
        editCustomerPanel.add(new JLabel("Возраст:"));
        editCustomerPanel.add(ageField);
        editCustomerPanel.add(new JLabel("Пол:"));
        editCustomerPanel.add(genderField);
        editCustomerPanel.add(editButton);
        editCustomerPanel.add(backButton);

        editButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                autoSalon.editCustomerInfo(name, age, gender);
                JOptionPane.showMessageDialog(null, "Информация о покупателе успешно отредактирована", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для возраста", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(editCustomerPanel, "editCustomer");
        cardLayout.show(cards, "editCustomer");
    }

    private void showSoldCarsPanel() {
        JPanel soldCarsPanel = new JPanel(new BorderLayout());
        JTextArea soldCarsTextArea = new JTextArea();
        soldCarsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(soldCarsTextArea);
        JButton backButton = createBackButton();

        List<SoldCar> soldCars = autoSalon.getSoldCars();
        StringBuilder soldCarsText = new StringBuilder();
        for (SoldCar soldCar : soldCars) {
            soldCarsText.append(soldCar).append("\n");
        }
        soldCarsTextArea.setText(soldCarsText.toString());

        soldCarsPanel.add(backButton, BorderLayout.NORTH);
        soldCarsPanel.add(scrollPane, BorderLayout.CENTER);

        cards.add(soldCarsPanel, "showSoldCars");
        cardLayout.show(cards, "showSoldCars");
    }




    private void showCarsPanel() {
        JPanel carsPanel = new JPanel(new BorderLayout());
        JTextArea carsTextArea = new JTextArea();
        carsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(carsTextArea);
        JButton backButton = createBackButton();

        List<Car> cars = autoSalon.getAllCars();
        StringBuilder carsText = new StringBuilder();
        for (Car car : cars) {
            carsText.append(car).append("\n");
        }
        carsTextArea.setText(carsText.toString());

        carsPanel.add(backButton, BorderLayout.NORTH);
        carsPanel.add(scrollPane, BorderLayout.CENTER);

        cards.add(carsPanel, "showCars");
        cardLayout.show(cards, "showCars");
    }

    private void showCustomersPanel() {
        JPanel customersPanel = new JPanel(new BorderLayout());
        JTextArea customersTextArea = new JTextArea();
        customersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customersTextArea);
        JButton backButton = createBackButton();

        List<Customer> customers = autoSalon.getAllCustomers();
        StringBuilder customersText = new StringBuilder();
        for (Customer customer : customers) {
            customersText.append(customer.getFormattedInfo()).append("\n");
        }
        customersTextArea.setText(customersText.toString());

        customersPanel.add(backButton, BorderLayout.NORTH);
        customersPanel.add(scrollPane, BorderLayout.CENTER);

        cards.add(customersPanel, "showCustomers");
        cardLayout.show(cards, "showCustomers");
    }

    private void openCustomerInfoPanel() {
        JPanel customerInfoPanel = new JPanel(new BorderLayout());
        JTextField nameField = new JTextField();
        nameField.setColumns(20);
        JButton showButton = new JButton("Показать информацию");
        JButton backButton = createBackButton();

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("ФИО:"));
        inputPanel.add(nameField);
        inputPanel.add(showButton);
        inputPanel.add(backButton);

        JTextArea customerInfoTextArea = new JTextArea();
        customerInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerInfoTextArea);

        showButton.addActionListener(e -> {
            String name = nameField.getText();
            List<Car> customerCars = autoSalon.getCustomerCars(name);
            List<SoldCar> soldCars = autoSalon.getSoldCars();
            StringBuilder customerInfoText = new StringBuilder();
            customerInfoText.append("Автомобили покупателя ").append(name).append(":\n");
            for (Car car : customerCars) {
                customerInfoText.append(car).append("\n");
            }
            for (SoldCar soldCar : soldCars) {
                if (soldCar.getCustomerName().equalsIgnoreCase(name)) {
                    customerInfoText.append("Проданный автомобиль: ").append(soldCar).append("\n");
                }
            }
            customerInfoTextArea.setText(customerInfoText.toString());
        });


        backButton.addActionListener(e -> cardLayout.show(cards, "main"));

        customerInfoPanel.add(inputPanel, BorderLayout.NORTH);
        customerInfoPanel.add(scrollPane, BorderLayout.CENTER);

        cards.add(customerInfoPanel, "customerInfo");
        cardLayout.show(cards, "customerInfo");
    }


    private void openSellCarPanel() {
        JPanel sellCarPanel = new JPanel(new GridLayout(2, 2));
        JTextField carIdField = new JTextField();
        JTextField customerNameField = new JTextField();
        JButton sellButton = new JButton("Продать");
        JButton backButton = createBackButton();

        sellCarPanel.add(new JLabel("Car ID:"));
        sellCarPanel.add(carIdField);
        sellCarPanel.add(new JLabel("Имя покупателя:"));
        sellCarPanel.add(customerNameField);
        sellCarPanel.add(sellButton);
        sellCarPanel.add(backButton);

        sellButton.addActionListener(e -> {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                String customerName = customerNameField.getText();
                Customer customer = new Customer(customerName, 0, "");
                autoSalon.sellCar(carId, customer);
                JOptionPane.showMessageDialog(null, "Автомобиль успешно продан", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для Car ID", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(e -> cardLayout.show(cards, "main"));

        cards.add(sellCarPanel, "sellCar");
        cardLayout.show(cards, "sellCar");
    }

    private void closeApplication() {
        autoSalon.closeConnection();
        System.exit(0);
    }

}