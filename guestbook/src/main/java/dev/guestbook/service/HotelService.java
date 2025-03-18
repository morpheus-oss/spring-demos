package dev.guestbook.service;

import dev.guestbook.entities.Hotel;
import dev.guestbook.repo.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    HotelService(HotelRepository repository)  {
        this.hotelRepository = repository;
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.saveAndFlush(hotel);
    }

}
