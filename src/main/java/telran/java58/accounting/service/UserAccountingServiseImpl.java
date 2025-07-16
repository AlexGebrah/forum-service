package telran.java58.accounting.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
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
public class UserAccountingServiseImpl implements AccountingService, CommandLineRunner {
    private  final UserAccountingRepository userAccountingRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto register(UserCreationDto userCreationDto) {
        if (userAccountingRepository.existsById(userCreationDto.getLogin())) {
            throw new UserAlreadyExistsException(userCreationDto.getLogin());
        }

        UserAccount userAccount = modelMapper.map(userCreationDto, UserAccount.class);
        userAccount.addRole("USER");
        String password = BCrypt.hashpw(userCreationDto.getPassword(), BCrypt.gensalt());
        userAccount.setPassword(password);
        userAccountingRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto login(String login) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String login) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        userAccountingRepository.delete(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UserUpdateDto userUpdateDto) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        if(userUpdateDto.getFirstName() != null) {
            userAccount.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            userAccount.setLastName(userUpdateDto.getLastName());
        }
        return modelMapper.map(userAccountingRepository.save(userAccount), UserDto.class);
    }

    @Override
    public NewRolesDto addRole(String login, String role) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        userAccount.addRole(role);
        userAccountingRepository.save(userAccount);
        return modelMapper.map(userAccount, NewRolesDto.class);
    }

    @Override
    public NewRolesDto deleteRole(String login, String role) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        userAccount.removeRole(role);
        userAccountingRepository.save(userAccount);
        return modelMapper.map(userAccount, NewRolesDto.class);
    }

    @Override
    public void changePassword(String login, String newPassword) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        userAccount.setPassword(hashedPassword);
        userAccountingRepository.save(userAccount);
    }

    @Override
    public UserDto getUser(String login) {
        UserAccount userAccount = userAccountingRepository.findById(login).orElseThrow();
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userAccountingRepository.existsById("admin")) {
            UserAccount admin = new UserAccount.builder()
                    .login("admin")
                    .password(BCrypt.checkpw())
        }
    }
}
