package com.company.app;

import com.company.controller.BookController;
import com.company.controller.UserController;
import com.company.dao.connection.DataSource;
import com.company.dao.impl.BookDAOImpl;
import com.company.dao.impl.UserDAOImpl;
import com.company.service.BookService;
import com.company.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner command = new Scanner(System.in);
        System.out.println("""
                Enter a list of who you want to access:
                - books
                - users
                - exit""");
        String list = command.nextLine();
        while (!Pattern.matches("exit", list.trim().toLowerCase())) {
            if (Pattern.matches("books", list.trim().toLowerCase())) {
                BookController bookController = new BookController(new BookService(new BookDAOImpl(new DataSource())));
                bookController.run();
            } else if (Pattern.matches("users", list.trim().toLowerCase())) {
                UserController userController = new UserController(new UserService(new UserDAOImpl(new DataSource())));
                userController.run();
            }
            System.out.println("\nIf you want to continue, then enter a new list.\n" +
                    "To exit, enter the command \"exit\".");
            list = command.nextLine();
        }
        DataSource dataSource = new DataSource();
        dataSource.close();
    }
}