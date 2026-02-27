public class PalindromeChecker {
    // Application information constants
    private static final String APP_NAME = "Palindrome Checker";
    private static final String APP_VERSION = "1.0";

    /**
     * Entry point for the application.
     * Displays welcome message and application information before continuing.
     */
    public static void main(String[] args) {
        displayWelcome();
        // continue with other application logic or exit
        // For now we simply exit, as additional use cases are not defined yet.
    }

    private static void displayWelcome() {
        System.out.println("=====================================");
        System.out.println("Welcome to " + APP_NAME + "!");
        System.out.println("Version: " + APP_VERSION);
        System.out.println("=====================================");
    }
}
