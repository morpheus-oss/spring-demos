package dev.guestbook.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

/*
CREATE TABLE IF NOT EXISTS "rating" (
	"id" serial NOT NULL UNIQUE,
	"hotel_id" bigint NOT NULL,
	"guest_name" varchar(255) NOT NULL,
	"guest_email" varchar(255) NOT NULL,
	"guest_phone" varchar(15) NOT NULL,
	"stars" int NOT NULL,
	"description" varchar(250) NOT NULL,
	"added_date" timestamp NOT NULL,
	"last_modified_date" timestamp NOT NULL,
	PRIMARY KEY ("rating_id")
);

ALTER TABLE "rating" ADD CONSTRAINT "fk_rating_hotel" FOREIGN KEY ("hotel_id") REFERENCES "hotel"("id");
 */

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "guest_phone")
    private String guestPhone;

    @Column
    private int stars;

    @Column
    private String description;

    @Column(name = "added_date")
    private Timestamp addedDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

}
