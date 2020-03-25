package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class LockerTest {
    @Test
    public void should_return_ticket_when_store_bag_given_bag(){
        Locker locker = new Locker();
        Bag bag = new Bag();
        assertThat(locker.receive(bag)).isInstanceOf(Ticket.class);
    }

}
