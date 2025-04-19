package dev.guestbook.domain;

import lombok.Builder;

import java.time.LocalDateTime;

/*
id:
  type: integer
  format: int64
  example: 10
  required: false
hotelId:
  type: integer
  format: int64
  example: 1234
  required: false
guestName:
  type: string
  example: John Doe
guestEmail:
  type: string
  example: john@email.com
guestPhone:
  type: string
  example: '12345'
stars:
  type: integerRating
  example: 5
comment:
  type: string
  example: Your experience in the hotel
commentedDate:
  type: string
  format: date-time
  required: false
 */

@Builder
public record RatingIn(long hotelId, String guestName, String guestEmail, String guestPhone,
                       int stars, String comment, LocalDateTime commentedDate) {
}
