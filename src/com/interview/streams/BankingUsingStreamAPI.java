package com.interview.streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BankingUsingStreamAPI {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T1", "CREDIT", 1000.0, "USD", "A1", new Date(2023 - 1900, 0, 1)), // Jan 1, 2023
                new Transaction("T2", "DEBIT", 500.0, "USD", "A1", new Date(2023 - 1900, 0, 2)), // Jan 2, 2023
                new Transaction("T3", "CREDIT", 2000.0, "EUR", "A2", new Date(2023 - 1900, 0, 3)), // Jan 3, 2023
                new Transaction("T4", "DEBIT", 1000.0, "EUR", "A2", new Date(2023 - 1900, 0, 4)), // Jan 4, 2023
                new Transaction("T5", "CREDIT", 1500.0, "GBP", "A3", new Date(2023 - 1900, 0, 5)), // Jan 5, 2023
                new Transaction("T6", "DEBIT", 750.0, "GBP", "A3", new Date(2023 - 1900, 0, 6)), // Jan 6, 2023
                new Transaction("T7", "CREDIT", 3000.0, "USD", "A4", new Date(2023 - 1900, 0, 7)), // Jan 7, 2023
                new Transaction("T8", "DEBIT", 1500.0, "USD", "A4", new Date(2023 - 1900, 0, 8)) // Jan 8, 2023
        );

        // Use this `transactions` list for all the questions below

        System.out.println("1. Find the total amount of all \"CREDIT\" transactions in \"USD\": " + (Double) transactions.stream()
                .filter(t -> t.getCurrency().equals("USD") && t.getType().equals("CREDIT")).mapToDouble(Transaction::getAmount).sum());

        System.out.println("2. Group transactions by currency and calculate the total amount for each currency: " +
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.summingDouble(Transaction::getAmount))));

        System.out.println("3. Find the transaction with the highest amount: " +
                transactions.stream().max(Comparator.comparing(Transaction::getAmount)).get());

        System.out.println("4. Get a list of all \"DEBIT\" transactions sorted by date in ascending order: " +
                transactions.stream().filter(t -> t.getType().equals("DEBIT")).sorted(Comparator.comparing(Transaction::getDate)).toList());

        System.out.println("5. Calculate the average amount of transactions for each account: " +
                transactions.stream().collect(Collectors.groupingBy(Transaction::getAccountId, Collectors.averagingDouble(Transaction::getAmount))));

        System.out.println("6. Find the account with the highest total transaction amount: " +
                transactions.stream().collect(Collectors.groupingBy(Transaction::getAccountId, Collectors.summingDouble(Transaction::getAmount)))
                        .entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null));

        Map<Boolean, List<Transaction>> data = transactions.stream().collect(Collectors.partitioningBy(t -> t.getType().equals("CREDIT")));
        System.out.println("7. Partition transactions into two groups: \n\"CREDIT\": " + data.get(true) + " \n\"DEBIT\": " + data.get(false));

        System.out.println("8. Get a map where the key is the currency and the value is the list of transaction amounts in that currency: " +
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.mapping(Transaction::getAmount, toList()))));

        System.out.println("9. Find the total number of transactions for each account: " +
                transactions.stream().collect(Collectors.groupingBy(Transaction::getAccountId, Collectors.counting())));

        System.out.println("10. Find the earliest transaction date for each account: " +
                transactions.stream().collect(Collectors.groupingBy(Transaction::getAccountId, Collectors.minBy(Comparator.comparing(Transaction::getDate)))));
    }
}

class Transaction {
    private String id;
    private String type; // "CREDIT" or "DEBIT"
    private double amount;
    private String currency; // "USD", "EUR", "GBP"
    private String accountId;
    private Date date;

    public Transaction(String id, String type, double amount, String currency, String accountId, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.accountId = accountId;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", accountId='" + accountId + '\'' +
                ", date=" + date +
                '}';
    }
}
