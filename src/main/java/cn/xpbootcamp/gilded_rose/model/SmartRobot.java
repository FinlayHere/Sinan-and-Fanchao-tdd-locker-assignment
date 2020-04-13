package cn.xpbootcamp.gilded_rose.model;

import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.ArrayList;
import java.util.List;

public class SmartRobot extends LockerManageRobot {

    public SmartRobot(List<Locker> managedLockers) {
        super(managedLockers);
    }

    @Override
    protected Locker findLocker() {
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
}
