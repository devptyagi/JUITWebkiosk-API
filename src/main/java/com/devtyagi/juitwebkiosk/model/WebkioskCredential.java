package com.devtyagi.juitwebkiosk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WebkioskCredential {

    @NotNull(message = "Roll Number is required!")
    @NotEmpty(message = "Roll Number must not be empty!")
    @JsonProperty("enrollmentNumber")
    private String enrollmentNumber;

    @NotNull(message = "Password is required!")
    @NotEmpty(message = "Password must not be empty!")
    @JsonProperty("password")
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
