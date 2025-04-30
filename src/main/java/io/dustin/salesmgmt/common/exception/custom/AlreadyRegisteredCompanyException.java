package io.dustin.salesmgmt.common.exception.custom;

public class AlreadyRegisteredCompanyException extends RuntimeException {
    public AlreadyRegisteredCompanyException(String message) {
        super(message);
    }
}
