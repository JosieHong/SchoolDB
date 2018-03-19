SELECT Sno,Sname,Ssex,Sbirth,Smajor --按学号查基本信息
FROM Student
WHERE Sno = '15030199001';

SELECT Sno,Sname,Ssex,Sbirth,Smajor --按姓名查基本信息
FROM Student
WHERE Sname = '张三';

SELECT Sno,Sname,Ssex,Sbirth,Mname --按专业查基本信息
FROM Student_Major
WHERE Mname = '计算机科学与技术';