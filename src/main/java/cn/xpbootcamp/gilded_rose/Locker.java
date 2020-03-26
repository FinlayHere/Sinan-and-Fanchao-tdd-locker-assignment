package cn.xpbootcamp.gilded_rose;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int capacity;
    public Locker() {
        this.capacity = Integer.MAX_VALUE;
    }

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    private Map<Ticket,Parcel> container = new HashMap<Ticket,Parcel>();

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<Ticket, Parcel> getContainer() {
        return container;
    }

    public void setContainer(Map<Ticket, Parcel> container) {
        this.container = container;
    }

    public Ticket receive(Parcel parcel) {
        if (this.container.size() < capacity) {
            Ticket ticket = new Ticket();
            this.container.put(ticket,parcel);
            return ticket;
        }
        return null;
    }

    public boolean include(Parcel parcel) {
        return this.container.containsValue(parcel);
    }

    public Parcel takeParcel(Ticket ticket) {
        Parcel parcel = this.container.get(ticket);
        this.container.remove(ticket);
        return parcel;
    }

}
