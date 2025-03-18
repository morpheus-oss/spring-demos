package dev.guestbook.repo;

import dev.guestbook.entities.Locality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalityRepository extends JpaRepository<Locality, Long> {

}
