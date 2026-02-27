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
    }
}
