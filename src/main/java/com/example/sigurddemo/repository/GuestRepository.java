package com.example.sigurddemo.repository;

import com.example.sigurddemo.model.Guest;
import com.example.sigurddemo.model.Person;

import java.util.Optional;

public interface GuestRepository extends CommonPersonRepository<Guest> {

    Optional<Guest> findGuestByPerson(Person person);
}
