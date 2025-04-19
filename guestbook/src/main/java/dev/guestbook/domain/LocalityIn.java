package dev.guestbook.domain;

import lombok.Builder;

@Builder
public record LocalityIn(String name, long cityId, String pincode) {
}
