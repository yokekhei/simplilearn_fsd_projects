insert into users(user_email, user_password, user_username, user_role, user_enabled, created_at)
values('admin@lacademy.com', 'Pa$sw0rd', 'admin', 'A', true, sysdate());
insert into users(user_email, user_password, user_username, user_role, user_enabled, created_at)
values('johndoe@gmail.com', 'Pa$sw0rd', 'johndoe', 'U', true, sysdate());

insert into courses(course_id, course_name, course_desc, course_enabled, created_at)
values(1, 'The Complete Java Certification Course', 'Become a confident industry ready core Java developer and get certified as a Java professional!', true, sysdate());
insert into courses(course_id, course_name, course_desc, course_enabled, created_at)
values(2, 'Beginning C++ Programming - From Beginner to Beyond', 'Obtain Modern C++ Object-Oriented Programming (OOP) and STL skills needed for game, system, and application development.', true, sysdate());

insert into student_course(student_id, course_id)
values('johndoe@gmail.com', 1);
insert into student_course(student_id, course_id)
values('johndoe@gmail.com', 2);