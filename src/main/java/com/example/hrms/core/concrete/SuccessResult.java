package com.example.hrms.core.concrete;

public class SuccessResult extends Result { // işleri kolaylaştırmak için.
    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
