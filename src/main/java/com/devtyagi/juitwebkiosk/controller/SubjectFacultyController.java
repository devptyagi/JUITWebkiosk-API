package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.SubjectFaculty;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.SubjectFacultyProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subjectFaculty")
public class SubjectFacultyController {

    private static String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Academic/StudSubjectFaculty.jsp";

    @PostMapping("")
    public ResponseEntity<?> getSubjectFaculty(@Valid @RequestBody WebkioskCredential credential, @RequestParam(required = false) String semester) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        String subjectFacultyUrl = URL;

        if(semester != null) {
            subjectFacultyUrl += Constants.URL_QUERY_PARAM + semester;
        }

        try {
            List<SubjectFaculty> res = SubjectFacultyProcessor.getSubjectFaculty(enrollmentNumber, password, subjectFacultyUrl);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            Map<String, String> res = new HashMap<>();
            res.put("error", "Invalid Credentials / Session Timed Out");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            Map<String, String> res = new HashMap<>();
            res.put("error", "Unknown Error");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
