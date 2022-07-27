package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;

public class HiringEmployeeEvent extends AbstractEmployeeEvent{
    public HiringEmployeeEvent(Object source, Employee employee) {
        super(source, employee);
    }

    @Override
    public String toString() {
        return String.format("Worker with id %d has been hired", employee.getId());
    }
}
