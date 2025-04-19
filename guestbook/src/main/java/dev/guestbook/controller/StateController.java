package dev.guestbook.controller;

import dev.guestbook.entities.State;
import dev.guestbook.service.StateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService)   {
        this.stateService = stateService;
    }

    @GetMapping("/state?page={pageNUm}&pageSize={pageNum}")
    public ResponseEntity<Page<State>> listStates(@RequestParam(name = "page", required = false) int pageNum,
                                                  @RequestParam(name = "pageSize", required = false) int pageSize)     {
        if (pageNum <= 1)   pageNum = 1;
        if (pageSize <= 0)  pageSize = 10;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<State> listStates = stateService.pageAll(pageable);
        return !listStates.isEmpty() ?
                ResponseEntity.ok(listStates) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/state/{stateId}")
    public ResponseEntity<State> getState(@PathVariable Long stateId) {
        Optional<State> state = Optional.ofNullable(stateService.getById(stateId));
        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
