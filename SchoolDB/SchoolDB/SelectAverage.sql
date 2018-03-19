SELECT Grade_Credit.Sno, Student.Sname, SUM(Grade*Lcredit)/SUM(Lcredit)
FROM Grade_Credit, Student
WHERE Grade_Credit.Sno = '15030199001' AND
			Grade_Credit.Sno = Student.Sno
GROUP BY Grade_Credit.Sno, Student.Sname;