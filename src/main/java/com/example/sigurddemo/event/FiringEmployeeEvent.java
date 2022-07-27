package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;

public class FiringEmployeeEvent extends AbstractEmployeeEvent{

    public FiringEmployeeEvent(Object source, Employee employee) {
        super(source, employee);
    }

    @Override
    public String toString() {
        return String.format("Worker with id %d has been fired", employee.getId());
    }
}
