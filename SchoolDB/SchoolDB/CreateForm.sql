CREATE TABLE Major --创建专业表
(	Mno CHAR(4) PRIMARY KEY,
	Mname CHAR(20) NOT NULL
);

CREATE TABLE Student	--创建学生表
(	Sno CHAR(11) PRIMARY KEY,
	Sname CHAR(6) NOT NULL,
	Ssex CHAR(2) CHECK (Ssex IN ('男','女')),
	Sbirth CHAR(8),
	Smajor CHAR(4) REFERENCES Major(Mno),
	Skey CHAR(11) NOT NULL
);

CREATE TABLE Teacher	--创建教师表
(	Tno CHAR(11) PRIMARY KEY,
	Tname CHAR(6) NOT NULL,
	Tsex CHAR(2) CHECK (Tsex IN ('男','女')),
	Tbirth CHAR(8),
	Tkey CHAR(11) NOT NULL
);

CREATE TABLE Lesson --创课程表
(	Mno CHAR(4) REFERENCES Major(Mno),
	Lno CHAR(5) NOT NULL,
	Lname CHAR(20) NOT NULL,
	RorO CHAR(4) CHECK( RorO IN ('必修','选修')),
	TeachingSemester SMALLINT CHECK(TeachingSemester>=1 AND TeachingSemester<=8) NOT NULL,
	Lcredit SMALLINT NOT NULL,
	PRIMARY KEY(Mno, Lno)
);

CREATE TABLE GivingLesson --创建授课表
(	Tno CHAR(11) REFERENCES Teacher(Tno),
	Cno CHAR(7),
	Lno CHAR(5),
	PRIMARY KEY(Tno, Cno)
);

CREATE TABLE Grades --创建成绩表
(	Sno CHAR(11) REFERENCES Student(Sno),
	Lno CHAR(5),
	Grade SMALLINT
	PRIMARY KEY(Sno, Lno)
);