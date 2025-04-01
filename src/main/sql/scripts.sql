CREATE TABLE IF NOT EXISTS employees(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    education VARCHAR NOT NULL DEFAULT '',
    address VARCHAR NOT NULL DEFAULT '',
    date_hired DATE NOT NULL
);

SELECT * FROM employees;

INSERT INTO employees(first_name, last_name, date_of_birth, date_hired)
VALUES ('Sergey', 'Nesterenko', '09.08.2004', '01.04.2025');

INSERT INTO employees(first_name, last_name, date_of_birth, date_hired)
VALUES ('Ekaterina', 'Starova', '24.01.2004', '02.04.2025');

INSERT INTO employees(first_name, last_name, date_of_birth, date_hired)
VALUES ('Pavel', 'Viktor', '26.05.1969', '07.12.2007');

INSERT INTO employees(first_name, last_name, date_of_birth, date_hired)
VALUES ('Alexander', 'Krivintsov', '27.02.2004', '15.03.2024');

INSERT INTO employees(first_name, last_name, date_of_birth, date_hired)
VALUES ('Dmitry', 'Kamzychakov', '15.03.2004', '17.07.2017');

