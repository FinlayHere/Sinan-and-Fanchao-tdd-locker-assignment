package cn.xpbootcamp.gilded_rose;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    public Locker() {
    }

    private Map<Ticket,Bag> container = new HashMap<Ticket,Bag>();


    public Ticket receive(Bag bag) {
        Ticket ticket = new Ticket();
        this.container.put(ticket,bag);
        return ticket;
    }

    public boolean include(Bag bag) {
        return this.container.containsValue(bag);
    }
}
