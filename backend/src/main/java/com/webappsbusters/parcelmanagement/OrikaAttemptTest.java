package com.webappsbusters.parcelmanagement;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelDto;
import com.webappsbusters.parcelmanagement.domain.ParcelStatus;
import ma.glasnost.orika.MapperFacade;

public class OrikaAttemptTest {
    public static void main(String[] args) {
        //Parcel parcel1 = new Parcel(ParcelStatus.OUT_FOR_DELIVERY,"sendcity","sendpost","sendstreet","recivcity","receivpost","receivstreet",100,10,20,30,"L");
        Parcel parcel1 = new Parcel.ParcelBuilder().receiverCity("wroclaw").build();
        MapperFacade facade = new OrikaAttempt();
        ParcelDto parcel1clone = facade.map(parcel1, ParcelDto.class);
        System.out.println("origin: " + parcel1);
        System.out.println("clone: " + parcel1clone);
    }
}
