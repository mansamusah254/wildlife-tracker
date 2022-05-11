CREATE DATABASE wildlife_tracker;

\c wildlife_tracker;

CREATE TABLE animals (animal_name varchar, animal_age int, animal_id int);
CREATE TABLE sightings (animal varchar, location varchar, ranger_name varchar, id serial PRIMARY KEY);