package com.devtyagi.juitwebkiosk.model;

public class SubjectFaculty {

    private String subjectName;
    private String lectureFaculty;
    private String tutorialFaculty;
    private String practicalFaculty;
    private String subjectCode;

    public SubjectFaculty() {
    }

    public SubjectFaculty(String subjectName, String lectureFaculty, String tutorialFaculty, String practicalFaculty, String subjectCode) {
        this.subjectName = subjectName;
        this.lectureFaculty = lectureFaculty;
        this.tutorialFaculty = tutorialFaculty;
        this.practicalFaculty = practicalFaculty;
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = !subjectName.equals("") ? subjectName : null;
    }

    public String getLectureFaculty() {
        return lectureFaculty;
    }

    public void setLectureFaculty(String lectureFaculty) {
        this.lectureFaculty = !lectureFaculty.equals("") ? lectureFaculty : null;
    }

    public String getTutorialFaculty() {
        return tutorialFaculty;
    }

    public void setTutorialFaculty(String tutorialFaculty) {
        this.tutorialFaculty = !tutorialFaculty.equals("") ? tutorialFaculty : null;
    }

    public String getPracticalFaculty() {
        return practicalFaculty;
    }

    public void setPracticalFaculty(String practicalFaculty) {
        this.practicalFaculty = !practicalFaculty.equals("") ? practicalFaculty : null;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = !subjectCode.equals("") ? subjectCode : null;
    }
}
