package com.example.sigurddemo.exception;

public class SqlTransactionException extends Exception {
    private final String message;
    private final Object obj;

    public SqlTransactionException(String message, Object obj) {
        super();
        this.message = message;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return message + obj;
    }
}
