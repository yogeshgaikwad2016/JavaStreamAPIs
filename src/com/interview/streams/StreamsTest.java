package com.interview.streams;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTest {
    static List<String> strings = Arrays
            .asList("apple", "banana", "cherry", "date", "grapefruit");

    static List<Person> persons = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 35)
    );

    static List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);

    static List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
    static List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);

    static List<Integer> ls1 = Arrays.asList(1, 2, 3, 4, 5);
    static List<Integer> ls2 = Arrays.asList(3, 4, 5, 6, 7);

    static List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 6, 5);

    static List<Transaction> transactions = Arrays.asList(
            new Transaction("2022-01-01", 100),
            new Transaction("2022-01-01", 200),
            new Transaction("2022-01-02", 300),
            new Transaction("2022-01-02", 400),
            new Transaction("2022-01-03", 500));

    public static void main(String[] args) {
        //Q. Find the longest string in a list of strings using Java streams:
        Optional<String> longStr = strings.stream().max(Comparator.comparing(String::length));
        System.out.println("Longest string: " + (longStr.orElse("No longest string present")));

        //Q. Calculate the average age of a list of Person objects using Java streams:
        System.out.println("Average age: " + persons.stream().mapToInt(Person::getAge).average().orElse(0));

        //Q. Check if a list of integers contains a prime number using Java streams:
        System.out.println("Is Prime: " + numbers.stream().anyMatch(StreamsTest::isPrime));

        //Q. Merge two sorted lists into a single sorted list using Java streams:
        System.out.println("Merged list: " + Stream.concat(list1.stream(), list2.stream()).toList());

        //Q. Find the intersection of two lists using Java streams:
        System.out.println("Intersection of two lists: " + ls1.stream().filter(ls2::contains).toList());

        //Q. Remove duplicates from a list while preserving the order using Java streams:
        System.out.println("Unique list: " + numbersWithDuplicates.stream().distinct().toList());

        //Q. Given a list of transactions, find the sum of transaction amounts for each day using Java streams:
        System.out.println("List of transaction for each day: " + transactions.stream().collect(Collectors.groupingBy(k -> k.date, Collectors.summingInt(t -> t.amount))));

        //Q. Find the kth smallest element in an array using Java streams:
        int[] array = {4, 2, 7, 1, 5, 3, 6};
        int k = 3; // Find the 3rd smallest element
        System.out.println(k + " smallest element: " + Arrays.stream(array).sorted().skip(k - 1).findFirst().orElse(-1));

        //Q. Given a list of strings, find the frequency of each word using Java streams:
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
        Map<String, Long> wordFrequency = words.stream().collect(Collectors.groupingBy(str -> str, Collectors.counting()));
        System.out.println("Word frequency: " + wordFrequency);

        //Q. Implement a method to partition a list into two groups based on a predicate using Java streams:
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> partitioned = numbers.stream().collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println("Even: " + partitioned.get(true) + " " + "Odd:" + partitioned.get(false));


    }

    static boolean isPrime ( int number){
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class Person {
    int age;
    String name;

    public int getAge() {
        return this.age;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Transaction {
    String date;
    int amount;

    Transaction(String date, int amount) {
        this.amount = amount;
        this.date = date;
    }
}
