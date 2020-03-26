package cn.xpbootcamp.gilded_rose;

public class InvalidatedTicketException extends RuntimeException {

    private String InvalidatedTicketExceptionMessage;

    public InvalidatedTicketException() {
        super();
    }

    public InvalidatedTicketException(String message) {
        super(message);
        this.InvalidatedTicketExceptionMessage = message;
    }

    public String getInvalidatedTicketExceptionMessage() {
        return InvalidatedTicketExceptionMessage;
    }
}
