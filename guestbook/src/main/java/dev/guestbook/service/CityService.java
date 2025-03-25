package dev.guestbook.service;

import dev.guestbook.entities.City;
import dev.guestbook.exception.ResourceNotFoundException;
import dev.guestbook.repo.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepo)    {
        this.cityRepository = cityRepo;
    }

    public City addCity(City city)  {
        return cityRepository.saveAndFlush(city);
    }

    public Optional<City> getCity(Long id) {
        return cityRepository.findById(id);
    }

    public City updateCity(City city)   {
        return cityRepository.save(city);
    }

    public Page<City> listCities(Pageable pageable)  {
        return cityRepository.findAll(pageable);
    }
}
