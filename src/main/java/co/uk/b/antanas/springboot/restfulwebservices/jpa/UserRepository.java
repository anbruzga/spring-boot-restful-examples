package co.uk.b.antanas.springboot.restfulwebservices.jpa;

import co.uk.b.antanas.springboot.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {
}
