package com.kickdrum.javaConcertTicketSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class UserNameChecker {
    public static void main(String[] args) {
        String[] userName = null;

        // read username as a single string
        // split the string and store usernames in array
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter your UserName twice seperated by a comma: ");
            userName = (br.readLine()).split(",");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // check if length in not equal to 2
        if (userName.length != 2) {
            System.out.println("Please Enter your UserName twice for ticket confirmation!");
            System.exit(-1);
        }
        userName[0] = userName[0].trim();
        userName[1] = userName[1].trim();

        // check if usernames are empty
        if (userName[0].length() == 0 || userName[1].length() == 0) {
            System.out.println("UserName shouldn't be empty!");
            System.exit(-1);
        }

        System.out.println("Length of the First String: " + userName[0].length());
        System.out.println("Length of the Second String: " + userName[1].length());

        // checks if strings length are same
        if (userName[0].length() == userName[1].length()) {
            System.out.println("Both Strings length Match: True");
        } else {
            System.out.println("Both Strings length Match: False");
        }

        // checks if strings are same
        if (userName[0].equals(userName[1])) {
            System.out.println("Both Strings Match: True");
        } else {
            System.out.println("Both Strings Match: False");
        }
    }
}