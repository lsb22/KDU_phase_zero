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

            // maintain max heap of size 3
            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> frequency.get(a) - frequency.get(b));

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
                    int val = frequency.get(key);
                    if (pq.size() < 3)
                        pq.offer(key);
                    else if (val > frequency.get(pq.peek())) {
                        pq.poll();
                        pq.offer(key);
                    }
                }

                String top1 = "", top2 = "", top3 = "";
                int top1Count = 0, top2Count = 0, top3Count = 0;

                top3 = pq.peek();
                top3Count = frequency.get(pq.poll());

                if (pq.size() > 0) {
                    top2 = pq.peek();
                    top2Count = frequency.get(pq.poll());
                }

                if (pq.size() > 0) {
                    top1 = pq.peek();
                    top1Count = frequency.get(pq.poll());
                }

                System.out.println("Top 3 items with the most frequency are: ");
                if (top1.length() != 0)
                    System.out.println(top1 + ": " + top1Count);
                if (top2.length() != 0)
                    System.out.println(top2 + ": " + top2Count);
                if (top3.length() != 0)
                    System.out.println(top3 + ": " + top3Count);

            } else {
                System.err.println("File is empty");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
