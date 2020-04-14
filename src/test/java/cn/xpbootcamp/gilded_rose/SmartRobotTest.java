package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidatedTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;
import cn.xpbootcamp.gilded_rose.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartRobotTest {

    /**
     * - Given
     * two lockers,
     * first locker(2 available capacity),
     * second locker(3 available capacity),
     * a smart robot,
     * a parcel
     * - When
     * store parcel
     * - Then
     * parcel should be store in the second locker
     */

    @Test
    public void should_store_parcel_in_larger_capacity_locker_when_store_parcel_given_two_lockers_different_available_capacity() {
        // Given
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(2);
        List<Locker> managedLockers = Arrays.asList(firstLocker, secondLocker);
        LockerManageRobot smartRobot = new SmartRobot(managedLockers);
        Parcel parcel = new Parcel();
        // When
        Ticket ticket = smartRobot.receive(parcel);
        // Then
        assertThat(secondLocker.takeParcel(ticket)).isEqualTo(parcel);
    }

    /**
     * - Given
     * first locker
     * second locker
     * two lockers have same used capacity
     * - When
     * store parcel
     * - Then
     * parcel should store in first locker
     */

    @Test
    void should_store_parcel_in_first_locker_when_store_parcel_given_two_lockers_same_available_capacity() {
        // Given
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        List<Locker> managedLockers = Arrays.asList(firstLocker, secondLocker);
        LockerManageRobot smartRobot = new SmartRobot(managedLockers);
        Parcel parcel = new Parcel();
        // When
        Ticket ticket = smartRobot.receive(parcel);
        // Then
        assertThat(firstLocker.takeParcel(ticket)).isEqualTo(parcel);
    }

    /**
     * - Given
     * two lockers,
     * first locker(0 available capacity),
     * second locker(0 available capacity),
     * a smart robot,
     * a parcel
     * - When
     * store parcel
     * - Then
     * parcel cannot store in lockers
     */

    @Test
    public void should_throw_exception_when_store_parcel_given_a_parcel() {
        // Given
        List<Locker> managedLockers = Arrays.asList(new Locker(0), new Locker(0));
        LockerManageRobot smartRobot = new SmartRobot(managedLockers);
        // When and Then
        assertThrows(LockerFullException.class, () -> smartRobot.receive(new Parcel()));
    }

    /**
     * - Given
     * two lockers,
     * first locker(1 available capacity),
     * second locker(2 available capacity),
     * a smart robot,
     * ticket
     * - When
     * take parcel
     * - Then
     * throw exception, cannot take
     */

    @Test
    public void should_throw_exception_when_take_parcel_given_ticket_invalid() {
        // Given
        LockerManageRobot smartRobot = new SmartRobot(Collections.singletonList(new Locker(1)));
        Ticket ticket = smartRobot.receive(new Parcel());
        assertNotNull(smartRobot.receive(ticket));
        // When Then
        assertThrows(InvalidatedTicketException.class, () -> smartRobot.receive(ticket));

    }
}
