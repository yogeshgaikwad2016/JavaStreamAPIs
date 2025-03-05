package com.interview.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class Person {
    private String name;
    private int age;
    private String city;
    private double salary;

    public Person(String name, int age, String city, double salary) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class StreamQuestions {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "New York", 50000),
                new Person("Bob", 30, "San Francisco", 60000),
                new Person("Charlie", 35, "New York", 70000),
                new Person("David", 40, "San Francisco", 80000),
                new Person("Eve", 45, "Los Angeles", 90000),
                new Person("Frank", 50, "Los Angeles", 100000),
                new Person("Grace", 55, "New York", 110000),
                new Person("Hank", 60, "San Francisco", 120000)
        );

        // Use this `people` list for all the questions below

        System.out.println("1. Find the average salary of people living in \"New York\": " + people.stream().filter(p -> "New York".equals(p.getCity()))
                                                                                                            .collect(Collectors.averagingDouble(Person::getSalary)));

        System.out.println("2. Group people by their city: " + people.stream().collect(Collectors.groupingBy(Person::getCity)));

        System.out.println("3. Find the person with the highest salary: " + people.stream().max(Comparator.comparingDouble(Person::getSalary)).orElse(null));

        System.out.println("4. Get a list of names of people who are older than 40: " + people.stream().filter(p -> p.getAge() > 40)
                                                                                                        .map(Person::getName).toList());

        System.out.println("5. Calculate the total salary of all people in \"San Francisco\" : " + people.stream()
                                                .filter(p -> "San Francisco".equals(p.getCity())).collect(Collectors.summingDouble(Person::getSalary)));

        System.out.println("6. Find the oldest person in each city: " + people.stream()
                                                .collect(Collectors.groupingBy(Person::getCity, Collectors.maxBy(Comparator.comparingInt(Person::getAge)))));

        System.out.println("7. Get a map where the key is the city and the value is the list of salaries of people living in that city: "
                            + people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getSalary, toList()))));

        System.out.println("8. Check if all people are older than 20: " + people.stream().allMatch(p -> p.getAge() > 20));

        System.out.println("9. Find the city with the highest total salary: " + people.stream()
                                                    .collect(Collectors.groupingBy(Person::getCity, Collectors.summingDouble(Person::getSalary)))
                                                    .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No city"));

        Map<Boolean, List<Person>> res = people.stream().collect(Collectors.partitioningBy(p -> p.getSalary() > 75000));
        System.out.println("10. Partition People by Salary \n True: " + res.get(true) + "\n False: " + res.get(false));
    }
}
