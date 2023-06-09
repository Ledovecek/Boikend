package me.ledovec.boikendv2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voyages")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Voyage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private long id;

    @Column private String name;

    @Column private String description;

    @Column private long beginDate;

    @Column private long endDate;

    @OneToOne
    private Buoy buoy;

}
