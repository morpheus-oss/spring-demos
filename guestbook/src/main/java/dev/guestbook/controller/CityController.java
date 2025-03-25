package dev.guestbook.controller;

import dev.guestbook.domain.CityIn;
import dev.guestbook.entities.City;
import dev.guestbook.entities.State;
import dev.guestbook.exception.ResourceNotFoundException;
import dev.guestbook.service.CityService;
import dev.guestbook.service.StateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CityController {

    private final CityService cityService;
    private final StateService stateService;

    public CityController(CityService cityService, StateService stateService)  {
        this.cityService = cityService;
        this.stateService = stateService;
    }

    @PostMapping("/city")
    public City addCity(@RequestBody CityIn cityIn) {
        State state = stateService.getById(cityIn.stateId());
        City city = City.builder()
                        .name(cityIn.name())
                        .state(state)
                        .build();
        return cityService.addCity(city);
    }

    @GetMapping("/city?page={pageNum}&pageSize={pageSize}")
    public Page<City> listCities(@Param("pageNum") int pageNum,
                                 @Param("pageSize") int pageSize)   {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return cityService.listCities(pageable);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<City> getCity(@PathVariable long cityId)  {
        Optional<City> city = cityService.getCity(cityId);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
