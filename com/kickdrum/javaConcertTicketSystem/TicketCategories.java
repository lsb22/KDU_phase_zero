package com.kickdrum.javaConcertTicketSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

class TicketCategories {
    public static void main(String[] args) {
        String[] tickets = null;

        // taking input as a single string
        // split the string into an array
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter 10 ticket types as a single Input seperated by comma: ");
            tickets = (br.readLine()).split(",");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // check if length not equal to 10
        if (tickets.length != 10) {
            System.err.println("Must provide 10 ticket types as Input");
            System.exit(-1);
        }

        for (int i = 0; i < tickets.length; i++) {
            // remove extra spaces around the string
            tickets[i] = tickets[i].trim();
            // check for empty strings
            if (tickets[i].length() == 0) {
                System.err.println("Must provide non empty Strings as Input");
                System.exit(-1);
            }
        }

        ArrayList<String> ticketsList = new ArrayList<>();
        HashSet<String> uniqueTickets = new HashSet<>();
        HashMap<String, Integer> ticketsFrequency = new HashMap<>();

        for (String curr : tickets) {
            ticketsList.add(curr);
            uniqueTickets.add(curr);
            ticketsFrequency.put(curr, ticketsFrequency.getOrDefault(curr, 0) + 1);
        }

        System.out.println("ArrayList: " + ticketsList);
        System.out.println("HashSet: " + uniqueTickets);
        System.out.print("HashMap: " + ticketsFrequency);
    }
}
