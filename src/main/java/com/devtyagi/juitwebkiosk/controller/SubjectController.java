package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.ApiMetaData;
import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Subject;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.SubjectProcessor;
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
@RequestMapping("/api/subjects")
@Api(value = "/api/subjects", tags = "Subject List")
public class SubjectController {

    private static String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Academic/StudSubjectTaken.jsp";

    @PostMapping("")
    @ApiOperation(value = ApiMetaData.subjectControllerValue, notes = ApiMetaData.subjectControllerNotes)
    public ResponseEntity<?> getSubjects(@Valid @RequestBody WebkioskCredential credential, @ApiParam(value = ApiMetaData.semesterParam) @RequestParam(required = false) String semester) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        String subjectsUrl = URL;

        if(semester != null) {
            subjectsUrl += Constants.URL_QUERY_PARAM + semester;
        }

        try {
            List<Subject> res = SubjectProcessor.getSubjects(enrollmentNumber, password, subjectsUrl);
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
