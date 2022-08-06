import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String publisher;
    private LocalDate publishmentDate;
    private BigDecimal price;
    private int paperback;
    private String ISBN10;
    private String ISBN13;
    private String lexileMeasure;
    private int weight;
    private String dimensions;
    private String bestSellersRank;
    private Language language;

    enum Language {
        ENGLISH, SPANISH, RUSSIAN, FRENCH, GERMAN
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishmentDate() {
        return publishmentDate;
    }

    public void setPublishmentDate(LocalDate publishmentDate) {
        this.publishmentDate = publishmentDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPaperback() {
        return paperback;
    }

    public void setPaperback(int paperback) {
        this.paperback = paperback;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getLexileMeasure() {
        return lexileMeasure;
    }

    public void setLexileMeasure(String lexileMeasure) {
        this.lexileMeasure = lexileMeasure;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getBestSellersRank() {
        return bestSellersRank;
    }

    public void setBestSellersRank(String bestSellersRank) {
        this.bestSellersRank = bestSellersRank;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return paperback == book.paperback && weight == book.weight && Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) && Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher) && Objects.equals(publishmentDate, book.publishmentDate) &&
                Objects.equals(price, book.price) && Objects.equals(ISBN10, book.ISBN10) &&
                Objects.equals(ISBN13, book.ISBN13) && Objects.equals(lexileMeasure, book.lexileMeasure) &&
                Objects.equals(dimensions, book.dimensions) && Objects.equals(bestSellersRank, book.bestSellersRank) &&
                language == book.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publisher, publishmentDate, price, paperback, ISBN10, ISBN13,
                lexileMeasure, weight, dimensions, bestSellersRank, language);
    }

    @Override
    public String toString() {
        return "Book: " +
                "id = " + id + '.' +
                " name='" + name + ';' +
                " author='" + author + ';' +
                " publishmentDate='" + publishmentDate + ';' +
                " language='" + language + ';';
    }
}
