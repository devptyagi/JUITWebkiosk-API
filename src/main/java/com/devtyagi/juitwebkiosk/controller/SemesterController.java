package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.ApiMetaData;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Semester;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.SemesterProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/semesters")
@Api(value = "/api/semester", tags = "Semester Codes")
public class SemesterController {

    @PostMapping("")
    @ApiOperation(value = ApiMetaData.semesterControllerValue, notes = ApiMetaData.semesterControllerNotes)
    public ResponseEntity<?> getSemesters(@Valid @RequestBody WebkioskCredential credential) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        try {
            List<Semester> res = SemesterProcessor.getSemesters(enrollmentNumber, password);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("success", false);
            res.put("error", "Invalid Credentials / Session Timed Out");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("success", false);
            res.put("error", "Unknown Error");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
