package dev.guestbook.repo;

import dev.guestbook.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

//    @Query(value = "SELECT c from city c WHERE c.state.id=?1")
//    List<City> findByState(Long stateId);

//    Page<City> pageCitiesByState(Long stateId, Pageable pageable);
}
