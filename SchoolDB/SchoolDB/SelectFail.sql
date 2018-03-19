SELECT DISTINCT Student.Sno, Sname, RFailSUM.SumCredit, OFailSUM.SumCredit
FROM RFailSUM, OFailSUM, Student
WHERE (Student.Sno = RFailSUM.Sno AND
				RFailSUM.SumCredit >=12 )
				OR 
				(Student.Sno = OFailSUM.Sno AND
				OFailSUM.SumCredit >=17);