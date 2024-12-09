package lab12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A recursive mathematical expression parser that evaluates 
 * mathematical expressions with support for floating-point numbers,
 * basic arithmetic operations, and parentheses.
 */
public class MathExpressionParser {

    /**
     * Evaluates a mathematical expression recursively.
     * 
     * @param expression The mathematical expression to evaluate
     * @return The result of the expression
     * @throws IllegalArgumentException for invalid expressions
     */
    public static double evaluateExpression(String expression) {
        // Remove all whitespace from the expression
        expression = expression.replaceAll("\\s+", "");

        // Validate if the expression contains only valid characters
        if (!expression.matches("[0-9\\+\\-\\*/\\.\\(\\)\\s]*")) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        try {
            return parseExpression(expression);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

    /**
     * Recursive method to parse and evaluate the expression.
     * 
     * @param expression The expression to parse
     * @return The evaluated result
     */
    private static double parseExpression(String expression) {
        // Handle parentheses first
        while (expression.contains("(")) {
            expression = resolveParentheses(expression);
        }

        // Then handle multiplication and division
        expression = resolveMultiplicationDivision(expression);

        // Finally, handle addition and subtraction
        return resolveAdditionSubtraction(expression);
    }

    /**
     * Resolves expressions within parentheses recursively.
     * 
     * @param expression The expression containing parentheses
     * @return Expression with innermost parentheses resolved
     */
    private static String resolveParentheses(String expression) {
        // Find the innermost parentheses
        int lastOpenParen = expression.lastIndexOf('(');
        int firstCloseParen = expression.indexOf(')', lastOpenParen);

        if (lastOpenParen == -1 || firstCloseParen == -1) {
            throw new IllegalArgumentException("Mismatched parentheses");
        }

        // Extract the sub-expression within parentheses
        String subExpression = expression.substring(lastOpenParen + 1, firstCloseParen);

        // Evaluate the sub-expression
        double subResult = parseExpression(subExpression);

        // Replace the entire parenthetical expression with its result
        String replacementExpression = expression.substring(0, lastOpenParen) +
                                       subResult +
                                       expression.substring(firstCloseParen + 1);

        return replacementExpression;
    }

    /**
     * Resolves multiplication and division operations.
     * 
     * @param expression The expression to resolve
     * @return Expression with multiplication and division resolved
     */
    private static String resolveMultiplicationDivision(String expression) {
        // Regex pattern to match multiplication and division (including negative and floating-point numbers)
        Pattern pattern = Pattern.compile("([-]?\\d*\\.?\\d+)[\\*/]([-]?\\d*\\.?\\d+)");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            double left = Double.parseDouble(matcher.group(1));
            double right = Double.parseDouble(matcher.group(2));
            double result;

            if (matcher.group(0).contains("*")) {
                result = left * right;
            } else {
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = left / right;
            }

            // Replace the operation with its result
            expression = expression.replace(matcher.group(0), String.valueOf(result));

            // Reset matcher
            matcher = pattern.matcher(expression);
        }

        return expression;
    }

    /**
     * Resolves addition and subtraction operations.
     * 
     * @param expression The expression to resolve
     * @return Final numeric result
     */
    private static double resolveAdditionSubtraction(String expression) {
        // Regex pattern to match addition and subtraction (including negative and floating-point numbers)
        Pattern pattern = Pattern.compile("([-]?\\d*\\.?\\d+)[+-]([-]?\\d*\\.?\\d+)");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            double left = Double.parseDouble(matcher.group(1));
            double right = Double.parseDouble(matcher.group(2));
            double result;

            if (matcher.group(0).contains("+")) {
                result = left + right;
            } else {
                result = left - right;
            }

            // Replace the operation with its result
            expression = expression.replace(matcher.group(0), String.valueOf(result));

            // Reset matcher
            matcher = pattern.matcher(expression);
        }

        return Double.parseDouble(expression);
    }

    /**
     * Comprehensive test method to demonstrate the parser's capabilities.
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Test cases covering various scenarios
        String[] testExpressions = {
            "3 + 5 * 2",           // Basic precedence
            "(3 + 5) * 2",          // Parentheses
            "10 / 2 + 3 * 4",       // Mixed operations
            "2.5 + 3.7 * 2",        // Floating-point numbers
            "-5 + 3 * -2",          // Negative numbers
            "(2 + 3) * (4 - 1)"     // Complex parentheses
        };

        for (String expr : testExpressions) {
            try {
                double result = evaluateExpression(expr);
                System.out.println(expr + " = " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error evaluating " + expr + ": " + e.getMessage());
            }
        }

        // Error case tests
        try {
            evaluateExpression("1 / 0");  // Division by zero
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        try {
            evaluateExpression("1 + (2 * 3");  // Mismatched parentheses
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
