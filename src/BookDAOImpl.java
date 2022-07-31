import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    public static final String GET_ALL = "SELECT id, name, author, publishment_date FROM books WHERE delete_book IS NULL";
    public static final String GET_BOOK_BY_ISBN10 = "SELECT id, name, author, publishment_date FROM books WHERE \"ISBN-10\" = ?";
    public static final String GET_BOOK_BY_ISBN13 = "SELECT id, name, author, publishment_date FROM books WHERE \"ISBN-13\" = ?";
    public static final String CREATE_BOOK = "INSERT INTO public.books(name, author, publicher, publishment_date, paperback, " +
            "\"ISBN-10\", \"ISBN-13\", lexile_measure, weight, dimensions, bestsellersrank) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_BOOK_BY_ID = "SELECT id, name, author, publicher, publishment_date, \"language\", paperback, \"ISBN-10\"," +
            "\"ISBN-13\", lexile_measure, weight, dimensions, bestSellersRank FROM books WHERE id = ?";
    public static final String UPDATE_BOOK = "UPDATE public.books SET name=?, author=?, publicher=?, publishment_date=?, language=?, paperback=?, " +
            "\"ISBN-10\"=?, \"ISBN-13\"=?, lexile_measure=?, weight=?, dimensions=?, bestsellersrank=? WHERE id =?";
    public static final String DELETE_BOOK = "DELETE FROM public.books WHERE id = ?";

    private final DataSource dataSource;

    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishmentDate(resultSet.getString("publishment_date"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publicher"));
                book.setPublishmentDate(resultSet.getString("publishment_date"));
                book.setLanguage(resultSet.getString("language"));
                book.setPaperback(resultSet.getInt("paperback"));
                book.setISBN10(resultSet.getString("ISBN-10"));
                book.setISBN13(resultSet.getString("ISBN-13"));
                book.setLexileMeasure(resultSet.getString("lexile_measure"));
                book.setWeight(resultSet.getInt("weight"));
                book.setDimensions(resultSet.getString("dimensions"));
                book.setBestSellersRank(resultSet.getString("bestSellersRank"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = null;
            if (isbn.length() == 10) {
                statement = connection.prepareStatement(GET_BOOK_BY_ISBN10);
            } else {
                statement = connection.prepareStatement(GET_BOOK_BY_ISBN13);
            }
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishmentDate(resultSet.getString("publishment_date"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_BOOK);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setDate(4, Date.valueOf(book.getPublishmentDate()));
            statement.setInt(5, book.getPaperback());
            statement.setString(6, book.getISBN10());
            statement.setString(7, book.getISBN13());
            statement.setString(8, book.getLexileMeasure());
            statement.setInt(9, book.getWeight());
            statement.setString(10, book.getDimensions());
            statement.setString(11, book.getBestSellersRank());
            statement.executeUpdate();
            return getBookByIsbn(book.getISBN10());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book update(Book book) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK);
            statement.setLong(13, book.getId());
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setDate(4, Date.valueOf(book.getPublishmentDate()));
            statement.setString(5, book.getLanguage());
            statement.setInt(6, book.getPaperback());
            statement.setString(7, book.getISBN10());
            statement.setString(8, book.getISBN13());
            statement.setString(9, book.getLexileMeasure());
            statement.setInt(10, book.getWeight());
            statement.setString(11, book.getDimensions());
            statement.setString(12, book.getBestSellersRank());
            statement.executeUpdate();
            return getBookById(book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BOOK);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, author, publishment_date FROM books WHERE author = ?");
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishmentDate(resultSet.getString("publishment_date"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int countAllBooks() {
        int count = 0;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                count = resultSet.getRow();
            };
        } catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }
}
