package com.example.sigurddemo.repository;

import com.example.sigurddemo.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends CommonPersonRepository<Employee> {


    @Query(value = "SELECT e.*, p.*, 1 as clazz_  FROM employee e LEFT JOIN person p ON e.id = p.id" +
            " WHERE fired_time IS NULL" +
            " ORDER BY rand() LIMIT ?1", nativeQuery = true)
    List<Employee> findSpecificQuantityEmployee(int count);

}
