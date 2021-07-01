package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.ApiMetaData;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.CGPAReport;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.CGPAProcessor;
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
@RequestMapping("/api/cgpa")
@Api(value = "/api/cgpa", tags = "CGPA Report")
public class CGPAController {

    @PostMapping("")
    @ApiOperation(value = ApiMetaData.cgpaControllerValue, notes = ApiMetaData.cgpaControllerNotes)
    public ResponseEntity<?> getCGPAReport (@Valid @RequestBody WebkioskCredential credential) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        try {
            List<CGPAReport> res = CGPAProcessor.getCGPAReport(enrollmentNumber, password);
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
