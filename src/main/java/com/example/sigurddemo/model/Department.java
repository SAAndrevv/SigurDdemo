package com.example.sigurddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", columnDefinition = "VARCHAR(32)")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
