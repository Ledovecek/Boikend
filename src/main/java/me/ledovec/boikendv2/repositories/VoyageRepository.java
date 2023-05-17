package me.ledovec.boikendv2.repositories;

import me.ledovec.boikendv2.entities.Buoy;
import me.ledovec.boikendv2.entities.Voyage;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {

    List<Voyage> findAllByBuoy(Buoy buoy);

}
