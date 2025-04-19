package dev.guestbook.service;

import dev.guestbook.domain.LocalityOut;
import dev.guestbook.entities.Locality;
import dev.guestbook.repo.LocalityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalityService {

    private final LocalityRepository localityRepository;
    public LocalityService(LocalityRepository localityRepo)     {
        this.localityRepository = localityRepo;
    }

    public LocalityOut addLocality(Locality locality)  {
        Locality locality1 =  localityRepository.saveAndFlush(locality);

        return LocalityOut.builder()
                        .id(locality1.getId())
                        .name(locality1.getName())
                        .cityId(locality1.getCity().getId())
                        .pincode(locality1.getPincode())
                        .build();
    }

    public Locality getLocality(Long localityId)    {
        return localityRepository.getReferenceById(localityId);
    }

    public Page<Locality> listLocalities(Pageable pageable)     {
        return localityRepository.findAll(pageable);
    }


}
