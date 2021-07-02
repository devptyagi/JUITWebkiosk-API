package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Subject;
import com.devtyagi.juitwebkiosk.util.CookieUtility;
import com.devtyagi.juitwebkiosk.util.StringUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectProcessor {

    public static List<Subject> getSubjects(String enrollmentNumber, String password, String url) throws IOException, InvalidCredentialsException {
        List<Subject> subjects = new ArrayList<>();

        Map<String, String> cookies = CookieUtility.getCookiesFor(enrollmentNumber, password);

        Document document = Jsoup.connect(url)
                .cookies(cookies)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute().parse();

        if (document.body().toString().toLowerCase().contains("session timeout")) {
            throw new InvalidCredentialsException();
        } else {
            Elements elements = document.body()
                    .getElementsByTag("table")
                    .get(2).getElementsByTag("tbody")
                    .get(0).children();

            for (int x = 1; x < elements.size() - 1; x++) {
                Elements subElements = elements.get(x).children();

                Subject subject = new Subject();
                subject.setSubjectName(StringUtility.getSubjectName(subElements.get(1).text()));
                subject.setSubjectCode(StringUtility.getSubjectCode(subElements.get(1).text()));
                subject.setSubjectCredits(Integer.parseInt(StringUtility.cleanString(subElements.get(2).text())));
                subject.setSubjectType(StringUtility.cleanString(subElements.get(3).text()));

                subjects.add(subject);
            }
        }

        return subjects;
    }

}
