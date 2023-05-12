package me.ledovec.boikendv2.repositories;

import me.ledovec.boikendv2.entities.Buoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuoyRepository extends JpaRepository<Buoy, Long> {

    Buoy findBuoyByCode(String code);

}
