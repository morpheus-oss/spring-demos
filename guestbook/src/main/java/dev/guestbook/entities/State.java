package dev.guestbook.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "state")
public class State {

    @Id
    private Long id;

    @Column
    private String name;

    public State(dev.guestbook.domain.State state) {
        this.id = state.id();
        this.name = state.name();
    }
}
