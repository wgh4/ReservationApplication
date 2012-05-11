create table PITCH_RESERVATIONS(id serial, comments varchar, trainer varchar, team varchar, pitch varchar, submit_date timestamp, reservation_date timestamp, primary key(id), CONSTRAINT reservation UNIQUE (reservation_date,pitch));

