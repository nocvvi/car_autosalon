package org.app;

public class Customer {
    private String fullName;
    private int age;
    private String gender;

    public Customer(String fullName, int age, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFormattedInfo() {
        return String.format("Имя: %s  Возраст: %d  Пол: %s", fullName, age, gender);
    }
}
