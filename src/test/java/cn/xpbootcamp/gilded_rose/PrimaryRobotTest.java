package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidatedTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;
import cn.xpbootcamp.gilded_rose.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimaryRobotTest {

    /**
     * Save successfully
     * - Given parcel, two lockers(the first one is full and second one available) and robot, when save parcel, then parcel should in the second one and return ticket.
     * - Given parcel, two lockers(both of two are available) and robot, when save parcel, then parcel should in the first one and return ticket.
     */

    @Test
    public void should_return_ticket_and_store_parcel_in_second_locker_when_save_parcel_given_parcel_robot_and_two_locker_the_first_one_full() {
        // Given
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(new Locker(1));
        managedLockers.add(new Locker(1));
        LockerManageRobot primaryRobot = new PrimaryRobot(managedLockers);
        primaryRobot.receive(new Parcel());
        // When
        Ticket ticket = primaryRobot.receive(new Parcel());
        // Then
        assertNotNull(ticket);
        assertThat(ticket.getLockerIndex()).isEqualTo(1);
    }

    @Test
    public void should_return_ticket_and_store_parcel_in_first_locker_when_save_parcel_given_parcel_robot_and_two_locker_both_available() {
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(new Locker(1));
        managedLockers.add(new Locker(1));
        LockerManageRobot primaryRobot = new PrimaryRobot(managedLockers);
        // When
        Ticket ticket = primaryRobot.receive(new Parcel());
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
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(new Locker(1));
        managedLockers.add(new Locker(1));
        LockerManageRobot primaryRobot = new PrimaryRobot(managedLockers);
        primaryRobot.receive(new Parcel());
        primaryRobot.receive(new Parcel());
        // When Then
        assertThrows(LockerFullException.class, () -> primaryRobot.receive(new Parcel()));
    }

    /**
     * take parcel successfully
     * Given ticket(valid), locker and robot, when take parcel, then return parcel
     */

    @Test
    public void should_return_parcel_when_take_parcel_given_valid_ticket() {
        // Given
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(new Locker(1));
        managedLockers.add(new Locker(1));
        LockerManageRobot primaryRobot = new PrimaryRobot(managedLockers);
        Parcel firstParcel = new Parcel();
        Parcel secondParcel = new Parcel();

        Ticket firstTicket = primaryRobot.receive(firstParcel);
        Ticket secondTicket = primaryRobot.receive(secondParcel);
        // When and Then
        assertThat(primaryRobot.receive(firstTicket)).isEqualTo(firstParcel);
        assertThat(primaryRobot.receive(secondTicket)).isEqualTo(secondParcel);
    }

    /**
     * take parcel fail
     * Given ticket(invalided), two lockers and robot, when take parcel, then take fail throw exception
     */

    @Test
    public void should_throw_InvaildTicketException_when_take_parcel_given_invalid_ticket() {
        // Given
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(new Locker(1));
        managedLockers.add(new Locker(1));
        LockerManageRobot primaryRobot = new PrimaryRobot(managedLockers);
        Parcel firstParcel = new Parcel();
        Parcel secondParcel = new Parcel();

        Ticket firstTicket = primaryRobot.receive(firstParcel);
        Ticket secondTicket = primaryRobot.receive(secondParcel);
        primaryRobot.receive(firstTicket);
        primaryRobot.receive(secondTicket);
        // When and Then
        assertThrows(InvalidatedTicketException.class, () -> primaryRobot.receive(firstTicket));
        assertThrows(InvalidatedTicketException.class, () -> primaryRobot.receive(secondTicket));
    }

}
