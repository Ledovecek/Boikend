package me.ledovec.boikendv2.repositories;

import me.ledovec.boikendv2.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByName(String name);

}
