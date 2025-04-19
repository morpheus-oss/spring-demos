package dev.guestbook.controller;

import dev.guestbook.domain.CityIn;
import dev.guestbook.domain.CityOut;
import dev.guestbook.entities.City;
import dev.guestbook.entities.State;
import dev.guestbook.exception.ResourceNotFoundException;
import dev.guestbook.service.CityService;
import dev.guestbook.service.StateService;
import dev.guestbook.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1")
public class CityController {

    private final CityService cityService;
    private final StateService stateService;

    @Autowired
    public CityController(CityService cityService, StateService stateService)  {
        this.cityService = cityService;
        this.stateService = stateService;
    }

    @PostMapping("/city")
    public CityOut addCity(@RequestBody CityIn cityIn) {
        State state = stateService.getById(cityIn.stateId());
        City city = City.builder()
                        .name(cityIn.name())
                        .state(state)
                        .build();
        return cityService.addCity(city);
    }

    @GetMapping("/city?page={pageNum}&pageSize={pageSize}")
    public Page<City> listCities(@RequestParam(name = "page", required = false) int pageNum,
                                 @RequestParam(name = "pageSize", required = false) int pageSize)   {

        return cityService.listCities(ControllerUtil.createPageable(pageNum, pageSize));
    }

    @GetMapping("/city")
    public ResponseEntity<List<City>> listAllCities()   {
        Optional<List<City>> cities = Optional.ofNullable(cityService.listAllCities());
        return (cities.isPresent() && !cities.get().isEmpty()) ?
                ResponseEntity.ok(cities.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<CityOut> getCity(@PathVariable long cityId)  {
        Optional<CityOut> city = cityService.getCity(cityId);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
/*

    @GetMapping("/city/state/{stateId}?page={pageSize}&pageNum={pageNum}")
    public ResponseEntity<Page<City>> listCitiesByState(@PathVariable(name = "stateId") Long stateId,
                                                        @RequestParam(name = "page", required = false) int pageNum,
                                                        @RequestParam(name = "pageSize", required = false) int pageSize)   {

        Pageable pageable = ControllerUtil.createPageable(pageNum, pageSize);

        Optional<Page<City>> cities = Optional.ofNullable(cityService.pageCitiesByState(stateId, pageable));
        return (cities.isPresent() && !cities.get().isEmpty()) ?
                ResponseEntity.ok(cities.get()) :
                ResponseEntity.notFound().build();
    }
*/

}
