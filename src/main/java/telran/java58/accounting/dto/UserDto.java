package telran.java58.accounting.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private  String login;
    private  String firstName;
    private String lastName;
    @Singular
    private List<String> roles;
}
