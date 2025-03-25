package dev.guestbook.controller;

import dev.guestbook.domain.Rating;
import dev.guestbook.repo.RatingRepository;
import dev.guestbook.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService service)  {
        this.ratingService = service;
    }

    @PostMapping("/hotel/{hotelId}/rating")
    public Rating addRating(@PathVariable long hotelId, @RequestBody Rating rating)     {
        return null;
    }
}
