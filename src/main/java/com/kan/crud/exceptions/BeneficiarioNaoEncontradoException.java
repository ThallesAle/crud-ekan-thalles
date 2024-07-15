package com.kan.crud.exceptions;

public class BeneficiarioNaoEncontradoException extends RuntimeException {

    public BeneficiarioNaoEncontradoException(String message) {
        super(message);
    }

    public BeneficiarioNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
