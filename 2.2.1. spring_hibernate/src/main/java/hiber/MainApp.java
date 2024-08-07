package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        //userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        //userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        // userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        //userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        User oleg = new User("Oleg", "Popov", "userOleg@mail.ru");
        User anton = new User("Anton", "Zadov", "userAnton@mail.ru");
        User jack = new User("Jack", "Piston", "userJack@mail.ru");
        Car bmv = new Car("BMV", 540);
        Car lada = new Car("Lada", 2107);
        Car volvo = new Car("Volvo", 740);

        userService.add(oleg.setCar(bmv).setUser(oleg));
        userService.add(anton.setCar(lada).setUser(anton));
        userService.add(jack.setCar(volvo).setUser(jack));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();

        }
        System.out.println(userService.getUserByCar("Lada", 2107));
        context.close();
    }
}
