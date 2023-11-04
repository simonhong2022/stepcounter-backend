package com.exorlive.stepcounter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST Wrong Request from param
    INVALID_PARAMETER(400, "check your parameter value"),

    //404 NOT_FOUND  Wrong Resource Access
    USER_NOT_FOUND(404, "User ID not exist"),
    BODY_NOT_FOUND(404, "Body ID not exist"),
    SESSION_NOT_FOUND(404, "Session ID not exist"),

    SAVED_SESSION_NOT_FOUND(404, "Session is not in DB"),
    SAVED_BODY_NOT_FOUND(404, "Body is not in DB"),
    SAVED_USER_NOT_FOUND(404, "User is not in DB"),

    //409 CONFLICT Duplicate Resource
    ALREADY_SAVED_SESSION(409, "Session is already saved"),
    ALREADY_SAVED_BODY(409, "Body is already saved"),
    ALREADY_SAVED_USER(409, "User is already saved"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "Server Error. Contact Server Team");

    private final int status;
    private final String message;

}