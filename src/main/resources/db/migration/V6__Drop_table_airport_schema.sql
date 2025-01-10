ALTER TABLE flights
    DROP CONSTRAINT IF EXISTS flights_departure_airport_id_fkey,
    DROP COLUMN IF EXISTS departure_airport_id,
    DROP CONSTRAINT IF EXISTS flights_arrival_airport_id_fkey,
    DROP COLUMN IF EXISTS arrival_airport_id;

DROP TABLE IF EXISTS airports;
DELETE FROM flyway_schema_history WHERE script = 'V6__Drop_table_airport_schema.sql';