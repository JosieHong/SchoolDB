SELECT Grade_rCredit.Sno, Student.Sname, SUM(Grade*Lcredit)/SUM(Lcredit)
FROM Grade_rCredit, Student
WHERE Grade_rCredit.Sno = '15030199001' AND
			Grade_rCredit.Sno = Student.Sno
GROUP BY Grade_rCredit.Sno, Student.Sname;