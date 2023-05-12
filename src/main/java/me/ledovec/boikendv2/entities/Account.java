package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id;

    @Column String name;

    @Column String password;

    @OneToMany @Column
    private Set<Buoy> buoys;

}
