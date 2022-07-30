package com.example.sigurddemo.service;

import com.example.sigurddemo.model.Person;
import com.example.sigurddemo.repository.CommonPersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractCommonPersonService<E extends Person, R extends CommonPersonRepository<E>>
        implements CommonPersonService<E> {

    @NonNull
    protected final R repository;
    @Transactional
    public Optional<E> save(E person){
        return Optional.of(repository.save(person));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<E> get(Long id) {
        return repository.findById(id);
    }
}
