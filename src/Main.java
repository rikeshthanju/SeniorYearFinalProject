/*
*   Names(s):  Rikesh Thanju
*/
import java.util.*;
public class Main {
  private static Scanner scan = new Scanner(System.in);
  private static Bookstore bookstore = new Bookstore();
  private static List<Book> cart =new ArrayList<>();


  public static void main(String[] args) {
    boolean exit = false;
    while(!exit){
      System.out.println("1. Search for a book by title");
      System.out.println("2. Add a new book");
      System.out.println("3. View Cart");
      System.out.println("4. Checkout");
      System.out.println("5. Add a book review");
      System.out.println("6. Rate a book");
      System.out.println("7. Exit");
      System.out.println("Enter your choice: ");

      int choice = scan.nextInt();
      scan.nextLine();

      switch(choice){
        case 1:
          searchBook();
          break;
        case 2:
          addNewBooks();
          break;
        case 3:
          viewCart();;
          break;
        case 4:
          checkout();
          break;
        case 5:
          addReview();
          break;
        case 6:
          rateBook();
          break;
        case 7:
          System.out.println("Thanks for shopping with us :)");
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");

      }
    }

  }

  private static void searchBook(){
    System.out.println("Enter title to search for book: ");
    String title = scan.nextLine();
    Book foundBook = bookstore.Booksearch(title);
    if(foundBook !=null){
      System.out.println("Title: " + foundBook.getTitle());
      System.out.println("Author: " + foundBook.getAuthor());
      System.out.println("Price: " + foundBook.getPrice());
      System.out.println("Rating: " + foundBook.getRating());
      System.out.println("Reviews: " + foundBook.getReviews());
      System.out.println("Would you like to add this book to your cart? (yes/no)");
      String addCart = scan.nextLine();
      if(addCart.equalsIgnoreCase("yes")){
        cart.add(foundBook);
        System.out.println("Book added to your cart.");

      }
    }
    else{
      System.out.println("Book not found in store");
      System.out.println("Would you like to order ' " + title + " ' and add it to your cart? (yes/no)");
      String orderChoice = scan.nextLine();
      if(orderChoice.equalsIgnoreCase("yes")){
        System.out.println("Enter author of the book");
        String author = scan.nextLine();
        Book orderedBook = bookstore.Order(title,author);
        System.out.println("'" + title+ "' has been order and added to your cart.");
        System.out.println("Title: " + orderedBook.getTitle());
        System.out.println("Author: " + orderedBook.getAuthor());
        System.out.println("Price: $" + orderedBook.getPrice());
        System.out.println("Ratings: " + orderedBook.getRating());
        System.out.println("Reivews: " + orderedBook.getReviews());
      }
      else{
        System.out.println("No problem. You can search for other books or add a new one.");
      }
    }
  }

  private static void addNewBooks(){
    System.out.println("Enter title of the new book");
    String title = scan.nextLine();
    System.out.println("Enter author of the new book: ");
    String author = scan.nextLine();
    Book existing = bookstore.Booksearch(title);
    if(existing!=null){
      System.out.println("The book '" + title + "' by '" + author + "' already exists in the system ");
      System.out.println("Would you like to add it to your cart instead (yes/no)");
      String add = scan.nextLine();
      if(add.equalsIgnoreCase("yes")){
        cart.add(existing);
        System.out.println("Book added to your cart");
      }
    }
    else{
      Book newBook = bookstore.Order(title,author);
      System.out.println("New book added successfully");
      System.out.println("Title: " + newBook.getTitle());
      System.out.println("Author: " + newBook.getAuthor());
      System.out.println("Price: $" + newBook.getPrice());
      System.out.println("Would you like to add this book to your cart? (yes/no)");
      String newBookAddCart = scan.nextLine();
      if(newBookAddCart.equalsIgnoreCase("yes")){
        cart.add(newBook);
        System.out.println("Book successfully added to your cart.");
      }
    }

  }

    private static void viewCart(){
      if(cart.isEmpty()){
        System.out.println("Your cart is empty.");
      }
      else{
        System.out.println("Item's currently in your cart: ");
        for(int i =0; i<cart.size(); i++){
          Book cartBook = cart.get(i);
          System.out.println((i+1) + ". " + cartBook.getTitle() + " by " + cartBook.getAuthor() + " - $" + cartBook.getPrice());
          }
        System.out.println("Would you like to proceed to checkout (yes/no)?");
        String cartChoice = scan.nextLine();
        if(cartChoice.equalsIgnoreCase("yes")){
          checkout();
        }
        else if(cartChoice.equalsIgnoreCase("no")){
          return;
        }
        else{
          System.out.println("Invalid choice. Please try again.");
        }
      }

    }

    private static void checkout(){
      if(cart.isEmpty()){
        System.out.println("Your cart is empty. Please add some books to your cart before checking out.");
      }
      else{
        double total = 0.0;
        System.out.println("Items in your cart: ");
        for(int i =0; i<cart.size(); i++){
          Book cartBook = cart.get(i);
          System.out.println((i+1) + ". " + cartBook.getTitle() + " by " + cartBook.getAuthor() + " - $" + cartBook.getPrice());
            total += cartBook.getPrice();
          }
        System.out.println("Total: $"+ String.format("%.2f",total));
        System.out.println("Would you like to proceed with the checkout? (yes/no)");
        String checkoutChoice = scan.nextLine();
        if(checkoutChoice.equalsIgnoreCase("yes")){
          System.out.println("Thank you for your purchase. Your books will be delivered to you shortly.");
          cart.clear();
        }
        else if(checkoutChoice.equalsIgnoreCase("no")){
          System.out.println("Checkout cancelled. Everything in your cart will stay.");
          return;
        }
        else{
          System.out.println("Invalid choice. Please try again.");
        }
      }
    }

  private static void addReview(){
    System.out.println("Enter the title of the book you would like to rate");
    String title = scan.nextLine();
    Book foundBook = bookstore.Booksearch(title);
    if(foundBook!=null){
      System.out.println("Enter your review");
      String review = scan.nextLine();
      foundBook.addReviews(review);
      System.out.println("Reivew added :)");
    }
    else{
      System.out.println("Book not found in store");
    }
  }

  private static void rateBook(){
    System.out.println("Enter title of the book you would like to rate");
    String title = scan.nextLine();
    Book foundBook = bookstore.Booksearch(title);
    if(foundBook !=null){
      System.out.println("Enter your rating (0.0 - 5.0): ");
      double rating = scan.nextDouble();
      scan.nextLine();
      if(rating>=0.0 && rating<=5.0){
        foundBook.addRatings(rating);
        System.out.println("Rating added :)");
      }
      else{
        System.out.println("Invalid rating. Please enter a value between 0.0 and 5.0");
      }
    }
    else{
      System.out.println("Book not found in store");
    }
  }
}