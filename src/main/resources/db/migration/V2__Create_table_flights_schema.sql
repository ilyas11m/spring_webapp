CREATE TABLE IF NOT EXISTS flights (
                                       flight_id SERIAL PRIMARY KEY,
                                       flight_number VARCHAR(50) UNIQUE NOT NULL,
                                       departure_location VARCHAR(100) NOT NULL,
                                       arrival_location VARCHAR(100) NOT NULL,
                                       duration_minutes INT NOT NULL,
                                       airplane_id INT NOT NULL,
                                       FOREIGN KEY (airplane_id) REFERENCES airplanes (airplane_id) ON DELETE CASCADE
);