package co.uk.b.antanas.springboot.restfulwebservices.jpa;

import co.uk.b.antanas.springboot.restfulwebservices.exception.PostNotFoundException;
import co.uk.b.antanas.springboot.restfulwebservices.exception.UserNotFoundException;
import co.uk.b.antanas.springboot.restfulwebservices.user.Post;
import co.uk.b.antanas.springboot.restfulwebservices.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class UserJpaResource {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:"+id);
        }

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        EntityModel<User> userEntityModel = EntityModel.of(user.get());
        userEntityModel.add(link.withRel("all-users"));

        return userEntityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser =  userRepository.save(user);
        // /users/4 => /users/{id}, user.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:"+userId);
        }
        return user.get().getPosts();
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post retrievePostForUser(@PathVariable int userId, @PathVariable int postId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:"+userId);
        }

        Optional<Post> post =  user.get().getPosts().stream().filter(p -> p.getId()==postId).findFirst();
        if (post.isEmpty()){
            throw new PostNotFoundException("userId:"+userId + " : " + "postId:"+postId );
        }

        return post.get();
    }

    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int userId, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:"+userId);
        }
        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }



}
