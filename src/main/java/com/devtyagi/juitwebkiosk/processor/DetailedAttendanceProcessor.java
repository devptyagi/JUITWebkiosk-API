package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.DetailedAttendance;
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

public class DetailedAttendanceProcessor {

    public static List<DetailedAttendance> getDetailedAttendance(String enrollmentNumber, String password, String url) throws IOException, InvalidCredentialsException {
        Map<String, String> cookies = CookieUtility.getCookiesFor(enrollmentNumber, password);

        Document document = Jsoup.connect(url)
                .cookies(cookies)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute().parse();

        List<DetailedAttendance> detailAttendanceResult = new ArrayList<>();

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

                DetailedAttendance detailAttendance = new DetailedAttendance();
                detailAttendance.setSerialNumber(StringUtility.convertStringToIntegerForDetailAttendance(subElements.get(0).text()));
                detailAttendance.setDate(StringUtility.cleanString(subElements.get(1).text()));
                detailAttendance.setFacultyName(StringUtility.cleanString(subElements.get(2).text()));
                detailAttendance.setStatus(StringUtility.cleanString(subElements.get(3).text()));
                detailAttendance.setClassType(StringUtility.cleanString(subElements.get(4).text()));

                if (subElements.size() >= 6) {
                    detailAttendance.setLtp(StringUtility.cleanString(subElements.get(5).text()));
                }

                detailAttendanceResult.add(detailAttendance);
            }
        }

        return detailAttendanceResult;
    }

}
