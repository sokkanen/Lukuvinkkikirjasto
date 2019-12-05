-- Tables for Book with many-to-many genres and courses

CREATE TABLE book(id VARCHAR(32) NOT NULL PRIMARY KEY, author VARCHAR(256) NOT NULL, title VARCHAR(256) NOT NULL, isbn VARCHAR(20));

CREATE TABLE genre(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(256));

CREATE TABLE course(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(256));

CREATE TABLE bookGenre(book_id VARCHAR(32) NOT NULL, genre_id INTEGER NOT NULL, FOREIGN KEY (book_id) REFERENCES book(id), FOREIGN KEY (genre_id) REFERENCES genre(id));

CREATE TABLE bookCourse(book_id VARCHAR(32) NOT NULL, course_id INTEGER NOT NULL, FOREIGN KEY (book_id) REFERENCES book(id), FOREIGN KEY (course_id) REFERENCES genre(id));
