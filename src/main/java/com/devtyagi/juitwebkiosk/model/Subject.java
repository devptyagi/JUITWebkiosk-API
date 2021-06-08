package com.devtyagi.juitwebkiosk.model;

public class Subject {

    private String subjectName;
    private String subjectCode;
    private int subjectCredits;
    private String subjectType;

    public Subject() {
    }

    public Subject(String subjectName, String subjectCode, int subjectCredits, String subjectType) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectCredits = subjectCredits;
        this.subjectType = subjectType;
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

    public int getSubjectCredits() {
        return subjectCredits;
    }

    public void setSubjectCredits(int subjectCredits) {
        this.subjectCredits = subjectCredits;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
