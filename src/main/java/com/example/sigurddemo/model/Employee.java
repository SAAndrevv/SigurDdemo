package com.example.sigurddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {

    @Temporal(TemporalType.DATE)
    @Column(name = "HIRE_TIME")
    private Date hireTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "FIRED_TIME")
    private Date firedTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    private Department department;
}
