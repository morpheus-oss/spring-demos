package dev.guestbook.service;

import dev.guestbook.entities.Rating;
import dev.guestbook.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating save(Rating rating)   {
        return ratingRepository.save(rating);
    }

    public Rating findById(long id)    {
        return ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public List<Rating> findByStars(int stars)  {
        return ratingRepository.findByStars(stars);
    }

    public List<Rating> findByDate(Timestamp date)  {
        return ratingRepository.findByDate(date);
    }

    public List<Rating> sortByStars()   {
        return ratingRepository.sortByStars();
    }

    public List<Rating> sortByDate()    {
        return ratingRepository.sortByDate(); 
    }
}
