/*
CREATE TABLE person (
  id integer not null,
  name varchar(255) not null,
  location varchar(255),
  birth_date timestamp,
  primary key(id)
);
*/

INSERT INTO person (id, name, location, birth_date)
VALUES (1001, 'Nilesh', 'Pune', sysdate());

INSERT INTO person (id, name, location, birth_date)
VALUES (1002, 'Shailesh', 'Amsterdam', sysdate());

INSERT INTO person (id, name, location, birth_date)
VALUES (1003, 'Kamlesh', 'London', sysdate());

INSERT INTO person (id, name, location, birth_date)
VALUES (1004, 'Harsh', 'Sydney', sysdate());