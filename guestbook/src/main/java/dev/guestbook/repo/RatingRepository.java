package dev.guestbook.repo;

import dev.guestbook.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM rating r WHERE stars = :stars")
    List<Rating> findByStars(int stars);

    @Query("SELECT r FROM rating r WHERE last_modified_date = :date")
    List<Rating> findByDate(Timestamp date);

    @Query("SELECT r from rating r ORDER BY stars DESC")
    List<Rating> sortByStars();

    @Query("SELECT r from rating r ORDER BY last_modified_date")
    List<Rating> sortByDate();



}
