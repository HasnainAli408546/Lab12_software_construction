package lab12;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

	/**
	 * A utility class implementing recursive binary search algorithms
	 * for both integer and string arrays.
	 */
	public class RecursiveBinarySearch {
	    
	    /**
	     * Recursive binary search for integer arrays.
	     * 
	     * @param arr    Sorted input array of integers
	     * @param target Value to search for
	     * @return Index of the target value, or -1 if not found
	     * @throws IllegalArgumentException if input array is null
	     */
	    public static int binarySearchRecursive(int[] arr, int target) {
	        // Error handling for null or empty array
	        if (arr == null) {
	            throw new IllegalArgumentException("Input array cannot be null");
	        }
	        
	        return binarySearchRecursive(arr, target, 0, arr.length - 1);
	    }
	    
	    /**
	     * Recursive binary search for integer arrays with specified search range.
	     * 
	     * @param arr    Sorted input array of integers
	     * @param target Value to search for
	     * @param left   Left boundary of search range
	     * @param right  Right boundary of search range
	     * @return Index of the target value, or -1 if not found
	     */
	    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
	        // Base case: search range is empty
	        if (left > right) {
	            return -1;
	        }
	        
	        // Calculate middle index
	        int mid = left + (right - left) / 2;
	        
	        // Compare middle element with target
	        if (arr[mid] == target) {
	            return mid;
	        }
	        
	        // Recursively search left or right half
	        if (target < arr[mid]) {
	            return binarySearchRecursive(arr, target, left, mid - 1);
	        } else {
	            return binarySearchRecursive(arr, target, mid + 1, right);
	        }
	    }
	    
	    /**
	     * Recursive binary search for string arrays.
	     * 
	     * @param arr    Sorted input array of strings
	     * @param target Value to search for
	     * @return Index of the target value, or -1 if not found
	     * @throws IllegalArgumentException if input array is null
	     */
	    public static int binarySearchRecursive(String[] arr, String target) {
	        // Error handling for null or empty array
	        if (arr == null) {
	            throw new IllegalArgumentException("Input array cannot be null");
	        }
	        
	        return binarySearchRecursive(arr, target, 0, arr.length - 1);
	    }
	    
	    /**
	     * Recursive binary search for string arrays with specified search range.
	     * 
	     * @param arr    Sorted input array of strings
	     * @param target Value to search for
	     * @param left   Left boundary of search range
	     * @param right  Right boundary of search range
	     * @return Index of the target value, or -1 if not found
	     */
	    private static int binarySearchRecursive(String[] arr, String target, int left, int right) {
	        // Base case: search range is empty
	        if (left > right) {
	            return -1;
	        }
	        
	        // Calculate middle index
	        int mid = left + (right - left) / 2;
	        
	        // Compare middle element with target
	        int compareResult = arr[mid].compareTo(target);
	        
	        if (compareResult == 0) {
	            return mid;
	        }
	        
	        // Recursively search left or right half
	        if (target.compareTo(arr[mid]) < 0) {
	            return binarySearchRecursive(arr, target, left, mid - 1);
	        } else {
	            return binarySearchRecursive(arr, target, mid + 1, right);
	        }
	    }
	    
	    /**
	     * Recursive method to find all indices of a target value in an integer array.
	     * 
	     * @param arr    Sorted input array of integers
	     * @param target Value to search for
	     * @return List of all indices where target is found
	     * @throws IllegalArgumentException if input array is null
	     */
	    public static List<Integer> binarySearchMultipleIndices(int[] arr, int target) {
	        if (arr == null) {
	            throw new IllegalArgumentException("Input array cannot be null");
	        }
	        
	        List<Integer> indices = new ArrayList<>();
	        findAllIndices(arr, target, 0, arr.length - 1, indices);
	        return indices;
	    }
	    
	    /**
	     * Helper method to recursively find all indices of a target value.
	     * 
	     * @param arr     Sorted input array of integers
	     * @param target  Value to search for
	     * @param left    Left boundary of search range
	     * @param right   Right boundary of search range
	     * @param indices List to store found indices
	     */
	    private static void findAllIndices(int[] arr, int target, int left, int right, List<Integer> indices) {
	        // Base case: search range is empty
	        if (left > right) {
	            return;
	        }
	        
	        // Calculate middle index
	        int mid = left + (right - left) / 2;
	        
	        // If target found, add to indices and search surrounding areas
	        if (arr[mid] == target) {
	            indices.add(mid);
	            
	            // Search left side for more occurrences
	            findAllIndices(arr, target, left, mid - 1, indices);
	            
	            // Search right side for more occurrences
	            findAllIndices(arr, target, mid + 1, right, indices);
	        } else if (target < arr[mid]) {
	            // Recursively search left half
	            findAllIndices(arr, target, left, mid - 1, indices);
	        } else {
	            // Recursively search right half
	            findAllIndices(arr, target, mid + 1, right, indices);
	        }
	    }
	    
	    /**
	     * Time complexity analysis:
	     * - Recursive Binary Search: O(log n)
	     *   - Divides the search space in half with each recursive call
	     *   - Maximum number of recursive calls is log(n)
	     * 
	     * Comparison with Iterative Binary Search:
	     * - Time complexity is identical: O(log n)
	     * - Recursive approach uses more memory due to call stack overhead
	     * - Iterative approach is generally more space-efficient
	     */
	    
	    // Unit tests demonstrating usage
	    public static void main(String[] args) {
	        // Integer array tests
	        int[] intArray = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9};
	        System.out.println("Array" + Arrays.toString(intArray));
	        System.out.println("Integer Search(give one occurence for 5): " + binarySearchRecursive(intArray, 5));  // Should return an index of 5
	        System.out.println("Multiple Indices: " + binarySearchMultipleIndices(intArray, 5));  // Should return [5, 4, 6]
	        
	        // String array tests
	        String[] stringArray = {"apple", "banana", "cherry", "date", "elderberry"};
	        System.out.println("Array" + Arrays.toString(stringArray));
	        System.out.println("String Search(displays index for cherry): " + binarySearchRecursive(stringArray, "cherry"));  // Should return 2
	    }
	}


