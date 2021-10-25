package ru.jawaprogrammer.lab03;

import ru.jawaprogrammer.lab03.entities.Employee;
import ru.jawaprogrammer.lab03.entities.EmployeeBuilder;
import ru.jawaprogrammer.lab03.utils.Accountant;
import ru.jawaprogrammer.lab03.utils.EmployeeGenerator;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Predicate<Employee> isFemale = (e) -> e.getGender() == Employee.Gender.FEMALE;
        Supplier<Employee> getStandart = () -> new EmployeeBuilder()
                .setGivenName("Иван")
                .setSurName("Иванов")
                .setAge(42)
                .setDept(1)
                .setGender(Employee.Gender.MALE)
                .setRole(Employee.Role.STAFF)
                .setAddress("ул. Цветочная, 21")
                .setCity("Солнечный")
                .setEmail("dontknower@mail.ru")
                .setPhone("+7 (800) 555-35-35")
                .setCode(99)
                .setState("Сказочный лес")
                .create();

        Function<Employee, String> anotherToString = (e) -> e.getGivenName() + " " + e.getSurName() + "\nОчень, очень " + (e.getGender() == Employee.Gender.MALE ? "хороший мальчик" : "хорошая девочка") + ". Не курит, слушает маму и каждый день делает зарядку.";

        BiPredicate<Employee, Integer> isInDep = (e, i) -> e.getDept() == i;

        List<Employee> employeeList = EmployeeGenerator.generate(7);
        employeeList.add(getStandart.get());

        System.out.println("Выплата премии женщинам сотрудникам");
        employeeList.stream().filter(isFemale).forEach(Accountant::payPremium);
        System.out.println("------------------------------");

        System.out.println("Выплата зарплаты сотрудникам определенного департамента");
        employeeList.stream().filter((e) -> isInDep.test(e, 2)).forEach(Accountant::paySalary);
        System.out.println("------------------------------");

        System.out.println("Выплата премии сотрудникам старше 30, работающим в определенном департаменте");
        employeeList.stream().filter((e) -> e.getDept() == 3 && e.getAge() > 30).forEach(Accountant::payPremium);
        System.out.println("------------------------------");

        System.out.println("Выплата зарплаты менеджерам");
        employeeList.stream().filter((e) -> e.getRole() == Employee.Role.MANAGER).forEach(Accountant::paySalary);
        System.out.println("------------------------------");

        System.out.println("Выплата премии стаффу");
        employeeList.stream().filter((e) -> e.getRole() == Employee.Role.STAFF).forEach(Accountant::payPremium);
        System.out.println("------------------------------");

        for (Employee e : employeeList) // просто что бы хоть как то использовать function))
            System.out.println(anotherToString.apply(e));
    }
}
