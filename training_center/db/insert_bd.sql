INSERT INTO company VALUES (DEFAULT, 'Tinkoff', 'Moscow, Golovinskoye road, 5');
INSERT INTO company VALUES (DEFAULT, 'Russian school of management', 'Moscow, Selskohozyastvennaya street, 17');
INSERT INTO company VALUES (DEFAULT, 'Academy of leadership competencies', 'Moscow, Dubininskaya street, 40');

--passwords for ptofessors - prof_i, i = 1..4
INSERT INTO professor VALUES (DEFAULT, 'arkaD', 'd3c6eb0075ad5d507caf01dd6e552cc9', 'Arkadiev', 'Dmitriy', 1);
INSERT INTO professor VALUES (DEFAULT, 'kornvictor', '6ecd969e26d35442388abf4af5d3d04a', 'Korneev', 'Victor', 2);
INSERT INTO professor VALUES (DEFAULT, 'simonya', '398b05a88e480ec9f618b4f7497ff3f0', 'Simonova', 'Ekaterina', 1);
INSERT INTO professor VALUES (DEFAULT, 'bolsh', 'a8c89bcb316e486ac3f62e9dd5b03b39', 'Bolshunov', 'Arkadiy', 3);

INSERT INTO course VALUES (DEFAULT, 'Basics of banking', '1 month', '1 hour', 1);
INSERT INTO course VALUES (DEFAULT, 'Legal culture', '1 month', '2 hours', 2);
INSERT INTO course VALUES (DEFAULT, 'Mathematical statistics in banking', '1 month', '1 hour', 1);
INSERT INTO course VALUES (DEFAULT, 'You are a leader! What is next?', '3 weeks', '2 hours', 4);

INSERT INTO lesson VALUES (DEFAULT, 3, TIMESTAMP '2020-03-01 15:00');
INSERT INTO lesson VALUES (DEFAULT, 1, TIMESTAMP '2020-03-05 16:30');
INSERT INTO lesson VALUES (DEFAULT, 2, TIMESTAMP '2020-03-02 14:00');

--passwords for students - stud_i, i = 1..5
INSERT INTO student VALUES (DEFAULT, 'ivkoz', 'cdf5e358a40de8e681ac9014c37badef', 'Kozlov', 'Ivan', 89125431212);
INSERT INTO student VALUES (DEFAULT, 'bolya', '1b06c1c926fac6898f6d884a2725d907', 'Bolshunova', 'Irina', 89124676425);
INSERT INTO student VALUES (DEFAULT, 'melk', '212ba19b0522e39a46cd56bced3cf37e', 'Menshov', 'Dmitriy', 89235124122);
INSERT INTO student VALUES (DEFAULT, 'ivanka', '0628836d3056aaf2aa3c6451a4283a5d', 'Ivanova', 'Alisa', 89235521111);
INSERT INTO student VALUES (DEFAULT, 'alfa', '8a73ed74dfb98ee945e131ca8b6499e9', 'Alferov', 'Andrey', 89456723412);

INSERT INTO stdLess VALUES (1, 4);
INSERT INTO stdLess VALUES (1, 1);
INSERT INTO stdLess VALUES (1, 3);
INSERT INTO stdLess VALUES (2, 1);
INSERT INTO stdLess VALUES (2, 4);
INSERT INTO stdLess VALUES (3, 4);
INSERT INTO stdLess VALUES (2, 2);
INSERT INTO stdLess VALUES (5, 4);
INSERT INTO stdLess VALUES (5, 3);
INSERT INTO stdLess VALUES (4, 2);

--password for admin - admin
INSERT INTO admin_acc VALUES (DEFAULT, 'admin', 'f189656226a53e50eae44f80d4befb6e', 'Araratova', 'Diana');