INSERT INTO course (id, name) VALUES (100, 'Spring JPA');
INSERT INTO course (id, name) VALUES (200, 'Spring Security');
INSERT INTO course (id, name) VALUES (300, 'ReactJS');
INSERT INTO course (id, name) VALUES (400, 'AngularJS');

INSERT INTO course (id, name) VALUES (401, 'Dummy 401');
INSERT INTO course (id, name) VALUES (402, 'Dummy 402');
INSERT INTO course (id, name) VALUES (403, 'Dummy 403');
INSERT INTO course (id, name) VALUES (404, 'Dummy 404');
INSERT INTO course (id, name) VALUES (405, 'Dummy 405');
INSERT INTO course (id, name) VALUES (406, 'Dummy 406');
INSERT INTO course (id, name) VALUES (407, 'Dummy 407');
INSERT INTO course (id, name) VALUES (408, 'Dummy 408');
INSERT INTO course (id, name) VALUES (409, 'Dummy 409');
INSERT INTO course (id, name) VALUES (410, 'Dummy 410');
INSERT INTO course (id, name) VALUES (411, 'Dummy 411');
INSERT INTO course (id, name) VALUES (412, 'Dummy 412');
INSERT INTO course (id, name) VALUES (413, 'Dummy 413');
INSERT INTO course (id, name) VALUES (414, 'Dummy 414');
INSERT INTO course (id, name) VALUES (415, 'Dummy 415');
INSERT INTO course (id, name) VALUES (416, 'Dummy 416');

INSERT INTO passport (id, number) VALUES (2001, 'W123456');
INSERT INTO passport (id, number) VALUES (2002, 'G123467');
INSERT INTO passport (id, number) VALUES (2003, 'B237655');

INSERT INTO address (id, city) VALUES (4001, 'Pune');
INSERT INTO address (id, city) VALUES (4002, 'Mumbai');
INSERT INTO address (id, city) VALUES (4003, 'Delhi');

INSERT INTO student (id, name, passport_id, address_id) VALUES (1001, 'Nilesh', 2001, 4001);
INSERT INTO student (id, name, passport_id, address_id) VALUES (1002, 'Shailesh', 2002, 4002);
INSERT INTO student (id, name, passport_id, address_id) VALUES (1003, 'Kamlesh', 2003, 4003);

INSERT INTO review (id, rating, description, course_id) VALUES (3001, 'ONE', 'Great course', 100);
INSERT INTO review (id, rating, description, course_id) VALUES (3002, 'FIVE', 'Average course', 100);
INSERT INTO review (id, rating, description, course_id) VALUES (3003, 'THREE', 'Bad course', 300);

INSERT INTO student_course (student_id, course_id) VALUES (1001, 100);
INSERT INTO student_course (student_id, course_id) VALUES (1001, 200);
INSERT INTO student_course (student_id, course_id) VALUES (1001, 300);
INSERT INTO student_course (student_id, course_id) VALUES (1002, 300);
INSERT INTO student_course (student_id, course_id) VALUES (1003, 200);
INSERT INTO student_course (student_id, course_id) VALUES (1003, 300);