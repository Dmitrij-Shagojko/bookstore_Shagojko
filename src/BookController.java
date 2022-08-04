import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BookController {
    public static final String AVAILABLE_COMMANDS = """
            List of available commands:
            ---> all
            ---> get {id}
            ---> update
            ---> create
            ---> delete {id}
            ---> get book by author
            ---> get book by isbn
            ---> count all books
            ---> exit <---
            Enter the command:""";
    private final BookService bookService;

    public BookController() {
        this.bookService = new BookService();
    }

    public void run() {
        System.out.println("Hello user!");
        System.out.println(AVAILABLE_COMMANDS);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!Pattern.matches("exit", command.trim().toLowerCase())) {
            if (Pattern.matches("all", command.trim().toLowerCase())) {
                getAll();
            } else if (Pattern.matches("get \\d+", command.trim().toLowerCase())) {
                getBookById(command);
            } else if (Pattern.matches("delete \\d+", command.trim().toLowerCase())) {
                deleteById(command);
            } else if (Pattern.matches("create", command.trim().toLowerCase())) {
                createBook(scanner);
            } else if (Pattern.matches("update", command.trim().toLowerCase())) {
                updateBook(scanner);
            } else if (Pattern.matches("get book by author", command.trim().toLowerCase())) {
                getBookByAuthor(scanner);
            } else if (Pattern.matches("count all books", command.trim().toLowerCase())) {
                countAllBooks();
            } else if (Pattern.matches("get book by isbn", command.trim().toLowerCase())) {
                getBookByIsbn(scanner);
            }
            System.out.println("If you want to continue, then enter a new command.\n" +
                    "To exit, enter the command \"exit\".");
            System.out.println(AVAILABLE_COMMANDS);
            command = scanner.nextLine();
        }
        System.out.println("Good bye!");
    }

    private void getBookByIsbn(Scanner scanner) {
        System.out.println("Enter ISBN: ISBN-10 or ISBN-13 (input format: 000-0000000000):");
        System.out.println(bookService.getBookByIsbn(scanner.nextLine()));
    }

    private void countAllBooks() {
        System.out.println(bookService.countAllBooks());
    }

    private void getBookByAuthor(Scanner scanner) {
        System.out.println("Enter author:");
        List<Book> books = bookService.getBookByAuthor(scanner.nextLine());
        books.forEach(System.out::println);
    }

    private void updateBook(Scanner scanner) {
        System.out.println("Enter the id of the book you want to update");
        Long id = Long.parseLong(scanner.nextLine());
        Book book = bookService.getBookById(id);
        System.out.println("""
                Select number of the item you want to update:
                1 - book name;
                2 - author name;
                3 - publisher name;
                4 - publication date;
                5 - price;
                6 - number of pages;
                7 - ISBN-10;
                8 - ISBN-13;
                9 - lexile measure;
                10 - weight of books (in grams);
                11 - dimension book (in mm);
                12 - bestseller rank;
                0 - exit from to update mode.""");
        System.out.println("Enter the number of the item you want to change:");
        int number = Integer.parseInt(scanner.nextLine());
        while (number != 0) {
            switch (number) {
                case 1 -> {
                    System.out.println("Enter book name:");
                    book.setName(scanner.nextLine());
                }
                case 2 -> {
                    System.out.println("Enter author name:");
                    book.setAuthor(scanner.nextLine());
                }
                case 3 -> {
                    System.out.println("Enter publisher name:");
                    book.setPublisher(scanner.nextLine());
                }
                case 4 -> {
                    System.out.println("Enter publication date (input format: YYYY-MM-DD):");
                    String[] date = scanner.nextLine().split("-");
                    int year = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int day = Integer.parseInt(date[2]);
                    book.setPublishmentDate(LocalDate.of(year, month, day));
                }
                case 5 -> {
                    System.out.println("Enter price:");
                    book.setPrice(new BigDecimal(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.println("Enter number of pages:");
                    book.setPaperback(Integer.parseInt(scanner.nextLine()));
                }
                case 7 -> {
                    System.out.println("Enter ISBN10 (10-number)");
                    book.setISBN10(scanner.nextLine());
                }
                case 8 -> {
                    System.out.println("Enter ISBN13 (input format: 000-0000000000):");
                    book.setISBN13(scanner.nextLine());
                }
                case 9 -> {
                    System.out.println("Enter lexile measure:");
                    book.setLexileMeasure(scanner.nextLine());
                }
                case 10 -> {
                    System.out.println("Enter weight of book in grams:");
                    book.setWeight(Integer.parseInt(scanner.nextLine()));
                }
                case 11 -> {
                    System.out.println("Enter dimension of book in mm (input format: lxbxh):");
                    book.setDimensions(scanner.nextLine());
                }
                case 12 -> {
                    System.out.println("Enter bestsellers rank:");
                    book.setBestSellersRank(scanner.nextLine());
                }
            }
            System.out.println("if you want to update other data - select the item number, " +
                    "otherwise enter 0 ");
            number = Integer.parseInt(scanner.nextLine());
        }
        bookService.update(book);
        System.out.println("Changes applied");
    }

    private void createBook(Scanner scanner) {
        Book book = new Book();
        System.out.println("Enter book name:");
        book.setName(scanner.nextLine());
        System.out.println("Enter author name:");
        book.setAuthor(scanner.nextLine());
        System.out.println("Enter publisher name:");
        book.setPublisher(scanner.nextLine());
        System.out.println("Enter publication date (input format: YYYY-MM-DD):");
        String[] date = scanner.nextLine().split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        book.setPublishmentDate(LocalDate.of(year, month, day));
        System.out.println("Enter price:");
        book.setPrice(new BigDecimal(scanner.nextLine()));
        System.out.println("Enter number of pages:");
        book.setPaperback(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter ISBN10 (10-number):");
        book.setISBN10(scanner.nextLine());
        System.out.println("Enter ISBN13 (input format: 000-0000000000):");
        book.setISBN13(scanner.nextLine());
        System.out.println("Enter lexile measure:");
        book.setLexileMeasure(scanner.nextLine());
        System.out.println("Enter weight of book in grams:");
        book.setWeight(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter dimension of book in mm (input format: l x b x h):");
        book.setDimensions(scanner.nextLine());
        System.out.println("Enter bestsellers rank:");
        book.setBestSellersRank(scanner.nextLine());
        System.out.println("A new book has been created:");
        System.out.println(bookService.create(book));
    }

    private void deleteById(String command) {
        String[] mas = command.trim().split(" ");
        Long id = Long.parseLong(mas[1]);
        if (bookService.delete(id)) {
            System.out.printf("Book with id = %d - deleted.", id);
        }
    }

    private void getBookById(String command) {
        String[] mas = command.trim().split(" ");
        Long id = Long.parseLong(mas[1]);
        Book book = bookService.getBookById(id);
        System.out.println("Book{" +
                "id=" + book.getId() +
                ", name='" + book.getName() + '\'' +
                ", author='" + book.getAuthor() + '\'' +
                ", publisher='" + book.getPublisher() + '\'' +
                ", publishmentDate='" + book.getPublishmentDate() + '\'' +
                ", price='" + book.getPrice() + '\'' +
                ", \n'paperback=" + book.getPaperback() +
                ", ISBN10='" + book.getISBN10() + '\'' +
                ", ISBN13='" + book.getISBN13() + '\'' +
                ", lexileMeasure='" + book.getLexileMeasure() + '\'' +
                ", weight=" + book.getWeight() +
                ", dimensions='" + book.getDimensions() + '\'' +
                ", bestSellersRank='" + book.getBestSellersRank() + '\'' +
                '}');
    }

    private void getAll() {
        List<Book> books = bookService.getAll();
        books.forEach(System.out::println);
    }
}
