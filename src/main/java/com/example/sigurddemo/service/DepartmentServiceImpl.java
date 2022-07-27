package com.example.sigurddemo.service;

import com.example.sigurddemo.model.Department;
import com.example.sigurddemo.repository.DepartmentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @NonNull
    private DepartmentRepository repository;

    @Override
    @Transactional
    public Optional<Department> save(Department dep) {
        return Optional.of(repository.save(dep));
    }

    @Override
    public List<Department> get() {
        List<Department> lst = new ArrayList<>();
        repository.findAll().forEach(lst::add);
        return lst;
    }

    public Optional<Department> getOne() {
        return Optional.of(get().get(0));
    }

    @Override
    public Optional<Department> getRandomDepartment() {
        return Optional.of(repository.findRandomDepartment());
    }

/*    @PostConstruct
    private void loadDate() {
        repository.save(new Department(1L, "1"));
        repository.save(new Department(2L, "2"));
        repository.save(new Department(3L, "3"));
        repository.save(new Department(4L, "4"));
        repository.save(new Department(5L, "5"));
        repository.save(new Department(6L, "6"));
        repository.save(new Department(7L, "7"));
    }*/
}

