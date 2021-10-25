package ru.jawaprogrammer.lab03.utils;

import ru.jawaprogrammer.lab03.entities.Employee;

public class Accountant {
    private Accountant() {
    }

    public static void paySalary(Employee employee) {
        System.out.println(employee + "\nполучает зарплату в размере " + employee.getRole().getSalary() + " рублей");
    }

    public static void payPremium(Employee employee) {
        System.out.println(employee + "\nполучает премию в размере " + (employee.getRole().getSalary() * employee.getRole().getPremium() / 100.0f) + " рублей");
    }
}
