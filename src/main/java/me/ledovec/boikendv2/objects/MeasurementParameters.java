package me.ledovec.boikendv2.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class MeasurementParameters {

    private String buoyCode;
    private String unit;
    private double value;

}
