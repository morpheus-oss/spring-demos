package dev.guestbook.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/*

CREATE TABLE IF NOT EXISTS "hotel" (
	"id" serial NOT NULL UNIQUE,
	"name" varchar(200) NOT NULL,
	"street" varchar(250) NOT NULL,
	"locality_id" bigint NOT NULL,
	"added_date" timestamp NOT NULL,
	"last_modified_date" timestamp NOT NULL,
	PRIMARY KEY ("id")
);

ALTER TABLE "hotel" ADD CONSTRAINT "fk_hotel_locality" FOREIGN KEY ("locality_id") REFERENCES "locality"("id");
 */
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String street;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "locality_id", referencedColumnName = "id")
    private Locality locality;

    @Column(name = "added_date")
    private Timestamp addedDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;
/*

    public Hotel(dev.guestbook.domain.Hotel hotel)  {
        this.name = hotel.name();
        this.street = hotel.street();
        this.locality = new Locality(hotel.locality());
    }
*/

}
