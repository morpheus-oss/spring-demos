package dev.guestbook.controller;

import dev.guestbook.domain.Rating;
import dev.guestbook.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @Autowired
    RatingRepository ratingRepo;

    @PostMapping("/hotel/{hotelId}/rating")
    public Rating addRating(@PathVariable long hotelId, @RequestBody Rating rating)     {
        return null;
    }
}
