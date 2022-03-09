package com.transactly.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @BeforeEach
    void setup() {
        this.stringCalculator = new StringCalculator();
    }

    @Test
    void sumSingleNumber() {
        assertEquals(3, this.stringCalculator.sum("3"));
        assertEquals(3, this.stringCalculator.sum("    3       "));
    }

    @Test
    void sumWithNullReturnZero() {
        assertEquals(0, this.stringCalculator.sum(null));
    }

    @Test
    void sumWithEmptyReturnsZero() {
        assertEquals(0, this.stringCalculator.sum(""));
    }

    @Test
    void sumMultipleCommaDelimitedNumbers() {
        assertEquals(6, this.stringCalculator.sum("1,2,3"));
        assertEquals(6, this.stringCalculator.sum("1   ,   2,   3    "));
    }

    @Test
    void sumMultipleNewLineDelimitedNumbers() {
        assertEquals(3, this.stringCalculator.sum("1\n2"));
        assertEquals(3, this.stringCalculator.sum("1 \n   2 "));
        assertEquals(11, this.stringCalculator.sum("1 \n   2 \n8"));
    }

    @Test
    void sumCustomDelimiter() {
        assertEquals(3, this.stringCalculator.sum("//;\n1;2"));
        assertEquals(3, this.stringCalculator.sum("//;\n    1;    2"));
        assertEquals(6, this.stringCalculator.sum("//-----\n    1-----    2-----3"));
    }

    @Test
    void sumNegativeNumbersThrowException() {
        try {
            this.stringCalculator.sum("1,   -2,-3    ");
            fail("Negative numbers should throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("negatives not allowed"));
            assertTrue(e.getMessage().contains("-2"));
            assertTrue(e.getMessage().contains("-3"));
            assertFalse(e.getMessage().contains("1"));
        }
    }

    @Test
    void sumSingleNegativeNumber() {
        try {
            this.stringCalculator.sum("-10000");
            fail("Negative numbers should throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("negatives not allowed"));
            assertTrue(e.getMessage().contains("-10000"));
        }
    }

    @Test
    void sumIgnoresNumbersLargerThan1000() {
        assertEquals(3, this.stringCalculator.sum("1,1000,,2"));
        assertEquals(1002, this.stringCalculator.sum("1,999,   1000, 1001,2"));
    }
}
