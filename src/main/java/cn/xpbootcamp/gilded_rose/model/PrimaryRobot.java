package cn.xpbootcamp.gilded_rose.model;

import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.List;

public class PrimaryRobot extends LockerManageRobot{

    public PrimaryRobot(List<Locker> managedLockers) {
        super(managedLockers);
    }

    @Override
    protected Locker findLocker() {
        for (Locker locker : managedLockers){
            if (locker.isAvailable()){
                return locker;
            }
        }
        throw new LockerFullException("Locker full cannot save parcel anymore");
    }
}
