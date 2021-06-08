package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.CGPAReport;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.processor.CGPAProcessor;
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
@RequestMapping("/cgpa")
public class CGPAController {

    @PostMapping("")
    public ResponseEntity<?> getCGPAReport (@Valid @RequestBody WebkioskCredential credential) {
        String enrollmentNumber = credential.getEnrollmentNumber();
        String password = credential.getPassword();

        try {
            List<CGPAReport> res = CGPAProcessor.getCGPAReport(enrollmentNumber, password);
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
