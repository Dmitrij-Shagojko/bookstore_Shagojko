import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    public static final String GET_ALL = "SELECT id, name, author, publicher, publishment_date, price, paperback, \"ISBN-10\"," +
            "\"ISBN-13\", lexile_measure, weight, dimensions, bestSellersRank FROM books";
    public static final String GET_BOOK_BY_ISBN10 = "SELECT id, name, author, publicher, publishment_date, price, paperback, \"ISBN-10\"," +
            "\"ISBN-13\", lexile_measure, weight, dimensions, bestSellersRank FROM books WHERE \"ISBN-10\" = ?";
    public static final String GET_BOOK_BY_ISBN13 = "SELECT id, name, author, publicher, publishment_date, price, paperback, \"ISBN-10\"," +
            "\"ISBN-13\", lexile_measure, weight, dimensions, bestSellersRank FROM books WHERE \"ISBN-13\" = ?";
    public static final String CREATE_BOOK = "INSERT INTO books(name, author, publicher, publishment_date, price, paperback, " +
            "\"ISBN-10\", \"ISBN-13\", lexile_measure, weight, dimensions, bestsellersrank) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_BOOK_BY_ID = "SELECT id, name, author, publicher, publishment_date, price, paperback, \"ISBN-10\"," +
            "\"ISBN-13\", lexile_measure, weight, dimensions, bestSellersRank FROM books WHERE id = ?";
    public static final String UPDATE_BOOK = "UPDATE public.books SET name=?, author=?, publicher=?, publishment_date=?, price=?, paperback=?, " +
            "\"ISBN-10\"=?, \"ISBN-13\"=?, lexile_measure=?, weight=?, dimensions=?, bestsellersrank=? WHERE id =?";
    public static final String DELETE_BOOK = "DELETE FROM public.books WHERE id = ?";
    public static final String GET_BOOK_BY_AUTHOR = "SELECT id, name, author, publicher, publishment_date, price, paperback, \"ISBN-10\"," +
            "\"ISBN-13\", lexile_measure, weight, dimensions, bestSellersRank FROM books WHERE author = ?";

    private final DataSource dataSource;

    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Book book = setBook(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return setBook(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Book setBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setPublisher(resultSet.getString("publicher"));
        book.setPublishmentDate(resultSet.getDate("publishment_date").toLocalDate());
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setPaperback(resultSet.getInt("paperback"));
        book.setISBN10(resultSet.getString("ISBN-10"));
        book.setISBN13(resultSet.getString("ISBN-13"));
        book.setLexileMeasure(resultSet.getString("lexile_measure"));
        book.setWeight(resultSet.getInt("weight"));
        book.setDimensions(resultSet.getString("dimensions"));
        book.setBestSellersRank(resultSet.getString("bestSellersRank"));
        return book;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = null;
        try {
            if (isbn.length() == 10) {
                statement = connection.prepareStatement(GET_BOOK_BY_ISBN10);
            } else {
                statement = connection.prepareStatement(GET_BOOK_BY_ISBN13);
            }
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return setBook(resultSet);
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
            statement.setBigDecimal(5, book.getPrice());
            statement.setInt(6, book.getPaperback());
            statement.setString(7, book.getISBN10());
            statement.setString(8, book.getISBN13());
            statement.setString(9, book.getLexileMeasure());
            statement.setInt(10, book.getWeight());
            statement.setString(11, book.getDimensions());
            statement.setString(12, book.getBestSellersRank());
            if (statement.executeUpdate() == 1) {
                return getBookByIsbn(book.getISBN10());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book update(Book book) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK)) {
            statement.setLong(13, book.getId());
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setDate(4, Date.valueOf(book.getPublishmentDate()));
            statement.setBigDecimal(5, book.getPrice());
            statement.setInt(6, book.getPaperback());
            statement.setString(7, book.getISBN10());
            statement.setString(8, book.getISBN13());
            statement.setString(9, book.getLexileMeasure());
            statement.setInt(10, book.getWeight());
            statement.setString(11, book.getDimensions());
            statement.setString(12, book.getBestSellersRank());
            if (statement.executeUpdate() == 1) {
                return getBookById(book.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_AUTHOR)) {
            List<Book> books = new ArrayList<>();
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = setBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countAllBooks() {
        Connection connection = dataSource.getConnection();
        int count = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                count = resultSet.getRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
