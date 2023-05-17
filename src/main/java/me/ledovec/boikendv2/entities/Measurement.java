package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "measurements")
@AllArgsConstructor @NoArgsConstructor
public class Measurement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private long id;

    @Column
    private String unit;

    @Column private double value;

    @Column private long date;

}
