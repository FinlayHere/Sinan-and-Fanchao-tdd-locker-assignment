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
    - Given locker 最大容量 20，已存包裹包裹0-19（0，19，10），when 存包裹，then 寄存成功 返回ticket
    - Given locker 最大容量 20，已存包裹20，when 存包裹，then 寄存失败 返回异常locker已经满了

    ### ----取包裹----
    - Given ticket 有效，when 取包裹，then 取包成功 ticket失效
    - Given ticket 无效，when 取包裹，then 取包失败 返回异常 无效票据
     */
    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_20() {
        // Given 容量是20的locker 当前可用容量是20 和一个待存入的包裹
        Locker locker = new Locker(20);
        // When save parcel
        Ticket ticket = locker.receive(new Parcel());
        // Then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_10() {
        // Given 容量是20的locker 当前可用容量是10 和一个待存入的包裹
        Locker locker = new Locker(20);
        locker.initUsedCapacity(10);
        // when save bag
        Ticket ticket = locker.receive(new Parcel());
        // then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_1() {
        // Given 容量是20的locker 当前可用容量是1 和一个待存入的包裹
        Locker locker = new Locker(20);
        locker.initUsedCapacity(19);
        // when save bag
        Ticket ticket = locker.receive(new Parcel());
        // then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    public void should_throw_Error_when_deposit_parcel_given_locker_max_capacity_20_available_0() {
        // Given 容量是20的locker 当前可用容量为0
        Locker locker = new Locker(20);
        locker.initUsedCapacity(20);
        Parcel parcel = new Parcel();
        // when save parcel then throw Exception
        assertThrows(LockerFullException.class,
                ()->locker.receive(parcel),
                "Locker full cannot save parcel anymore");
    }

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

    @Test
    public void should_invalidate_ticket_when_use_ticket_take_parcel_when_effective_ticket() {
        // Given effective ticket
        Locker locker = new Locker();
        Ticket ticket = locker.receive(new Parcel());
        // When take parcel
        locker.takeParcel(ticket);
        // Then ticket should be invalidated
        assertThat(locker.getContainer().containsKey(ticket.getId())).isFalse();
    }

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
