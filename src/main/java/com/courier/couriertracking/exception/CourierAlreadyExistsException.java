package com.courier.couriertracking.exception;

public class CourierAlreadyExistsException extends RuntimeException {
    public CourierAlreadyExistsException(String message) {
        super(message);
    }
}
