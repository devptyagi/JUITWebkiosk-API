package com.devtyagi.juitwebkiosk.model;

public class ExamGrade {

    private int serialNumber;
    private String subjectName;
    private String subjectCode;
    private String examCode;
    private String grade;

    public ExamGrade() {
    }

    public ExamGrade(int serialNumber, String subjectName, String subjectCode, String examCode, String grade) {
        this.serialNumber = serialNumber;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.examCode = examCode;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

}
