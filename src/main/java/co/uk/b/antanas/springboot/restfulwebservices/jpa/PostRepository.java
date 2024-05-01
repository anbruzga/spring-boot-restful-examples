package co.uk.b.antanas.springboot.restfulwebservices.jpa;

import co.uk.b.antanas.springboot.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Integer> {
}
