package com.example.sigurddemo.repository;

import com.example.sigurddemo.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommonPersonRepository<V extends Person> extends CrudRepository<V, Long> {

    /*@Query(value = "SELECT p FROM Person p WHERE p.card = ?1")
    Optional<V> findPersonByCard(byte [] card);*/

    @Query(value = "SELECT * FROM person ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<V> findRandomPerson();


}
