package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "buyois")
@Getter
public class Buoy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id;

    @Column private String code;

}
