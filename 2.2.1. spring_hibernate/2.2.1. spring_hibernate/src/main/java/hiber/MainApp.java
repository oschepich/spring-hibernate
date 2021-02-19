package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            UserService userService = context.getBean(UserService.class);
//создание узеров с машинами
            userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Volga", 2410)));
            userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Volga", 2109)));
            userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Volga", 3110)));
            userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Volga", 3300)));

//вывод в консоль всех столбцов узеров с машинами
            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println("model = " + user.getUserCar().getModel());
                System.out.println("series = " + user.getUserCar().getSeries());
                System.out.println();
            }

//вывод в консоль всех узеров владеющих машиной по ее модели и серии.
            try {
                User user = userService.getUserCar("Volga", 2109);
                System.out.println(user.toString());
                System.out.println();
                User user1 = userService.getUserCar("Volga", 31105);

            }
            catch (NoResultException e){
                System.out.println("Пользователя (User-а) с автомобилем Volga, серии 31105 не существует");
            }

        }
    }
}
