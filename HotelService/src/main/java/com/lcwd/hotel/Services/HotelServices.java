package com.lcwd.hotel.Services;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelServices {
    //create
    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel get(String id);

}
