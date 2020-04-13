package cn.xpbootcamp.gilded_rose.model;

import java.util.List;

public abstract class LockerManageRobot {

    protected List<Locker> managedLockers;

    public LockerManageRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket receive(Parcel parcel){
        Locker targetLocker = findLocker();
        return targetLocker.receive(parcel, managedLockers.indexOf(targetLocker));
    }

    public Parcel receive(Ticket ticket){
        return managedLockers.get(ticket.getLockerIndex()).takeParcel(ticket);
    }

    protected abstract Locker findLocker();
}
