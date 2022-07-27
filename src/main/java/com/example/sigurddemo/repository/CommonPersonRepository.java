package com.example.sigurddemo.repository;

import com.example.sigurddemo.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface CommonPersonRepository<V extends Person> extends CrudRepository<V, Long> {
}
