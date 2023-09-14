INSERT INTO
    person (name, position, telephone, email)
VALUES
    ('Konrad', 'Software engineer', '+48123456789','konrad@email.com'),
    ('John', 'Supervisor', '+49987654321','john@email.com');
INSERT INTO
    task (title, description, category, date_added, date_deadline, task_finished, person_id)
VALUES
    ('Cleaning desktop','Deleting unnecessary files','Improvement','2023-08-01 12:00:00','2023-09-01 12:00:00', 'false', 1),
    ('Developing Spring project','Adding new classes to make application more universal','Improvement','2023-08-02 12:00:00','2023-09-02 12:00:00','false',  1),
    ('Organizing party','Buying tickets for Friday night show','Entertainment','2023-08-03 12:00:00','2023-09-03 12:00:00','false', 2);