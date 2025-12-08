package com.kickdrum.javaConcertTicketSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class PopularMerchandise {
    public static void main(String[] args) {
        String fileName = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter the CSV File Name: ");
            fileName = br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // check if the entered file is a CSV
        if (fileName == null || fileName.length() == 0
                || !fileName.substring(fileName.length() - 3, fileName.length()).equals("csv")) {
            System.err.println("Please Enter valid file path!");
            System.exit(-1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] items = null;
            Map<String, Integer> frequency = new HashMap<>();

            // read the entire file as a single string
            // and then split the string into an array
            while (br.ready()) {
                items = (br.readLine()).split(",");
            }

            // max heap
            PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

            if (items != null) {
                for (int i = 0; i < items.length; i++) {
                    // remove extra space around each string
                    items[i] = items[i].trim();
                }

                // iterate and add strings to map
                for (String curr : items) {
                    if (curr.length() > 0)
                        frequency.put(curr, frequency.getOrDefault(curr, 0) + 1);
                }

                // if file contains empty strings
                if (frequency.size() == 0) {
                    System.err.println("File is empty");
                    System.exit(-1);
                }

                // add key value pairs to queue
                for (String key : frequency.keySet()) {
                    pq.offer(new String[] { key, frequency.get(key) + "" });
                }

                System.out.println("Top 3 items with the most frequency are: ");
                System.out.println(pq.peek()[0] + ": " + pq.poll()[1]);
                if (pq.size() > 0) {
                    System.out.println(pq.peek()[0] + ": " + pq.poll()[1]);
                }
                if (pq.size() > 0) {
                    System.out.println(pq.peek()[0] + ": " + pq.poll()[1]);
                }
            } else {
                System.err.println("File is empty");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
