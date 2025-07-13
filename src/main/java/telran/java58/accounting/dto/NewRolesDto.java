package telran.java58.accounting.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRolesDto {
private String login;
@Singular
private List<String> roles;
}
