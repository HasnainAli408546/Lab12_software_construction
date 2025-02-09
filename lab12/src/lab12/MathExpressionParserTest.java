package lab12;
import lab12.MathExpressionParser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for MathExpressionParser.
 */
public class MathExpressionParserTest {

    // Test for simple addition
    @Test
    public void testAddition() {
        assertEquals(8.0, MathExpressionParser.evaluateExpression("3 + 5"), 0.0001);
    }

    // Test for simple multiplication
    @Test
    public void testMultiplication() {
        assertEquals(10.0, MathExpressionParser.evaluateExpression("5 * 2"), 0.0001);
    }

    // Test for handling parentheses
    @Test
    public void testParentheses() {
        assertEquals(16.0, MathExpressionParser.evaluateExpression("(3 + 5) * 2"), 0.0001);
    }

    // Test for complex nested parentheses
    @Test
    public void testComplexParentheses() {
        assertEquals(15.0, MathExpressionParser.evaluateExpression("(2 + 3) * (4 - 1)"), 0.0001);
    }


    // Test for mismatched parentheses
    @Test
    public void testMismatchedParentheses() {
        try {
            MathExpressionParser.evaluateExpression("1 + (2 * 3");
            fail("Expected IllegalArgumentException for mismatched parentheses");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid expression"));
        }
    }

    // Test for empty expression
    @Test
    public void testEmptyExpression() {
        try {
            MathExpressionParser.evaluateExpression("");
            fail("Expected IllegalArgumentException for empty expression");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid expression"));
        }
    }

    // Test for invalid characters in the expression
    @Test
    public void testInvalidCharacter() {
        try {
            MathExpressionParser.evaluateExpression("3 + $5");
            fail("Expected IllegalArgumentException for invalid character");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid expression"));
        }
    }
}
