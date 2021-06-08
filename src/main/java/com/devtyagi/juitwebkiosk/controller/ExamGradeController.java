package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.ApiMetaData;
import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.ExamGrade;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.ExamGradeProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/examGrade")
@Api(value = "/api/examGrade", tags = "Exam Grades")
public class ExamGradeController {

    private static String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Exam/StudentEventGradesView.jsp";

    @PostMapping("")
    @ApiOperation(value = ApiMetaData.examGradeControllerValue, notes = ApiMetaData.examGradeControllerNotes)
    public ResponseEntity<?> getExamGrades(@Valid @RequestBody WebkioskCredential credential, @ApiParam(value = ApiMetaData.semesterParam) @RequestParam String semester) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

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
