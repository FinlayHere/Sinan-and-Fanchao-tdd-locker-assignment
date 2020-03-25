package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LockerTest {
    @Test
    public void should_return_a_new_ticket_when_store_bag_given_bag(){
        Locker locker = new Locker();
        Bag bag = new Bag();
        Ticket ticket = locker.receive(bag);
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    public void should_bag_in_locker_when_after_store_bag_given_bag(){
        Locker locker = new Locker();
        Bag bag = new Bag();
        locker.receive(bag);
        assertTrue(locker.include(bag));
    }



}
