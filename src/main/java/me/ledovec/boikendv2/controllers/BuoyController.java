package me.ledovec.boikendv2.controllers;

import me.ledovec.boikendv2.entities.Account;
import me.ledovec.boikendv2.entities.Buoy;
import me.ledovec.boikendv2.enums.AuthResult;
import me.ledovec.boikendv2.enums.BuoyResult;
import me.ledovec.boikendv2.enums.Result;
import me.ledovec.boikendv2.repositories.AccountRepository;
import me.ledovec.boikendv2.repositories.BuoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/buoys")
public class BuoyController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BuoyRepository buoyRepository;

    @PostMapping(path = "append")
    public Result appendBuoy(@RequestBody Account account, @RequestBody Buoy buoyRef) {
        Account acc = accountRepository.findAccountByName(account.getName());
        Buoy buoy = buoyRepository.findBuoyByCode(buoyRef.getCode());
        if (acc == null) {
            return AuthResult.NOT_REGISTERED;
        }
        if (buoy == null) {
            return BuoyResult.NOT_FOUND;
        }
        acc.getBuoys().add(buoy);
        accountRepository.save(acc);
        return BuoyResult.SUCCESSFUL;
    }

}
