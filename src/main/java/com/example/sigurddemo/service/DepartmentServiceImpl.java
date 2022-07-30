package com.example.sigurddemo.service;

import com.example.sigurddemo.model.Department;
import com.example.sigurddemo.repository.DepartmentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
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
    public Optional<Department> getRandomDepartment() {
        return Optional.of(repository.findRandomDepartment());
    }

/*    @PostConstruct
    private void loadDate() {
        repository.saveAll(
                Arrays.asList(new Department(1L,"First"),
                        new Department(4L, "Second"),
                        new Department(3L,"Third"),
                        new Department(4L, "Fifth"),
                        new Department(6L, "Sixth"),
                        new Department(7L, "Seventh"),
                        new Department(8L, "Eighth"),
                        new Department(9L, "Ninth"),
                        new Department(10L, "Tenth")

                ) );
    }*/
}

