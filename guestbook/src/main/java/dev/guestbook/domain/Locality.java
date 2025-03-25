package dev.guestbook.domain;

import lombok.Builder;

@Builder
public record Locality(long id, String name, City city, String pincode) {
}
