package me.ledovec.boikendv2.controllers;

import me.ledovec.boikendv2.Constants;
import me.ledovec.boikendv2.entities.Account;
import me.ledovec.boikendv2.entities.Buoy;
import me.ledovec.boikendv2.entities.Voyage;
import me.ledovec.boikendv2.enums.AuthResult;
import me.ledovec.boikendv2.enums.BuoyResult;
import me.ledovec.boikendv2.enums.Result;
import me.ledovec.boikendv2.objects.VoyageParameters;
import me.ledovec.boikendv2.repositories.AccountRepository;
import me.ledovec.boikendv2.repositories.BuoyRepository;
import me.ledovec.boikendv2.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/voyages")
public class VoyageController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private BuoyRepository buoyRepository;

    @PostMapping(path = "new")
    public Result newVoyage(@RequestBody VoyageParameters voyageParameters) {
        Account acc = accountRepository.findAccountByName(voyageParameters.getAccountName());
        Buoy buoy = buoyRepository.findBuoyByCode(voyageParameters.getBuoyCode());
        if (acc == null) {
            return AuthResult.NOT_REGISTERED;
        }
        if (buoy == null) {
            return BuoyResult.NOT_FOUND;
        }
        Voyage voyage = new Voyage(Constants.DEFAULT_ID, buoy);
        voyageRepository.save(voyage);
        return BuoyResult.SUCCESSFUL;
    }

}
