package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Attendance;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.AttendanceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/attendance")
public class AttendanceController {

    private static final String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Academic/StudentAttendanceList.jsp";

    @PostMapping("")
    public ResponseEntity<?> getAttendance(@Valid @RequestBody WebkioskCredential credential) {
        try {
            List<Attendance> res = AttendanceProcessor.getAttendance(credential.getEnrollmentNumber(), credential.getPassword(), URL);
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

    @PostMapping("/{semester}")
    public ResponseEntity<?> getAttendance(@Valid @RequestBody WebkioskCredential credential, @PathVariable String semester) {
        try {
            List<Attendance> res = AttendanceProcessor.getAttendance(credential.getEnrollmentNumber(), credential.getPassword(), URL + Constants.URL_QUERY_PARAM + semester);
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
