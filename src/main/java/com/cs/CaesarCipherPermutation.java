package com.cs;

import java.util.LinkedHashSet;
import java.util.Set;

public class CaesarCipherPermutation {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String permutedAlphabet;

    public CaesarCipherPermutation(String keyword) {
        if (keyword == null || keyword.length() < 7 || !keyword.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Keyword must have at least 7 letters (A-Z).");
        }
        this.permutedAlphabet = generatePermutedAlphabet(keyword.toUpperCase());
    }

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
            int index = permutedAlphabet.indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            int newIndex = (index + shift + 26) % 26;
            result.append(permutedAlphabet.charAt(newIndex));
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

    private String generatePermutedAlphabet(String keyword) {
        Set<Character> letters = new LinkedHashSet<>();

        for (char c : keyword.toCharArray()) {
            letters.add(c);
        }

        for (char c : ALPHABET.toCharArray()) {
            letters.add(c);
        }

        StringBuilder permuted = new StringBuilder();
        for (char c : letters) {
            permuted.append(c);
        }

        return permuted.toString();
    }

    public String getPermutedAlphabet() {
        return permutedAlphabet;
    }
}

