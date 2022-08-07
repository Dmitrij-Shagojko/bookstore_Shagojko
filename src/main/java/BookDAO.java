import java.util.List;

public interface BookDAO {
    //CRUD
    //read
    List<Book> getAll();

    Book getBookById(Long id);

    Book getBookByIsbn(String isbn);

    //create
    Book create(Book book);

    //update
    Book update(Book book);

    //delete
    boolean delete(Long id);

    List<Book> getBooksByAuthor(String author);

    int countAllBooks();
}
