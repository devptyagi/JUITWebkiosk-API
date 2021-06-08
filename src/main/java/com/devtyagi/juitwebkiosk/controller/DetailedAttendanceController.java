package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.DetailedAttendance;
import com.devtyagi.juitwebkiosk.model.DetailedAttendancePayload;
import com.devtyagi.juitwebkiosk.processor.DetailedAttendanceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/detailedAttendance")
public class DetailedAttendanceController {

    @PostMapping("")
    public ResponseEntity<?> getDetailedAttendance(@Valid @RequestBody DetailedAttendancePayload payload) {
        String enrollmentNumber = payload.getEnrollmentNumber();
        String password = payload.getPassword();
        String attendanceUrl = payload.getAttendanceUrl();

        try {
            List<DetailedAttendance> res = DetailedAttendanceProcessor.getDetailedAttendance(enrollmentNumber, password, attendanceUrl);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (IOException e) {
            Map<String, String> res = new HashMap<>();
            res.put("error", "Unknown Error!");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidCredentialsException e) {
            Map<String, String> res = new HashMap<>();
            res.put("error", "Invalid Credentials / Session Timed Out");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

}
