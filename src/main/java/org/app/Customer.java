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

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }


}