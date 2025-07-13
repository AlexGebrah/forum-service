package telran.java58.accounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "login")
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    @Setter
    private  String login;
    @Setter
    private  String firstName;
    @Setter
    private String lastName;
    @Setter
    private String password;
    private Set<String> roles = new HashSet<>();

    public User(String login, String firstName, String password, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = new HashSet<>(Set.of("USER"));
    }

    public  void addRole (String role) {
        roles.add(role.toUpperCase());
    }
}
