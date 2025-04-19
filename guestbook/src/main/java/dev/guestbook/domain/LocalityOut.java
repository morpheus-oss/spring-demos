package dev.guestbook.domain;

import lombok.Builder;

@Builder
public record LocalityOut(long id, String name, long cityId, String pincode) {
}