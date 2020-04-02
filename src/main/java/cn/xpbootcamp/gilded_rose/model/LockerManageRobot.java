package cn.xpbootcamp.gilded_rose.model;

import java.util.ArrayList;

public class LockerManageRobot {

    private ArrayList<Locker> managedLockers;

    public LockerManageRobot(ArrayList<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket recieve(Parcel parcel) {
        for(int lockerIndex = 0; lockerIndex < managedLockers.size(); lockerIndex++) {
            Locker locker = managedLockers.get(lockerIndex);
            if (locker.isAvailable()){
                return locker.receive(parcel, lockerIndex);
            }
        }
        return null;
    }
}
