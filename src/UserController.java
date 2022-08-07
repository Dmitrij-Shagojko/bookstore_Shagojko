import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserController {
    private UserService userService;

    public static final String AVAILABLE_COMMAND_USER = """
            List of available commands:
            ---> all
            ---> get {id}
            ---> get by email
            ---> get by last name
            ---> create
            ---> update
            ---> delete {id}
            ---> count all users
            ---> exit""";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        System.out.println("Hello user!");
        System.out.println(AVAILABLE_COMMAND_USER);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!Pattern.matches("exit", command.trim().toLowerCase())) {
            if (Pattern.matches("all", command.trim().toLowerCase())) {
                getAll();
            } else if (Pattern.matches("get \\d+", command.trim().toLowerCase())) {
                getUserById(command);
            } else if (Pattern.matches("delete \\d+", command.trim().toLowerCase())) {
                deleteById(command);
            } else if (Pattern.matches("create", command.trim().toLowerCase())) {
                createUser(scanner);
            } else if (Pattern.matches("update", command.trim().toLowerCase())) {
                updateUser(scanner);
            } else if (Pattern.matches("get by last name", command.trim().toLowerCase())) {
                getUserByLastName(scanner);
            } else if (Pattern.matches("count all users", command.trim().toLowerCase())) {
                countAllUsers();
            } else if (Pattern.matches("get by email", command.trim().toLowerCase())) {
                getUserByEmail(scanner);
            }
            System.out.println("\nIf you want to continue, then enter a new command.\n" +
                    "To exit, enter the command \"exit\".");
            System.out.println(AVAILABLE_COMMAND_USER);
            command = scanner.nextLine();
        }
        System.out.println("Good bye!");
    }

    private void getUserByEmail(Scanner scanner) {
        System.out.println("Enter user email:");
        System.out.println(userService.getUserByEmail(scanner.nextLine()));
    }

    private void countAllUsers() {
        System.out.println(userService.countAllUsers());
    }

    private void getUserByLastName(Scanner scanner) {
        System.out.println("Enter user last name:");
        List<User> users = userService.getUserByLastName(scanner.nextLine());
        users.forEach(System.out::println);
    }

    private void updateUser(Scanner scanner) {
        System.out.println("Enter the id of the user you want to update");
        Long id = Long.parseLong(scanner.nextLine());
        User user = userService.getUserById(id);
        System.out.println("""
                Select number of the item you want to update:
                1 - first name;
                2 - last name;
                3 - email;
                4 - role;
                0 - exit from to update mode.""");
        System.out.println("Enter the number of the item you want to change:");
        int number = Integer.parseInt(scanner.nextLine());
        while (number != 0) {
            switch (number) {
                case 1 -> {
                    System.out.println("Enter first name:");
                    user.setFirstName(scanner.nextLine());
                }
                case 2 -> {
                    System.out.println("Enter last name:");
                    user.setLastName(scanner.nextLine());
                }
                case 3 -> {
                    System.out.println("Enter user email:");
                    user.setEmail(scanner.nextLine());
                }
                case 4 -> {
                    System.out.println("Enter role user (choose from available):");
                    System.out.println(Arrays.toString(User.Role.values()));
                    user.setRole(User.Role.valueOf(scanner.nextLine().toUpperCase()));
                }
            }
            System.out.println("if you want to update other data - select the item number, " +
                    "otherwise enter 0 ");
            number = Integer.parseInt(scanner.nextLine());
        }
        userService.update(user);
        System.out.println("Changes applied");
    }

    private void createUser(Scanner scanner) {
        User user = new User();
        System.out.println("Enter first name:");
        user.setFirstName(scanner.nextLine());
        System.out.println("Enter last name:");
        user.setLastName(scanner.nextLine());
        System.out.println("Enter user email:");
        user.setEmail(scanner.nextLine());
        System.out.println("Enter user role(choose from available):");
        System.out.println(Arrays.toString(User.Role.values()));
        user.setRole(User.Role.valueOf(scanner.nextLine().toUpperCase()));
        System.out.println("A new user has been created:");
        System.out.println(userService.create(user));
    }

    private void deleteById(String command) {
        String[] mas = command.trim().split(" ");
        Long id = Long.parseLong(mas[1]);
        userService.delete(id);
    }

    private void getUserById(String command) {
        String[] mas = command.trim().split(" ");
        Long id = Long.parseLong(mas[1]);
        User user = userService.getUserById(id);
        System.out.println(user);
    }

    private void getAll() {
        List<User> users = userService.getAll();
        users.forEach(System.out::println);
    }
}
