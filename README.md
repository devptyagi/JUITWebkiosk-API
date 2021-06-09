## JUIT WebKiosk API - Spring Boot (Unofficial)

<img alt="Spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/> <img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/>

A __working api__ for accessing JUIT WebKiosk Data. Created using Java Spring Boot.  
API Url: https://webkiosk-juit.herokuapp.com/

### Required request body
```
{
    "enrollmentNumber": "XXXXXX",
    "password": "XXXXXXXX"
}
```

### Endpoints

* ```/api/login```    
  Login into the JUIT WebKiosk portal.
  

* ```/api/attendance```    
  Get attendance details for a given semester.
  

* ```/api/detailedAttendance```   
  Get detailed attendance records.
  

* ```/api/cgpa```   
  Get the CGPA report for all semesters.
  

* ```/api/examGrade```    
  Get the Exam Grades for a given semesters.
  

* ```/api/semesters```    
  Get the list of valid Semester Codes.
  

* ```/api/subjectFaculty```   
  Get the list of registered subject faculty.
  
  
* ```/api/subjects```   
  Get the list of registered subjects for a given semester
  

## Full Documentation

Read the full documentation [here.](https://webkiosk-juit.herokuapp.com/swagger-ui.html)  

