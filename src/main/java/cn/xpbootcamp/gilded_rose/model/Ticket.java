package cn.xpbootcamp.gilded_rose.model;

import java.util.UUID;

public class Ticket {
    private String id;

    public Ticket() {
        this.id = UUID.randomUUID().toString().replace("-","");
    }

    public String getId() {
        return id;
    }
}
