package cn.xpbootcamp.gilded_rose.model;

import java.util.UUID;

public class Parcel{
    private String id;

    public Parcel() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
