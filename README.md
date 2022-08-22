# **Bookstore**

**Content:**

This project has six classes:
* DataSource;
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
Class has a standard set of methods: getters, setters, equals, hashCode 
and toString.

Class **BookDAOImpl** - this class has implemented methods of the BookDAO 
interface:
* ***getAll*** - output a list of all books;
* ***getBookById*** - search a book by its id;
* ***getBookByIsbn*** - search a book by its ISBN;
* ***create*** - creates a new record in DB;
* ***update*** - updates a record in the DB;
* ***delete*** - delete a record from DB;
* ***getBooksByAuthor*** - search a book by its author;
* ***countAllBooks*** - output quantity lines in the DB.

## **module2_DAO**

Class **BookController** - this class controller for user interaction 
with the application.

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
:white_check_mark: ***getCostBookByAuthor***.   

Class **BookService** - this class service for connection UI with DAO.
This class has the same methods as the class BookController and has its 
own implemented method - getCostBookByAuthor.

Class **DataSource** has a connection to the '_bookstore_bh.properties_' 
file, which stores data for connection  to the **bookstore_bh** database 
on local server and  stores data for connection to the Db on remote server.


## **module3_user**

Added new entity - **User**.

Class **User** - this class is used to describe an object.
Class has a standard set of methods: getters, setters, equals, hashCode
and toString.

Class **UserDAOImpl** - this class has implemented methods of the UserDAO
interface:
* ***getAll*** - output a list of all users;
* ***getUserById*** - search a user by its id;
* ***getUserByLastName*** - search a user by its last name;
* ***create*** - creates a new record in DB;
* ***update*** - updates a record in the DB;
* ***delete*** - delete a record from DB;
* ***getUserByEmail*** - search a user by its email;
* ***countAllUsers*** - output quantity lines in the DB.

Class **UserService** - this class service for connection UI with DAO.
This class has the same methods as the class UserDAOImpl.

Class **BookController** - this class controller for user interaction
with the application.

Class **Main** - this class allows you to choose the DAO you want to work with.

Framework connected to the project - **Log4j2**.

Setting up logs:    
* class _DataSource_ - level **info**;
* classes _BookService_ and _UserService_ - level **debug**;
* classes _BookDAOImpl_ and _UserServiceImpl_ - level **error**;


## **module4_servlet**

Jakarta servlet connected to the project.

Project assembly into a war archive and saving it to the directory with 
the TOMCAT server is configured.

Created servlet:
* **BookController** - getting a book by id;
* **BooksController** - getting a list of books;
* **UserController** - getting a user by id;
* **UsersController** - getting a list of user.

The correct output of the requested information on the html page
is configured.


## **module5_jsp**

Created **"front" Controller**.

Created **commandFactory**.

Corresponding **jsp** files have been created to display information 
in the browser.

Added the ability to create a user entity.

Error handling configured:
- entering a non-existent command;
- calling a non-existent user;
- calling a non-existent book;
- creating a user with incorrect data.

=======================================================================

__Developed by Dmitrij Shagojko__