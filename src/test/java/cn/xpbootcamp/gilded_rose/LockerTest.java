package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // Given 容量是20的locker 当前可用容量是20
        Locker locker = new Locker(20);
        // When save parcel
        Ticket ticket = locker.receive(new Parcel());
        // Then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_10() {
        // Given 容量是20的locker 当前可用容量是10
        Locker locker = new Locker(20);
        for (int i = 0;i < 9; i++) {
            locker.receive(new Parcel());
        }
        // when save bag
        Ticket ticket = locker.receive(new Parcel());
        // then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    public void should_return_ticket_when_deposit_parcel_given_locker_max_capacity_20_available_capacity_1() {
        // Given 容量是20的locker 当前可用容量是10
        Locker locker = new Locker(20);
        for (int i = 0;i < 19; i++) {
            locker.receive(new Parcel());
        }
        // when save bag
        Ticket ticket = locker.receive(new Parcel());
        // then return a ticket
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

}
