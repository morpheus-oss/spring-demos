package dev.guestbook.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Hotel(long id, String name, String street, Locality locality, String pincode, LocalDateTime addedDate) {
}
