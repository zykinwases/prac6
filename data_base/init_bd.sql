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
	professor_id INTEGER,
	relevance BOOLEAN DEFAULT TRUE --if course is finished it is not deleted to save (and show) history
);

CREATE TABLE IF NOT EXISTS lesson (
	lesson_id SERIAL PRIMARY KEY,
	course_id INTEGER NOT NULL,
	"time" TIMESTAMP NOT NULL	
);

CREATE TABLE IF NOT EXISTS student (
	student_id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name TEXT NOT NULL,
	first_name TEXT,
	mobile BIGINT CHECK ((mobile > 70000000000) AND (mobile <= 89999999999)),
	relevance BOOLEAN DEFAULT TRUE --if student leaves courses he is not deleted to save (and show) history
);

CREATE TABLE IF NOT EXISTS std_less (
	student_id INTEGER REFERENCES student (student_id),
	course_id INTEGER REFERENCES course (course_id),
	PRIMARY KEY(student_id, course_id)
);

CREATE TABLE IF NOT EXISTS admin_acc (
	login TEXT NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name TEXT NOT NULL,
	first_name TEXT
);