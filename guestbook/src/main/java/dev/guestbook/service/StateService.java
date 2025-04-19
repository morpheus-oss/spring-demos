package dev.guestbook.service;

import dev.guestbook.entities.State;
import dev.guestbook.repo.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository)   {
        this.stateRepository = stateRepository;
    }

    public List<State> listAll()  {
        return stateRepository.findAll();
    }

    public Page<State> pageAll(Pageable pageable)    {
        return stateRepository.findAll(pageable);
    }

    public State getById(Long id) {
        return stateRepository.getReferenceById(id);
    }

}
