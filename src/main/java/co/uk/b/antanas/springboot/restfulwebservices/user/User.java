package co.uk.b.antanas.springboot.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name="user_details")
public class User {

    @Id
    @GeneratedValue
    @NonNull
    private Integer id;

    @Size(min=2, message = "Name should have at least 2 characters")
//    @JsonProperty("user_name")
    @NonNull
    private String name;

    @Past(message = "Birth Date should be in the past")
    @NonNull
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
}
