package dev.guestbook.controller;

import dev.guestbook.entities.State;
import dev.guestbook.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping("/state")
    public ResponseEntity<List<State>> listStates()     {
        List<State> listStates = stateService.listAll();
        return !listStates.isEmpty() ?
                ResponseEntity.ok(listStates) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/state/{stateId}")
    public ResponseEntity<State> getState(@PathVariable Long stateId) {
        Optional<State> state = Optional.ofNullable(stateService.findById(stateId));
        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
