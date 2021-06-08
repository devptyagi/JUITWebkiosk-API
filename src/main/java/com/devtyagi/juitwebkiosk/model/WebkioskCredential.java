package com.devtyagi.juitwebkiosk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WebkioskCredential {

    @NotNull(message = "Roll Number is required!")
    @NotEmpty(message = "Roll Number must not be empty!")
    @JsonProperty("enrollmentNumber")
    @ApiModelProperty(notes = "Roll Number of the student.")
    private String enrollmentNumber;

    @NotNull(message = "Password is required!")
    @NotEmpty(message = "Password must not be empty!")
    @JsonProperty("password")
    @ApiModelProperty(notes = "Password for WebKiosk account.")
    private String password;

    public WebkioskCredential(String enrollmentNumber, String password) {
        this.enrollmentNumber = enrollmentNumber;
        this.password = password;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
