package me.ledovec.boikendv2.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.ledovec.boikendv2.entities.Unit;

@AllArgsConstructor
@Getter
public class MeasurementParameters {

    private long voyageId;
    private Unit unit;
    private long value;
    private long date;

}
