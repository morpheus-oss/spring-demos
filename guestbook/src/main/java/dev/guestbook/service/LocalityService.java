package dev.guestbook.service;

import dev.guestbook.repo.LocalityRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalityService {

    private final LocalityRepository localityRepository;
    public LocalityService(LocalityRepository localityRepo)     {
        this.localityRepository = localityRepo;
    }



}
