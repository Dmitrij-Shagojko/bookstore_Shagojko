package com.company.controller;

import com.company.dao.connection.DataSource;
import com.company.dao.impl.BookDAOImpl;
import com.company.dao.impl.UserDAOImpl;
import com.company.service.impl.BookServiceImpl;
import com.company.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static final CommandFactory INSTANCE = new CommandFactory();

    public final Map<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        BookServiceImpl bookServiceImpl = new BookServiceImpl(new BookDAOImpl(DataSource.INSTANCE));
        UserServiceImpl userServiceImpl = new UserServiceImpl(new UserDAOImpl(DataSource.INSTANCE));
        commandMap.put("book", new BookCommand(bookServiceImpl));
        commandMap.put("books", new BooksCommand(bookServiceImpl));
        commandMap.put("user", new UserCommand(userServiceImpl));
        commandMap.put("users", new UsersCommand(userServiceImpl));
        commandMap.put("createUser", new CreateUserCommand(userServiceImpl));
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}