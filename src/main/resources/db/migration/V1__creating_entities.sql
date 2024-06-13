CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       username VARCHAR(255),
                       password VARCHAR(255),
                       role VARCHAR(255)
);

CREATE TABLE trips(
              id SERIAL PRIMARY KEY ,
              created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
              updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
              created_by VARCHAR(255),
              user_id INTEGER,
              origin VARCHAR(255),
              destination VARCHAR(255),
              available_seats INTEGER,
              total_seats INTEGER,
              route VARCHAR(255),
              FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE reservations(
              id SERIAL PRIMARY KEY ,
              created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
              updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
              created_by VARCHAR(255),
              user_id INTEGER,
              trip_id INTEGER,
              seat_number INTEGER,
              FOREIGN KEY (user_id) REFERENCES users(id),
              FOREIGN KEY (trip_id) REFERENCES trips(id)
);

insert into users(id, created_at, first_name, last_name, username, password, role)
values (1,
        current_timestamp,
        'Arianit',
        'Likaj',
        'superadmin',
        '$2a$10$Cz9xGfJtHLAPEZIHsHQat.udW9TdYwHKlqP9OZT9dSpiThlzGN2dS',
        'SUPER_ADMIN');