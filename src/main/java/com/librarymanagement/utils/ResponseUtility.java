package com.librarymanagement.utils;

import com.librarymanagement.models.responses.DefaultResponse;

public class ResponseUtility {
    public static DefaultResponse successResponseWithBody(String statusCode, Object responseBody) {
        return new DefaultResponse(Boolean.TRUE, statusCode, responseBody);

    }

    public static DefaultResponse failureResponseWithBody(String statusCode, Object responseBody) {
        return new DefaultResponse(Boolean.FALSE, statusCode, responseBody);
    }

    public static DefaultResponse successResponseWithMessageAndBody(String statusCode, String message, Object responseBody) {
        return new DefaultResponse(Boolean.TRUE, statusCode, message, responseBody);

    }

    public static DefaultResponse failureResponseWithMessageAndBody(String statusCode, String message, Object responseBody) {
        return new DefaultResponse(Boolean.FALSE, statusCode, message, responseBody);

    }

    public static DefaultResponse successResponseWithMessage(String statusCode, String message) {
        return new DefaultResponse(Boolean.TRUE, statusCode, message);
    }

    public static DefaultResponse failureResponseWithMessage(String statusCode, String message) {
        return new DefaultResponse(Boolean.FALSE, statusCode, message);
    }


}
