package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.ExamGrade;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.ExamGradeProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/examGrade")
public class ExamGradeController {

    private static String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Exam/StudentEventGradesView.jsp";

    @PostMapping("")
    public ResponseEntity<?> getExamGrades(@Valid @RequestBody WebkioskCredential credential, @RequestParam(required = false) String semester) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        if(semester == null) {
            Map<String, Object> res = new HashMap<>();
            res.put("status", 400);
            res.put("error", "Semester query param missing");
            res.put("path", "/examGrade");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        try {
            List<ExamGrade> res = ExamGradeProcessor.getExamGrades(enrollmentNumber, password, URL + Constants.URL_QUERY_PARAM + semester);
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
