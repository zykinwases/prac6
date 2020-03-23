CREATE TABLE IF NOT EXISTS company (
	company_id SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL,
	address TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS professor (
	professor_id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name TEXT NOT NULL,
	first_name TEXT,
	company_id INTEGER REFERENCES company (company_id)
);

CREATE TABLE IF NOT EXISTS course (
	course_id SERIAL PRIMARY KEY,
	course_name TEXT,
	duration INTERVAL NOT NULL, --длительность курса
	intense INTERVAL NOT NULL, --кол-во часов за занятие
	professor_id INTEGER REFERENCES professor (professor_id), 
	relevance BOOLEAN DEFAULT TRUE --if course is finished it is not deleted to save (and show) history
);

CREATE TABLE IF NOT EXISTS lesson (
	lesson_id SERIAL PRIMARY KEY,
	course_id INTEGER REFERENCES course (course_id),
	"time" TIMESTAMP NOT NULL	
);

CREATE TABLE IF NOT EXISTS student (
	student_id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name TEXT NOT NULL,
	first_name TEXT,
	mobile TEXT,
	relevance BOOLEAN DEFAULT TRUE --if student leaves center he is not deleted to save (and show) history
);

CREATE TABLE IF NOT EXISTS std_less (
	id SERIAL PRIMARY KEY,
	student_id INTEGER REFERENCES student (student_id),
	course_id INTEGER REFERENCES course (course_id)
);

CREATE TABLE IF NOT EXISTS admin_acc (
	admin_id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name TEXT NOT NULL,
	first_name TEXT
);