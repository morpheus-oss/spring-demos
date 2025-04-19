package dev.guestbook.service;

import dev.guestbook.domain.CityOut;
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

    public CityOut addCity(City city)  {
        City out = cityRepository.saveAndFlush(city);
        return new CityOut(out.getId(), out.getName(), out.getState().getId());
    }

    public Optional<City> getCityEntity(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<CityOut> getCity(Long id) {
        Optional<City> city = cityRepository.findById(id);

        return city.map(c -> new CityOut(c.getId(), c.getName(), c.getState().getId()));
    }


    public City updateCity(City city)   {
        return cityRepository.save(city);
    }

    public Page<City> listCities(Pageable pageable)  {
        return cityRepository.findAll(pageable);
    }

    public List<City> listAllCities()   {
        return cityRepository.findAll();
    }
/*
    public List<City> listCitiesByState(Long stateId)   {
        return cityRepository.findByState(stateId);
    }

    public Page<City> pageCitiesByState(Long stateId, Pageable pageable)   {
        return cityRepository.pageCitiesByState(stateId, pageable);
    }
*/

}
