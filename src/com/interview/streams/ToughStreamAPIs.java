package com.interview.streams;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class ToughStreamAPIs {
    public static void main(String[] args) {
        List<Department> departments = Arrays.asList(
                new Department("D1", "Engineering", Arrays.asList(
                        new Employee("E1", "Alice", 100000, "D1"),
                        new Employee("E2", "Bob", 120000, "D1"),
                        new Employee("E3", "Charlie", 110000, "D1")
                )),
                new Department("D2", "Sales", Arrays.asList(
                        new Employee("E4", "David", 90000, "D2"),
                        new Employee("E5", "Eve", 95000, "D2")
                )),
                new Department("D3", "HR", Arrays.asList(
                        new Employee("E6", "Frank", 80000, "D3"),
                        new Employee("E7", "Grace", 85000, "D3")
                ))
        );

        // Use this `departments` list for all the questions below

        System.out.println("1. Find the department with the highest total salary: " +
                departments.stream().flatMap(d -> d.getEmployees().stream().map(m -> new AbstractMap.SimpleEntry<>(d.getName(), m.getSalary())))
                        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)))
                        .entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null));

        System.out.println("2. Get a map where the key is the department name and the value is the list of employee names in that department: " +
                departments.stream().collect(Collectors.groupingBy(Department::getName,
                        Collectors.flatMapping(d -> d.getEmployees().stream(), Collectors.mapping(Employee::getName, toList())))));

        System.out.println("3. Find the employee with the highest salary in each department: " +
                departments.stream().collect(Collectors.groupingBy(Department::getName,
                        Collectors.flatMapping(d -> d.getEmployees().stream(), Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))))));

        System.out.println("4. Calculate the average salary for each department: " +
                departments.stream().collect(Collectors.groupingBy(Department::getName, Collectors.flatMapping(d -> d.getEmployees().stream(),
                        Collectors.averagingDouble(Employee::getSalary)))));

        System.out.println("5. Get a map where the key is the department name and the value is the total number of employees in that department: " +
                departments.stream().collect(Collectors.groupingBy(Department::getName, Collectors.flatMapping(
                        d -> d.getEmployees().stream(), Collectors.counting()
                ))));

        Map<String, Double> data = departments.stream()
                .collect(Collectors.groupingBy(
                        Department::getName,
                        Collectors.flatMapping(
                                (Department d) -> d.getEmployees().stream(),
                                Collectors.averagingDouble(Employee::getSalary)
                        )));

        System.out.println("6. Find all employees whose salary is above...: " +
                departments.stream()
                        .collect(Collectors.groupingBy(
                                Department::getName,
                                Collectors.flatMapping(
                                        (Department d) -> d.getEmployees().stream()
                                                .filter(e -> e.getSalary() > data.get(d.getName())),
                                        Collectors.toList()
                                )
                        )));

        System.out.println("7. Get a map where the key is the department name and the value is a list of employees sorted by salary in descending order: " +
                departments.stream().collect(Collectors.groupingBy(Department::getName, Collectors.flatMapping(
                        d -> d.getEmployees().stream().sorted(Comparator.comparing(Employee::getSalary).reversed()), toList()))));

        System.out.println("8. Find the department with the most employees: " +
                departments.stream().collect(Collectors.groupingBy(Department::getName, Collectors.flatMapping(
                        d -> d.getEmployees().stream(), Collectors.counting()))).entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null));

        System.out.println("9. Get a map where the key is the department name and the value is the sum of salaries of employees whose names start with a specific letter (e.g., \"A\"): " +
                departments.stream().collect(Collectors.groupingBy(Department::getName, Collectors.flatMapping(
                        d -> d.getEmployees().stream().filter(e -> e.getName().startsWith("A")), Collectors.summingDouble(Employee::getSalary)
                ))));

        System.out.println("10. Find the employee with the highest salary across all departments: " +
                departments.stream().flatMap(d -> d.getEmployees().stream()).max(Comparator.comparing(Employee::getSalary)).orElse(null));
    }
}

class Employee {
    private String id;
    private String name;
    private double salary;
    private String departmentId;

    public Employee(String id, String name, double salary, String departmentId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}

class Department {
    private String id;
    private String name;
    private List<Employee> employees;

    public Department(String id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}