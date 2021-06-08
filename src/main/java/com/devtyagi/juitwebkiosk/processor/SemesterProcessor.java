package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Semester;
import com.devtyagi.juitwebkiosk.util.CookieUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SemesterProcessor {

    private static final String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Academic/StudSubjectFaculty.jsp";

    public static List<Semester> getSemesters(String enrollmentNumber, String password) throws IOException, InvalidCredentialsException {

        List<Semester> semesters =  new ArrayList<>();

        Map<String, String> cookies = CookieUtility.getCookiesFor(enrollmentNumber, password);

        Document document = Jsoup.connect(URL)
                .cookies(cookies)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute().parse();

        if (document.body().toString().toLowerCase().contains("session timeout")) {
            throw new InvalidCredentialsException();
        } else {
            Elements elements = document.body().getElementsByAttributeValue("name", "frm")
                    .get(0).getElementsByTag("table")
                    .get(0).getElementsByTag("tbody")
                    .get(0).getElementsByTag("tr")
                    .get(1).getElementsByTag("td")
                    .get(0).getElementsByTag("select")
                    .get(0).children();

            for (Element element : elements) {
                semesters.add(new Semester(element.attr("value")));
            }
        }

        return semesters;
    }

}
