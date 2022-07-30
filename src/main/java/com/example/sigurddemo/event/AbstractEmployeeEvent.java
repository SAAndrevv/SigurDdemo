package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

public abstract class AbstractEmployeeEvent extends ApplicationEvent implements CommonEmployeeEvent {
    protected Employee employee;
    private Date date;

    public AbstractEmployeeEvent(Object source, Employee employee, Date date) {
        super(source);
        this.employee = employee;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Employee getEmployee() {
        return employee;
    }
}
