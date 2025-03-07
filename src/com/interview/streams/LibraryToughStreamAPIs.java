package com.interview.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class LibraryToughStreamAPIs {

    public static void main(String[] args) {
        List<Library> libraries = Arrays.asList(
                new Library("Central Library", Arrays.asList(
                        new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, Arrays.asList(
                                new Member("Alice", 25, "Regular"),
                                new Member("Bob", 30, "Premium")
                        )),
                        new Book("1984", "George Orwell", 1949, Arrays.asList(
                                new Member("Charlie", 22, "Regular"),
                                new Member("David", 35, "Premium")
                        ))
                )),
                new Library("City Library", Arrays.asList(
                        new Book("To Kill a Mockingbird", "Harper Lee", 1960, Arrays.asList(
                                new Member("Eve", 28, "Premium"),
                                new Member("Frank", 40, "Regular")
                        )),
                        new Book("The Catcher in the Rye", "J.D. Salinger", 1951, Arrays.asList(
                                new Member("Grace", 19, "Regular"),
                                new Member("Hank", 50, "Premium")
                        )),
                        new Book("The Rye", "J.D. Salinger", 1950, Arrays.asList(
                                new Member("Grace", 19, "Regular"),
                                new Member("Hank", 50, "Premium"),
                                new Member("Charlie", 22, "Regular")
                        ))
                )),
                new Library("Central Library", Arrays.asList(
                        new Book("Book A", "Author X", 2005, Arrays.asList(
                                new Member("Alice", 25, "Regular"),
                                new Member("Bob", 30, "Premium"),
                                new Member("Bob", 30, "Premium"),
                                new Member("Charlie", 22, "Regular")
                        )),
                        new Book("Book B", "Author Y", 2010, Arrays.asList(
                                new Member("Alice", 25, "Regular"), // Alice borrowed another book
                                new Member("Charlie", 22, "Regular")
                        ))
                ))
        );

        System.out.println("Question 1: Find the average age of members who have borrowed books published after the year 2000: " +
                libraries.stream().flatMap(l -> l.getBooks().stream().filter(b -> b.getYearPublished() > 2000).flatMap(b -> b.getBorrowers().stream()))
                        .collect(Collectors.collectingAndThen(toSet(), m -> m.stream().collect(Collectors.averagingInt(Member::getAge)))));

        System.out.println("Question 2: Group books by the number of members who have borrowed them: " +
                libraries.stream().flatMap(l -> l.getBooks().stream()).collect(Collectors.groupingBy(Book::getTitle, Collectors.flatMapping(
                        b -> b.getBorrowers().stream(), Collectors.counting()
                ))));

        System.out.println("Question 3: Find the book with the highest number of borrowers: " +
                libraries.stream().flatMap(l -> l.getBooks().stream()).collect(
                        Collectors.groupingBy(Function.identity(), Collectors.flatMapping(b -> b.getBorrowers().stream(), Collectors.counting())))
                        .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null));

        System.out.println("Question 4: Get a map where the key is the author and the value is the list of books they have written, sorted by publication year: " +
                libraries.stream().flatMap(l -> l.getBooks().stream())
                        .collect(Collectors.groupingBy(Book::getAuthor, Collectors.collectingAndThen(toList(), b -> {
                            b.sort(Comparator.comparing(Book::getYearPublished));
                            return Collections.unmodifiableList(b);
                        }))));

        System.out.println("Question 5: Calculate the total number of books borrowed by premium members: " +
                libraries.stream().flatMap(l -> l.getBooks().stream()).flatMap(b -> b.getBorrowers().stream())
                        .filter(m -> "Premium".equals(m.getMembershipType())).toList().size());

        System.out.println("Question 6: Find the member who has borrowed the most books: " +
                libraries.stream().flatMap(l -> l.getBooks().stream()).flatMap(b -> b.getBorrowers().stream())
                        .collect(Collectors.groupingBy(Member::getName, Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null));

        System.out.println("Question 7: Group members by their membership type and calculate the average age for each group: " +
                libraries.stream().flatMap(l -> l.getBooks().stream()).flatMap(b -> b.getBorrowers().stream())
                        .collect(Collectors.groupingBy(Member::getMembershipType, Collectors.averagingDouble(Member::getAge))));

        System.out.println("Question 8: Find all books that have been borrowed by at least 3 members and were published before 2010: " +
                libraries.stream().flatMap(l -> l.getBooks().stream().filter(b -> b.getYearPublished() < 2010))
                        .filter(b -> b.getBorrowers().size() >= 3) // Filter books borrowed by at least 3 members
                        .map(Book::getTitle) // Get the title of each book
                        .toList());

        System.out.println("Question 9: Get a map where the key is the book title and the value is the list of members who have borrowed it, sorted by member age: " +
                libraries.stream().flatMap(l -> l.getBooks().stream())
                        .collect(Collectors.groupingBy(Book::getTitle, Collectors.flatMapping(
                                b -> b.getBorrowers().stream(), Collectors.collectingAndThen(toList(), m -> {
                                    m.sort(Comparator.comparing(Member::getAge));
                                    return m;
                                }
                                )))));

        System.out.println("Question 10: Find the author with the highest total number of borrowers across all their books: " +
                libraries.stream().flatMap(l -> l.getBooks().stream())
                        .collect(Collectors.groupingBy(Book::getAuthor,
                                Collectors.flatMapping(b -> b.getBorrowers().stream(), Collectors.counting())))
                        .entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null));

    }
}

class Library {
    private String name;
    private List<Book> books;

    public Library(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() { return name; }
    public List<Book> getBooks() { return books; }
}

class Book {
    private String title;
    private String author;
    private int yearPublished;
    private List<Member> borrowers;

    public Book(String title, String author, int yearPublished, List<Member> borrowers) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowers = borrowers;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYearPublished() { return yearPublished; }
    public List<Member> getBorrowers() { return borrowers; }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }
}

class Member {
    private String name;
    private int age;
    private String membershipType; // "Regular", "Premium"

    public Member(String name, int age, String membershipType) {
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMembershipType() { return membershipType; }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }
}
