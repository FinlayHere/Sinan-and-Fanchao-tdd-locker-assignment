package cn.xpbootcamp.gilded_rose.model;

import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.ArrayList;
import java.util.List;

public class LockerManageRobot {

    private List<Locker> managedLockers;

    public LockerManageRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket receive(Parcel parcel) {
        for(int lockerIndex = 0; lockerIndex < managedLockers.size(); lockerIndex++) {
            Locker locker = managedLockers.get(lockerIndex);
            if (locker.isAvailable()){
                return locker.receive(parcel, lockerIndex);
            }
        }
        throw new LockerFullException("Locker full cannot save parcel anymore");
    }

    public Parcel receive(Ticket ticket) {
        return managedLockers.get(ticket.getLockerIndex()).takeParcel(ticket);
    }
}
