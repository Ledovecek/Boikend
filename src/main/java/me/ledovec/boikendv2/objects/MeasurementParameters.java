package me.ledovec.boikendv2.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeasurementParameters {

    private String buoyCode;
    private String unit;
    private double value;
    private long date;

}
