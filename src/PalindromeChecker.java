public class PalindromeChecker {
    // Application metadata
    private static final String APP_NAME = "PalindromeChecker";
    private static final String APP_VERSION = "1.0";

    /**
     * Entry point for the application. Prints a welcome message and exits or
     * continues to the remainder of the program.
     */
    public static void main(String[] args) {
        // UC1: Application Entry & Welcome Message
        System.out.println("Welcome to the " + APP_NAME + " application!");
        System.out.println("Version: " + APP_VERSION);
        System.out.println();
        System.out.println("This console program will allow you to verify whether a string is a palindrome.");

        // After printing the welcome message the program can proceed to additional use
        // cases or simply exit. For now we just demonstrate the startup behavior.
        // UC2: Print a Hardcoded Palindrome Result
        String testString = "madam"; // hardcoded example
        System.out.println("Checking hardcoded string: " + testString);
        if (isPalindrome(testString)) {
            System.out.println(testString + " is a palindrome.");
        } else {
            System.out.println(testString + " is not a palindrome.");
        }

        // UC3: Palindrome Check Using String Reverse
        String reversed = reverseString(testString);
        System.out.println("Reversed string: " + reversed);
        if (testString.equals(reversed)) {
            System.out.println("(reverse-check) " + testString + " is a palindrome.");
        } else {
            System.out.println("(reverse-check) " + testString + " is not a palindrome.");
        }

        // UC4: Character Array Based Palindrome Check
        if (charArrayPalindrome(testString)) {
            System.out.println("(char-array) " + testString + " is a palindrome.");
        } else {
            System.out.println("(char-array) " + testString + " is not a palindrome.");
        }

        // UC5: Stack-Based Palindrome Checker
        if (stackPalindrome(testString)) {
            System.out.println("(stack) " + testString + " is a palindrome.");
        } else {
            System.out.println("(stack) " + testString + " is not a palindrome.");
        }

        // UC6: Queue + Stack Based Palindrome Check
        if (queueStackPalindrome(testString)) {
            System.out.println("(queue-vs-stack) " + testString + " is a palindrome.");
        } else {
            System.out.println("(queue-vs-stack) " + testString + " is not a palindrome.");
        }

        // UC7: Deque-Based Optimized Palindrome Checker
        if (dequePalindrome(testString)) {
            System.out.println("(deque) " + testString + " is a palindrome.");
        } else {
            System.out.println("(deque) " + testString + " is not a palindrome.");
        }
    }

    /**
     * Simple helper to determine if a string is a palindrome.
     */
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Reverse the input string using a for loop.
     */
    private static String reverseString(String s) {
        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += s.charAt(i);
        }
        return reversed;
    }

    /**
     * Converts the string to a char array and compares characters from both
     * ends using a two-pointer technique.
     */
    private static boolean charArrayPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Uses a Stack<Character> to push all characters then pop them back while
     * comparing to the original string characters.
     */
    private static boolean stackPalindrome(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        for (char c : s.toCharArray()) {
            if (!stack.empty() && stack.pop() != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Enqueues characters into a queue and simultaneously pushes them onto a
     * stack, then compares dequeue output with pop output to illustrate FIFO vs
     * LIFO semantics.
     */
    private static boolean queueStackPalindrome(String s) {
        java.util.Queue<Character> queue = new java.util.LinkedList<>();
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            queue.add(c); // enqueue
            stack.push(c); // push onto stack
        }
        while (!queue.isEmpty() && !stack.isEmpty()) {
            if (!queue.remove().equals(stack.pop())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Uses a Deque to compare front and rear elements until empty. Optimized for
     * direct access to both ends.
     */
    private static boolean dequePalindrome(String s) {
        java.util.Deque<Character> deque = new java.util.LinkedList<>();
        for (char c : s.toCharArray()) {
            deque.addLast(c);
        }
        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
