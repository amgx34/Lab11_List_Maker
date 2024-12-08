import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        boolean running = true;

        while (running) {
            displayMenu(list);
            String choice = getRegExString("Choose an option: ", "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    addItem(list);
                    break;
                case "D":
                    deleteItem(list);
                    break;
                case "I":
                    insertItem(list);
                    break;
                case "P":
                    printList(list);
                    break;
                case "Q":
                    running = !quitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    private static void displayMenu(ArrayList<String> list) {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
        System.out.println("\nMenu:");
        System.out.println("A - Add an item");
        System.out.println("D - Delete an item");
        System.out.println("I - Insert an item");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItem(ArrayList<String> list) {
        String newItem = getNonZeroLenString("Enter the item to add: ");
        list.add(newItem);
        System.out.println("Item added.");
    }

    private static void deleteItem(ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        displayNumberedList(list);
        int index = getRangedInt("Enter the number of the item to delete: ", 1, list.size()) - 1;
        System.out.println("Deleted item: " + list.remove(index));
    }

    private static void insertItem(ArrayList<String> list) {
        String newItem = getNonZeroLenString("Enter the item to insert: ");
        int index = getRangedInt("Enter the position to insert at: ", 1, list.size() + 1) - 1;
        list.add(index, newItem);
        System.out.println("Item inserted.");
    }

    private static void printList(ArrayList<String> list) {
        System.out.println("\nList Contents:");
        for (String item : list) {
            System.out.println("- " + item);
        }
    }

    private static boolean quitProgram() {
        return getYNConfirm("Are you sure you want to quit? (Y/N)");
    }

    private static void displayNumberedList(ArrayList<String> list) {
        System.out.println("\nNumbered List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    // SafeInput methods for robust input handling
    private static String getNonZeroLenString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static String getRegExString(String prompt, String pattern) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.matches(pattern)) {
                System.out.println("Invalid input. Try again.");
            }
        } while (!input.matches(pattern));
        return input;
    }

    private static int getRangedInt(String prompt, int low, int high) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
            }
            value = scanner.nextInt();
            scanner.nextLine(); // clear newline
            if (value < low || value > high) {
                System.out.println("Input out of range. Try again.");
            }
        } while (value < low || value > high);
        return value;
    }

    private static boolean getYNConfirm(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            if (!input.equals("Y") && !input.equals("N")) {
                System.out.println("Invalid input. Enter 'Y' or 'N'.");
            }
        } while (!input.equals("Y") && !input.equals("N"));
        return input.equals("Y");
    }
}
