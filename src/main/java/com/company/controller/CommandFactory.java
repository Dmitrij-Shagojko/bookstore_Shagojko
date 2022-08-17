package com.company.controller;

import com.company.dao.connection.DataSource;
import com.company.dao.impl.BookDAOImpl;
import com.company.dao.impl.UserDAOImpl;
import com.company.service.BookService;
import com.company.service.UserService;
import com.company.service.impl.BookServiceImpl;
import com.company.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static final CommandFactory INSTANCE = new CommandFactory();

    public final Map<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        BookService bookService = new BookServiceImpl(new BookDAOImpl(DataSource.INSTANCE));
        UserService userService = new UserServiceImpl(new UserDAOImpl(DataSource.INSTANCE));
        commandMap.put("book", new BookCommand(bookService));
        commandMap.put("books", new BooksCommand(bookService));
        commandMap.put("user", new UserCommand(userService));
        commandMap.put("users", new UsersCommand(userService));
        commandMap.put("createUser", new CreateUserCommand(userService));
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}