package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.ExamGrade;
import com.devtyagi.juitwebkiosk.util.CookieUtility;
import com.devtyagi.juitwebkiosk.util.StringUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExamGradeProcessor {

    public static List<ExamGrade> getExamGrades(String enrollmentNumber, String password, String url) throws IOException, InvalidCredentialsException {
        List<ExamGrade> examGradeList = new ArrayList<>();

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
                    .get(2)
                    .getElementsByTag("tbody")
                    .get(0)
                    .children();

            for (Element element : elements) {
                Elements subElements = element.children();

                ExamGrade examGrade = new ExamGrade();
                examGrade.setSerialNumber(StringUtility.convertStringToIntegerForDetailAttendance(subElements.get(0).text()));
                examGrade.setSubjectName(StringUtility.getSubjectName(subElements.get(1).text()));
                examGrade.setSubjectCode(StringUtility.getSubjectCode(subElements.get(1).text()));
                examGrade.setExamCode(StringUtility.cleanString(subElements.get(2).text()));
                examGrade.setGrade(StringUtility.cleanString(subElements.get(3).text()));

                examGradeList.add(examGrade);
            }
        }

        return examGradeList;
    }

}
