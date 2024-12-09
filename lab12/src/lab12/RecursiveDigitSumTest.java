package lab12;
import lab12.RecursiveDigitSum;

import org.junit.Test;
import java.math.BigInteger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RecursiveDigitSumTest {

  
    @Test
    public void testNullInputBigInteger() {
        // Test for null input in BigInteger method
        assertThrows(IllegalArgumentException.class, () -> {
            RecursiveDigitSum.sumOfDigits((BigInteger) null);
        });
    }

    @Test
    public void testSumOfDigitsInteger() {
        // Regular positive integer
        assertEquals(6, RecursiveDigitSum.sumOfDigits(123));

        // Negative integer
        assertEquals(15, RecursiveDigitSum.sumOfDigits(-456));

        // Zero input
        assertEquals(0, RecursiveDigitSum.sumOfDigits(0));

        // Large integer
        assertEquals(36, RecursiveDigitSum.sumOfDigits(9999));

        // Very large integer
        assertEquals(1, RecursiveDigitSum.sumOfDigits(1000000));
    }

    @Test
    public void testNullInputInteger() {
        // Test for null input handling, though this won't be invoked since primitives can't be null.
        assertThrows(IllegalArgumentException.class, () -> {
            RecursiveDigitSum.sumOfDigits(null);
        });
    }
}
