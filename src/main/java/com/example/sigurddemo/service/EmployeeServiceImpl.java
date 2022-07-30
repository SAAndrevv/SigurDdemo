package com.example.sigurddemo.service;

import com.example.sigurddemo.model.Employee;
import com.example.sigurddemo.repository.EmployeeRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployeeServiceImpl extends AbstractCommonPersonService<Employee, EmployeeRepository>{

    private final Random rnd = new Random();

    public EmployeeServiceImpl(@NonNull EmployeeRepository repository) {
        super(repository);
    }

    public Iterable<Employee> getSpecificQuantityRandomEmployee() {
        int randCountEmpl = rnd.nextInt(2) + 1;
        return repository.findSpecificQuantityEmployee(randCountEmpl);
    }

    public Employee getEmployeeById(Long id) {
        return repository.findEmployeeById(id).orElse(null);
    }

}
