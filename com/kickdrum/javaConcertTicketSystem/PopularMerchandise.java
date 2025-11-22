package com.kickdrum.javaConcertTicketSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

            // variables to hold the top 3 most frequent strings
            String first = "", second = "", third = "";
            // variables to hold the frequency of the top 3 most frequent strings
            int first_frequency = 0, second_frequency = 0, third_frequency = 0;

            if (items != null) {
                for (int i = 0; i < items.length; i++) {
                    // remove extra space around each string
                    items[i] = items[i].trim();
                }

                // iterate and find the most frequent strings
                for (String curr : items) {
                    frequency.put(curr, frequency.getOrDefault(curr, 0) + 1);
                    if (first_frequency < frequency.get(curr)) {
                        first_frequency = frequency.get(curr);
                        first = curr;
                    } else if (second_frequency < frequency.get(curr)) {
                        second_frequency = frequency.get(curr);
                        second = curr;
                    } else if (third_frequency < frequency.get(curr)) {
                        third_frequency = frequency.get(curr);
                        third = curr;
                    }
                }
            }

            System.out.println("Top 3 items with the most frequency are: ");
            System.out.println(first + ": " + first_frequency);
            System.out.println(second + ": " + second_frequency);
            System.out.println(third + ": " + third_frequency);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
