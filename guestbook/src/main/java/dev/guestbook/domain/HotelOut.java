package dev.guestbook.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HotelOut(String name, String street, long localityId, String pincode, LocalDateTime addedDate) {
}
