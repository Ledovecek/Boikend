package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buyois")
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Buoy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id;

    @Column private String nickname;

    @Column private String code;

}
