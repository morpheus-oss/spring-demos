package dev.guestbook.service;

import dev.guestbook.repo.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;

    CityService(CityRepository cityRepo)    {
        this.cityRepository = cityRepo;
    }
}
