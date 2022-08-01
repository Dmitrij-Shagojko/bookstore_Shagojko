import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello user!");
        System.out.println("List of available commands: \n" +
                "---> all \n" +
                "---> get{id}\n" +
                "---> update \n" +
                "---> create \n" +
                "---> delete{id} \n" +
                "---> exit <---");
        System.out.println("Enter the command:");
        String command = scanner.nextLine();
        BookDAO bookDAO = new BookDAOImpl(new DataSource());
        while (!Pattern.matches("exit", command.trim().toLowerCase())) {
            if (Pattern.matches("all", command.trim().toLowerCase())) {
                List<Book> books = bookDAO.getAll();
                books.forEach(System.out::println);
            } else if (Pattern.matches("(get\\{)\\d+.", command.trim().toLowerCase())) {
                StringBuilder stringBuilder = new StringBuilder(command);
                int start = stringBuilder.indexOf("{");
                int end = stringBuilder.indexOf("}");
                Long id = Long.parseLong(String.valueOf(stringBuilder.delete(end, stringBuilder.length()).delete(0, start + 1)));
                Book book = bookDAO.getBookById(id);
                System.out.println("Book{" +
                        "id=" + book.getId() +
                        ", name='" + book.getName() + '\'' +
                        ", author='" + book.getAuthor() + '\'' +
                        ", publisher='" + book.getPublisher() + '\'' +
                        ", publishmentDate='" + book.getPublishmentDate() + '\'' +
                        ", language='" + book.getLanguage() +
                        ", '\n'paperback=" + book.getPaperback() +
                        ", ISBN10='" + book.getISBN10() + '\'' +
                        ", ISBN13='" + book.getISBN13() + '\'' +
                        ", lexileMeasure='" + book.getLexileMeasure() + '\'' +
                        ", weight=" + book.getWeight() +
                        ", dimensions='" + book.getDimensions() + '\'' +
                        ", bestSellersRank='" + book.getBestSellersRank() + '\'' +
                        '}');
            } else if (Pattern.matches("(delete\\{)\\d+.", command.trim().toLowerCase())) {
                StringBuilder stringBuilder = new StringBuilder(command);
                int start = stringBuilder.indexOf("{");
                int end = stringBuilder.indexOf("}");
                Long id = Long.parseLong(String.valueOf(stringBuilder.delete(end, stringBuilder.length()).delete(0, start + 1)));
                if (bookDAO.delete(id)) {
                    System.out.printf("Book with id = %d - deleted.", id);
                }
            } else if (Pattern.matches("create", command.trim().toLowerCase())) {
                Book book = new Book();
                System.out.println("Enter book name:");
                book.setName(scanner.nextLine());
                System.out.println("Enter author name:");
                book.setAuthor(scanner.nextLine());
                System.out.println("Enter publisher name:");
                book.setPublisher(scanner.nextLine());
                System.out.println("Enter publication date (input format: YYYY-MM-DD):");
                book.setPublishmentDate(scanner.nextLine());
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
                System.out.println(bookDAO.create(book));
            } else if (Pattern.matches("update", command.trim().toLowerCase())) {
                System.out.println("Enter the id of the book you want to update");
                Long id = Long.parseLong(scanner.nextLine());
                Book book = bookDAO.getBookById(id);
                System.out.println("Select number of the item you want to update:" +
                        "1 - book name\n" +
                        "2 - author name\n" +
                        "3 - publisher name\n" +
                        "4 - publication date\n" +
                        "5 - number of pages\n" +
                        "6 - ISBN10\n" +
                        "7 - ISBN13\n" +
                        "8 - lexile measure\n" +
                        "9 - weight of books (in grams)\n" +
                        "10 - dimension book (in mm)\n" +
                        "11 - bestseller rank\n" +
                        "0 - exit from update mode\n");
                System.out.println("Enter the number of the item you want to change:");
                int number = Integer.parseInt(scanner.nextLine());
                while (number != 0) {
                    switch (number) {
                        case 1:
                            System.out.println("Enter book name:");
                            book.setName(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("Enter author name:");
                            book.setAuthor(scanner.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter publisher name:");
                            book.setPublisher(scanner.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter publication date (input format: YYYY-MM-DD):");
                            book.setPublishmentDate(scanner.nextLine());
                            break;
                        case 5:
                            System.out.println("Enter number of pages:");
                            book.setPaperback(Integer.parseInt(scanner.nextLine()));
                            break;
                        case 6:
                            System.out.println("Enter ISBN10 (10-number)");
                            book.setISBN10(scanner.nextLine());
                            break;
                        case 7:
                            System.out.println("Enter ISBN13 (input format: 000-0000000000):");
                            book.setISBN13(scanner.nextLine());
                            break;
                        case 8:
                            System.out.println("Enter lexile measure:");
                            book.setLexileMeasure(scanner.nextLine());
                            break;
                        case 9:
                            System.out.println("Enter weight of book in grams:");
                            book.setWeight(Integer.parseInt(scanner.nextLine()));
                            break;
                        case 10:
                            System.out.println("Enter dimension of book in mm (input format: lxbxh):");
                            book.setDimensions(scanner.nextLine());
                            break;
                        case 11:
                            System.out.println("Enter bestsellers rank:");
                            book.setBestSellersRank(scanner.nextLine());
                            break;
                    }
                    System.out.println("if you want to update other data - select the item number, " +
                            "otherwise enter 0 ");
                    number = Integer.parseInt(scanner.nextLine());
                }
                bookDAO.update(book);
                System.out.println("Changes applied");
            }
            System.out.println("If you want to continue, then enter a new command.\n" +
                    "To exit, enter the command \"exit\".");
            System.out.println("List of available commands: \n" +
                    "---> all \n" +
                    "---> get{id}\n" +
                    "---> update \n" +
                    "---> create \n" +
                    "---> delete{id} \n" +
                    "---> exit <---");
            System.out.println("Enter the command:");
            command = scanner.nextLine();
        }
        System.out.println("Good bye!");
        scanner.close();
    }
}