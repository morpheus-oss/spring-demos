package dev.guestbook.controller;

import dev.guestbook.domain.Hotel;
import dev.guestbook.domain.Rating;
import dev.guestbook.repo.HotelRepository;
import dev.guestbook.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HotelController {

    private final HotelService hotelService;
    private final HotelRepository hotelRepository;
    public HotelController(HotelService hotelService, HotelRepository hotelRepository)   {
        this.hotelService = hotelService;
        this.hotelRepository = hotelRepository;
    }

    @PostMapping("/hotel")
    public Rating addRating(@RequestBody Hotel hotel)     {
//        dev.guestbook.entities.Hotel entityHotel = dev.guestbook.entities.Hotel.
//        hotelService
        return null;
    }



}
