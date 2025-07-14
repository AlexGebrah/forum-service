package telran.java58.accounting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java58.accounting.dto.NewRolesDto;
import telran.java58.accounting.dto.UserCreationDto;
import telran.java58.accounting.dto.UserDto;
import telran.java58.accounting.dto.UserUpdateDto;
import telran.java58.accounting.service.AccountingService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountingController {
private final AccountingService accountingService;

@PostMapping("/register")
    public UserDto register(@RequestBody UserCreationDto userCreationDto) {
        return accountingService.register(userCreationDto);
    }

    @GetMapping("/login")
    public UserDto login(Principal principal) {
        return accountingService.login(principal.getName());
    }

    @DeleteMapping("/user/{login}")
    public UserDto deleteUser(@PathVariable String login) {
        return accountingService.deleteUser(login);
    }

    @PatchMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UserUpdateDto userUpdateDto) {
        return accountingService.updateUser(login, userUpdateDto);
}

    @PatchMapping("/user/{login}/role/{role}")
    public NewRolesDto addRole(@PathVariable String login, @PathVariable String role) {
        return accountingService.addRole(login, role);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public NewRolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
        return accountingService.deleteRole(login, role);
    }

    @PatchMapping("/password")
    public void changePassword(Principal principal, @RequestHeader("X-password") String newPassword) {
        accountingService.changePassword(principal.getName(), newPassword);
    }

    @GetMapping("/user/{login}")
    public UserDto getUser(@PathVariable String login) {
        return accountingService.getUser(login);
    }
}
