package telran.java58.accounting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor@EqualsAndHashCode(of = {"login"})
@Builder
@Document(collection = "users")
public class UserAccount {
    @Id
    private String login;
    @Setter
    private String password;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Singular
    private Set<Role> roles = new HashSet<>();

    public UserAccount(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public  boolean addRole(String role) {
        return roles.add(Role.valueOf(role.toUpperCase()));
    }

    public  boolean removeRole(String role) {
        return roles.remove(Role.valueOf(role.toUpperCase()));
    }
}
