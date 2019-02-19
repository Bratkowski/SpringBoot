package com.bratkowski.booklibary.restController;

public class ExceptionDetails {
    private String exceptionName;
    private String message;



    public ExceptionDetails(String exceptionName, String message) {
        this.exceptionName = exceptionName;
        this.message = message;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionmessage() {
        return message;
    }

    public void setExceptionmessage(String message) {
        this.message = message;
    }
}
