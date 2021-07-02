package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.CGPAReport;
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

public class CGPAProcessor {

    private static final String URL = "https://webkiosk.juit.ac.in:9443/StudentFiles/Exam/StudCGPAReport.jsp";

    public static List<CGPAReport> getCGPAReport(String enrollmentNumber, String password) throws IOException, InvalidCredentialsException {
        List<CGPAReport> cgpaReport = new ArrayList<>();
        Map<String, String> cookies = CookieUtility.getCookiesFor(enrollmentNumber, password);

        Document document = Jsoup.connect(URL)
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

            for (Element element : elements) {
                Elements subElements = element.children();

                CGPAReport report = new CGPAReport();
                report.setSemesterIndex(Integer.parseInt(StringUtility.cleanString(subElements.get(0).text())));
                report.setGradePoints(Double.parseDouble(StringUtility.cleanString(subElements.get(1).text())));
                report.setCourseCredit(Double.parseDouble(StringUtility.cleanString(subElements.get(2).text())));
                report.setEarnedCredit(Double.parseDouble(StringUtility.cleanString(subElements.get(3).text())));
                report.setPointsSecuredSgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(4).text())));
                report.setPointsSecuredCgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(5).text())));
                report.setSgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(6).text())));
                report.setCgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(7).text())));

                cgpaReport.add(report);
            }

            return cgpaReport;
        }
    }

}
