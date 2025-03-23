CREATE TABLE IF NOT EXISTS state  (
    id serial NOT NULL PRIMARY KEY,
    name  varchar(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS city (
	id serial NOT NULL PRIMARY KEY,
	name varchar(80) NOT NULL,
	state_id bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS locality (
	id serial NOT NULL PRIMARY KEY,
	name varchar(255) NOT NULL,
	city_id bigint NOT NULL,
	pincode VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS hotel (
	id serial NOT NULL PRIMARY KEY,
	name varchar(200) NOT NULL,
	street varchar(250) NOT NULL,
	locality_id bigint NOT NULL,
	added_date timestamp NOT NULL,
	last_modified_date timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS rating (
	id serial NOT NULL PRIMARY KEY,
	hotel_id bigint NOT NULL,
	guest_name varchar(255) NOT NULL,
	guest_email varchar(255) NOT NULL,
	guest_phone varchar(15) NOT NULL,
	stars int NOT NULL,
	description varchar(250) NOT NULL,
	added_date timestamp NOT NULL,
	last_modified_date timestamp NOT NULL
);

ALTER TABLE city ADD CONSTRAINT fk_city_state FOREIGN KEY ("state_id") REFERENCES state("id");
ALTER TABLE locality ADD CONSTRAINT fk_locality_city FOREIGN KEY ("city_id") REFERENCES city("id");
ALTER TABLE hotel ADD CONSTRAINT fk_hotel_locality FOREIGN KEY ("locality_id") REFERENCES locality("id");
ALTER TABLE rating ADD CONSTRAINT fk_rating_hotel FOREIGN KEY ("hotel_id") REFERENCES hotel("id");


INSERT INTO state VALUES
    (1, 'Andhra Pradesh'),
    (2, 'Arunachal Pradesh'),
    (3, 'Assam'),
    (4, 'Bihar'),
    (5, 'Chhattisgarh'),
    (6, 'Goa'),
    (7, 'Gujarat'),
    (8, 'Haryana'),
    (9, 'Himachal Pradesh'),
    (10, 'Jharkhand'),
    (11, 'Karnataka'),
    (12, 'Kerala'),
    (13, 'Madhya Pradesh'),
    (14, 'Maharashtra'),
    (15, 'Manipur'),
    (16, 'Meghalaya'),
    (17, 'Mizoram'),
    (18, 'Nagaland'),
    (19, 'Odisha'),
    (20, 'Punjab'),
    (21, 'Rajasthan'),
    (22, 'Sikkim'),
    (23, 'Tamil Nadu'),
    (24, 'Telangana'),
    (25, 'Tripura'),
    (26, 'Uttar Pradesh'),
    (27, 'Uttarakhand'),
    (28, 'West Bengal'),
    (29, 'Andaman and Nicobar Islands'),
    (30, 'Chandigarh'),
    (31, 'Dadra and Nagar Haveli and Daman and Diu'),
    (32, 'Delhi (NCR)'),
    (33, 'Jammu and Kashmir'),
    (34, 'Ladakh'),
    (35, 'Lakshadweep'),
    (36, 'Puducherry');
