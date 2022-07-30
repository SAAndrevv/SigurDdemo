package com.example.sigurddemo.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class DateEvent extends ApplicationEvent {
    private final Date date;

    public DateEvent(Object source, Date date) {
        super(source);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Next day " + date;
    }
}
