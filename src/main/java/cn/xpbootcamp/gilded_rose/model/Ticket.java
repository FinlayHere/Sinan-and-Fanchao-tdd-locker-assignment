package cn.xpbootcamp.gilded_rose.model;

import java.util.UUID;

public class Ticket {

    private String id;
    private int index;

    public Ticket() {
        this.id = UUID.randomUUID().toString();
    }

    public Ticket(int index) {
        this.id = UUID.randomUUID().toString();
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public int getLockerIndex() {
        return index;
    }
}
