package com.example.sigurddemo.service;

import com.example.sigurddemo.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Optional<Department> save(Department dep);

    Optional<Department> getRandomDepartment();

}
