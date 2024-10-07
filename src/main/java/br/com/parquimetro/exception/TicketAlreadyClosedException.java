package br.com.parquimetro.exception;

public class TicketAlreadyClosedException extends RuntimeException {

    public TicketAlreadyClosedException(String message) {

        super(message);

    }

}
