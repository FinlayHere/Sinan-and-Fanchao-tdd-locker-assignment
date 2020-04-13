package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

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
        List<Locker> managedLockers = Arrays.asList(firstLocker,secondLocker);
        LockerManageRobot smartRobot = new SmartRobot(managedLockers);
        Parcel parcel = new Parcel();
        // When
        Ticket ticket = smartRobot.receive(parcel);
        // Then
        assertThat(secondLocker.takeParcel(ticket)).isEqualTo(parcel);
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
        LockerManageRobot smartRobot = new SmartRobot(managedLockers);
        Parcel parcel = new Parcel();
        // When
        Ticket ticket = smartRobot.receive(parcel);
        // Then
        assertThat(firstLocker.takeParcel(ticket)).isEqualTo(parcel);
    }
}
