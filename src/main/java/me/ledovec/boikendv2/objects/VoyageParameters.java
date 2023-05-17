package me.ledovec.boikendv2.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoyageParameters {

    private String buoyCode;
    private String name;
    private String description;
    private long beginTime;
    private long endTime;

}
