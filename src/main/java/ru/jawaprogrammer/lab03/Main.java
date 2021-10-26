package ru.jawaprogrammer.lab03;

import ru.jawaprogrammer.lab03.entities.Employee;
import ru.jawaprogrammer.lab03.entities.EmployeeBuilder;
import ru.jawaprogrammer.lab03.entities.Gender;
import ru.jawaprogrammer.lab03.entities.Role;
import ru.jawaprogrammer.lab03.utils.Accountant;
import ru.jawaprogrammer.lab03.utils.EmployeeGenerator;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Predicate<Employee> isFemale = (e) -> e.getGender().equals(Gender.FEMALE);
        Supplier<Employee> getStandart = () -> new EmployeeBuilder()
                .setGivenName("Иван")
                .setSurName("Иванов")
                .setAge(42)
                .setDept(1)
                .setGender(Gender.MALE)
                .setRole(Role.STAFF)
                .setAddress("ул. Цветочная, 21")
                .setCity("Солнечный")
                .setEmail("dontknower@mail.ru")
                .setPhone("+7 (800) 555-35-35")
                .setCode(99)
                .setState("Сказочный лес")
                .build();

        Function<Employee, String> anotherToString = (e) -> e.getGivenName() + " " + e.getSurName() + "\nОчень, очень " + (e.getGender() == Gender.MALE ? "хороший мальчик" : "хорошая девочка") + ". Не курит, слушает маму и каждый день делает зарядку.";

        Consumer<Employee> paySalay = Accountant::paySalary, payPremium = Accountant::payPremium;

        BiPredicate<Employee, Integer> isInDep = (e, i) -> e.getDept() == i;

        List<Employee> employeeList = EmployeeGenerator.generate(10);
        employeeList.add(getStandart.get());

        System.out.println("Выплата премии женщинам сотрудникам");
        employeeList.stream().filter(isFemale).forEach(payPremium);
        System.out.println("------------------------------");

        System.out.println("Выплата зарплаты сотрудникам определенного департамента");
        employeeList.stream().filter((e) -> isInDep.test(e, 2)).forEach(paySalay);
        System.out.println("------------------------------");

        System.out.println("Выплата премии сотрудникам старше 30, работающим в определенном департаменте");
        employeeList.stream().filter((e) -> e.getDept() == 3 && e.getAge() > 30).forEach(payPremium);
        System.out.println("------------------------------");

        System.out.println("Выплата зарплаты менеджерам");
        employeeList.stream().filter((e) -> e.getRole().equals(Role.MANAGER)).peek(paySalay);
        System.out.println("------------------------------");

        System.out.println("Выплата премии стаффу");
        employeeList.stream().filter((e) -> e.getRole().equals(Role.STAFF)).forEach(payPremium);
        System.out.println("------------------------------");

        // просто что бы хоть как то использовать function и map))
        List<String> lst = employeeList.stream().map(anotherToString).collect(Collectors.toList());
        for (String s : lst) System.out.println(s);
        System.out.println("------------------------------");

        // далее чисто 4я лаба уже
        System.out.println("Сотрудник, сгенерированный раньше остальных: " + employeeList.stream().findFirst().get());

        System.out.println("Сотрудник с самым длинным именем: " + employeeList.stream().max((o1, o2) -> o1.getGivenName().length() + o1.getSurName().length() - o2.getSurName().length() - o2.getGivenName().length()).get());
        System.out.println("Сотрудник с самым коротким именем: " + employeeList.stream().min((o1, o2) -> o1.getGivenName().length() + o1.getSurName().length() - o2.getSurName().length() - o2.getGivenName().length()).get());

        System.out.println("------------------------------");

        ToIntFunction<Employee> toSalary = (e) -> e.getRole().getSalary();

        double average = employeeList.stream().mapToInt(toSalary).average().getAsDouble();
        int summary = employeeList.stream().mapToInt(toSalary).sum();

        System.out.println("Предприятие тратит на зарплаты сотрудникам " + summary + " рублей. В среднем зарплата сотрудника предприятия составляет " + Math.round(average * 100) / 100.0f + " рублей.");
    }


}
