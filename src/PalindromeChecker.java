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
}
