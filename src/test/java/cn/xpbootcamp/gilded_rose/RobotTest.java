package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.model.Locker;
import cn.xpbootcamp.gilded_rose.model.Parcel;
import cn.xpbootcamp.gilded_rose.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RobotTest {
    /**
     * Save successfully
     * - Given parcel, two lockers(the first one is full and second one available) and robot, when save parcel, then parcel should in the second one and return ticket.
     * - Given parcel, two lockers(both of two are available) and robot, when save parcel, then parcel should in the first one and return ticket.
     */

    @Test
    public void should_return_ticket_and_store_parcel_in_second_locker_when_save_parcel_given_parcel_robot_and_two_locker_the_first_one_full() {
        // Given
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(firstLocker);
        managedLockers.add(secondLocker);
        LockerManageRobot lockerManageRobot = new LockerManageRobot(managedLockers);
        lockerManageRobot.recieve(new Parcel());
        // When
        Ticket ticket = lockerManageRobot.recieve(new Parcel());
        // Then
        assertNotNull(ticket);
        assertThat(ticket.getLockerIndex).isEqualTo(1);
    }
}
