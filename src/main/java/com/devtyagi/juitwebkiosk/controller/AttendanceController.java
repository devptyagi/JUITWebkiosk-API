package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.ApiMetaData;
import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Attendance;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.AttendanceProcessor;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/attendance")
@Api(value = "/api/attendance", tags = "Attendance")
public class AttendanceController {

    private static final String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Academic/StudentAttendanceList.jsp";

    @PostMapping("")
    @ApiOperation(value = ApiMetaData.attendanceControllerValue, notes = ApiMetaData.attendanceControllerNotes)
    public ResponseEntity<?> getAttendance(@Valid @RequestBody WebkioskCredential credential, @ApiParam(value = ApiMetaData.semesterParam) @RequestParam(required = false) String semester) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        String attendanceUrl = URL;

        if(semester != null) {
            attendanceUrl += Constants.URL_QUERY_PARAM + semester;
        }

        try {
            List<Attendance> res = AttendanceProcessor.getAttendance(enrollmentNumber, password, attendanceUrl);
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
