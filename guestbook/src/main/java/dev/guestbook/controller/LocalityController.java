package dev.guestbook.controller;

import dev.guestbook.domain.CityOut;
import dev.guestbook.domain.LocalityIn;
import dev.guestbook.domain.LocalityOut;
import dev.guestbook.entities.City;
import dev.guestbook.entities.Locality;
import dev.guestbook.service.CityService;
import dev.guestbook.service.LocalityService;
import dev.guestbook.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LocalityController {

    private final LocalityService localityService;
    private final CityService cityService;

    @Autowired
    public LocalityController(LocalityService localityService, CityService cityService)  {
        this.localityService = localityService;
        this.cityService = cityService;
    }

    @PostMapping("/locality")
    public ResponseEntity<LocalityOut> addLocality(@RequestBody LocalityIn localityIn)     {

        Optional<City> city = cityService.getCityEntity(localityIn.cityId());
        if (city.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Locality locality = Locality.builder()
                                    .city(city.get())
                                    .name(localityIn.name())
                                    .pincode(localityIn.pincode())
                                    .build();
        return ResponseEntity.ok(localityService.addLocality(locality));
    }

    @GetMapping("/locality/{localityId}")
    public ResponseEntity<Locality> getLocality(@PathVariable(name = "localityId") Long localityId)   {

        Optional<Locality> locality = Optional.ofNullable(localityService.getLocality(localityId));
        return locality.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/locality?page={pageNum}&pageSize={pageSize}")
    public Page<Locality> listLocalities(@RequestParam(name = "page", required = false) int pageNum,
                                         @RequestParam(name = "pageSize", required = false) int pageSize)  {
        return localityService.listLocalities(ControllerUtil.createPageable(pageNum, pageSize));
    }
}
