package dev.guestbook.controller;

import dev.guestbook.domain.HotelIn;
import dev.guestbook.entities.Hotel;
import dev.guestbook.service.HotelService;
import dev.guestbook.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService)   {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel")
    public Hotel addRating(@RequestBody HotelIn hotel)     {
        return null;
    }

    @GetMapping("/hotel")
    public List<Hotel> listAllHotels()  {
        return hotelService.findAll();
    }

    @GetMapping("/hotel?page={pageNum}&pageSize={pageSize}")
    public Page<Hotel> listHotels(@RequestParam(name = "pageNum", required = false) int pageNum,
                                        @RequestParam(name = "pageSize", required = false) int pageSize)   {

        return hotelService.findAllHotels(ControllerUtil.createPageable(pageNum, pageSize));
    }



}
