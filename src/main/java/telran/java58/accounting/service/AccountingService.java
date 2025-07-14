package telran.java58.accounting.service;

import telran.java58.accounting.dto.NewRolesDto;
import telran.java58.accounting.dto.UserCreationDto;
import telran.java58.accounting.dto.UserDto;
import telran.java58.accounting.dto.UserUpdateDto;

public interface AccountingService {
    UserDto register (UserCreationDto userCreationDto);

    UserDto login (String login);

    UserDto deleteUser (String login);

    UserDto updateUser (String login, UserUpdateDto userUpdateDto);

    NewRolesDto addRole (String login, String role);

    NewRolesDto deleteRole (String login, String role);

    void changePassword (String password, String newPassword);

    UserDto getUser (String login);
}
