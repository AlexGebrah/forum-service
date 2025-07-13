package telran.java58.accounting.dto;

import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRolesDto {
private String login;
@Singular
private Set<String> roles;
}
