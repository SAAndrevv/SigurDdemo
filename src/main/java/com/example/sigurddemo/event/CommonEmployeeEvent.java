package com.example.sigurddemo.event;

import com.example.sigurddemo.model.Employee;

import java.util.Date;

public interface CommonEmployeeEvent {

    Employee getEmployee();

    Date getDate();
}
