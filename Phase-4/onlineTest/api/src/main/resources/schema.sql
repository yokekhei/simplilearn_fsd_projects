DROP DATABASE IF EXISTS OnlineTest;
CREATE DATABASE OnlineTest;
USE OnlineTest;
DROP USER IF EXISTS 'onlinetest_user'@'localhost';
CREATE USER 'onlinetest_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'onlinetest_user'@'localhost';
FLUSH PRIVILEGES;
DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (category_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_role CHAR(1) NOT NULL,
    user_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Quizzes;
CREATE TABLE Quizzes (
    quiz_id BIGINT NOT NULL AUTO_INCREMENT,
    quiz_name VARCHAR(255) NOT NULL,
    quiz_category BIGINT NOT NULL,
    quiz_tester VARCHAR(255) NOT NULL,
    quiz_image BLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (quiz_id),
    CONSTRAINT fk_quiz_category FOREIGN KEY (quiz_category) REFERENCES Categories(category_id),
    CONSTRAINT fk_quiz_tester FOREIGN KEY (quiz_tester) REFERENCES Users(user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Questions;
CREATE TABLE Questions (
    question_id BIGINT NOT NULL AUTO_INCREMENT,
    question_quiz BIGINT NOT NULL,
    question_desc VARCHAR(255) NOT NULL,
    PRIMARY KEY (question_id),
    CONSTRAINT fk_question_quiz FOREIGN KEY (question_quiz) REFERENCES Quizzes(quiz_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Choices;
CREATE TABLE Choices (
    choice_id BIGINT NOT NULL AUTO_INCREMENT,
    choice_question BIGINT NOT NULL,
    choice_desc VARCHAR(255) NOT NULL,
    PRIMARY KEY (choice_id),
    CONSTRAINT fk_choice_question FOREIGN KEY (choice_question) REFERENCES Questions(question_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Answers;
CREATE TABLE Answers (
    answer_id BIGINT NOT NULL AUTO_INCREMENT,
    answer_question BIGINT NOT NULL,
    answer_choice BIGINT NOT NULL,
    PRIMARY KEY (answer_id),
    CONSTRAINT fk_answer_question FOREIGN KEY (answer_question) REFERENCES Questions(question_id),
    CONSTRAINT fk_answer_choice FOREIGN KEY (answer_choice) REFERENCES Choices(choice_id)
) ENGINE=INNODB;
