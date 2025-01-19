CREATE TABLE IF NOT EXISTS airplanes (
                                         airplane_id SERIAL PRIMARY KEY,
                                         model VARCHAR(100) NOT NULL,
                                         capacity INT NOT NULL,
                                         distance INT NOT NULL
);
