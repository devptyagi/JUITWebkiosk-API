package com.devtyagi.juitwebkiosk.constants;

public class ApiMetaData {

    public static final String semesterParam = "Semester code.\nFor example: 2021EVESEM / 2020ODDSEM etc.";

    public static final String attendanceControllerValue = "Get attendance details for a given semester.";
    public static final String attendanceControllerNotes = "Returns the attendance details for a given semester.\nIf semester code is not provided in the " +
            "query params, this endpoint will fetch the details for current default semester.";

    public static final String cgpaControllerValue = "Get the CGPA report for all semesters.";
    public static final String cgpaControllerNotes = "Returns the CGPA report of any student for all the semesters.";

    public static final String detailedAttendanceControllerValue = "Get detailed attendance records.";
    public static final String detailedAttendanceControllerNotes = "Returns the Detailed Attendance records of any student for a given subject.\n" +
            "Requires detailedAttendanceUrl, that can be retrieved from /attendance endpoint for each subject.";

    public static final String examGradeControllerValue = "Get the Exam Grades for a given semesters.";
    public static final String examGradeControllerNotes = "Returns the Exam Grades of any student for a specific semester.\n" +
            "Requires 'semester' as a query parameter. [IMPORTANT]";

    public static final String loginControllerValue = "Login into the JUIT WebKiosk portal.";
    public static final String loginControllerNotes = "Can be used to implement login functionality in the front end.";

    public static final String semesterControllerValue = "Get the list of valid Semester Codes.";
    public static final String semesterControllerNotes = "Returns a list of valid Semester Codes.";

    public static final String subjectFacultyControllerValue = "Get the list of registered subject faculty.";
    public static final String subjectFacultyControllerNotes = "Returns the information of registered subjects faculty.";

    public static final String subjectControllerValue = "Get the list of registered subjects for a given semester";
    public static final String subjectControllerNotes = "Returns the subject list for a given semester.\nIf semester code is not provided in the " +
            "query params, this endpoint will fetch the details for current default semester.";

}
