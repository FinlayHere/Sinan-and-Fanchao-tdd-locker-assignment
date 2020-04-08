package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.model.Locker;
import cn.xpbootcamp.gilded_rose.model.LockerManageRobot;
import cn.xpbootcamp.gilded_rose.model.Parcel;
import cn.xpbootcamp.gilded_rose.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmartRobotTest {

    public Boolean isIn(Parcel parcel, Locker locker){
        return locker.getContainer().containsValue(parcel);
    }
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
    public void should_store_parcel_in_second_locker_when_store_parcel_given_two_lockers_different_available_capacity() {
        // Given
        Locker firstLocker = new Locker(2);
        Locker secondLocker = new Locker(3);
        List<Locker> managedLockers = Arrays.asList(firstLocker,secondLocker);
        LockerManageRobot smartRobot = new LockerManageRobot(managedLockers);
        Parcel parcel = new Parcel();
        // When
        Ticket ticket = smartRobot.receive(parcel);
        // Then
        assertThat(ticket.getLockerIndex()).isEqualTo(1);
        assertTrue(isIn(parcel, secondLocker));
    }

    /**
     * - Given
     *     first locker
     *     second locker
     *     two lockers have same used capacity
     * - When
     *     store parcel
     * - Then
     *     parcel should store in first locker
     */

    @Test void  should_store_parcel_in_first_locker_when_store_parcel_given_two_lockers_same_available_capacity() {
        // Given
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        List<Locker> managedLockers = Arrays.asList(firstLocker,secondLocker);
        LockerManageRobot smartRobot = new LockerManageRobot(managedLockers);
        Parcel parcel = new Parcel();
        // When
        Ticket ticket = smartRobot.receive(parcel);
        // Then
        assertThat(ticket.getLockerIndex()).isEqualTo(0);
        assertTrue(isIn(parcel, firstLocker));
    }
}
