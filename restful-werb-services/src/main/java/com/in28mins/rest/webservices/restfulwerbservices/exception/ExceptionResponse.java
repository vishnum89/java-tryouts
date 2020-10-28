package com.in28mins.rest.webservices.restfulwerbservices.exception;

import java.util.Date;

public class ExceptionResponse {

    Date timestamp;

    String message;

    String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
