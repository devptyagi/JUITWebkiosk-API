package com.devtyagi.juitwebkiosk.controller;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.model.WebkioskCredential;
import com.devtyagi.juitwebkiosk.util.CookieUtility;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/StudentPage.jsp";

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody WebkioskCredential webkioskCredential) throws IOException {
        String enrollmentNumber = webkioskCredential.getEnrollmentNumber();
        String password = webkioskCredential.getPassword();
        Map<String, String> cookies = CookieUtility.getCookiesFor(enrollmentNumber, password);

        Document document = Jsoup.connect(URL)
                .cookies(cookies)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute().parse();

        if (document.toString().contains("FrameLeftStudent.jsp")) {
            Map<String, String> body = new HashMap<>();
            body.put("message", "Success");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            Map<String, String> body = new HashMap<>();
            body.put("message", "Invalid");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

}
