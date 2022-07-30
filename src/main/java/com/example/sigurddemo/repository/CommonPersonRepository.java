package com.example.sigurddemo.repository;

import com.example.sigurddemo.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommonPersonRepository<V extends Person> extends CrudRepository<V, Long> {
}
