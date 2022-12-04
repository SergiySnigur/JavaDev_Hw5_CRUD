-- create a worker table
CREATE TABLE IF NOT EXISTS worker (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000)
         CONSTRAINT check_worker_name
         CHECK(CHAR_LENGTH(name) >= 2 AND CHAR_LENGTH(name) <= 1000) NOT NULL,
    birthday DATE
             CONSTRAINT check_date_birthday
             CHECK(birthday >'1900-01-01'),
    level VARCHAR(100)
          CONSTRAINT check_level
          CHECK(level IN ('Trainee', 'Junior', 'Middle', 'Senior')) NOT NULL,
    salary BIGINT
           CONSTRAINT check_salary_value
           CHECK(salary >= 100 AND salary<= 100000)
);

-- create a client table
CREATE TABLE IF NOT EXISTS client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000)
         CONSTRAINT check_name_client
         CHECK(CHAR_LENGTH(name) >= 2 AND CHAR_LENGTH(name) <= 1000) NOT NULL
);

-- create a project table
CREATE TABLE IF NOT EXISTS project (
    id IDENTITY PRIMARY KEY,
    client_id BIGINT NOT NULL,
    start_date DATE,
    finish_date DATE
);

-- create a project_worker table
CREATE TABLE IF NOT EXISTS project_worker(
project_id BIGINT NOT NULL,
worker_id BIGINT NOT NULL,
PRIMARY KEY(project_id, worker_id),
FOREIGN KEY(project_id) REFERENCES project(id),
FOREIGN KEY(worker_id) REFERENCES worker(id)
);

-- add constraint between project table and client table
ALTER TABLE project
ADD CONSTRAINT client_id_fk
FOREIGN KEY(client_id)
REFERENCES client(id);