package me.ledovec.boikendv2.controllers;

import com.google.common.collect.Lists;
import me.ledovec.boikendv2.Constants;
import me.ledovec.boikendv2.entities.Buoy;
import me.ledovec.boikendv2.entities.Measurement;
import me.ledovec.boikendv2.entities.Voyage;
import me.ledovec.boikendv2.enums.BuoyResult;
import me.ledovec.boikendv2.enums.Result;
import me.ledovec.boikendv2.objects.VoyageParameters;
import me.ledovec.boikendv2.repositories.BuoyRepository;
import me.ledovec.boikendv2.repositories.MeasurementsRepository;
import me.ledovec.boikendv2.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private BuoyRepository buoyRepository;

    @Autowired
    private MeasurementsRepository measurementsRepository;

    @PostMapping(path = "create")
    public Pair<Result, Voyage> newVoyage(@RequestBody VoyageParameters voyageParameters) {
        Buoy buoy = buoyRepository.findBuoyByCode(voyageParameters.getBuoyCode());
        long beginTime = voyageParameters.getBeginTime();
        long endTime = voyageParameters.getEndTime();
        if (buoy == null) {
            return Pair.of(BuoyResult.NOT_FOUND, null);
        }
        Voyage voyage = new Voyage(Constants.DEFAULT_ID, voyageParameters.getName(), voyageParameters.getDescription(), beginTime, endTime, buoy);
        Voyage save = voyageRepository.saveAndFlush(voyage);
        return Pair.of(BuoyResult.SUCCESSFUL, save);
    }

    @GetMapping
    public Pair<Result, List<Voyage>> getVoyages(@RequestParam String buoyCode) {
        Buoy buoy = buoyRepository.findBuoyByCode(buoyCode);
        if (buoy == null) {
            return Pair.of(BuoyResult.NOT_FOUND, Lists.newArrayList());
        }
        return Pair.of(BuoyResult.SUCCESSFUL, voyageRepository.findAllByBuoy(buoy));
    }

    @PostMapping(path = "stop")
    public void stopVoyage(@RequestParam long voyageId, @RequestParam long endDate) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(voyageId);
        if (voyageOptional.isPresent()) {
            Voyage voyage = voyageOptional.get();
            voyage.setEndDate(endDate);
            voyageRepository.saveAndFlush(voyage);
        }
    }

}
