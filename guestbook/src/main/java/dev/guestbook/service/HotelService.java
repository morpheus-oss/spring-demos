package dev.guestbook.service;

import dev.guestbook.entities.Hotel;
import dev.guestbook.exception.ResourceNotFoundException;
import dev.guestbook.repo.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    HotelService(HotelRepository repository)  {
        this.hotelRepository = repository;
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(Long id) throws ResourceNotFoundException {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElseThrow();
    }
}
