package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;
import org.springframework.context.ApplicationEvent;

public abstract class AbstractEmployeeEvent extends ApplicationEvent {
    protected Employee employee;

    public AbstractEmployeeEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
