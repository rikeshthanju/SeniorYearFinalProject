import java.util.*;

class Book{
  private String title;
  private String author;
  private double price;
  private String keys;
  private List<String> reviews;
  private double rating;
  private int ratingCount;
  private double ratingSum;

  public Book(String title, String author, double price, String keys){
    this.title = title;
    this.author = author;
    this.price = price;
    this.keys = keys;
    this.reviews = new ArrayList<>();
    this.rating = 0.0;
    this.ratingCount = 0;
    this.ratingSum = 0.0;

  }
  public String getTitle(){
    return title;
  }
  public String getAuthor(){
    return author;
  }
  public double getPrice(){
    return price;
  }
  public String getKeys(){
    return keys;
  }
  public List<String> getReviews(){
    return reviews;
  }
  public double getRating(){
    return rating;
  }
  public int getRatingCount(){
    return ratingCount;
  }
  public void addReviews(String review){
    reviews.add(review);
  }
  public void addRatings(double ratings){
    ratingSum +=ratings;
    ratingCount++;
    rating = ratingSum/ratingCount;
  }

}

class Bookstore{
  private HashMap<String, Book> books;
  private Random random;
  public Bookstore(){
    books = new HashMap<>();
    random = new Random();
    addBook("671937", new Book("Maze Runner", "James Dashner",21.99,"671937"));
    addBook("444962", new Book("The Hunger Games", "Suzanne Collins",19.99,"444962"));
  }

  public void addBook(String keys, Book book){
    books.put(keys,book);
  }

  public Book Booksearch(String title){
    for(Book book: books.values()){
      if(book.getTitle().equalsIgnoreCase(title)){
        return book;
      }
    }
    return null;
  }

  public Book Order(String title, String author){
    double price = 9.99 + (random.nextDouble() * (25.99 - 9.99));
    price = Double.parseDouble(String.format("%.2f", price));
    String key = "Key-" + title.hashCode();
    Book newBook = new Book(title,author,price,key);
    addBook(key,newBook);
    return newBook;
  }
}
