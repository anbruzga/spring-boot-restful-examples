package co.uk.b.antanas.springboot.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {


    private static int usersCount = 0;

    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(26)));
        users.add(new User(++usersCount, "John", LocalDate.now().minusYears(64)));
    }

    public List<User> findAll() {
        return users;
    }


    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> matchingId = user -> user.getId().equals(id);
        return users.stream().filter(matchingId).findFirst().orElse(null);
    }

    public boolean deleteById(int id) {
        Predicate<? super User> matchingId = user -> user.getId().equals(id);
        return users.removeIf(matchingId);
    }

}
