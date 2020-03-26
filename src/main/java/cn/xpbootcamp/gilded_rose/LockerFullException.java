package cn.xpbootcamp.gilded_rose;

public class LockerFullException extends RuntimeException {
    private String lockerFullMessage;

    public LockerFullException(){
        super();
    }
    public LockerFullException(String message) {
        super(message);
        this.lockerFullMessage = message;
    }

    public String getLockerFullMessage() {
        return lockerFullMessage;
    }
}
