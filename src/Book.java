public class Book implements Comparable<Book> {
    private int id;
    private String title;

    Book(int id, String title) {
        if (id < 0)
            throw new IllegalArgumentException();
        setId(id);
        if (title.isBlank())
            throw new IllegalArgumentException("Name of the book with ID \'" + this.getId() + "\' can not be blank!");
        setTitle(title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Book anotherBook) {
        return Integer.compare(this.getId(), anotherBook.getId());
    }

    @Override
    public String toString() {
        return "(" + this.getId() + ", \"" + this.getTitle() + "\")";
    }
}