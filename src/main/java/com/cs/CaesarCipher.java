package com.cs;

public class CaesarCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encrypt(String text, int key) {
        text = preprocess(text);
        validateKey(key);
        return transform(text, key);
    }

    public String decrypt(String text, int key) {
        text = preprocess(text);
        validateKey(key);
        return transform(text, -key);
    }

    private String transform(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            int newIndex = (index + shift + 26) % 26;
            result.append(ALPHABET.charAt(newIndex));
        }

        return result.toString();
    }

    private void validateKey(int key) {
        if (key < 1 || key > 25) {
            throw new IllegalArgumentException("Key must be between 1 and 25.");
        }
    }

    private String preprocess(String text) {
        String cleaned = text.replaceAll(" ", "").toUpperCase();
        if (!cleaned.matches("[A-Z]+")) {
            throw new IllegalArgumentException("Text must contain only A-Z or a-z letters.");
        }
        return cleaned;
    }
}