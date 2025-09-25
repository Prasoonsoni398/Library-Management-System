package libraryManagementSystem;
import java.util.*;

// Book Class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

// User Class
class User {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | Borrowed: " + borrowedBooks.size() + " books";
    }
}

// Library Class
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void issueBook(int userId, int bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user != null && book != null && !book.isIssued()) {
            book.issueBook();
            user.borrowBook(book);
            System.out.println(book.getTitle()+" book issued to " + user.getName()+" ✅");
        } else {
            System.out.println("❌ Book cannot be issued.");
        }
    }

    public void returnBook(int userId, int bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user != null && book != null && book.isIssued()) {
            book.returnBook();
            user.returnBook(book);
            System.out.println(book.getTitle()+" book returned by " +user.getName() +" ✅");
        } else {
            System.out.println("❌ Book cannot be returned.");
        }
    }

    private User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    private Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }
}

// Main Class with Menu
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Adding some demo books & users
        library.addBook(new Book(1, "Java Programming", "James Gosling"));
        library.addBook(new Book(2, "Python Basics", "Guido van Rossum"));
        library.addBook(new Book(3, "C++ Concepts", "Bjarne Stroustrup"));

        library.addUser(new User(1, "Prasoon"));
        library.addUser(new User(2, "Anit"));
        library.addUser(new User(3, "Ashish"));
        library.addUser(new User(4, "Shubham"));

        int choice;
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Book");
            System.out.println("3. Display Books");
            System.out.println("4. Display Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
            	case 1:
            		  System.out.print("Enter User ID: ");
                      int newUserId = sc.nextInt();
                      sc.nextLine(); // consume newline
                      System.out.print("Enter User Name: ");
                      String name = sc.nextLine();
                      library.addUser(new User(newUserId, name));
                      System.out.println("✅ User added successfully!");
                      break;
            	case 2:
            		 System.out.print("Enter Book ID: ");
                     int newBookId = sc.nextInt();
                     sc.nextLine(); // consume newline
                     System.out.print("Enter Book Title: ");
                     String title = sc.nextLine();
                     System.out.print("Enter Author Name: ");
                     String author = sc.nextLine();
                     library.addBook(new Book(newBookId, title, author));
                     System.out.println("✅ Book added successfully!");
                     break;
                case 3: library.displayBooks(); break;
                case 4: library.displayUsers(); break;
                case 5:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    library.issueBook(uid, bid);
                    break;
                case 6:
                    System.out.print("Enter User ID: ");
                    uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    bid = sc.nextInt();
                    library.returnBook(uid, bid);
                    break;
                case 7:
                    System.out.println("📚 Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
