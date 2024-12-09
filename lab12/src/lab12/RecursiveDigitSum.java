package lab12;

import java.math.BigInteger;

/**
 * A utility class for calculating the sum of digits using recursive methods.
 * Handles both positive and negative integers, and supports large number inputs.
 */
public class RecursiveDigitSum {

    /**
     * Calculates the sum of digits recursively for an integer input.
     * 
     * @param number Input number to calculate digit sum
     * @return Sum of all digits in the number
     */
    public static int sumOfDigits(int number) {
        // Handle negative numbers by converting to positive
        number = Math.abs(number);

        // Base case: when number becomes 0
        if (number == 0) {
            return 0;
        }

        // Recursive case: sum last digit with sum of remaining digits
        return (number % 10) + sumOfDigits(number / 10);
    }

    /**
     * Overloaded method to handle BigInteger for extremely large numbers.
     * 
     * @param number BigInteger input 
     * @return Sum of all digits in the number
     * @throws IllegalArgumentException if input is null
     */
    public static int sumOfDigits(BigInteger number) {
        if (number == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        // Handle edge case for zero
        if (number.equals(BigInteger.ZERO)) {
            return 0;
        }

        // Take absolute value to avoid any negative BigInteger (not common for sum of digits, but for completeness)
        number = number.abs();

        // Convert to string for easier digit manipulation
        String numberStr = number.toString();

        // Base case: only one digit left
        if (numberStr.length() == 1) {
            return Character.getNumericValue(numberStr.charAt(0));
        }

        // Recursive case: add the first digit and continue with the rest
        return Character.getNumericValue(numberStr.charAt(0)) + 
               sumOfDigits(new BigInteger(numberStr.substring(1)));
    }

    /**
     * Time Complexity Analysis:
     * - For int input: O(log n)
     *   - Number of recursive calls is proportional to number of digits
     *   - Each recursive call reduces the number by a factor of 10
     * 
     * - For BigInteger: O(m), where m is number of digits
     *   - Slightly less efficient due to string conversion
     * 
     * Space Complexity:
     * - Recursive call stack depth is O(log n)
     * - Each recursive call uses constant extra space
     */

    /**
     * Comprehensive test method demonstrating various input scenarios.
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Test cases for standard integer input
        int[] testIntegers = {
            0,          // Edge case: zero
            123,        // Standard positive number
            -456,       // Negative number
            9999,       // Large number
            1000000     // Very large number
        };

        System.out.println("Integer Input Tests:");
        for (int num : testIntegers) {
            System.out.printf("Sum of digits for %d: %d%n", 
                              num, sumOfDigits(num));
        }

        // Test cases for BigInteger input
        BigInteger[] testBigIntegers = {
            BigInteger.ZERO,
            new BigInteger("123456789"),
            new BigInteger("999999999999999"),
            new BigInteger("123456789012345678901234567890")
        };

        System.out.println("\nBigInteger Input Tests:");
        for (BigInteger num : testBigIntegers) {
            System.out.printf("Sum of digits for %s: %d%n", 
                              num.toString(), sumOfDigits(num));
        }

        // Error handling test
        try {
            sumOfDigits((BigInteger) null);
        } catch (IllegalArgumentException e) {
            System.out.println("\nNull input test passed: " + e.getMessage());
        }
    }
}
