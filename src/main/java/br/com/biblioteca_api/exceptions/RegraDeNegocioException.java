package br.com.biblioteca_api.exceptions;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String message) {
        super(message);
    }
}
