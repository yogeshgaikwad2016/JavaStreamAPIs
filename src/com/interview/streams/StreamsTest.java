package com.interview.streams;

import java.util.*;
import java.util.function.Function;
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
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> partitioned = numbers1.stream().collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println("Even: " + partitioned.get(true) + " " + "Odd:" + partitioned.get(false));

        //Q. Find the sum of two largest odd numbers from list.
        int sum = numbers.stream().filter(n -> n % 2 != 0).sorted(Collections.reverseOrder()).limit(2).mapToInt(Integer::intValue).sum();
        System.out.println("Sum of two largest odd numbers: " + sum);

        //Q. How do you find most repeated element from array and it's count
        System.out.println("Repeated element with count: " + words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()));

        Map<String, Double> grades1 = new HashMap<>();
        grades1.put("Math", 90.5);
        grades1.put("Science", 85.0);
        grades1.put("History", 78.5);

        Map<String, Double> grades2 = new HashMap<>();
        grades2.put("Math", 88.0);
        grades2.put("Science", 92.5);
        grades2.put("History", 81.0);

        Map<String, Double> grades3 = new HashMap<>();
        grades3.put("Math", 95.0);
        grades3.put("Science", 89.5);
        grades3.put("History", 76.0);

        // Create student objects
        Student student1 = new Student("Alice", grades1);
        Student student2 = new Student("Bob", grades2);
        Student student3 = new Student("Charlie", grades3);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        //Q. Get average marks map for each student.
        System.out.println("AVG marks: " + students.stream().collect(Collectors.toMap(Student::getName, student -> student.getStudentGrade().values().stream().collect(Collectors.averagingDouble(Double::doubleValue)))));

        //Q. Student who scored the highest show - Name and in which subject.
        System.out.println("Student scored highest: " + students.stream().collect(Collectors.toMap(Student::getName, s -> s.getStudentGrade().values().stream().max(Double::compareTo).orElse(0.0))));

        List<Product> electronicsProducts = new ArrayList<>();
        electronicsProducts.add(new Product("Laptop", "Electronics", 999.99));
        electronicsProducts.add(new Product("Smartphone", "Electronics", 499.99));
        electronicsProducts.add(new Product("Headphones", "Electronics", 199.99));

        List<Product> clothingProducts = new ArrayList<>();
        clothingProducts.add(new Product("Shirt", "Clothing", 29.99));
        clothingProducts.add(new Product("Jeans", "Clothing", 49.99));
        clothingProducts.add(new Product("Shoes", "Clothing", 79.99));

        List<Product> groceryProducts = new ArrayList<>();
        groceryProducts.add(new Product("Apples", "Groceries", 4.99));
        groceryProducts.add(new Product("Bread", "Groceries", 2.99));
        groceryProducts.add(new Product("Milk", "Groceries", 3.49));

        // Create store objects
        Store electronicsStore = new Store("Electronics Store", electronicsProducts);
        Store clothingStore = new Store("Clothing Store", clothingProducts);
        Store groceryStore = new Store("Grocery Store", groceryProducts);

        // Create a list of stores
        List<Store> stores = new ArrayList<>();
        stores.add(electronicsStore);
        stores.add(clothingStore);
        stores.add(groceryStore);

        //Q. Find out store names which has price sum more than 50.
        //Map<String, List<String>> data = stores.stream().collect(Collectors.toMap(Store::getName, store -> store.getProducts().stream().filter(p -> p.getPrice() > 50).map(Product::getName).toList()));
        System.out.println("Store with product price > 50: " + stores.stream().filter(store -> store.getProducts().stream().anyMatch(p -> p.getPrice() > 50)).map(Store::getName).toList());

        //Q. Find out each store category with sum of all the prices.
        System.out.println("Store category with prices sum: " + stores.stream().flatMap(store -> store.getProducts().stream()).collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice))));
    }

    static boolean isPrime(int number) {
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

class Student {
    private String name;
    private Map<String, Double> studentGrade; // Map of subject and grade

    // Constructor
    public Student(String name, Map<String, Double> studentGrade) {
        this.name = name;
        this.studentGrade = studentGrade;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Map<String, Double> getStudentGrade() {
        return studentGrade;
    }
}

class Product {
    private String name;
    private String category;
    private double price;

    // Constructor
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}

// Store class
class Store {
    private String name;
    private List<Product> products;

    // Constructor
    public Store(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

}