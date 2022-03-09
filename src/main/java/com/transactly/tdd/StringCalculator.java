package com.transactly.tdd;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {
    public int sum(String numbers) {
        if (!StringUtils.hasText(numbers)) {
            return 0;
        }

        if (numbers.contains("//")) {
            String[] parts = numbers.split("\n");
            return this.splitAndSum(parts[1], parts[0].substring(2));
        }

        if (numbers.contains("\n")) {
            return this.splitAndSum(numbers, "\n");
        }

        return this.splitAndSum(numbers, ",");
    }

    private int splitAndSum(String numbers, String delimiter) {
        String[] parts = numbers.split(delimiter);
        this.validate(parts);
        return this.getInts(parts)
                .filter(val -> val < 1000)
                .sum();
    }

    private void validate(String[] parts) {
        List<String> negativeNumbers = this.getInts(parts)
                .filter(val -> val < 0)
                .mapToObj(String::valueOf)
                .toList();

        if (!CollectionUtils.isEmpty(negativeNumbers)) {
            throw new IllegalArgumentException("Negatives not allowed - " + String.join(", ", negativeNumbers));
        }
    }

    private IntStream getInts(String[] parts) {
        return Stream.of(parts)
                .filter(StringUtils::hasText)
                .mapToInt(this::getInt);
    }

    private int getInt(String number) {
        return Integer.parseInt(number.trim());
    }
}
