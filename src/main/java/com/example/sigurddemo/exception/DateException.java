package com.example.sigurddemo.exception;

import java.util.Date;

public class DateException extends Exception {
    private final Date date;

    public DateException(Date date) {
        super();
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date cannot exceed 12/31/2022. Now date:  " + date;
    }
}
