package com.interview.companies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NIQ {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 3, 4, 5, 5};
        String str = "level";

        List<Integer> ans1 = new ArrayList<>();
        for (int j : arr) {
            if (!ans1.contains(j)) {
                ans1.add(j);
            }
        }
        System.out.println("Q1. [1,1,2,3,4,5,5] remove duplicate from given list? \n" +
                "There are two ways to solve: \n" +
                "   1. Programmatically using hashmap and loop: " + ans1 +
                "   1. Using Streams: ");
        boolean res = false;

        System.out.println("Q2. Print all palindromes of a string: " + Arrays.toString(str.split("")));
    }
}
