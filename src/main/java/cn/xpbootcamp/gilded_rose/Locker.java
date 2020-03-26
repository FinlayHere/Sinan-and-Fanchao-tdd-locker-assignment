package cn.xpbootcamp.gilded_rose;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int capacity;
    private Map<String,Parcel> container = new HashMap<>();

    public Map<String, Parcel> getContainer() {
        return container;
    }

    public Locker() {
        this.capacity = Integer.MAX_VALUE;
    }

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket receive(Parcel parcel) {
        if (this.container.size() < capacity) {
            Ticket ticket = new Ticket();
            this.container.put(ticket.getId(),parcel);
            return ticket;
        }
        throw new LockerFullException("Locker full cannot save parcel anymore");
    }


    public Parcel takeParcel(Ticket ticket) {
        if (container.containsKey(ticket.getId())){
            Parcel parcel = this.container.get(ticket.getId());
            return this.container.remove(ticket.getId());
        }
        throw new InvalidatedTicketException("Invalidated ticket");
    }

    public void initUsedCapacity(int numberOfInitUsedCapacity) {
        for (int i = 0;i < numberOfInitUsedCapacity; i++){
            this.receive(new Parcel());
        }
    }
}
