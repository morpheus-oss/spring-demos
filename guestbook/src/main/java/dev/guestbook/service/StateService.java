package dev.guestbook.service;

import dev.guestbook.entities.State;
import dev.guestbook.repo.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository)   {
        this.stateRepository = stateRepository;
    }

    public List<State> listAll()  {
        return this.stateRepository.findAll();
    }

    public State getById(Long id) {
        return stateRepository.getReferenceById(id);
    }

}
