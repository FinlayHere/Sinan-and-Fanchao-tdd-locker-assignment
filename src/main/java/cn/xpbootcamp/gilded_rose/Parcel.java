package cn.xpbootcamp.gilded_rose;

import java.util.UUID;

public class Parcel{
    private String id;

    public Parcel() {
        this.id = UUID.randomUUID().toString().replace("-","");
    }

    public Parcel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
