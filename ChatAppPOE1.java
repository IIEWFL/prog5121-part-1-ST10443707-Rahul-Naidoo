
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean setUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            this.username = username;
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("Username is not correctly formatted. It must contain an underscore and be no more than 5 characters.");
            return false;
        }
    }

    public boolean setPassword(String password) {
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=[\\]{};':\"\\|,.<>/?].*");
        boolean isLongEnough = password.length() >= 8;

        if (hasUpper && hasDigit && hasSpecial && isLongEnough) {
            this.password = password;
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password must be at least 8 characters and include a capital letter, a number, and a special character.");
            return false;
        }
    }

    public boolean setPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+27\\d{9,10}$");
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            this.phoneNumber = phoneNumber;
            System.out.println("Phone number successfully captured.");
            return true;
        } else {
            System.out.println("Phone number must start with +27 and be 10-11 digits long.");
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class Login {
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn;

    public Login(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.storedUsername = username;
        this.storedPassword = password;
    }

    public boolean loginUser(String username, String password) {
        isLoggedIn = storedUsername.equals(username) && storedPassword.equals(password);
        return isLoggedIn;
    }

    public String returnLoginStatus() {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

public class ChatAppPart1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Chat App Registration ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        User newUser = new User(firstName, lastName);

        while (!newUser.setUsername(prompt(scanner, "Enter username (must contain _ and be <= 5 chars): "))) {}

        while (!newUser.setPassword(prompt(scanner, "Enter password (8+ chars, 1 uppercase, 1 number, 1 special): "))) {}

        while (!newUser.setPhoneNumber(prompt(scanner, "Enter phone number (+27XXXXXXXXX): "))) {}

        System.out.println("Registration successful!");

        System.out.println("\n=== Chat App Login ===");

        Login login = new Login(firstName, lastName, newUser.getUsername(), newUser.getPassword());

        String loginUsername = prompt(scanner, "Enter username: ");
        String loginPassword = prompt(scanner, "Enter password: ");

        login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus());

        scanner.close();
    }

    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
