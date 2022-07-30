package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;

import java.util.Date;

public class FiringEmployeeEvent extends AbstractEmployeeEvent{

    public FiringEmployeeEvent(Object source, Employee employee, Date date) {
        super(source, employee, date);
    }

    @Override
    public String toString() {
        return String.format("Worker with id %d has been fired", employee.getId());
    }
}
