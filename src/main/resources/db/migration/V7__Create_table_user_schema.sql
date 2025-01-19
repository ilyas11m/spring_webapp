CREATE TABLE IF NOT EXISTS users (
                                     user_id SERIAL PRIMARY KEY,
                                     name VARCHAR(30) NOT NULL,
                                     email VARCHAR(50) UNIQUE NOT NULL,
                                     password_hash VARCHAR(60) NOT NULL,
                                     role VARCHAR(10) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);
