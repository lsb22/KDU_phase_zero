package com.kickdrum.javaConcertTicketSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

class TicketCategories {
    public static void main(String[] args) {
        if (args.length < 10) {
            System.err.println("Must provide 10 ticket types as Input");
            System.exit(-1);
        }
        ArrayList<String> ticketsList = new ArrayList<>();
        HashSet<String> uniqueTickets = new HashSet<>();
        HashMap<String, Integer> ticketsFrequency = new HashMap<>();

        for (String curr : args) {
            ticketsList.add(curr);
            uniqueTickets.add(curr);
            ticketsFrequency.put(curr, ticketsFrequency.getOrDefault(curr, 0) + 1);
        }

        System.out.println("ArrayList: " + ticketsList);
        System.out.println("HashSet: " + uniqueTickets);

        int size = ticketsFrequency.size(), count = 0;

        System.out.print("HashMap: {");
        for (String key : ticketsFrequency.keySet()) {
            System.out.print(key + "=" + ticketsFrequency.get(key));
            if (count++ != size - 1)
                System.out.print(", ");
        }
        System.out.println("}");
    }
}
