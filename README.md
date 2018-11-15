# SchoolDB

SchoolDB is a university student management database system that conforms to the given semantics and performs specific functions. You can create tables with `CreateForm.sql` and start using the database with `SchoolDB/SchoolDataBase/src/GUI/Login.java`. 

## Environment

- SQLServer
- JAVA

## Functions

Functional requirements and implementation ideas:

1. Enter a student's information including student number, name, gender, date of birth, class, etc.(Insert Student Table)
2. Query basic information of students according to the student number, name and major. (Search Student Table)
3. Enter the score of a student in a course. (Insert Grades Table)
4. Query the course, nature (compulsory or elective), semester, credits and grades of a student. Check his average grades of compulsory course and of all courses (average grades should be weighted by credits). (Create a View of Grades Table and GivingLesson Table then query it)
5. Query which teachers have taught a certain student. (Search GivingLesson Table)
6. Enquiries for students who are about to be expelled (within 3 credits from being expelled). (Search the View of failing students in compulsory courses or failing students in elective courses)

## E-R Map

![image_1csah5t99alk1gith133k38894d.png-64.6kB][1]

**Note**: The student number can reflect the information of its class number.

## Semantics

Determining the data in the database by the semantics of the database. 

1. There are a number of majors in the school. Each major recruits several classes each year, and each class has several students; (construction professional table, student table)
2. Each major has its own teaching plan, which stipulates the nature of the relevant courses of the major (compulsory or elective) and the semester of the lecture; (constructing the teaching schedule, curriculum)
3. A teacher can bring classes to multiple classes, but can not bring multiple classes to a class; (constructing teacher tables, and a many-to-many relationship between teachers, students, and courses)
4. One course allows students to make up for one test at a time; students are expelled from one of the following conditions: 15 credits for failing compulsory courses or 20 credits for failing elective courses; (constructing transcripts, constructing compulsory courses Passing the view of students who pass the elective course and failing students)

[1]: http://static.zybuluo.com/JosieException/8h6iykyoc1qak5jrhn0p86vp/image_1csah5t99alk1gith133k38894d.png