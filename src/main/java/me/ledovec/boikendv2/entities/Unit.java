package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private long id;

    @Column private String name;

}
