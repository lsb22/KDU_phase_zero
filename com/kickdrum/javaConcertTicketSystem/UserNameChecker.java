package com.kickdrum.javaConcertTicketSystem;

class UserNameChecker {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please Enter your UserName twice for ticket confirmation!");
            System.exit(-1);
        }
        System.out.println("Length of the First String: " + args[0].length());
        System.out.println("Length of the Second String: " + args[1].length());

        // checks if strings length are same
        if (args[0].length() == args[1].length()) {
            System.out.println("Both Strings length Match: True");
        } else {
            System.out.println("Both Strings length Match: False");
        }

        // checks if strings are same
        if (args[0].equals(args[1])) {
            System.out.println("Both Strings Match: True");
        } else {
            System.out.println("Both Strings Match: False");
        }
    }
}