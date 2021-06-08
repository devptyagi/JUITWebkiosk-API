package com.devtyagi.juitwebkiosk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Attendance {

    @JsonProperty("subjectName")
    private String subjectName;

    @JsonProperty("subjectCode")
    private String subjectCode;

    @JsonProperty("overallAttendance")
    private Integer overallAttendance;

    @JsonProperty("lectureAttendance")
    private Integer lectureAttendance;

    @JsonProperty("tutorialAttendance")
    private Integer tutorialAttendance;

    @JsonProperty("practicalAttendance")
    private Integer practicalAttendance;

    @JsonProperty("detailAttendanceUrl")
    private String detailAttendanceUrl;

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

    public Integer getOverallAttendance() {
        return overallAttendance;
    }

    public void setOverallAttendance(Integer overallAttendance) {
        this.overallAttendance = overallAttendance;
    }

    public Integer getLectureAttendance() {
        return lectureAttendance;
    }

    public void setLectureAttendance(Integer lectureAttendance) {
        this.lectureAttendance = lectureAttendance;
    }

    public Integer getTutorialAttendance() {
        return tutorialAttendance;
    }

    public void setTutorialAttendance(Integer tutorialAttendance) {
        this.tutorialAttendance = tutorialAttendance;
    }

    public String getDetailAttendanceUrl() {
        return detailAttendanceUrl;
    }

    public void setDetailAttendanceUrl(String detailAttendanceUrl) {
        this.detailAttendanceUrl = detailAttendanceUrl;
    }

    public Integer getPracticalAttendance() {
        return practicalAttendance;
    }

    public void setPracticalAttendance(Integer practicalAttendance) {
        this.practicalAttendance = practicalAttendance;
    }

}
