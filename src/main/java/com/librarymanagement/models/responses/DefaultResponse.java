package com.librarymanagement.models.responses;

public class DefaultResponse {
    private final Boolean success;
    private final String responseCode;
    private String message;
    private Object responseBody;


    public DefaultResponse(Boolean success, String message, String responseCode, Object responseBody) {
        this.success = success;
        this.message = message;
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public DefaultResponse(Boolean success, String message, String responseCode) {
        this.success = success;
        this.message = message;
        this.responseCode = responseCode;
    }

    public DefaultResponse(Boolean success, String responseCode, Object responseBody) {
        this.success = success;
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }
}
