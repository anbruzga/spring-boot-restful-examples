package co.uk.b.antanas.springboot.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
//@JsonIgnoreProperties({"password","userEmail"}) // alternative1
// @JsonIgnoreProperties("password") // alternative2
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String userNickname;
    private String userEmail;

    //@JsonIgnore // alternative3 preferred
    private String password;
}
