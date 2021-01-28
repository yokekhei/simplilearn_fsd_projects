USE OnlineTest;

delete from categories;
insert into categories(category_id, category_name) values (1, 'Mathematics');
insert into categories(category_id, category_name) values (2, 'English and Language Arts');
insert into categories(category_id, category_name) values (3, 'Computer Science and Skills');
insert into categories(category_id, category_name) values (4, 'Social Studies');

delete from users;
insert into users (user_email, user_password, user_name, user_role)
values ('admin@onlintest.com', 'Pa$sw0rd', 'admin', 'A');
insert into users (user_email, user_password, user_name, user_role)
values ('johndoe@gmail.com', 'Pa$sw0rd', 'johndoe', 'R');
insert into users (user_email, user_password, user_name, user_role)
values ('marry@gmail.com', 'Pa$sw0rd', 'marry', 'E');

delete from answers;
delete from choices;
delete from questions;
delete from quizzes;
insert into quizzes (quiz_id, quiz_name, quiz_category, quiz_tester)
values (1, 'Addition', 1, 'johndoe@gmail.com');
insert into questions (question_id, question_quiz, question_desc)
values (1, 1, '1 + 1 = ?');
insert into questions (question_id, question_quiz, question_desc)
values (2, 1, '2 + 3 = ?');
insert into choices (choice_id, choice_question, choice_desc)
values (1, 1, '2');
insert into choices (choice_id, choice_question, choice_desc)
values (2, 1, '1');
insert into choices (choice_id, choice_question, choice_desc)
values (3, 1, '3');
insert into choices (choice_id, choice_question, choice_desc)
values (4, 1, '0');
insert into choices (choice_id, choice_question, choice_desc)
values (5, 2, '3');
insert into choices (choice_id, choice_question, choice_desc)
values (6, 2, '4');
insert into choices (choice_id, choice_question, choice_desc)
values (7, 2, '8');
insert into choices (choice_id, choice_question, choice_desc)
values (8, 2, '5');
insert into answers (answer_id, answer_question, answer_choice)
values (1, 1, 1);
insert into answers (answer_id, answer_question, answer_choice)
values (2, 2, 8);
insert into quizzes (quiz_id, quiz_name, quiz_category, quiz_tester)
values (2, '1st Grade', 2, 'johndoe@gmail.com');
insert into questions (question_id, question_quiz, question_desc)
values (3, 2, 'What are you giving Max for his birthday?');
insert into questions (question_id, question_quiz, question_desc)
values (4, 2, '______? There is just a little.');
insert into questions (question_id, question_quiz, question_desc)
values (5, 2, 'I''ve ______ a terrible headache.');
insert into choices (choice_id, choice_question, choice_desc)
values (9, 3, 'I give him a gold watch.');
insert into choices (choice_id, choice_question, choice_desc)
values (10, 3, 'I''m giving him this antique watch.');
insert into choices (choice_id, choice_question, choice_desc)
values (11, 3, 'I''m giving him a few money.');
insert into choices (choice_id, choice_question, choice_desc)
values (12, 3, 'I''m giving a gold watch him.');
insert into choices (choice_id, choice_question, choice_desc)
values (13, 4, 'Is there an ice cream left?');
insert into choices (choice_id, choice_question, choice_desc)
values (14, 4, 'Is there left any ice cream?');
insert into choices (choice_id, choice_question, choice_desc)
values (15, 4, 'Is there any ice cream left?');
insert into choices (choice_id, choice_question, choice_desc)
values (16, 4, 'There''s some ice cream left?');
insert into choices (choice_id, choice_question, choice_desc)
values (17, 5, 'have');
insert into choices (choice_id, choice_question, choice_desc)
values (18, 5, 'gets');
insert into choices (choice_id, choice_question, choice_desc)
values (19, 5, 'has');
insert into choices (choice_id, choice_question, choice_desc)
values (20, 5, 'got');
insert into answers (answer_id, answer_question, answer_choice)
values (3, 3, 10);
insert into answers (answer_id, answer_question, answer_choice)
values (4, 4, 15);
insert into answers (answer_id, answer_question, answer_choice)
values (6, 5, 20);

