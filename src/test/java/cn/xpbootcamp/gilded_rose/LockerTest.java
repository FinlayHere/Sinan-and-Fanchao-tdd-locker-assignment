package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void should_tickets_be_different_when_every_time_store_bag_given_more_than_two_ticket(){
        Locker locker = new Locker();
        Set<Ticket> tickets = new HashSet<Ticket>();
        for (int i =0;i < 100;i++){
            Bag bag = new Bag();
            Ticket ticket = locker.receive(bag);
            tickets.add(ticket);
        }
        assertThat(tickets.size()).isEqualTo(100);
    }

    @Test
    public void should_locker_no_longer_contain_bag_when_user_take_their_bag_away_given_ticket(){
        Locker locker = new Locker();
        Bag bag = new Bag();
        Ticket ticket = locker.receive(bag);
        locker.takeBag(ticket);
        assertThat(locker.include(bag)).isEqualTo(false);

    }



}
