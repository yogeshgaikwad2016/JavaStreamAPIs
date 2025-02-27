package com.interview.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIsOperations {

    /*
      Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
     */
    static void findEvenNumbers() {
        List<Integer> result = list.stream().filter(n -> n % 2 == 0).toList();
        System.out.println(result);
    }

    /*
     Given a list of integers, find out all the numbers starting with K using Stream functions?
     */
    static void findNumStartingWithK(int k) {
        System.out.println("Starting int with k: " + list.stream().map(Object::toString).filter(str -> str.startsWith(String.valueOf(k))).toList());
    }

    /*
    How to find duplicate elements in a given integers list in java using Stream functions?
     */
    static void findDuplicates() {
        Set<Integer> temp = new HashSet<>();
        System.out.println("Duplicate elements: " + list.stream().filter(n -> !temp.add(n)).toList());
    }

    /*
     Given the list of integers, find the first element of the list using Stream functions?
     */
    static void findFirstElement() {
        System.out.println("First element of list: " + list.stream().findFirst());
    }

    static List<Notes> noteLst = new ArrayList<>();

    /*
    Given a list of integers, find the total number of elements present in the list using Stream functions?
     */
    static void findTotalNumberElements() {
        System.out.println("Total number of elements: " + list.stream().count());
    }

    /*
    Given a list of integers, find the maximum value element present in it using Stream functions?
     */
    static void findMax() {
        System.out.println("Max: " + list.stream().max(Integer::compare).orElse(-1));
    }

    /*
    Given a String, find the first non-repeated character in it using Stream functions?
     */
    static void findFirstNonRepeatedChar() {
        System.out.println(input.chars().mapToObj(ch -> Character.toLowerCase((char) ch)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter((k -> k.getValue() == 1)).findFirst());
    }

    /*
    Given a String, find the first repeated character in it using Stream functions?
     */
    static void findFirstRepeatedChar() {
        System.out.println(input.chars().filter(ch -> ch != ' ').mapToObj(ch -> Character.toLowerCase((char) ch)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(d -> d.getValue() > 1).findFirst());
    }

    /*
    Given a list of integers, sort all the values present in it using Stream functions?
     */
    static void sortList(Comparator<? super Integer> order) {
        System.out.println("Sorted list: " + list.stream().sorted().toList());
    }

    /*
    Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
     */
    static void checkIfRepeatationExists() {

        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().anyMatch(d -> d.getValue() > 1));
    }

    /*
    Write a Java 8 program to concatenate two Streams?
     */
    static void concatTwoLists() {
        System.out.println("Merged list: " + Stream.concat(list1.stream(), list2.stream()).toList());
    }

    /*
    Java 8 program to perform cube on list elements and filter numbers greater than 50.
     */
    static void findCubedNumGreaterThanK(int k) {
        System.out.println(list.stream().map(n -> n * n * n).filter(num -> num > 50).toList());
    }

    /*
    Write a Java 8 program to sort an array and then convert the sorted array into Stream?
     */
    static void convertSortedArrStream() {
        int[] arr = { 99, 55, 203, 99, 4, 91 };
        Arrays.stream(arr).sorted().forEach(n -> System.out.print(n + ", "));
        System.out.println();
    }

    /*
    How to use map to convert object into Uppercase in Java 8?
     */
    static void convertToUpperCase() {
        System.out.println("UpperCase: " + noteLst.stream().map(obj -> obj.getName().toUpperCase()).toList());
    }

    /*
    How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?
     */
    static void convertToMap() {
        noteLst.stream().sorted(Comparator.comparingInt(Notes::getId).reversed()).collect(Collectors.toMap(Notes::getName, Function.identity(), (old, val) -> old, LinkedHashMap::new))
                .forEach((key, value) -> System.out.print(" id: " + value.id + " name: " + value.name + " price: " + value.price + "\n"));
    }

    /*
    How to count each element/word from the String ArrayList in Java8?
     */
    static void countString() {
        System.out.println();
        System.out.println("Count elements in string: " + Arrays.stream(input.split(" ")).collect(Collectors.groupingBy(String::length)));
    }

    /*
    How to find only duplicate elements with its count from the String ArrayList in Java8?
     */
    static void countDup() {
        System.out.println(names.stream().filter(f -> Collections.frequency( names, f) > 1).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    /*
    How to check if list is empty in Java 8 using Optional, if not null iterate through the list and print the object?
     */
    static void useOfOptional() {
        Optional.ofNullable(list1).ifPresent(System.out::print);
        System.out.println();
    }

    /*
    Separate even and odd
     */
    static void separateEvOd() {
        System.out.println("Even: " + list.stream().filter(n -> n % 2 == 0).toList());
        System.out.println("Odd: " + list.stream().filter(n -> n % 2 != 0).toList());
    }

    static void anagramsTest() {
        String s = "anagram", t = "naGaram";
        boolean res = Arrays.stream(s.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining())
                .equals(Arrays.stream(t.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining()));

        System.out.print("Anagram: " + res);
    }

    /*
    Join the list of strings with prefix, suffix, delimiter
     */
    static void joinWith() {
        String pref = " #", suffix = "*", delimiter = " - ";

        System.out.println("Pref, suf, del joined: " + list2.stream().collect(Collectors.joining(delimiter, pref, suffix)));
    }

    static final String input = "java articles are Awesome";
    static List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
    static final List<Integer> list = Arrays.asList(10,15,8,49,25,98,98,32,15);
    static List<String> list1 = Arrays.asList("Java", "8");
    static List<String> list2 = Arrays.asList("explained", "through", "programs", "explained");

    public static void main(String[] args) {

        noteLst.add(new Notes(4, "note4", 44));
        noteLst.add(new Notes(5, "note5", 55));
        noteLst.add(new Notes(6, "note4", 66));
        noteLst.add(new Notes(1, "note1", 11));
        noteLst.add(new Notes(2, "note2", 22));
        noteLst.add(new Notes(3, "note3", 33));


        findEvenNumbers();
        findNumStartingWithK(4);
        findDuplicates();
        findFirstElement();
        findTotalNumberElements();
        findMax();
        findFirstNonRepeatedChar();
        findFirstRepeatedChar();
        sortList(Comparator.reverseOrder());
        checkIfRepeatationExists();
        concatTwoLists();
        findCubedNumGreaterThanK(50);
        convertSortedArrStream();
        convertToUpperCase();
        convertToMap();
        countString();
        countDup();
        useOfOptional();
        separateEvOd();
        joinWith();
        anagramsTest();
    }
}

class Notes {
    int id;
    String name;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    Notes(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}