SELECT Grades.Sno, Student.Sname, Grades.Lno, Lesson.RorO,  Lesson.TeachingSemester, Grades.Grade
FROM Grades, Lesson, Student
WHERE Student.Sno = Grades.Sno AND
			  Grades.Lno = Lesson.Lno AND
			  Lesson.Mno = SUBSTRING ( Grades.Sno, 3 , 4) AND
			  Grades.Sno = '15030199001';