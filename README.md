# **Bookstore**

## **module1_DAO**

This project has six classes:
* DataSourse;
* Book;
* BookDAOImpl;
* Main;
* BookController.

Also the project has one interface:
* BookDAO.

Class **DataSource** has one implemented method - ***getConnection***.
This method creates a connection to the database PostrgeSQL (bookstore_bh).

Class **Book** - this class is used to describe an object. 
Class has a standard set of methods: getters, setters, equals, hashCode and toString.

Class **BookDAOImpl** - this class has implemented methods of the BookDAO interface:
* ***getAll*** - output a list of all books;
* ***getBookById*** - search a book by its id;
* ***getBookByIsbn*** - search a book by its ISBN;
* ***create*** - creates a new record in DB;
* ***update*** - updates a record in the DB;
* ***delete*** - delete a record from DB;
* ***getBooksByAuthor*** - search a book by its author;
* ***countAllBooks*** - output quantity lines in the DB.

Class **BookController** - this class serves for user interaction with the application.

Class **BookContoller** has the following methods:

+ ***getAll***;
+ ***getById***;
+ ***deleteById***;
+ ***createBook***;
+ ***deleteBook***;

  :white_check_mark: (testing)

  :negative_squared_cross_mark: (testing)


Class **Main** - is used to run a console application.


__Developed by Dmitrij Shagojko__
