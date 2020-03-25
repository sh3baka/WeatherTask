package com.practice.weatherapp.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String getMessage;

    public ExceptionResponse(Date timestamp, String message, String getMessage) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.getMessage = getMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getGetMessage() {
        return getMessage;
    }
}
