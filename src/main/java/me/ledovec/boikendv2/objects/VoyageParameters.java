package me.ledovec.boikendv2.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoyageParameters {

    private String buoyCode;
    private String name;
    private String description;
    private long beginTime;
    private long endTime;

}
