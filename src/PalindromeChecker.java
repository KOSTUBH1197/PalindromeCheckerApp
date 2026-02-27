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
        // UC11: create service object to encapsulate palindrome logic
        // UC12: also demonstrate strategy pattern
        PalindromeStrategy stackStrat = new StackStrategy();
        PalindromeStrategy dequeStrat = new DequeStrategy();
        // default service using built-in algorithm
        PalindromeChecker service = new PalindromeChecker();
        // additional services using specific strategies
        PalindromeChecker stackService = new PalindromeChecker(stackStrat);
        PalindromeChecker dequeService = new PalindromeChecker(dequeStrat);

        // UC2: Print a Hardcoded Palindrome Result (now via OOP service)
        String testString = "madam"; // hardcoded example
        System.out.println("Checking hardcoded string: " + testString);
        if (service.checkPalindrome(testString)) {
            System.out.println(testString + " is a palindrome.");
        } else {
            System.out.println(testString + " is not a palindrome.");
        }

        // show dynamic switching
        System.out.println("\n-- Strategy Pattern Demo --");
        System.out.println("stackService result: " + stackService.checkPalindrome(testString));
        System.out.println("dequeService result: " + dequeService.checkPalindrome(testString));

        // UC13: Performance Comparison
        System.out.println("\n-- Performance Comparison --");
        long t1 = time("default", () -> service.checkPalindrome(testString));
        long t2 = time("stack-strategy", () -> stackService.checkPalindrome(testString));
        long t3 = time("deque-strategy", () -> dequeService.checkPalindrome(testString));
        long t4 = time("char-array", () -> charArrayPalindrome(testString));
        long t5 = time("reverse-string", () -> reverseString(testString));
        System.out.println("Timings (nanoseconds):");
        System.out.println(" default      : " + t1);
        System.out.println(" stack-strategy: " + t2);
        System.out.println(" deque-strategy: " + t3);
        System.out.println(" char-array   : " + t4);
        System.out.println(" reverse      : " + t5);

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

        // UC8: Linked List Based Palindrome Checker
        if (linkedListPalindrome(testString)) {
            System.out.println("(linked-list) " + testString + " is a palindrome.");
        } else {
            System.out.println("(linked-list) " + testString + " is not a palindrome.");
        }

        // UC9: Recursive Palindrome Checker
        if (recursivePalindrome(testString)) {
            System.out.println("(recursive) " + testString + " is a palindrome.");
        } else {
            System.out.println("(recursive) " + testString + " is not a palindrome.");
        }

        // UC10: Case-Insensitive & Space-Ignored Palindrome
        String normalized = normalize(testString);
        System.out.println("Normalized input: " + normalized);
        if (service.checkPalindrome(normalized)) {
            System.out.println("(normalized) " + testString + " is a palindrome when ignoring case and spaces.");
        } else {
            System.out.println("(normalized) " + testString + " is not a palindrome even when ignoring case and spaces.");
        }
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

    /**
     * Builds a singly linked list from the string, finds the middle using fast
     * and slow pointers, reverses the second half in-place, and then compares
     * the two halves for palindrome equivalence.
     */
    private static boolean linkedListPalindrome(String s) {
        // construct list
        Node head = null;
        Node tail = null;
        for (char c : s.toCharArray()) {
            Node node = new Node(c);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        if (head == null || head.next == null) {
            return true;
        }

        // find middle
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half
        Node prev = null;
        Node curr = slow;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node secondHalf = prev; // head of reversed second half

        // compare
        Node p1 = head;
        Node p2 = secondHalf;
        while (p2 != null) {
            if (p1.data != p2.data) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    // --- Strategy Pattern Types for UC12 ---
    public interface PalindromeStrategy {
        boolean check(String s);
    }

    public static class StackStrategy implements PalindromeStrategy {
        @Override
        public boolean check(String s) {
            if (s == null) return false;
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
    }

    public static class DequeStrategy implements PalindromeStrategy {
        @Override
        public boolean check(String s) {
            if (s == null) return false;
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

    // Modified service constructors and check method for strategy
    private PalindromeStrategy strategy;

    public PalindromeChecker() {
        this.strategy = new DefaultStrategy();
    }

    public PalindromeChecker(PalindromeStrategy strategy) {
        this.strategy = strategy == null ? new DefaultStrategy() : strategy;
    }

    private static class DefaultStrategy implements PalindromeStrategy {
        @Override
        public boolean check(String s) {
            if (s == null) return false;
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
    }

    public boolean checkPalindrome(String s) {
        return strategy.check(s);
    }

    /**
     * Utility to time a Runnable action and return duration in nanoseconds.
     */
    private static long time(String name, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Simple node for linked list
     */
    private static class Node {
        char data;
        Node next;
        Node(char d) { data = d; }
    }

    /**
     * Recursively checks if substring [left:right] is palindrome.
     */
    private static boolean recursiveCheck(String s, int left, int right) {
        if (left >= right) {
            return true; // base condition
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        return recursiveCheck(s, left + 1, right - 1);
    }

    private static boolean recursivePalindrome(String s) {
        return recursiveCheck(s, 0, s.length() - 1);
    }

    /**
     * Normalize input by removing whitespace and converting to lowercase.
     */
    private static String normalize(String s) {
        if (s == null) {
            return "";
        }
        return s.replaceAll("\\s+", "").toLowerCase();
    }
}
