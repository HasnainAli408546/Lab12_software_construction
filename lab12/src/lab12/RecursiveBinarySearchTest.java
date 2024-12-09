package lab12;
import lab12.RecursiveBinarySearch;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class RecursiveBinarySearchTest {

    @Test
    public void testBinarySearchRecursiveIntegerFound() {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9};
        assertEquals(5, RecursiveBinarySearch.binarySearchRecursive(arr, 5));
    }

    @Test
    public void testBinarySearchRecursiveIntegerNotFound() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(arr, 10));
    }

    @Test
    public void testBinarySearchRecursiveStringFound() {
        String[] arr = {"apple", "banana", "cherry", "date", "elderberry"};
        assertEquals(2, RecursiveBinarySearch.binarySearchRecursive(arr, "cherry"));
    }

    @Test
    public void testBinarySearchRecursiveStringNotFound() {
        String[] arr = {"apple", "banana", "cherry", "date", "elderberry"};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(arr, "grape"));
    }

    @Test
    public void testBinarySearchMultipleIndices() {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9};
        List<Integer> expected = List.of(5, 4, 6);
        assertEquals(expected, RecursiveBinarySearch.binarySearchMultipleIndices(arr, 5));
    }

    @Test
    public void testBinarySearchRecursiveNullArrayInteger() {
        try {
            RecursiveBinarySearch.binarySearchRecursive((int[]) null, 3);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Input array cannot be null", e.getMessage());
        }
    }

    @Test
    public void testBinarySearchRecursiveNullArrayString() {
        try {
            RecursiveBinarySearch.binarySearchRecursive((String[]) null, "banana");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Input array cannot be null", e.getMessage());
        }
    }

    @Test
    public void testBinarySearchRecursiveEmptyArrayInteger() {
        int[] arr = {};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(arr, 3));
    }

    @Test
    public void testBinarySearchRecursiveEmptyArrayString() {
        String[] arr = {};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(arr, "apple"));
    }

    @Test
    public void testBinarySearchSingleElementFound() {
        int[] arr = {10};
        assertEquals(0, RecursiveBinarySearch.binarySearchRecursive(arr, 10));
    }

    @Test
    public void testBinarySearchSingleElementNotFound() {
        int[] arr = {10};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(arr, 5));
    }
}

