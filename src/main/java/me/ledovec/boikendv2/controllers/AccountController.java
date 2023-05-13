package me.ledovec.boikendv2.controllers;

import me.ledovec.boikendv2.entities.Account;
import me.ledovec.boikendv2.enums.AuthResult;
import me.ledovec.boikendv2.repositories.AccountRepository;
import me.ledovec.boikendv2.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/authentication")
public class AccountController {

    private final AccountRepository accountRepository;
    private final Security security;

    @Autowired
    public AccountController(AccountRepository accountRepository, Security security) {
        this.accountRepository = accountRepository;
        this.security = security;
    }

    @PostMapping(path = "register")
    public AuthResult register(@RequestBody Account account) {
        Account acc = accountRepository.findAccountByName(account.getName());
        if (acc != null) {
            return AuthResult.ALREADY_REGISTERED;
        }
        accountRepository.save(account);
        return AuthResult.SUCCESSFUL;
    }

    @PostMapping(path = "login")
    public Pair<AuthResult, String> login(@RequestBody Account account) {
        Account acc = accountRepository.findAccountByName(account.getName());
        if (acc == null) {
            return Pair.of(AuthResult.NOT_REGISTERED, "");
        }
        String password = account.getPassword();
        String secret = acc.getPassword();
        boolean matches = security.passwordMatches(password, secret);
        if (matches) {
            return Pair.of(AuthResult.SUCCESSFUL, UUID.randomUUID().toString());
        }
        return Pair.of(AuthResult.PASSWORD_MISMATCH, "");
    }

}
