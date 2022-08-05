# **Bookstore**

**Content:**

This project has six classes:
* DataSourse;
* Book;
* BookDAOImpl;
* Main;
* BookController.

Also, the project has one interface:
* BookDAO.

## **module1_DAO**

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

## **module2_DAO**

Class **BookController** - this class controller for user interaction with the application.

Class **BookContoller** has the following methods:

:white_check_mark: ***run***;  
:white_check_mark: ***countAllBooks***;     
:white_check_mark: ***getAll***;    
:white_check_mark: ***getBookById***;   
:white_check_mark: ***getBookByAuthor***;   
:white_check_mark: ***getBookByISBN***;     
:white_check_mark: ***deleteById***;    
:white_check_mark: ***createBook***;    
:white_check_mark: ***updateBook***;    

Class **BookService** - this class service for connection UI with DAO.
This class has the same methods as the class BookController and has its own implemented method - getCostBookByAuthor.

Class **Main** - is used to run a console application.

=======================================================================

__Developed by Dmitrij Shagojko__
