package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidatedTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;
import cn.xpbootcamp.gilded_rose.model.Locker;
import cn.xpbootcamp.gilded_rose.model.Parcel;
import cn.xpbootcamp.gilded_rose.model.Ticket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {
    /*
    ### ----存包裹----
    - Given locker 最大容量 20，已经使用的容量为 0，when 存包裹，then 寄存成功 返回ticket
    - Given locker 最大容量 20，已经使用的容量为 10，when 存包裹，then 寄存成功 返回ticket
    - Given locker 最大容量 20，已经使用的容量为 19，when 存包裹，then 寄存成功 返回ticket
    - Given locker 最大容量 20，已经使用的容量为 20，when 存包裹，then 寄存失败 返回异常locker已经满了

    ### ----取包裹----
    - Given 一个包裹 和它对应的 ticket，when 使用ticket 取包裹，then 取包裹成功 得到同一个包裹
    - Given 一个包裹 和它对应的 ticket，when 使用同一张 ticket 取两次包裹，then 取包失败 抛出异常 无效票据
     */

    public void initUsed(Locker locker, int capacity) {
        for(int i = 0; i < capacity; i++){
            locker.receive(new Parcel());
        }
    }

    // Given locker 最大容量 20，已经使用的容量为 0，when 存包裹，then 寄存成功 返回ticket
    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_20() {
        // Given 容量是20的locker 当前可用容量是20 和一个待存入的包裹
        Locker locker = new Locker(20);
        // When save parcel
        Ticket ticket = locker.receive(new Parcel());
        // Then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    // Given locker 最大容量 20，已经使用的容量为 10，when 存包裹，then 寄存成功 返回ticket
    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_10() {
        // Given 容量是20的locker 当前可用容量是10 和一个待存入的包裹
        Locker locker = new Locker(20);
        this.initUsed(locker, 10);
        // when save bag
        Ticket ticket = locker.receive(new Parcel());
        // then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    // Given locker 最大容量 20，已经使用的容量为 19，when 存包裹，then 寄存成功 返回ticket
    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_1() {
        // Given 容量是20的locker 当前可用容量是1 和一个待存入的包裹
        Locker locker = new Locker(20);
        this.initUsed(locker, 19);
        // when save bag
        Ticket ticket = locker.receive(new Parcel());
        // then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    // Given locker 最大容量 20，已经使用的容量为 20，when 存包裹，then 寄存失败 返回异常locker已经满了
    @Test
    public void should_throw_Error_when_deposit_parcel_given_locker_max_capacity_20_available_0() {
        // Given 容量是20的locker 当前可用容量为0
        Locker locker = new Locker(20);
        this.initUsed(locker, 20);
        Parcel parcel = new Parcel();
        // when save parcel then throw Exception
        assertThrows(LockerFullException.class,
                ()->locker.receive(parcel),
                "Locker full cannot save parcel anymore");
    }

    // Given 一个包裹 和它对应的 ticket，when 使用ticket 取包裹，then 取包裹成功 得到同一个包裹
    @Test
    public void should_return_parcel_which_map_by_ticket_when_use_ticket_take_parcel_when_effective_ticket_and_certain_parcel() {
        // Given certain parcel and effective ticket
        Locker locker = new Locker();
        Parcel certainParcel = new Parcel("certainId");
        Ticket ticket = locker.receive(certainParcel);
        // when take away parcel
        Parcel parcel = locker.takeParcel(ticket);
        // then parcel should equal certain parcel
        assertThat(parcel.getId()).isEqualTo(certainParcel.getId());
    }

    // Given 一个包裹 和它对应的 ticket，when 使用同一张 ticket 取两次包裹，then 取包失败 抛出异常 无效票据
    @Test
    public void should_throw_InvalidatedTicketException_when_take_parcel_given_invalidated_ticket() {
        // Given invalidated ticket
        Locker locker = new Locker();
        Ticket ticket = locker.receive(new Parcel());
        locker.takeParcel(ticket);
        // When use invalidated ticket
        // Then throw InvalidatedTicketException
        assertThrows(InvalidatedTicketException.class,
                ()->locker.takeParcel(ticket),
                "Invalidated ticket");
    }
}
