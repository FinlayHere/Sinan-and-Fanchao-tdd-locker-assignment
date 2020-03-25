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

    private Map<Ticket,Bag> container = new HashMap<Ticket,Bag>();

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<Ticket, Bag> getContainer() {
        return container;
    }

    public void setContainer(Map<Ticket, Bag> container) {
        this.container = container;
    }

    public Ticket receive(Bag bag) {
        if (this.container.size() < capacity) {
            Ticket ticket = new Ticket();
            this.container.put(ticket,bag);
            return ticket;
        }
        return null;
    }

    public boolean include(Bag bag) {
        return this.container.containsValue(bag);
    }

    public void takeBag(Ticket ticket) {
        container.remove(ticket);
    }
}
