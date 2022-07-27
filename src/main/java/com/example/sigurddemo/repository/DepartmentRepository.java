package com.example.sigurddemo.repository;

import com.example.sigurddemo.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "SELECT * FROM department ORDER BY rand() LIMIT 1", nativeQuery = true)
    Department findRandomDepartment();
}
