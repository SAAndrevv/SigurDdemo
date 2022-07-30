package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;

import java.util.Date;

public class HiringEmployeeEvent extends AbstractEmployeeEvent{
    public HiringEmployeeEvent(Object source, Employee employee, Date date) {
        super(source, employee, date);
    }

    @Override
    public String toString() {
        return String.format("Worker with id %d has been hired", employee.getId());
    }
}
