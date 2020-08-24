package com.packagename.myapp.model;


public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String data;

    protected Customer() {}

    public Customer(Long id, String firstName, String lastName, String data) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', data='%s']",
                id, firstName, lastName, data);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
