# String Calculator Kata

## TDD Steps

1. Write a failing test
2. Write the simplest code to make the test pass
3. Refactor and remove all duplication

## Requirements

1. Given a single positive whole number in a string, return the number, ex. "3" returns 3
   1. Given null, return 0
   2. Given empty string, return 0
   3. Ignore all whitespace
2. Given 2 comma delimited numbers, return the sum, ex. "1,2" returns 3
3. Given multiple comma delimited numbers, return the sum, ex. "1,2,3" returns 6
4. Allow new line delimited string instead of commas, ex. "1\n2" returns 3
5. Support different delimiters, regardless of length, defined on the first line of the spring prefixed with "//", ex. "//;\n1;2" would return 3
6. If a string contains a negative number, throw an IllegalArgumentException, the exception should contain "negatives not allowed" and _all_ of the negative numbers in the message.
7. Numbers bigger than or equal to 1000 should be ignored, ex. "1,1000,2" returns 3
