package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "voyages")
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private long id;

    @OneToOne @Column
    private Buoy buoy;

}
