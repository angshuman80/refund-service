package com.turbotax.refundservice.model;

public class ErrorCode {
    public static final String INVALID_USER_ID = "ERR001";
    public static final String RESOURCE_NOT_FOUND = "ERR002";
    public static final String INTERNAL_SERVER_ERROR = "ERR003";
    public static final String BAD_REQUEST = "ERR004";

    private ErrorCode() {
        // Private constructor to prevent instantiation
    }
}
