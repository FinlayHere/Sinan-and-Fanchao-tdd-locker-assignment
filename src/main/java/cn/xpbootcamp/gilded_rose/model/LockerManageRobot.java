package cn.xpbootcamp.gilded_rose.model;

import cn.xpbootcamp.gilded_rose.exception.LockerFullException;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class LockerManageRobot {

    private List<Locker> managedLockers;

    public LockerManageRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket receive(Parcel parcel) {

        Locker targetLocker = findTargetLocker();

        return targetLocker.receive(parcel, managedLockers.indexOf(targetLocker));

    }

    private Locker findTargetLocker(){

        List<Locker> mostAvailableCapacityLocker = new ArrayList<>();
        int availableCapacity = 0;
        for (Locker locker: managedLockers){
            int tempAvailableCapacity = locker.getCapacity() - locker.getContainer().size();
            if (tempAvailableCapacity == availableCapacity){
                mostAvailableCapacityLocker.add(locker);
            }
            if (tempAvailableCapacity > availableCapacity) {
                mostAvailableCapacityLocker.clear();
                mostAvailableCapacityLocker.add(locker);
                availableCapacity = tempAvailableCapacity;
            }
        }

        if (0 == availableCapacity){
            throw new LockerFullException("Locker full cannot save parcel anymore");
        }

        return mostAvailableCapacityLocker.get(0);

    }

    public Parcel receive(Ticket ticket) {
        return managedLockers.get(ticket.getLockerIndex()).takeParcel(ticket);
    }
}
