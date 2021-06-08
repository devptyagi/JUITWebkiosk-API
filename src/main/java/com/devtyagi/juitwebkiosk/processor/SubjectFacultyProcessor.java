package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.SubjectFaculty;
import com.devtyagi.juitwebkiosk.util.CookieUtility;
import com.devtyagi.juitwebkiosk.util.StringUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectFacultyProcessor {

    public static List<SubjectFaculty> getSubjectFaculty(String enrollmentNumber, String password, String url) throws IOException, InvalidCredentialsException {

        List<SubjectFaculty> subjectFacultyList = new ArrayList<>();

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

            for (int x = 1; x < elements.size(); x++) {
                //Create new SubjectFaculty object
                SubjectFaculty subjectFaculty = new SubjectFaculty();

                //Get the sub elements of each row
                Elements subElements = elements.get(x).getElementsByTag("td");
                subjectFaculty.setSubjectName(StringUtility.getSubjectName(subElements.get(1).text()));
                subjectFaculty.setSubjectCode(StringUtility.getSubjectCode(subElements.get(1).text()));
                subjectFaculty.setLectureFaculty(StringUtility.cleanString(subElements.get(2).text()));
                subjectFaculty.setTutorialFaculty(StringUtility.cleanString(subElements.get(3).text()));
                subjectFaculty.setPracticalFaculty(StringUtility.cleanString(subElements.get(4).text()));

                //Add to subject faculty result
                subjectFacultyList.add(subjectFaculty);
            }
        }
        return subjectFacultyList;
    }

}
