package com.interview.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterviewEPAM {
    public static void main(String[] args) {
        List<Employee1> employees;
        List<Project> projects;

        employees = Arrays.asList(
                new Employee1("Alice", 25, "Engineering", 60000),
                new Employee1("Bob", 30, "Engineering", 70000),
                new Employee1("Charlie", 22, "HR", 50000),
                new Employee1("David", 35, "Engineering", 80000)
        );
        System.out.println("1: Find the average salary of employees in a specific (Engineering) department: " +
                employees.stream().filter(e -> "Engineering".equals(e.getDepartment())).mapToDouble(Employee1::getSalary).average().orElse(0));

        employees = Arrays.asList(
                new Employee1("Alice", 25, "Engineering", 60000),
                new Employee1("Bob", 30, "Engineering", 70000),
                new Employee1("Charlie", 22, "HR", 50000),
                new Employee1("David", 35, "Finance", 80000)
        );
        System.out.println("2: Group employees by department: " +
                employees.stream().collect(Collectors.groupingBy(Employee1::getDepartment)));

        projects = Arrays.asList(
                new Project("Project A", "Java", Arrays.asList(
                        new Employee1("Alice", 25, "Engineering", 60000),
                        new Employee1("Bob", 30, "Engineering", 70000)
                )),
                new Project("Project B", "Python", Arrays.asList(
                        new Employee1("Charlie", 22, "HR", 50000),
                        new Employee1("David", 35, "Finance", 80000),
                        new Employee1("Eve", 28, "Engineering", 75000)
                ))
        );
        System.out.println("3: Find the project with the largest team: " +
                projects.stream()
                        .max(Comparator.comparing(p -> p.getTeam().size()))
                        .orElse(null)
        );

        System.out.println("4: Find the total salary expenditure for each department: " +
                employees.stream()
                        .collect(Collectors.groupingBy(Employee1::getDepartment, Collectors.summingDouble(Employee1::getSalary))));

        System.out.println("5: Find the youngest employee in each department: " +
                employees.stream()
                        .collect(Collectors.toMap(Employee1::getDepartment, Function.identity(),
                                BinaryOperator.minBy(Comparator.comparingDouble(Employee1::getAge)))));

        System.out.println("6: Find all employees who work on projects using a specific (Java) technology: " +
                projects.stream()
                        .filter(p -> "Java".equals(p.getTechnology()))
                        .flatMap(p -> p.getTeam().stream())
                        .toList());

        System.out.println("7: Find the department with the highest total salary expenditure: " +
                employees.stream()
                        .collect(Collectors.groupingBy(Employee1::getDepartment, Collectors.summingDouble(Employee1::getSalary)))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null));

        System.out.println("8: Find the average age of employees in each department: " +
                employees.stream()
                        .collect(Collectors.groupingBy(Employee1::getDepartment, Collectors.averagingDouble(Employee1::getAge))));

        System.out.println("9: Find all employees who earn more than a specific(70000) salary: " +
                employees.stream()
                        .filter(e -> e.getSalary() > 70000)
                        .toList());

        System.out.println("10: Find the project with the highest total salary expenditure: " +
                projects.stream()
                        .max(Comparator.comparing(p -> p.getTeam().stream().mapToDouble(Employee1::getSalary).sum()))
                        .orElse(null));

    }
}

class Employee1 {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee1(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", department='" + department + "', salary=" + salary + "}";
    }
}

class Project {
    private String name;
    private String technology;
    private List<Employee1> team;

    public Project(String name, String technology, List<Employee1> team) {
        this.name = name;
        this.technology = technology;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTechnology() {
        return technology;
    }

    public List<Employee1> getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Project{name='" + name + "', technology='" + technology + "', team=" + team + "}";
    }
}

class Company {
    private String name;
    private List<Project> projects;

    public Company(String name, List<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
