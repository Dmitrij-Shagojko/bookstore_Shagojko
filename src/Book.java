import java.util.Objects;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String publisher;
    private String publishmentDate;
    private String language;
    private int paperback;
    private String ISBN10;
    private String ISBN13;
    private String lexileMeasure;
    private int weight;
    private String dimensions;
    private String bestSellersRank;
    private Character flagOfDelete;

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

    public String getPublishmentDate() {
        return publishmentDate;
    }

    public void setPublishmentDate(String publishmentDate) {
        this.publishmentDate = publishmentDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Character getFlagOfDelete() {
        return flagOfDelete;
    }

    public void setFlagOfDelete(Character flagOfDelete) {
        this.flagOfDelete = flagOfDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return paperback == book.paperback && weight == book.weight && Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) && Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher) && Objects.equals(publishmentDate, book.publishmentDate) &&
                Objects.equals(language, book.language) && Objects.equals(ISBN10, book.ISBN10) &&
                Objects.equals(ISBN13, book.ISBN13) && Objects.equals(lexileMeasure, book.lexileMeasure) &&
                Objects.equals(dimensions, book.dimensions) && Objects.equals(bestSellersRank, book.bestSellersRank) &&
                Objects.equals(flagOfDelete, book.flagOfDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publisher, publishmentDate, language, paperback, ISBN10, ISBN13,
                lexileMeasure, weight, dimensions, bestSellersRank, flagOfDelete);
    }

    @Override
    public String toString() {
        return "Book: " +
                "id = " + id + '.' +
                " name='" + name + ';' +
                " author='" + author + ';' +
                " publishmentDate='" + publishmentDate + '.';
    }
}
