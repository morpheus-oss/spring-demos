package dev.guestbook.service;

import dev.guestbook.domain.HotelIn;
import dev.guestbook.entities.Hotel;
import dev.guestbook.exception.ResourceNotFoundException;
import dev.guestbook.repo.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    HotelService(HotelRepository repository)  {
        this.hotelRepository = repository;
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.saveAndFlush(hotel);
    }

    public Hotel getHotel(Long id) throws ResourceNotFoundException {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElseThrow();
    }

    public List<Hotel> findAll()    {
        return hotelRepository.findAll();
    }

    public Page<Hotel> findAllHotels(Pageable pageable)  {
        return hotelRepository.findAll(pageable);
    }

}
