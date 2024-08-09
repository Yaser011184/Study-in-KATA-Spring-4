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

        User oleg = new User("Oleg", "Popov", "userOleg@mail.ru");
        User anton = new User("Anton", "Zadov", "userAnton@mail.ru");
        User jack = new User("Jack", "Piston", "userJack@mail.ru");
        Car bmv = new Car("BMV", 540);
        Car lada = new Car("Lada", 2107);
        Car volvo = new Car("Volvo", 740);

        oleg.setCar(bmv);
        anton.setCar(lada);
        jack.setCar(volvo);
        userService.add(oleg);
        userService.add(anton);
        userService.add(jack);

        for (User user : userService.listUsers()) {
            System.out.println(user.toString());
        }
        System.out.println(userService.getUserByCar("Lada", 2107));
        context.close();
    }
}
