CREATE TABLE IF NOT EXISTS admin_acc (
	admin_id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name TEXT NOT NULL,
	first_name TEXT
);

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
	company_id INTEGER REFERENCES company (company_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS course (
	course_id SERIAL PRIMARY KEY,
	name TEXT,
	duration TEXT NOT NULL, --длительность курса
	intense TEXT NOT NULL, --кол-во часов за занятие
	professor_id INTEGER REFERENCES professor (professor_id) ON DELETE CASCADE, 
	relevance BOOLEAN DEFAULT TRUE --if course is finished it is not deleted to save (and show) history
);

CREATE TABLE IF NOT EXISTS lesson (
	lesson_id SERIAL PRIMARY KEY,
	course_id INTEGER REFERENCES course (course_id) ON DELETE CASCADE,
	"time" TIMESTAMP NOT NULL	
);

CREATE TABLE IF NOT EXISTS student (
	student_id BIGSERIAL PRIMARY KEY,
	login VARCHAR(255) NOT NULL UNIQUE,
	pswd_hash CHAR(32) NOT NULL UNIQUE, --modified md5
	last_name VARCHAR(255) NOT NULL,
	first_name VARCHAR(255),
	mobile VARCHAR(16),
	relevance BOOLEAN DEFAULT TRUE --if student leaves center he is not deleted to save (and show) history
);

CREATE TABLE IF NOT EXISTS stdLess (
	student_id INTEGER REFERENCES student (student_id) ON DELETE CASCADE,
	course_id INTEGER REFERENCES course (course_id) ON DELETE CASCADE,
	PRIMARY KEY (student_id, course_id)
);

CREATE TABLE IF NOT EXISTS stdLessHyst (
	student_id INTEGER REFERENCES student (student_id) ON DELETE CASCADE,
	course_id INTEGER REFERENCES course (course_id) ON DELETE CASCADE,
	PRIMARY KEY (student_id, course_id)
);

CREATE RULE colHys AS ON DELETE 
TO stdLess
DO (INSERT INTO stdLessHyst SELECT OLD.* ON CONFLICT DO NOTHING);