package dev.guestbook.domain;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RatingOut(long hotelId, String guestName, String guestEmail, String guestPhone,
                        int stars, String comment, LocalDateTime commentedDate) {
}
