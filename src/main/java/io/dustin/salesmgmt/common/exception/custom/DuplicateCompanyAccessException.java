package io.dustin.salesmgmt.common.exception.custom;


import java.util.List;

public class DuplicateCompanyAccessException extends RuntimeException {
    public DuplicateCompanyAccessException(String message) {
        super(message);
    }
}

