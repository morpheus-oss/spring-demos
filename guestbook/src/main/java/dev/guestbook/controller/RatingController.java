package dev.guestbook.controller;

import dev.guestbook.domain.RatingIn;
import dev.guestbook.entities.Hotel;
import dev.guestbook.entities.Rating;
import dev.guestbook.exception.ResourceNotFoundException;
import dev.guestbook.service.HotelService;
import dev.guestbook.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class RatingController {

    private final RatingService ratingService;
    private final HotelService hotelService;

    @Autowired
    public RatingController(RatingService service, HotelService hotelService)  {
        this.ratingService = service;
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel/{hotelId}/rating")
    public Rating addRating(@PathVariable long hotelId, @RequestBody RatingIn rating)     {

        try {
            Hotel hotel = hotelService.getHotel(hotelId);
            Rating ratingEntity = Rating.builder()
                                        .hotel(hotel)
                                        .guestName(rating.guestName())
                                        .guestPhone(rating.guestPhone())
                                        .guestEmail(rating.guestEmail())
                                        .description(rating.comment())
                                        .stars(rating.stars())
                                        .addedDate(Timestamp.valueOf(LocalDateTime.now()))
                                        .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                                        .build();
            return ratingService.addRating(ratingEntity);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
