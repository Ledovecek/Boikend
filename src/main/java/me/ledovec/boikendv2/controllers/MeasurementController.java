package me.ledovec.boikendv2.controllers;

import com.google.common.collect.Lists;
import me.ledovec.boikendv2.Constants;
import me.ledovec.boikendv2.entities.Measurement;
import me.ledovec.boikendv2.entities.Voyage;
import me.ledovec.boikendv2.enums.BuoyResult;
import me.ledovec.boikendv2.enums.Result;
import me.ledovec.boikendv2.objects.MeasurementParameters;
import me.ledovec.boikendv2.repositories.MeasurementsRepository;
import me.ledovec.boikendv2.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/measurements")
public class MeasurementController {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private MeasurementsRepository measurementsRepository;

    @PostMapping("write")
    public Result write(@RequestBody MeasurementParameters measurementParameters) {
        long now = System.currentTimeMillis();
        Measurement measurement = new Measurement(Constants.DEFAULT_ID,
                measurementParameters.getUnit(), measurementParameters.getValue(), now);
        measurementsRepository.saveAndFlush(measurement);
        return BuoyResult.SUCCESSFUL;
    }

    @PostMapping("writeMultiple")
    public Result writeMultiple(@RequestBody List<MeasurementParameters> measurementParameters) {
        measurementParameters.forEach(this::write);
        return BuoyResult.SUCCESSFUL;
    }

    @GetMapping
    public List<Measurement> get(@RequestParam long voyageId) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(voyageId);
        if (voyageOptional.isEmpty()) {
            return Lists.newArrayList();
        }
        Voyage voyage = voyageOptional.get();
        long beginDate = voyage.getBeginDate();
        long endDate = voyage.getEndDate();
        List<Measurement> measurements = measurementsRepository.findAll();
        if (endDate == -1) {
            return measurements.stream().filter(m -> m.getDate() >= beginDate).collect(Collectors.toList());
        }
        return measurements.stream().filter(m -> m.getDate() >= beginDate && m.getDate() <= endDate).collect(Collectors.toList());
    }

}
