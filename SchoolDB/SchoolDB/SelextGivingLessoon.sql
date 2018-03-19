SELECT Sno, Sname, Tname, Lno
FROM Student, Teacher, GivingLesson
WHERE Student.Sno = '15030199001' AND
			  GivingLesson.Cno = LEFT(Student.Sno, 7) AND
			  GivingLesson.Tno = Teacher.Tno;