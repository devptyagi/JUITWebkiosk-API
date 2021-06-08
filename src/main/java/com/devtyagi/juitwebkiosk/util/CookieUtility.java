package com.devtyagi.juitwebkiosk.util;

import com.devtyagi.juitwebkiosk.constants.Constants;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class CookieUtility {


    /*
     * Connect to webkiosk.juit.ac.in:9443
     * Login with the provided credentials and return the cookies received
     * from the host.
     * These cookies can be used to further crawl other Webkiosk Urls
     * */
    public static Map<String, String> getCookiesFor(String enrollmentNumber, String password) throws IOException {
        Connection.Response loginForm = Jsoup.connect("https://webkiosk.juit.ac.in:9443/")
                .method(Connection.Method.GET)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute();

        Connection.Response mainPage = Jsoup.connect("https://webkiosk.juit.ac.in:9443/CommonFiles/UserAction.jsp")
                .data("txtInst", "Institute")
                .data("InstCode", "JUIT")
                .data("txtuType", "Member Type")
                .data("UserType", "S")
                .data("txtCode", "Enrollment No")
                .data("MemberCode", enrollmentNumber)
                .data("txtPin", "Password/Pin")
                .data("Password", password)
                .data("BTNSubmit", "Submit")
                .cookies(loginForm.cookies())
                .execute();

        return mainPage.cookies();
    }

}
