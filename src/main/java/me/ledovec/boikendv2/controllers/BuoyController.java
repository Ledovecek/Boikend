package me.ledovec.boikendv2.controllers;

import me.ledovec.boikendv2.entities.Account;
import me.ledovec.boikendv2.entities.Buoy;
import me.ledovec.boikendv2.enums.AuthResult;
import me.ledovec.boikendv2.enums.BuoyResult;
import me.ledovec.boikendv2.enums.Result;
import me.ledovec.boikendv2.repositories.AccountRepository;
import me.ledovec.boikendv2.repositories.BuoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "api/buoys")
public class BuoyController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BuoyRepository buoyRepository;

    @PostMapping(path = "append")
    public Result appendBuoy(@RequestParam String accountName, @RequestParam String buoyCode) {
        Account acc = accountRepository.findAccountByName(accountName);
        Buoy buoy = buoyRepository.findBuoyByCode(buoyCode);
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

    @PostMapping("create")
    public Pair<Result, Buoy> createBuoy(@RequestBody Buoy buoy) {
        Buoy save = buoyRepository.save(buoy);
        return Pair.of(BuoyResult.SUCCESSFUL, save);
    }

    @GetMapping("get")
    public Pair<Result, Buoy> getBuoy(@RequestParam String code) {
        Buoy buoyByCode = buoyRepository.findBuoyByCode(code);
        if (buoyByCode != null) {
            return Pair.of(BuoyResult.SUCCESSFUL, buoyByCode);
        }
        return Pair.of(BuoyResult.NOT_FOUND, null);
    }

    @GetMapping
    public Set<Buoy> getBuoys(@RequestParam long userId) {
        Account referenceById = accountRepository.getReferenceById(userId);
        if (referenceById != null) {
            return referenceById.getBuoys();
        }
        return null;
    }

}
