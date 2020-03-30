package cn.xpbootcamp.gilded_rose.exception;

public class InvalidatedTicketException extends RuntimeException {

    public InvalidatedTicketException(String message) {
        super(message);
    }
}
