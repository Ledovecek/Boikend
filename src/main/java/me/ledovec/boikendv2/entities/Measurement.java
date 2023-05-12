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

    @Column @OneToOne
    private Voyage voyage;

    @OneToOne
    @Column private Unit unit;

    @Column private long value;

    @Column private long date;

}
