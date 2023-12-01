package io.github.tropheusj.aoc_2023;

import io.github.tropheusj.Solution;

import java.util.function.Predicate;

public class Day1 implements Solution {
    @Override
    public void run(String data) {
        int sum = 0;
        String[] lines = data.split("\n");
        for (String line : lines) {
            sum += decodeLine(line);
        }
        System.out.println("Total: " + sum);
    }

    private static int decodeLine(String line) {
        int first = indexOf(line, Character::isDigit);
        int last = lastIndexOf(line, Character::isDigit);
        char a = line.charAt(first);
        char b = line.charAt(last);
        String num = String.valueOf(a) + b;
        return Integer.parseInt(num);
    }

    private static int indexOf(String input, Predicate<Character> test) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (test.test(c))
                return i;
        }
        return -1;
    }

    private static int lastIndexOf(String input, Predicate<Character> test) {
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if (test.test(c))
                return i;
        }
        return -1;
    }
}
