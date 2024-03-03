CREATE TABLE Category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL
);

CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    category INT,
    quantity INT,
    price DOUBLE,
    FOREIGN KEY (category) REFERENCES Category(id)
);
