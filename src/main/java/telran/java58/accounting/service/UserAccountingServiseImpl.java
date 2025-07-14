package telran.java58.accounting.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java58.accounting.dao.UserAccountingRepository;
import telran.java58.accounting.dto.NewRolesDto;
import telran.java58.accounting.dto.UserCreationDto;
import telran.java58.accounting.dto.UserDto;
import telran.java58.accounting.dto.UserUpdateDto;
import telran.java58.accounting.dto.exceptions.UserAlreadyExistsException;
import telran.java58.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountingServiseImpl implements AccountingService{
    private  final UserAccountingRepository accountingRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto register(UserCreationDto userCreationDto) {
        if (accountingRepository.existsById(userCreationDto.getLogin())) {
            throw new UserAlreadyExistsException(userCreationDto.getLogin());
        }

        UserAccount userAccount = modelMapper.map(userCreationDto, UserAccount.class);
        userAccount.addRole("USER");
        accountingRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto login(String login) {
        UserAccount userAccount = accountingRepository.findById(login).orElseThrow();
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String login) {
        return null;
    }

    @Override
    public UserDto updateUser(String login, UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public NewRolesDto addRole(String login, String role) {
        return null;
    }

    @Override
    public NewRolesDto deleteRole(String login, String role) {
        return null;
    }

    @Override
    public void changePassword(String password, String newPassword) {

    }

    @Override
    public UserDto getUser(String login) {
        return null;
    }
}
