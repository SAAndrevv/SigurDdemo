package com.example.sigurddemo.service;

import com.example.sigurddemo.model.Person;

import java.util.Optional;

public interface CommonPersonService<V extends Person> {

    Optional<V> save(V person);

    Optional<V> get(Long id);

    void  delete(Long id);
}
