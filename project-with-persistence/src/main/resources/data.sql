INSERT INTO Project(id, code, name, description) VALUES (1, 'P1', 'Project 1', 'Description of Project 1');
INSERT INTO Project(id, code, name, description) VALUES (2, 'P2', 'Project 2', 'About Project 2');
INSERT INTO Project(id, code, name, description) VALUES (3, 'P3', 'Project 3', 'About Project 3');

INSERT INTO User(id, email, first_name, last_name) VALUES(1, 'nicu.rosu@cfrcalatori.com', 'nicu', 'rosu');
INSERT INTO User(id, email, first_name, last_name) VALUES(2, 'popescu.ion@cfrcalatori.com', 'ion', 'popescu');
INSERT INTO User(id, email, first_name, last_name) VALUES(3, 'popa.ionut@cfrcalatori.com', 'ionut', 'popa');
INSERT INTO User(id, email, first_name, last_name) VALUES(4, 'vasile.stan@cfrcalatori.com', 'vasile', 'stan');

INSERT INTO Task(id, name, due_date, description, project_id, status, assignee_id) VALUES (1, 'Task 1', '2025-01-12', 'Task 1 Description', 1, 0, 2);
INSERT INTO Task(id, name, due_date, description, project_id, status, assignee_id) VALUES (2, 'Task 2', '2025-02-10', 'Task 2 Description', 1, 0, 3);
INSERT INTO Task(id, name, due_date, description, project_id, status, assignee_id) VALUES (3, 'Task 3', '2021-03-16', 'Task 3 Description', 1, 0, 4);
INSERT INTO Task(id, name, due_date, description, project_id, status, assignee_id) VALUES (4, 'Task 4', '2025-06-25', 'Task 4 Description', 2, 0, 1);