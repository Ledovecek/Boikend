package me.ledovec.boikendv2.controllers;

import me.ledovec.boikendv2.Constants;
import me.ledovec.boikendv2.entities.Measurement;
import me.ledovec.boikendv2.entities.Voyage;
import me.ledovec.boikendv2.enums.BuoyResult;
import me.ledovec.boikendv2.enums.Result;
import me.ledovec.boikendv2.objects.MeasurementParameters;
import me.ledovec.boikendv2.repositories.MeasurementsRepository;
import me.ledovec.boikendv2.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/measurements")
public class MeasurementController {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private MeasurementsRepository measurementsRepository;

    @PostMapping("write")
    public Result write(MeasurementParameters measurementParameters) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(measurementParameters.getVoyageId());
        if (voyageOptional.isPresent()) {
            Voyage voyage = voyageOptional.get();
            Measurement measurement = new Measurement(Constants.DEFAULT_ID,
                    voyage, measurementParameters.getUnit(), measurementParameters.getValue(), measurementParameters.getDate());
            measurementsRepository.save(measurement);
            return BuoyResult.SUCCESSFUL;
        }
        return BuoyResult.NOT_FOUND;
    }

}
