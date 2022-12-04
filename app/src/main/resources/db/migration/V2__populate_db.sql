-- insert values into worker table
INSERT INTO worker
(name, birthday, level, salary)
VALUES
     ('Serhii', '1994-12-31', 'Senior', 5500),
     ('Kate', '1994-01-01', 'Middle', 5900),
     ('Dmitro', '1996-04-13', 'Middle', 2500),
     ('Aziz', '1996-08-25', 'Junior', 800),
     ('Oleg', '1996-02-15', 'Trainee', 400),
     ('Roma', '1997-05-18', 'Junior', 900),
     ('Andrew', '1997-05-18', 'Junior', 900),
     ('Valeriy', '1975-12-14', 'Senior', 7000),
     ('Diana', '1999-04-25', 'Junior', 700),
     ('Den', '1996-07-13', 'Middle', 1500),
     ('Alex', '1991-12-03', 'Senior', 7000),
     ('Oleksandr', '1999-04-25', 'Junior', 800);

     -- insert values into client table
INSERT INTO client (name)
VALUES
     ('John'),
     ('Skot'),
     ('Luke'),
     ('Kevin'),
     ('Rayan');

    --  insert values into project table
INSERT INTO project
(client_id, start_date, finish_date)
VALUES
     (1, '2022-01-01', '2022-06-06'),
     (1, '2022-03-13', '2022-12-30'),
     (2, '2022-02-14', '2022-07-15'),
     (4, '2022-11-15', '2023-06-03'),
     (5, '2022-05-01', '2022-08-30'),
     (3, '2022-02-14', '2022-07-15'),
     (5, '2022-08-05', '2023-01-01'),
     (4, '2022-12-14', '2023-07-05'),
     (3, '2022-02-14', '2022-07-15'),
     (2, '2022-04-30', '2022-10-25'),
     (1, '2022-11-15', '2023-06-12');

    --  insert values into project_worker table
INSERT INTO project_worker
(project_id, worker_id)
VALUES
     (1,1),
     (1,3),
     (2,5),
     (2,2),
     (3,1),
     (3,10),
     (3,4),
     (4,6),
     (4,3),
     (5,7),
     (6,8),
     (6,10),
     (6,4),
     (7,9),
     (7,7),
     (8,1),
     (8,2),
     (9,10),
     (10,5),
     (10,6),
     (10,7),
     (10,9);