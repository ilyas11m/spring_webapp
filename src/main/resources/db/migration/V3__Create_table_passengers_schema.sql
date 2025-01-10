CREATE TABLE if not exists passengers (
                                          passenger_id SERIAL PRIMARY KEY,
                                          first_name VARCHAR(100) NOT NULL,
                                          last_name VARCHAR(100) NOT NULL,
                                          email VARCHAR(100) UNIQUE NOT NULL,
                                          phone_number VARCHAR(20)
);