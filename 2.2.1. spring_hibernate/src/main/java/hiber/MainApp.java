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

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("Toyota", 530);
      Car car2 = new Car("Subary", 420);
      Car car3 = new Car("Nissan", 110);
      Car car4 = new Car("UAZ", 666);
      User user1 = new User("Ilove", "Toyotas", "toyotavod@mail.ru", car1);
      User user2 = new User("Ilove", "Subaras", "subaruvod@mail.ru", car2);
      User user3 = new User("Ilove", "Nissanas", "nissanovod@mail.ru", car3);
      User user4 = new User("Ilove", "UAZas", "russkiy@mail.ru", car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      User result = userService.findByCar(car1);
      System.out.println("Owner of car: " + result.getUserCar());
      System.out.println("Name: " + result.getFirstName() + " " + result.getLastName());
      System.out.println("Email: " + result.getEmail());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Car = " + user.getUserCar());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
