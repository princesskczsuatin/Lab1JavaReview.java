import java.util.Scanner;
public class CampusLibrarySystem {
    static final int maxbooks = 5;
    static String[] bookTitles = new String[maxbooks];
    static String[] bookStatus = new String[maxbooks];
    static int bookCount = 0; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n°°°°°°°° Campus Library Book Management System °°°°°°°°");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book Status");
            System.out.println("3. Show All Books");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("What do you want to do?: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input, Please Enter a number between 1-5: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    updateBookStatus(sc);
                    break;
                case 3:
                    showBooks();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting the Campus Library System, Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select from 1-5.");
            }
        } while (choice != 5);
        sc.close();
    }
    public static void addBook(Scanner sc) {
        if (bookCount >= maxbooks) {
            System.out.println("Cannot add more books the maximum limit reached (" + maxbooks + ").");
            return;
        }
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        bookTitles[bookCount] = title;
        bookStatus[bookCount] = "Available";
        bookCount++;
        System.out.println("Book \"" + title + "\" added successfully with status Available.");
    }
    public static void updateBookStatus(Scanner sc) {
        if (bookCount == 0) {
            System.out.println("No books available to update.");
            return;
        }
        showBooks();
        System.out.print("Enter book number to update: ");
        int bookNum;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input, enter a valid book number: ");
                sc.next();
            }
            bookNum = sc.nextInt();
            sc.nextLine();
            if (bookNum < 1 || bookNum > bookCount) {
                System.out.print("Invalid book number, please enter again: ");
            } else {
                break;
            }
        }
        int index = bookNum - 1;
        if (bookStatus[index].equals("Available")) {
            bookStatus[index] = "Borrowed";
            System.out.println("Book \"" + bookTitles[index] + "\" is now Borrowed.");
        } else {
            bookStatus[index] = "Available";
            System.out.println("Book \"" + bookTitles[index] + "\" is now Available.");
        }
    }
    public static void showBooks() {
        if (bookCount == 0) {
            System.out.println("There is no books registered in the system.");
            return;
        }
        System.out.println("\n°°°°°°°°  LIST OF BOOKS °°°°°°°° ");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + bookTitles[i] + " - " + bookStatus[i]);
        }
    }
    public static void generateReport() {
        if (bookCount == 0) {
            System.out.println("The report cannot be created because no books have been registered.");
            return;
        }
        int availableCount = 0, borrowedCount = 0;
        for (int i = 0; i < bookCount; i++) {
            if (bookStatus[i].equals("Available")) {
                availableCount++;
            } else {
                borrowedCount++;
            }
        }
        System.out.println("\n°°°°°°°° LIBRARY REPORT °°°°°°°°");
        System.out.println("Total Books Registered: " + bookCount);
        System.out.println("Available Books: " + availableCount);
        System.out.println("Borrowed Books: " + borrowedCount);
    }
}
