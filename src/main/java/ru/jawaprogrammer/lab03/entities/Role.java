package ru.jawaprogrammer.lab03.entities;

public enum Role {
    STAFF(120, 10), MANAGER(170, 20), EXECUTIVE(250, 30);
    private int salary, premium;

    Role(int sal, int prem) {
        salary = sal;
        premium = prem;
    }

    public int getPremium() {
        return premium;
    }

    public int getSalary() {
        return salary;
    }
}
