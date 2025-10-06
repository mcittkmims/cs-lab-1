package com.cs;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Caesar Cipher Interactive Tool ===");

        while (running) {
            System.out.println("\nChoose algorithm:");
            System.out.println("1. Caesar Cipher");
            System.out.println("2. Caesar Cipher with Permutation");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> runCaesar(scanner);
                case 2 -> runCaesarPermutation(scanner);
                case 0 -> {
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void runCaesar(Scanner scanner) {
        CaesarCipher cipher = new CaesarCipher();

        System.out.print("Encrypt or Decrypt (E/D): ");
        char mode = scanner.nextLine().trim().toUpperCase().charAt(0);

        System.out.print("Enter key (1-25): ");
        int key = readInt(scanner);

        System.out.print("Enter message: ");
        String text = scanner.nextLine();

        try {
            String result = (mode == 'E')
                    ? cipher.encrypt(text, key)
                    : cipher.decrypt(text, key);

            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runCaesarPermutation(Scanner scanner) {
        System.out.print("Enter permutation keyword (min 7 letters): ");
        String keyword = scanner.nextLine();

        CaesarCipherPermutation cipher;
        try {
            cipher = new CaesarCipherPermutation(keyword);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Encrypt or Decrypt (E/D): ");
        char mode = scanner.nextLine().trim().toUpperCase().charAt(0);

        System.out.print("Enter key (1-25): ");
        int key = readInt(scanner);

        System.out.print("Enter message: ");
        String text = scanner.nextLine();

        try {
            String result = (mode == 'E')
                    ? cipher.encrypt(text, key)
                    : cipher.decrypt(text, key);

            System.out.println("Generated alphabet: " + cipher.getPermutedAlphabet());
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
