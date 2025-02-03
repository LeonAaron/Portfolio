package assn02;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static Scanner _sc = new Scanner(System.in);

    //Constant
    static int _maxCapacity = 100;

    //Class variable for every book object
    static int _bookCount = 0;
    static Book[] _allBooks = new Book[_maxCapacity];

    //Main meathod of program
    public static void main(String[] args) {
        LibraryManagementSystem.menu();
    }


    public static void menu() {
        //Exit loop if exit = true
        boolean exit = false;
        int input = 0;

        do {
            //Display Menu Options
            System.out.println("Library Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Add a Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Check Out a Book");
            System.out.println("4. Return a Book");
            System.out.println("Enter your choice:");
            //Get user input
            input = _sc.nextInt();

            //Functionality depending on user input
            switch (input) {
                case 0: {
                    //Exits the program
                    exit = true;
                    break;
                }
                case 1: {
                    addBook();
                    break;
                }
                case 2: {
                    displayBooks();
                    break;
                }
                case 3: {
                    checkOutBook();
                    break;
                }
                case 4: {
                    returnBook();
                    break;
                }
                default: {
                    //If user doesn't provide valid input
                    System.out.println("Invalid choice. Try again");
                }
            }
        } while (!exit);

        System.out.println("Goodbye!");
    }


    public static void addBook() {

        //Only execute if mac capacity not exceeded
        if (_bookCount != _maxCapacity) {
            String bookTitle = "";
            String bookAuthor = "";

            //Create new book object
            Book newBook = new Book();

            //Modify newBook attributes to match user input
            System.out.println("Enter book title:");

            //double .nextLine() to prevent error
            _sc.nextLine();
            bookTitle = _sc.nextLine();
            newBook._bookTitle = bookTitle;

            System.out.println("Enter book author:");
            bookAuthor = _sc.nextLine();
            newBook._bookAuthor = bookAuthor;

            //Append newBook to _allBooks, based on current _bookCount
            _allBooks[_bookCount] = newBook;

            //Incriment _bookCount
            _bookCount++;

            System.out.println("Book added!");
        }
        else {
            System.out.println("Library is full!");
        }
    }


    public static void displayBooks(){
        String isCheckedOut = "";

        System.out.println("Books in Library:");
        for (int i = 0; i < _bookCount; i++) {
            //Every Book object in _allBooks
            Book currentBook = _allBooks[i];

            //Modify string representation of whether book was checked out
            if (currentBook._checkedOut) {
                isCheckedOut = "Yes";
            } else {
                isCheckedOut = "No";
            }

            System.out.println(i + 1 + ". Title: " + currentBook._bookTitle + ", Author: " + currentBook._bookAuthor + ", Checked Out: " + isCheckedOut);
        }
    }


    public static void checkOutBook() {
        int checkOutNumber = 0;

        System.out.println("Enter book number to check out:");
        //Obtain user input
        checkOutNumber = _sc.nextInt();

        //If user input is invalid, checkOutNumber out of range
        if (checkOutNumber > _bookCount || checkOutNumber <= 0) {
            System.out.println("Invalid book number!");
        }
        else {
            //[checkOutNumber - 1] to obtain index for Book object
            Book currentBook = _allBooks[checkOutNumber - 1];
            currentBook._checkedOut = true;
            System.out.println("Book checked out!");
        }
    }


    public static void returnBook() {
        int returnNumber = 0;

        System.out.println("Enter book number to return:");
        returnNumber = _sc.nextInt();

        if (returnNumber > _bookCount || returnNumber <= 0) {
            System.out.println("Invalid book number!");
        }
        else {
            Book currentBook = _allBooks[returnNumber - 1];
            currentBook._checkedOut = false;
            System.out.println("Book returned!");
        }
    }
}


//Define a book class
class Book {
    //To be set by user
    String _bookTitle;
    String _bookAuthor;

    boolean _checkedOut = false;
}
