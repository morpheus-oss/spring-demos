package dev.guestbook.controller;

import dev.guestbook.domain.Hotel;
import dev.guestbook.domain.Rating;
import dev.guestbook.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    private HotelService hotelService;
    public HotelController(HotelService hotelService)   {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel")
    public Rating addRating(@RequestBody Hotel hotel)     {
//        dev.guestbook.entities.Hotel entityHotel = dev.guestbook.entities.Hotel.
//        hotelService
        return null;
    }



}
