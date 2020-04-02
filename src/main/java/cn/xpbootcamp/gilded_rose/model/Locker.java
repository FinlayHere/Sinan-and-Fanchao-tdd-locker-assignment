package cn.xpbootcamp.gilded_rose.model;

import cn.xpbootcamp.gilded_rose.exception.InvalidatedTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int capacity;
    private Map<String,Parcel> container = new HashMap<>();

    public Locker() {
        this.capacity = Integer.MAX_VALUE;
    }

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket receive(Parcel parcel) {
        if (this.isAvailable()) {
            Ticket ticket = new Ticket();
            this.container.put(ticket.getId(),parcel);
            return ticket;
        }
        throw new LockerFullException("Locker full cannot save parcel anymore");
    }

    public Ticket receive(Parcel parcel, int lockerIndex) {
        if (this.isAvailable()) {
            Ticket ticket = new Ticket(lockerIndex);
            this.container.put(ticket.getId(),parcel);
            return ticket;
        }
        throw new LockerFullException("Locker full cannot save parcel anymore");
    }


    public Parcel takeParcel(Ticket ticket) {
        if (container.containsKey(ticket.getId())){
            return this.container.remove(ticket.getId());
        }
        throw new InvalidatedTicketException("Invalidated ticket");
    }

    public boolean isAvailable() {
        return this.container.size() < capacity;
    }
}
