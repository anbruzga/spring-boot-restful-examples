package co.uk.b.antanas.springboot.restfulwebservices.versioning;


import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Name {
    private String firstName;
    private String lastName;
}
