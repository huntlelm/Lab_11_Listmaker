import java.util.ArrayList;
import java.util.Scanner;

public class Listmaker {
    // Shared list and scanner for the program
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner pipe = new Scanner(System.in);

    public static void main(String[] args) {
        String menuChoice = "";
        boolean quit = false;

        do {
            // Display the current list and menu options
            displayList();

            // Get menu choice
            menuChoice = SafeInput.getRegExString(pipe,
                    "Menu: [A]dd, [D]elete, [I]nsert, [P]rint, [Q]uit",
                    "[AaDdIiPpQq]");

            // Process the choice
            switch (menuChoice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit = SafeInput.getYNConfirm(pipe, "Are you sure you want to quit?");
                    if (quit) {
                        System.out.println("Exiting ListMaker. Goodbye!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!quit);
    }

    // Displays the current list items
    private static void displayList() {
        System.out.println("\n----------------------------------");
        if (list.isEmpty()) {
            System.out.println("Your list is currently empty.");
        } else {
            System.out.println("Current List:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d. %s\n", (i + 1), list.get(i));
            }
        }
        System.out.println("----------------------------------");
    }

    // Adds an item to the end of the ArrayList
    private static void addItem() {
        System.out.print("Enter the item you want to add: ");
        String item = pipe.nextLine();
        list.add(item);
        System.out.println("Item added successfully!");
    }

    // Deletes an item from the ArrayList
    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        int itemNum = SafeInput.getRangedInt(pipe, "Enter the number of the item to delete", 1, list.size());
        int index = itemNum - 1;

        String removed = list.remove(index);
        System.out.println("Removed: \"" + removed + "\"");
    }

    // Inserts an item into a specific location in the ArrayList
    private static void insertItem() {
        System.out.print("Enter the item you want to insert: ");
        String item = pipe.nextLine();

        // If list is empty, just add it at position 1
        if (list.isEmpty()) {
            list.add(item);
            System.out.println("List was empty. Item added at position 1.");
            return;
        }

        // User can insert anywhere from 1 up to list.size() + 1
        int position = SafeInput.getRangedInt(pipe, "Enter the position number where you want to insert", 1, list.size() + 1);
        int index = position - 1; // Convert to 0-based index

        list.add(index, item);
        System.out.println("Item inserted successfully at position " + position + "!");
    }

    // Prints the list explicitly when requested
    private static void printList() {
        displayList();
        System.out.println("Press Enter to continue...");
        pipe.nextLine(); // Pause so user can view the printed list
    }
}