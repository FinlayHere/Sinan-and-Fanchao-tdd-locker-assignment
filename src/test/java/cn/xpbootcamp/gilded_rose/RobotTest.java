package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidatedTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;
import cn.xpbootcamp.gilded_rose.model.Locker;
import cn.xpbootcamp.gilded_rose.model.LockerManageRobot;
import cn.xpbootcamp.gilded_rose.model.Parcel;
import cn.xpbootcamp.gilded_rose.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RobotTest {
    /**
     * Save successfully
     * - Given parcel, two lockers(the first one is full and second one available) and robot, when save parcel, then parcel should in the second one and return ticket.
     * - Given parcel, two lockers(both of two are available) and robot, when save parcel, then parcel should in the first one and return ticket.
     */

    @Test
    public void should_return_ticket_and_store_parcel_in_second_locker_when_save_parcel_given_parcel_robot_and_two_locker_the_first_one_full() {
        // Given
        List<Locker> managedLockers = Arrays.asList(new Locker(1),new Locker(1));
        LockerManageRobot lockerManageRobot = new LockerManageRobot(managedLockers);
        lockerManageRobot.receive(new Parcel());
        // When
        Ticket ticket = lockerManageRobot.receive(new Parcel());
        // Then
        assertNotNull(ticket);
        assertThat(ticket.getLockerIndex()).isEqualTo(1);
    }

    @Test
    public void should_return_ticket_and_store_parcel_in_first_locker_when_save_parcel_given_parcel_robot_and_two_locker_both_available() {
        List<Locker> managedLockers = Arrays.asList(new Locker(1),new Locker(1));
        LockerManageRobot lockerManageRobot = new LockerManageRobot(managedLockers);
        // When
        Ticket ticket = lockerManageRobot.receive(new Parcel());
        // Then
        assertNotNull(ticket);
        assertThat(ticket.getLockerIndex()).isEqualTo(0);
    }

    /**
     * Save fail
     * Given package, two lockers(all full, capacity run out) and  robot, when save package, then save fail throw exception.
     */

    @Test
    public void should_throw_LockerFullException_when_save_parcel_given_parcel_robot_and_two_full_lockers() {
        // Given
        List<Locker> managedLockers = Arrays.asList(new Locker(1),new Locker(1));
        LockerManageRobot lockerManageRobot = new LockerManageRobot(managedLockers);
        lockerManageRobot.receive(new Parcel());
        lockerManageRobot.receive(new Parcel());
        // When Then
        assertThrows(LockerFullException.class, () -> lockerManageRobot.receive(new Parcel()));
    }

    /**
     * take parcel successfully
     * Given ticket(valid), locker and robot, when take parcel, then return parcel
     */
    @Test
    public void should_return_parcel_when_take_parcel_given_valid_ticket() {
        // Given
        List<Locker> managedLockers = Arrays.asList(new Locker(1),new Locker(1));
        LockerManageRobot lockerManageRobot = new LockerManageRobot(managedLockers);
        Parcel firstParcel = new Parcel();
        Parcel secondParcel = new Parcel();

        Ticket firstTicket = lockerManageRobot.receive(firstParcel);
        Ticket secondTicket = lockerManageRobot.receive(secondParcel);
        // When and Then
        assertThat(lockerManageRobot.receive(firstTicket)).isEqualTo(firstParcel);
        assertThat(lockerManageRobot.receive(secondTicket)).isEqualTo(secondParcel);
    }

    /**
     * take parcel fail
     * Given ticket(invalided), two lockers and robot, when take parcel, then take fail throw exception
     */
    @Test
    public void should_throw_InvalidTicketException_when_take_parcel_given_invalid_ticket() {
        // Given
        List<Locker> managedLockers = Arrays.asList(new Locker(1),new Locker(1));
        LockerManageRobot lockerManageRobot = new LockerManageRobot(managedLockers);
        Parcel firstParcel = new Parcel();
        Parcel secondParcel = new Parcel();

        Ticket firstTicket = lockerManageRobot.receive(firstParcel);
        Ticket secondTicket = lockerManageRobot.receive(secondParcel);
        lockerManageRobot.receive(firstTicket);
        lockerManageRobot.receive(secondTicket);
        // When and Then
        assertThrows(InvalidatedTicketException.class, () -> lockerManageRobot.receive(firstTicket));
        assertThrows(InvalidatedTicketException.class, () -> lockerManageRobot.receive(secondTicket));
    }

}
