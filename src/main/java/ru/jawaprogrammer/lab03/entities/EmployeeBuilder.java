package ru.jawaprogrammer.lab03.entities;

public class EmployeeBuilder {
    private Employee employee;

    public EmployeeBuilder() {
        employee = new Employee();
    }

    public Employee build() {
        if (employee.getGender() == null ||
                employee.getSurName() == null ||
                employee.getGivenName() == null ||
                employee.getAddress() == null ||
                employee.getCity() == null ||
                employee.getEmail() == null ||
                employee.getRole() == null ||
                employee.getState() == null ||
                employee.getPhone() == null
        )
            throw new RuntimeException("Объект был инициализирован не полностью");
        return employee;
    }

    public EmployeeBuilder setGivenName(String givenName) {
        employee.setGivenName(givenName);
        return this;
    }

    public EmployeeBuilder setSurName(String surName) {
        employee.setSurName(surName);
        return this;
    }

    public EmployeeBuilder setAge(int age) {
        employee.setAge(age);
        return this;
    }

    public EmployeeBuilder setGender(Gender gender) {
        employee.setGender(gender);
        return this;
    }

    public EmployeeBuilder setRole(Role role) {
        employee.setRole(role);
        return this;
    }


    public EmployeeBuilder setDept(int dept) {
        employee.setDept(dept);
        return this;
    }

    public EmployeeBuilder setEmail(String email) {
        employee.setEmail(email);
        return this;
    }

    public EmployeeBuilder setPhone(String phone) {
        employee.setPhone(phone);
        return this;
    }

    public EmployeeBuilder setAddress(String address) {
        employee.setAddress(address);
        return this;
    }

    public EmployeeBuilder setCity(String city) {
        employee.setCity(city);
        return this;
    }

    public EmployeeBuilder setState(String state) {
        employee.setState(state);
        return this;
    }

    public EmployeeBuilder setCode(int code) {
        employee.setCode(code);
        return this;
    }
}
