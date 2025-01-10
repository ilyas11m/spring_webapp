CREATE TABLE IF NOT EXISTS tickets (
                                       ticket_id SERIAL PRIMARY KEY,
                                       ticket_number VARCHAR(50) UNIQUE NOT NULL,
                                       passenger_id INT NOT NULL,
                                       flight_id INT NOT NULL,
                                       seat_number VARCHAR(10) NOT NULL,
                                       price DECIMAL(10, 2) NOT NULL,
                                       FOREIGN KEY (passenger_id) REFERENCES passengers (passenger_id) ON DELETE CASCADE,
                                       FOREIGN KEY (flight_id) REFERENCES flights (flight_id) ON DELETE CASCADE
);